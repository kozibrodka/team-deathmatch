package net.kozibrodka.deathmatch.block;

import net.kozibrodka.deathmatch.events.Listener;
import net.kozibrodka.sdk.events.ItemListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SdkKitFactory {

    public static void giveSdkKit(int number, PlayerEntity player){
        switch(number) {
            case 1 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunAk47));
                player.inventory.setStack(1, new ItemStack(Item.COOKED_PORKCHOP));
                player.inventory.setStack(2, new ItemStack(Item.COOKED_PORKCHOP));
                player.inventory.setStack(3, new ItemStack(ItemListener.itemGrenade));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(39, new ItemStack(Item.CHAIN_HELMET));
                player.inventory.setStack(38, new ItemStack(Item.CHAIN_CHESTPLATE));
            }
            case 2 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunM4));
                player.inventory.setStack(1, new ItemStack(Item.COOKED_FISH));
                player.inventory.setStack(2, new ItemStack(Item.COOKED_FISH));
                player.inventory.setStack(3, new ItemStack(ItemListener.itemGrenadeSticky));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(39, new ItemStack(Item.DIAMOND_HELMET));
                player.inventory.setStack(38, new ItemStack(Item.LEATHER_CHESTPLATE));
            }
            case 3 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunSg552));
                player.inventory.setStack(1, new ItemStack(Item.BREAD));
                player.inventory.setStack(2, new ItemStack(Item.BREAD));
                player.inventory.setStack(3, new ItemStack(ItemListener.itemGrenadeIncendiary));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(37, new ItemStack(Item.GOLDEN_LEGGINGS));
                player.inventory.setStack(36, new ItemStack(Item.GOLDEN_BOOTS));
            }
            case 4 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunShotgun));
                player.inventory.setStack(1, new ItemStack(ItemListener.itemGunDeagle));
                player.inventory.setStack(2, new ItemStack(Item.MUSHROOM_STEW));
                player.inventory.setStack(3, new ItemStack(Item.MUSHROOM_STEW));
                player.inventory.setStack(4, new ItemStack(ItemListener.itemGrenadeStun));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletShell, 8));
                player.inventory.setStack(8, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(7, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(6, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(5, new ItemStack(ItemListener.itemBulletMedium, 8));
                player.inventory.setStack(39, new ItemStack(Item.IRON_HELMET));
                player.inventory.setStack(38, new ItemStack(Item.IRON_CHESTPLATE));
            }
            case 5 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunSniper));
                player.inventory.setStack(1, new ItemStack(Item.COOKIE, 8));
                player.inventory.setStack(2, new ItemStack(Item.COOKIE, 8));
                player.inventory.setStack(3, new ItemStack(ItemListener.itemGrenadeSmoke));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletHeavy, 4));
                player.inventory.setStack(38, new ItemStack(Item.DIAMOND_CHESTPLATE));
            }
            case 6 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunMp5));
                player.inventory.setStack(1, new ItemStack(ItemListener.itemGunFlamethrower));
                player.inventory.setStack(2, new ItemStack(Item.COOKED_PORKCHOP));
                player.inventory.setStack(3, new ItemStack(Item.COOKED_FISH));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(8, new ItemStack(ItemListener.itemOil));
                player.inventory.setStack(7, new ItemStack(ItemListener.itemOil));
                player.inventory.setStack(6, new ItemStack(ItemListener.itemOil));
                player.inventory.setStack(39, new ItemStack(Item.GOLDEN_HELMET));
                player.inventory.setStack(38, new ItemStack(Item.GOLDEN_CHESTPLATE));
            }
            case 7 -> {
                player.inventory.setStack(0, new ItemStack(ItemListener.itemGunMp5));
                player.inventory.setStack(1, new ItemStack(ItemListener.itemGunRocketLauncher));
                player.inventory.setStack(2, new ItemStack(Item.COOKED_PORKCHOP));
                player.inventory.setStack(3, new ItemStack(Item.COOKED_FISH));
                player.inventory.setStack(27, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(28, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(29, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(30, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(31, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(32, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(33, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(34, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(35, new ItemStack(ItemListener.itemBulletLight, 32));
                player.inventory.setStack(8, new ItemStack(ItemListener.itemBulletRocket, 4));
                player.inventory.setStack(39, new ItemStack(Item.LEATHER_HELMET));
                player.inventory.setStack(38, new ItemStack(Item.LEATHER_CHESTPLATE));
            }
        }



        if(player instanceof ServerPlayerEntity servPlayer){
            servPlayer.currentScreenHandler.sendContentUpdates();
        }
    }
}
