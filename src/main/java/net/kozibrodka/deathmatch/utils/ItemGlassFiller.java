package net.kozibrodka.deathmatch.utils;

import net.kozibrodka.deathmatch.events.Listener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ItemGlassFiller extends TemplateItem {
    public ItemGlassFiller(Identifier identifier) {
        super(identifier);
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {

        if(!corner){
            first = new Vec3i(x,y,z);
            user.sendMessage("first corner saved");
        }else{
            second = new Vec3i(x,y,z);
            user.sendMessage("second corner saved");
        }

        corner = !corner;
        return false;
    }

    public boolean preMine(ItemStack stack, BlockState state, int x, int y, int z, int side, PlayerEntity player) {

        if(first != null && second != null){
            fillArea(player.world, first, second, Listener.lobbyGlass.id, 0);
        }else{
            player.sendMessage("one of positions is null");
        }

        first = null;
        second = null;
        return false;
    }

    public static void fillArea(World world, Vec3i first, Vec3i second, int blockId, int meta) {
        int minX = Math.min(first.x, second.x);
        int minY = Math.min(first.y, second.y);
        int minZ = Math.min(first.z, second.z);

        int maxX = Math.max(first.x, second.x);
        int maxY = Math.max(first.y, second.y);
        int maxZ = Math.max(first.z, second.z);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.setBlock(x, y, z, blockId, meta);
                }
            }
        }
    }


    boolean corner;
    Vec3i first;
    Vec3i second;
}
