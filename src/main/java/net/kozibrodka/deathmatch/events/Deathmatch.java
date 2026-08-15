package net.kozibrodka.deathmatch.events;

import net.kozibrodka.deathmatch.utils.MapTDM;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deathmatch {

    @EventListener
    public void tickGame(GameTickEvent.End event){
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
        tick = UtilsTDM.mcServ.ticks;
        /// Warm-up, oczekiwanie na komende start
        if(tick % 200 == 0){
            addGlobalMessage("Choose teams by right-clicling on Blue/Red team block");
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
            gamePhase = Phase.MATCH;
        }
    }

    public void tickMatch(){

    }

    public static boolean balanceTeamsSizes(){
        int redCount = TEAM_RED.size();
        int blueCount = TEAM_BLUE.size();

        int roznica = Math.abs(redCount - blueCount);

        if(roznica < 2){
            return true;
        }else{
            int sila = Math.floorDiv(roznica,2);

            if(redCount > blueCount){ /// Za dużo czerwonych
                for (int i = 0; i < sila; i++) {
                    PlayerEntity redPlayer = TEAM_RED.iterator().next();
                    TEAM_RED.remove(redPlayer);
                    addPlayerToTeam(false, redPlayer);
                }
            }
            if(blueCount > redCount){ /// Za dużo niebieskich
                for (int i = 0; i < sila; i++) {
                    PlayerEntity bluePlayer = TEAM_BLUE.iterator().next();
                    TEAM_BLUE.remove(bluePlayer);
                    addPlayerToTeam(false, bluePlayer);
                }
            }

        }


        return true;
    }

    public static void swapTeams(){

    }


    public static MapTDM map;
    public static final Set<PlayerEntity> TEAM_RED = new HashSet<>();
    public static final Set<PlayerEntity> TEAM_BLUE = new HashSet<>();
    public static Phase gamePhase = Phase.NULL;
    static int tick;
    public static int warmupReady = 0;
    int RED_TICKETS;
    int BLUE_TICKETS;

    public static void addPlayerToTeam(boolean isRed, PlayerEntity player){
        if(gamePhase == Phase.WARMUP){
//            (isRed ? TEAM_RED : TEAM_BLUE).add(player);
            String team = "";
            if(isRed){
                TEAM_RED.add(player);
                team = "§cRed";
            }else{
                TEAM_BLUE.add(player);
                team = "§9Blue";
            }
            addDirectMessage(player, "You have joined team " + team);
        }
    }

    public static void forcePlayerToTeam(boolean isRed, PlayerEntity player){
//        if(gamePhase)
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
