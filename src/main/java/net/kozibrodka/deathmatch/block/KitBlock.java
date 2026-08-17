package net.kozibrodka.deathmatch.block;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.deathmatch.events.ClientListener;
import net.kozibrodka.deathmatch.events.Listener;
import net.kozibrodka.deathmatch.utils.EnvToolTDM;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Arrays;

public class KitBlock extends TemplateBlock {
    public KitBlock(Identifier identifier, int kitID) {
        super(identifier, Material.METAL);
        numberKit = kitID;
    }

    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        player.swingHand();
        if(world.isRemote || EnvToolTDM.isEnvClient()){
            return false;
        }
        cleanInventory(player);
        if(FabricLoader.getInstance().isModLoaded("sdk")){

        }else{
            giveVanillaKit(player);
        }
        UtilsTDM.movePlayerToTeamSpawn((ServerPlayerEntity) player, world);
        return false;
    }

    public void giveVanillaKit(PlayerEntity player){
        switch(numberKit){
            case 1:

                player.inventory.setStack(0, new ItemStack(Listener.coordStick));
                player.inventory.setStack(1, new ItemStack(Item.DIAMOND_SWORD));
                player.inventory.setStack(2, new ItemStack(Item.APPLE));
//                player.inventory.addStack()
                break;
            case 2:
        }

        if(player instanceof ServerPlayerEntity servPlayer){
            servPlayer.currentScreenHandler.sendContentUpdates();
        }
    }

    public void cleanInventory(PlayerEntity player){
        Arrays.fill(player.inventory.main, null);
        Arrays.fill(player.inventory.armor, null);
    }

    @Override
    public int getTexture(int i)
    {
        if(i == 1 || i == 0){
            return ClientListener.clean;
        }else{
            return ClientListener.kit_ak;
        }
    }

    protected int numberKit;
}
