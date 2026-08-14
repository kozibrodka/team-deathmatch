package net.kozibrodka.deathmatch;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.deathmatch.command.TDMCommands;

public class ModHelper implements ModInitializer{
    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("retrocommands")) {
            TDMCommands.init();
        }
    }
}
