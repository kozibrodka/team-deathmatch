package net.kozibrodka.deathmatch.events;

import net.mine_diver.unsafeevents.listener.EventListener;

import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ClientListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();


    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        Listener.coordStick.setTexture(Identifier.of(MOD_ID, "item/coorditem"));
        Listener.glassEdit.setTexture(Identifier.of(MOD_ID, "block/glass"));

        clean = registerBlockTexture("block/clean");
        teamRed = registerBlockTexture("block/team_red");
        teamBlue = registerBlockTexture("block/team_blue");
        cleanRed = registerBlockTexture("block/clean_red");
        cleanBlue = registerBlockTexture("block/clean_blue");
        kit_ak = registerBlockTexture("block/kit_ak");
        kit_m4 = registerBlockTexture("block/kit_m4");
        kit_sg552 = registerBlockTexture("block/kit_sg552");
        kit_shotgun = registerBlockTexture("block/kit_shotgun");
        kit_sniper = registerBlockTexture("block/kit_sniper");
        kit_flame = registerBlockTexture("block/kit_flame");
        kit_rpg = registerBlockTexture("block/kit_rpg");
        glass = registerBlockTexture("block/glass");
    }

    private int registerBlockTexture(String s) {
        if(s == null) {
            return 0;
        }
        return Atlases.getStationTerrain().addTexture(Identifier.of(MOD_ID, s)).index;
    }


    public static int clean;
    public static int teamRed;
    public static int teamBlue;
    public static int cleanRed;
    public static int cleanBlue;
    public static int kit_ak;
    public static int kit_m4;
    public static int kit_sg552;
    public static int kit_shotgun;
    public static int kit_sniper;
    public static int kit_flame;
    public static int kit_rpg;
    public static int glass;
}
