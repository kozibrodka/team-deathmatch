package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.GameBox;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class Map_port extends MapTDM {

    public Map_port(){
        super();
        spawn_lobby = GameBox.create(new Vec3i(508, 201, 115), new Vec3i(522, 201, 101));
        spawn_red = GameBox.create(new Vec3i(563, 71, 198), new Vec3i(567, 71, 202));
        home_red = GameBox.create(new Vec3i(585, 67, 221), new Vec3i(535, 67, 184));
        spawn_blue = GameBox.create(new Vec3i(420, 66, 88), new Vec3i(423, 66, 91));
        home_blue = GameBox.create(new Vec3i(437, 66, 101), new Vec3i(414, 66, 77));
        weapons_red = GameBox.create(new Vec3i(562, 201, 114), new Vec3i(561, 201, 102));
        weapons_blue = GameBox.create(new Vec3i(468, 201, 102), new Vec3i(469, 201, 114));
        game_area = GameBox.create(new Vec3i(614, 64, 225), new Vec3i(411, 64, 14));
        map_name = "port";
        seed = 5014627097246605980L;
    }
}
