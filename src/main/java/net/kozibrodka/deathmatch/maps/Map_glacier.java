package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.GameBox;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class Map_glacier extends MapTDM {

    public Map_glacier() {
        super();
        spawn_lobby = GameBox.create(new Vec3i(19, 82, 72), new Vec3i(7, 82, 60));
        spawn_red = GameBox.create(new Vec3i(28, 67, 3), new Vec3i(31, 67, 0));
        home_red = GameBox.create(new Vec3i(17, 67, 10), new Vec3i(49, 67, 0));
        spawn_blue = GameBox.create(new Vec3i(28, 67, 51), new Vec3i(31, 67, 48));
        home_blue = GameBox.create(new Vec3i(49, 67, 51), new Vec3i(17, 67, 41));
        weapons_red = GameBox.create(new Vec3i(15, 108, 90), new Vec3i(13, 108, 95));
        weapons_blue = GameBox.create(new Vec3i(19, 108, 90), new Vec3i(17, 108, 95));
        game_area = GameBox.create(new Vec3i(16, 67, -1), new Vec3i(50, 67, 52));
        map_name = "glacier";
        seed = 1772835215L;
    }

}
