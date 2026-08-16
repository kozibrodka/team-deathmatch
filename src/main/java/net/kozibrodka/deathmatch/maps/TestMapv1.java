package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.GameBox;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class TestMapv1 extends MapTDM {

    public TestMapv1(){
        super();
        spawn_lobby = GameBox.create(new Vec3i(46, 65, -21), new Vec3i(49, 65, -18));
        spawn_red = GameBox.create(new Vec3i(40, 64, -22), new Vec3i(37, 64, -19));
        spawn_blue = GameBox.create(new Vec3i(58, 64, -22), new Vec3i(55, 64, -19));
        weapons_red = GameBox.create(new Vec3i(43, 65, -13), new Vec3i(36, 65, -12));
        weapons_blue = GameBox.create(new Vec3i(61, 65, -12), new Vec3i(55, 65, -13));
        game_area = GameBox.create(new Vec3i(63, 65, -10), new Vec3i(34, 65, -28));
    }

}
