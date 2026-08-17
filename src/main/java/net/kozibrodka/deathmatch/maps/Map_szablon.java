package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.GameBox;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class Map_szablon extends MapTDM {
    public Map_szablon(){
        super();
        spawn_lobby = GameBox.create(new Vec3i(), new Vec3i());
        spawn_red = GameBox.create(new Vec3i(), new Vec3i());
        home_red = GameBox.create(new Vec3i(), new Vec3i());
        spawn_blue = GameBox.create(new Vec3i(), new Vec3i());
        home_blue = GameBox.create(new Vec3i(), new Vec3i());
        weapons_red = GameBox.create(new Vec3i(), new Vec3i());
        weapons_blue = GameBox.create(new Vec3i(), new Vec3i());
        game_area = GameBox.create(new Vec3i(), new Vec3i());
        map_name = "";
        seed = 0L;
    }
}
