package net.kozibrodka.deathmatch.events;

import net.kozibrodka.deathmatch.network.TeamSyncPacket;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

import java.rmi.ServerError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deathmatch {

    @EventListener
    public void tickGame(GameTickEvent.End event){
        tick = UtilsTDM.mcServ.ticks;
        switch (gamePhase){
            case NULL -> {
                return;
            }
            case WARMUP -> tickWarmup();
            case STARTING -> tickStarting();
            case MATCH ->  tickMatch();
        }
    }

    public void tickWarmup(){
        /// Warm-up, oczekiwanie na komende start
        if(tick % 200 == 0){
            addGlobalMessage("Choose team by right-clicling on Blue/Red team block");
        }
    }

    public void tickStarting(){
        /// Warm-up, odliczanie do startu
        warmupReady--;
        if(warmupReady % 100 == 0){
            String text = "Game starting in " + warmupReady/20 + " seconds";
            addGlobalMessage(text);
        }
        if(warmupReady == 1){
            allPlayerToWeapons();
            TICKETS_BLUE = TICKETS_RED = 5;
            addGlobalMessage("Match has started");
            gamePhase = Phase.MATCH;
            syncTeamsWithClients();
        }
    }

    public void tickMatch(){
        if(tick % 200 == 0){
            addGlobalMessage("Tickets §cRed: §f" + TICKETS_RED +", §9Blue: §f" + TICKETS_BLUE);
            addGlobalMessage("§9BLUES: " + String.join(", ", TEAM_BLUE));
            addGlobalMessage("§cReds: " + String.join(", ", TEAM_RED));
        }





        if(matchClosure > 0){
            matchClosure--;
            if(matchClosure == 1){
                allPlayerToLobby();
                gamePhase = Phase.AFTERMATH;
            }
        }else{
            if(TICKETS_RED <= 0){
                addGlobalMessage("Team §9Blue §fhas won!");
                matchClosure = 200;
            }
            if(TICKETS_BLUE <= 0){
                addGlobalMessage("Team §cRed §fhas won!");
                matchClosure = 200;
            }
        }
    }

    /// -------------------------------------------------------------------------------------------------------------------------------------------- ///

    public static boolean balanceTeamsSizes(World world){
        int redCount = TEAM_RED.size();
        int blueCount = TEAM_BLUE.size();

        int roznica = Math.abs(redCount - blueCount);

        if(roznica < 2){
            return true;
        }else{
            int sila = Math.floorDiv(roznica,2);

            if(redCount > blueCount){ /// Za dużo czerwonych
                for (int i = 0; i < sila; i++) {
                    String redName = TEAM_RED.iterator().next();
                    addPlayerToTeam(false, world.getPlayer(redName));
                }
            }
            if(blueCount > redCount){ /// Za dużo niebieskich
                for (int i = 0; i < sila; i++) {
                    String blueName = TEAM_BLUE.iterator().next();
                    addPlayerToTeam(true, world.getPlayer(blueName));
                }
            }
        }
        return true;
    }

    public static void teamUpMissingPlayers(){
        boolean color = false;
        for (Object gracz : UtilsTDM.mcServ.playerManager.players){
            PlayerEntity player = (PlayerEntity) gracz;
            if(!TEAM_BLUE.contains(player.name) && !TEAM_RED.contains(player.name)){
                addPlayerToTeam(color, player);
                color = !color;
            }
        }
    }

    public static void swapTeams(){
        Set<String> temp = new HashSet<>(TEAM_RED);
        TEAM_RED.clear();
        TEAM_RED.addAll(TEAM_BLUE);
        TEAM_BLUE.clear();
        TEAM_BLUE.addAll(temp);
        syncTeamsWithClients();
    }

    public static void syncTeamsWithClients(){
        for (Object playerObj : UtilsTDM.mcServ.playerManager.players){
            PlayerEntity player = (PlayerEntity) playerObj;
            PacketHelper.sendTo(player, new TeamSyncPacket(TEAM_RED, TEAM_BLUE));
        }
    }

    public static void onPlayerConnect(PlayerEntity player){
        System.out.println("CONNECT 1");

        if(map == null){
            syncTeamsWithIndividual(player);
            return;
        }
        if(gamePhase == Phase.WARMUP || gamePhase == Phase.AFTERMATH){ /// PRZED MECZEM
            syncTeamsWithIndividual(player);
            UtilsTDM.movePlayerLobby((ServerPlayerEntity) player, player.world);
            return;
        }

        if(gamePhase == Phase.MATCH || gamePhase == Phase.STARTING) { /// W TRAKCIE MECZU
            System.out.println("CONNECT 2");
            if (!TEAM_RED.contains(player.name) && !TEAM_BLUE.contains(player.name)) {
                System.out.println("CONNECT 3");
                if (TEAM_RED.size() == TEAM_BLUE.size()) { /// EQUAL
                    if(TICKETS_RED == TICKETS_BLUE){
                        boolean flag = player.world.random.nextInt(2) == 0;
                        forcePlayerToTeam(flag, player);
                    }else if(TICKETS_RED > TICKETS_BLUE){ /// WYGRYWAJĄ RED
                        forcePlayerToTeam(false, player);
                    }else{ /// WYGRYWAJĄ BLUE
                        forcePlayerToTeam(true, player);
                    }
                } else if (TEAM_RED.size() > TEAM_BLUE.size()) { /// MNIEJ OSÓB W BLUE
                    forcePlayerToTeam(false, player);
                } else { /// MNIEJ OSÓB W RED
                    System.out.println("CONNECT 4");
                    forcePlayerToTeam(true, player);
                }
                if(gamePhase == Phase.STARTING){
                    UtilsTDM.movePlayerLobby((ServerPlayerEntity) player, player.world);
                }else{
                    UtilsTDM.movePlayerToTeamWeapons((ServerPlayerEntity) player, player.world);
                }
            } else {
                syncTeamsWithIndividual(player);
            }
        }
    }

    public static void syncTeamsWithIndividual(PlayerEntity player){
        PacketHelper.sendTo(player, new TeamSyncPacket(TEAM_RED, TEAM_BLUE));
    }

    public static void allPlayerToWeapons(){
        for (Object playerObj : UtilsTDM.mcServ.playerManager.players){
            ServerPlayerEntity player = (ServerPlayerEntity) playerObj;
            UtilsTDM.movePlayerToTeamWeapons(player, player.world);
        }
    }

    public static void allPlayerToLobby(){
        for (Object playerObj : UtilsTDM.mcServ.playerManager.players){
            ServerPlayerEntity player = (ServerPlayerEntity) playerObj;
            UtilsTDM.movePlayerLobby(player, player.world);
        }
    }


    public static World world;
    public static MapTDM map;
    public static final Set<String> TEAM_RED = new HashSet<String>();
    public static final Set<String> TEAM_BLUE = new HashSet<String>();
    public static int TICKETS_RED;
    public static int TICKETS_BLUE;
    public static Phase gamePhase = Phase.NULL;
    static int tick;
    public static int warmupReady = 0;
    public static int matchClosure = 0;
    int RED_TICKETS;
    int BLUE_TICKETS;

    public static void onPlayerDeath(String name){
        if(TEAM_RED.contains(name)){
            TICKETS_RED--;
        }
        if(TEAM_BLUE.contains(name)){
            TICKETS_BLUE--;
        }
    }

    public static void addPlayerToTeam(boolean isRed, PlayerEntity player){
        if(gamePhase == Phase.WARMUP && map != null){
//            (isRed ? TEAM_RED : TEAM_BLUE).add(player);
            String team = "";
            if(isRed){
                TEAM_BLUE.remove(player.name);
                TEAM_RED.add(player.name);
                team = "§cRed";
            }else{
                TEAM_RED.remove(player.name);
                TEAM_BLUE.add(player.name);
                team = "§9Blue";
            }
            addDirectMessage(player, "You have joined team " + team);
            syncTeamsWithClients();
        }
    }

    public static void forcePlayerToTeam(boolean isRed, PlayerEntity player){ /// Joining MID-GAME, force team choose without TeamBlock.
        if(map != null){
            String team = "";
            if(isRed){
                TEAM_BLUE.remove(player.name);
                TEAM_RED.add(player.name);
                team = "§cRed";
            }else{
                TEAM_RED.remove(player.name);
                TEAM_BLUE.add(player.name);
                team = "§9Blue";
            }
            addDirectMessage(player, "You have joined team " + team);
            syncTeamsWithClients();
        }
    }

    public static void startGame(){

    }

    public static void addGlobalMessage(String text){
        for (Object playerObj : UtilsTDM.mcServ.playerManager.players){
            ServerPlayerEntity player = (ServerPlayerEntity) playerObj;
            UtilsTDM.mcServ.playerManager.messagePlayer(player.name, text);
        }
    }

    public static void addDirectMessage(PlayerEntity player, String text){
        UtilsTDM.mcServ.playerManager.messagePlayer(player.name, text);
    }


}
