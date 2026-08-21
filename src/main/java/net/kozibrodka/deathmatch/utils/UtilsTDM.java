package net.kozibrodka.deathmatch.utils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.maps.Map_glacier;
import net.kozibrodka.deathmatch.maps.Map_port;
import net.kozibrodka.deathmatch.maps.Map_testv1;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

public class UtilsTDM {

    @Environment(EnvType.SERVER)
    public static MinecraftServer mcServ = (MinecraftServer) FabricLoader.getInstance().getGameInstance();

    public static MapTDM getMap(String name){
        return switch (name) {
            case "v1" -> new Map_testv1();
            case "glacier" -> new Map_glacier();
            case "port" -> new Map_port();
            default -> null;
        };
    }

    public static boolean isInEnemyHome(double playerX, double playerZ, boolean isRed) {
        if(isRed) {
            return Deathmatch.map.home_blue.containsIn2D(playerX, playerZ);
        }else{
            return Deathmatch.map.home_red.containsIn2D(playerX, playerZ);
        }
    }

    public static boolean isInGameArea(double playerX, double playerZ){
        return Deathmatch.map.game_area.containsIn2D(playerX,playerZ);
    }

    public static boolean isInTeamWeapons(double playerX, double playerZ, boolean isRed) {
        if(isRed) {
            return Deathmatch.map.weapons_red.containsIn2D(playerX, playerZ);
        }else{
            return Deathmatch.map.weapons_blue.containsIn2D(playerX, playerZ);
        }
    }



    public static Vec3i randomPosInRect(Vec3i corner1, Vec3i corner2, Random random) {
        int minX = Math.min(corner1.x, corner2.x);
        int maxX = Math.max(corner1.x, corner2.x);
        int minZ = Math.min(corner1.z, corner2.z);
        int maxZ = Math.max(corner1.z, corner2.z);

        int x = minX + random.nextInt(maxX - minX + 1);
        int z = minZ + random.nextInt(maxZ - minZ + 1);
        int y = corner1.y; // zakładamy, że Y jest wspólne dla obu punktów

        return new Vec3i(x, y, z);
    }


    public static void movePlayerToTeamSpawn(ServerPlayerEntity player, World world){
        if(Deathmatch.map == null){return;}
        Vec3i loc = getTeamSpawn(player, world);
        if(loc != null){
            player.networkHandler.teleport(loc.x + 0.5D, loc.y + 0.5D, loc.z + 0.5D, player.yaw, player.pitch);
        }else{
            System.out.println("Player has no team, cant teleport to spawn_team area");
        }
    }

    public static void movePlayerToTeamWeapons(ServerPlayerEntity player, World world){
        if(Deathmatch.map == null){return;}
        Vec3i loc = getTeamWeapons(player, world);
        if(loc != null){
            player.networkHandler.teleport(loc.x + 0.5D, loc.y + 0.5D, loc.z + 0.5D, player.yaw, player.pitch);
        }else{
            System.out.println("Player has no team, cant teleport to spawn_team area");
        }
    }

    public static void movePlayerLobby(ServerPlayerEntity player, World world){
        if(Deathmatch.map == null){return;}
        Vec3i loc = getLobby(player, world);
        player.networkHandler.teleport(loc.x + 0.5D, loc.y + 0.5D, loc.z + 0.5D, player.yaw, player.pitch);
    }

    public static Vec3i getTeamSpawn(PlayerEntity player, World world){
        if(Deathmatch.TEAM_RED.contains(player.name)){
            Vec3i pos;
            do {
                pos = Deathmatch.map.spawn_red.randomPosInXZPlane(world.random);
            } while(!world.isAir(pos.x, pos.y, pos.z));

            return pos;
        }
        if(Deathmatch.TEAM_BLUE.contains(player.name)){
            Vec3i pos;
            do {
                pos = Deathmatch.map.spawn_blue.randomPosInXZPlane(world.random);
            } while(!world.isAir(pos.x, pos.y, pos.z));

            return pos;
        }
        return null;
    }

    public static Vec3i getTeamWeapons(PlayerEntity player, World world){
        if(Deathmatch.TEAM_RED.contains(player.name)){
            return Deathmatch.map.weapons_red.randomPosInXZPlane(world.random);
        }
        if(Deathmatch.TEAM_BLUE.contains(player.name)){
            return Deathmatch.map.weapons_blue.randomPosInXZPlane(world.random);
        }
        return null;
    }

    public static Vec3i getLobby(PlayerEntity player, World world){
//        return Deathmatch.map.spawn_lobby.randomPosInXZPlane(world.random);
        Vec3i pos;
        do {
            pos = Deathmatch.map.spawn_lobby.randomPosInXZPlane(world.random);
        } while(!world.isAir(pos.x, pos.y, pos.z));

        return pos;
    }
}
