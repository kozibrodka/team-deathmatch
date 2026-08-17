package net.kozibrodka.deathmatch.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.deathmatch.events.ClientListener;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class TdmGlassBlock extends TemplateTranslucentBlock {
    public TdmGlassBlock(Identifier identifier) {
        super(identifier, 0, Material.GLASS, false);
    }

    @Override
    public int getTexture(int i)
    {
        return ClientListener.glass;
    }

    public int getDroppedItemCount(Random random) {
        return 1;
    }

//    @Environment(EnvType.CLIENT)
//    public int getRenderLayer() {
//        return 0;
//    }

//    public boolean isOpaque()
//    {
//        return false;
//    }
//
//    public int getRenderLayer(){
//        return 0;
//    }
}
