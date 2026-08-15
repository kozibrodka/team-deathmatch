package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Redirect(
            method = "respawnPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;findRespawnPosition(Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/Vec3i;"

            )
    )
    private Vec3i tdmFindRespawn(World world, Vec3i spawnPos, ServerPlayerEntity player, int dimensionId) {
        /// SZUKANIE GDZIE PRZY ŁÓŻKU MOGĘ WSTAĆ

        if (Deathmatch.map == null) { /// Vanilla spawn, map=null
            return PlayerEntity.findRespawnPosition(world, spawnPos);
        }

        if (Deathmatch.gamePhase == Phase.WARMUP || Deathmatch.gamePhase == Phase.STARTING) {
            return UtilsTDM.randomPosInRect(Deathmatch.map.spawn_lobby[0], Deathmatch.map.spawn_lobby[1], world.random);
        }

        if (Deathmatch.gamePhase == Phase.MATCH) {
            return UtilsTDM.getTeamWeapons(player, world);
        }

        return PlayerEntity.findRespawnPosition(world, spawnPos);
    }




    @Redirect(
            method = "respawnPlayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ServerPlayerEntity;getSpawnPos()Lnet/minecraft/util/math/Vec3i;"
            )
    )
    private Vec3i tdmGetSpawnPos(ServerPlayerEntity player) {
        /// WSTĘPNE SZUKANIE POZYCJI SPAWNA
        /// Jeżeli mapa nie jest wczytana - vanilla spawn.
        /// Jeżeli mapa jest wczytana - przekaż po prostu puste kordy, byle by nigdy nie było null.
        Vec3i real = player.getSpawnPos();
        if (Deathmatch.map != null) {
            return real != null ? real : new Vec3i(0, -10, 0);
        }
        return real;
    }
}
