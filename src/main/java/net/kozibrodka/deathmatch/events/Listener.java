package net.kozibrodka.deathmatch.events;

import net.kozibrodka.deathmatch.block.TeamBlock;
import net.kozibrodka.deathmatch.utils.ItemCoordStick;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Listener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static Item coordStick;
    public static Block teamRed;
    public static Block teamBlue;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        coordStick =  new ItemCoordStick(Identifier.of(MOD_ID, "coordStick")).setTranslationKey(MOD_ID, "coordStick");
    }

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        teamRed = (TemplateBlock) new TeamBlock(Identifier.of(MOD_ID, "teamRed"), true).setTranslationKey(MOD_ID, "teamRed").setHardness(5F).setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        teamBlue = (TemplateBlock) new TeamBlock(Identifier.of(MOD_ID, "teamBlue"), false).setTranslationKey(MOD_ID, "teamBlue").setHardness(5F).setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
    }
}
