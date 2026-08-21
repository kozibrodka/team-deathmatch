package net.kozibrodka.deathmatch.command;

import com.matthewperiut.retrocommands.api.Command;
import com.matthewperiut.retrocommands.util.SharedCommandSource;
import net.kozibrodka.deathmatch.events.Listener;
import net.minecraft.entity.player.PlayerEntity;

public class command_Floor implements Command {
    @Override
    public void command(SharedCommandSource commandSource, String[] strings) {
        PlayerEntity player = commandSource.getPlayer();
        if (player == null) {
            commandSource.sendFeedback("You must be a player to use this command");
            return;
        }
        player.world.setBlock((int) Math.floor(player.x), (int) Math.floor(player.y)-2, (int) Math.floor(player.z), Listener.lobbyGlass.id,0);
    }

    @Override
    public String name() {
        return "floor";
    }

    @Override
    public void manual(SharedCommandSource sharedCommandSource) {

    }
}
