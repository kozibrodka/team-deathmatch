package net.kozibrodka.deathmatch.events;

import net.kozibrodka.deathmatch.utils.MapTDM;
import net.kozibrodka.deathmatch.utils.Phase;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;

import java.util.ArrayList;
import java.util.List;

public class Deathmatch {

    @EventListener
    public void tickGame(GameTickEvent.End event){

    }


    public static MapTDM map;
    private static final List<PlayerEntity> TEAM_RED = new ArrayList<>();
    private static final List<PlayerEntity> TEAM_BLUE = new ArrayList<>();
    public static Phase gamePhase;


    public static void addPlayerToTeam(boolean isRed, PlayerEntity player){
        if(gamePhase == Phase.WARMUP){
            (isRed ? TEAM_RED : TEAM_BLUE).add(player);
        }
    }

    public static void forcePlayerToTeam(boolean isRed, PlayerEntity player){
//        if(gamePhase)
    }
}
