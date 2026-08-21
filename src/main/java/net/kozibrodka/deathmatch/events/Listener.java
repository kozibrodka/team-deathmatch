package net.kozibrodka.deathmatch.events;

import net.kozibrodka.deathmatch.block.KitBlock;
import net.kozibrodka.deathmatch.block.TdmGlassBlock;
import net.kozibrodka.deathmatch.block.TeamBlock;
import net.kozibrodka.deathmatch.network.TeamSyncPacket;
import net.kozibrodka.deathmatch.utils.ItemCoordStick;
import net.kozibrodka.deathmatch.utils.ItemGlassFiller;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class Listener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static Item coordStick;
    public static Item glassEdit;
    public static Block teamRed;
    public static Block teamBlue;
    public static Block kit_ak;
    public static Block kit_m4;
    public static Block kit_sg552;
    public static Block kit_shotgun;
    public static Block kit_sniper;
    public static Block kit_flame;
    public static Block kit_rpg;
    public static Block lobbyGlass;
    //todo glass with cool text - unbreakable

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        coordStick =  new ItemCoordStick(Identifier.of(MOD_ID, "coordStick")).setTranslationKey(MOD_ID, "coordStick");
        glassEdit =  new ItemGlassFiller(Identifier.of(MOD_ID, "glassEdit")).setTranslationKey(MOD_ID, "glassEdit");
    }

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        teamRed = new TeamBlock(Identifier.of(MOD_ID, "teamRed"), true).setTranslationKey(MOD_ID, "teamRed").setUnbreakable().setResistance(6000000.0F).setSoundGroup(Block.METAL_SOUND_GROUP);
        teamBlue = new TeamBlock(Identifier.of(MOD_ID, "teamBlue"), false).setTranslationKey(MOD_ID, "teamBlue").setUnbreakable().setResistance(6000000.0F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_ak = new KitBlock(Identifier.of(MOD_ID, "kit_ak"), 1).setTranslationKey(MOD_ID, "kit_ak").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_m4 = new KitBlock(Identifier.of(MOD_ID, "kit_m4"), 2).setTranslationKey(MOD_ID, "kit_m4").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_sg552 = new KitBlock(Identifier.of(MOD_ID, "kit_sg552"), 3).setTranslationKey(MOD_ID, "kit_sg552").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_shotgun = new KitBlock(Identifier.of(MOD_ID, "kit_shotgun"), 4).setTranslationKey(MOD_ID, "kit_shotgun").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_sniper = new KitBlock(Identifier.of(MOD_ID, "kit_sniper"), 5).setTranslationKey(MOD_ID, "kit_sniper").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_flame = new KitBlock(Identifier.of(MOD_ID, "kit_flame"), 6).setTranslationKey(MOD_ID, "kit_flame").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        kit_rpg = new KitBlock(Identifier.of(MOD_ID, "kit_rpg"), 7).setTranslationKey(MOD_ID, "kit_rpg").setUnbreakable().setResistance(10F).setSoundGroup(Block.METAL_SOUND_GROUP);
        lobbyGlass = new TdmGlassBlock(Identifier.of(MOD_ID, "lobbyGlass")).setTranslationKey(MOD_ID, "lobbyGlass").setUnbreakable().setResistance(6000000.0F).setSoundGroup(Block.GLASS_SOUND_GROUP);
    }

    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("teamlist"), TeamSyncPacket.TYPE);

    }
}
