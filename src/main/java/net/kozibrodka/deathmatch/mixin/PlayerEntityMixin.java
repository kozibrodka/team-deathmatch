package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Shadow protected abstract void spawnItem(ItemEntity itemEntity);

    @Inject(method = "findRespawnPosition", at = @At("HEAD"), cancellable = true)       //ZŁE MIEJSCE
    private static void getSpawnPoint(World world, Vec3i spawnPos, CallbackInfoReturnable<Vec3i> cir) {
        /// SZUKANIE GDZIE PRZY ŁÓŻKU MOGĘ WSTAĆ
        System.out.println("SZUKAM");
        if(Deathmatch.map == null){
            System.out.println("MapTDM is null, Vanilla spawning");
        }else{
            cir.setReturnValue(UtilsTDM.randomPosInRect(Deathmatch.map.spawn_lobby[0], Deathmatch.map.spawn_lobby[1], world.random));
//            cir.setReturnValue(new Vec3i(mod_deathmatch.map.spawn_lobby));
        }
    }


    @Inject(method = "getSpawnPos", at = @At("HEAD"), cancellable = true)
    void getSpawnPosition(CallbackInfoReturnable<Vec3i> cir) {
        /// WSTĘPNE SZUKANIE POZYCJI SPAWNA
        /// Jeżeli mapa nie jest wczytana - vanilla spawn.
        /// Jeżeli mapa jest wczytana - przekaż po prostu puste kordy, byle by nigdy nie było null.
        if(Deathmatch.map == null){
            System.out.println("MapTDM is null, Vanilla spawning");
        }else{
            cir.setReturnValue(new Vec3i(0,100,0));
            System.out.println("SKY SPAWN");
        }
    }




}
