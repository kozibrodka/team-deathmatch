package net.kozibrodka.deathmatch.command;
import com.matthewperiut.retrocommands.api.CommandRegistry;

public class TDMCommands {

    public static void init() {
        CommandRegistry.add(new command_LoadMap());
    }
}
