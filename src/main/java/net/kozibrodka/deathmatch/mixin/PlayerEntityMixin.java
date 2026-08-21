package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.ServPlayerAccessor;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ServPlayerAccessor {

    @Shadow public String name;

    @Override
    public void resetTeam(){
        myTeam = 0;
    }

    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void tickTailInject(CallbackInfo ci){

        if(!constructed){
            Deathmatch.onPlayerConnect(world.getPlayer(this.name));
            constructed = true;
        }


        livingTick++;
        if(livingTick % 20 == 0){
            if(Deathmatch.map == null || Deathmatch.gamePhase != Phase.MATCH){
                return;
            }
            if(myTeam == 0){
                if(Deathmatch.TEAM_RED.contains(this.name)){myTeam = 1;}
                if(Deathmatch.TEAM_BLUE.contains(this.name)){myTeam = 2;}
            }
//            System.out.println(livingTick + "  desercja: " + deserting);
            if(UtilsTDM.isInEnemyHome(this.x, this.z, myTeam == 1) || (!UtilsTDM.isInGameArea(this.x, this.z) && !UtilsTDM.isInTeamWeapons(this.x, this.y, myTeam == 1))){
                Deathmatch.addDirectMessage(world.getPlayer(this.name), "§4Return to combat area!");
                if(deserting > 5){
                    damage(100);
                }
                deserting++;
            }else{
                if(deserting > 0) {
                    deserting--;
                }
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
    @Unique
    boolean isRed;

    @Unique
    boolean constructed;

}
