package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.Phase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(ServerPlayerEntity.class)
public class ServerPlaterMixin extends PlayerEntity {

    public ServerPlaterMixin(World world) {
        super(world);
    }

    @Override
    public void onKilledBy(Entity adversary) {
        if (Deathmatch.gamePhase == Phase.MATCH) {
            Arrays.fill( this.inventory.main, null); ///ADDON
            Arrays.fill( this.inventory.armor, null); ///ADDON
            currentScreenHandler.sendContentUpdates();  ///ADDON
            Deathmatch.onPlayerDeath(this.name); ///ADDON
        }else{
            this.inventory.dropInventory();
        }
    }


//    @Inject(method = "<init>", at = @At("TAIL"))
//    private void onConstruct(MinecraftServer server, World world, String name, ServerPlayerInteractionManager interactionManager, CallbackInfo ci) {
//        ServerPlayerEntity self = (ServerPlayerEntity)(Object)this;
//        // twój kod tutaj, np. self.someMethod();
////        Deathmatch.syncTeamsWithIndividual(name);
////        Deathmatch.syncTeamsWithIndividual(self);
//    }

    @Override
    public void spawn() {

    }

}
