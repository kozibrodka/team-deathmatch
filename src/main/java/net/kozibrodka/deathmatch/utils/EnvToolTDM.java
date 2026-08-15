package net.kozibrodka.deathmatch.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class EnvToolTDM {
    public static boolean isEnvServ(){
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    public static boolean isEnvClient(){
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }
}
