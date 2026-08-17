package net.kozibrodka.deathmatch.command;

import com.matthewperiut.retrocommands.api.Command;
import com.matthewperiut.retrocommands.util.SharedCommandSource;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.Phase;
import net.minecraft.entity.player.PlayerEntity;

public class command_Game implements Command {
    @Override
    public void command(SharedCommandSource commandSource, String[] strings) {
        PlayerEntity player = commandSource.getPlayer();
        if (player == null) {
            commandSource.sendFeedback("You must be a player to use this command");
            return;
        }
        if(Deathmatch.map == null){
            commandSource.sendFeedback("Team Deathmatch map is NULL, load one first");
            return;
        }

        if (strings.length > 1) {
            if (strings[1].equalsIgnoreCase("start")) {
                if(Deathmatch.gamePhase == Phase.WARMUP || Deathmatch.gamePhase == Phase.AFTERMATH) {
                    Deathmatch.teamUpMissingPlayers();
                    Deathmatch.balanceTeamsSizes(player.world);
                    Deathmatch.warmupReady = 300; ///600 - 30s
                    Deathmatch.gamePhase = Phase.STARTING;
                }
            }
            if(strings[1].equalsIgnoreCase("swap")){
                if(Deathmatch.gamePhase == Phase.WARMUP || Deathmatch.gamePhase == Phase.AFTERMATH) {
                    Deathmatch.swapTeams();
                }
            }
        }
    }

    @Override
    public String name() {
        return "tdm";
    }

    @Override
    public void manual(SharedCommandSource commandSource) {
        commandSource.sendFeedback("Feedback");
    }
}
