package net.kozibrodka.deathmatch.maps;

import net.kozibrodka.deathmatch.utils.MapTDM;
import net.minecraft.util.math.Vec3i;

public class TestMapv1 extends MapTDM {

    public TestMapv1(){
        super();
        spawn_lobby[0] = new Vec3i(46, 65, -21);
        spawn_lobby[1] = new Vec3i(49, 65, -18);
    }
}
