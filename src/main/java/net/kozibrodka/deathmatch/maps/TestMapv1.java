package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class TestMapv1 extends MapTDM {

    public TestMapv1(){
        super();
        spawn_lobby[0] = new Vec3i(46, 65, -21);
        spawn_lobby[1] = new Vec3i(49, 65, -18);
        spawn_red[0] = new Vec3i(40, 64, -22);
        spawn_red[1] = new Vec3i(37, 64, -19);
        spawn_blue[0] = new Vec3i(58, 64, -22);
        spawn_blue[1] = new Vec3i(55, 64, -19);
        weapons_red[0] = new Vec3i(43, 65, -13);
        weapons_red[1] = new Vec3i(36, 65, -12);
        weapons_blue[0] = new Vec3i(61, 65, -12);
        weapons_blue[1] = new Vec3i(55, 65, -13);


//        spawn_lobby[0] = new Vec3i();
//        spawn_lobby[1] = new Vec3i();
    }

}
