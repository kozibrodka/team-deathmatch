package net.kozibrodka.deathmatch.utils;

import net.minecraft.util.math.Vec3i;

public class MapTDM {

    public MapTDM() {
        spawn_lobby = new Vec3i[2];
    }

    public Vec3i spawn_lobby[];
    public Vec3i game_area[];
    public Vec3i spawn_red[];
    public Vec3i spawn_blue[];
    public Vec3i weapons_red[];
    public Vec3i weapons_blue[];

}
