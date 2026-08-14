package net.kozibrodka.deathmatch.utils;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

public class UtilsTDM {

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

    public static void allPlayersToSpawn(World world){

        Vec3i loc;

        for (Object player : world.players) {
            loc = randomPosInRect(Deathmatch.map.spawn_lobby[0], Deathmatch.map.spawn_lobby[1], world.random);
            ((PlayerEntity)player).setPositionAndAnglesKeepPrevAngles((float)loc.x + 0.5F, (float)loc.y + 0.1F, (float)loc.z + 0.5F, 0.0F, 0.0F);
        }


    }
}
