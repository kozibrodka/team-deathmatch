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

        clean = registerBlockTexture("block/clean");
        teamRed = registerBlockTexture("block/team_red");
        teamBlue = registerBlockTexture("block/team_blue");
        cleanRed = registerBlockTexture("block/clean_red");
        cleanBlue = registerBlockTexture("block/clean_blue");
        kit_ak = registerBlockTexture("block/kit_ak");
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
}
