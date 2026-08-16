package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.kozibrodka.deathmatch.utils.UtilsTDM.getTeamSpawn;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow public String name;

    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void tickTailInject(CallbackInfo ci){
        livingTick++;

        if(livingTick % 20 == 0){
            if(Deathmatch.map == null || Deathmatch.gamePhase != Phase.MATCH){
                return;
            }
            if(myTeam == 0){
                if(Deathmatch.TEAM_RED.contains(this.name)){myTeam = 1;}
                if(Deathmatch.TEAM_BLUE.contains(this.name)){myTeam = 2;}
            }

            if(UtilsTDM.isInEnemySpawn(this.x, this.z, myTeam == 1) || !UtilsTDM.isInGameArea(this.x, this.z)){
                Deathmatch.addDirectMessage(world.getPlayer(this.name), "§4Return to combat area!");
                if(deserting > 5){
                    damage(100);
                }
                deserting++;
            }else{
                deserting--;
            }



        }

        if(livingTick > 24000){
            livingTick = 0;
        }
    }



    @Unique
    int livingTick;
    @Unique
    int myTeam;
    @Unique
    int deserting = 0;

}
