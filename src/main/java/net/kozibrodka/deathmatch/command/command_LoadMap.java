package net.kozibrodka.deathmatch.command;
import com.matthewperiut.retrocommands.api.Command;
import com.matthewperiut.retrocommands.util.SharedCommandSource;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.utils.MapTDM;
import net.kozibrodka.deathmatch.utils.Phase;
import net.kozibrodka.deathmatch.utils.UtilsTDM;
import net.minecraft.entity.player.PlayerEntity;

public class command_LoadMap implements Command{
    @Override
    public void command(SharedCommandSource commandSource, String[] strings) {
        PlayerEntity player = commandSource.getPlayer();
        if (player == null) {
            commandSource.sendFeedback("You must be a player to use this command");
            return;
        }
        if (strings.length > 1) {
            if(strings[1].equalsIgnoreCase("null")){
                Deathmatch.map = null;
                Deathmatch.gamePhase = Phase.NULL;
                Deathmatch.TEAM_BLUE.clear();
                Deathmatch.TEAM_RED.clear();
                commandSource.sendFeedback("Team Deathmatch map is now NULL");
            }else{
                MapTDM newMap = UtilsTDM.getMap(strings[1].toLowerCase());
                if(newMap != null && Deathmatch.map == null){

                    if(newMap.seed != player.world.getSeed()){
                        commandSource.sendFeedback("Map seed is not equal to choosen map");
                        return;
                    }

                    Deathmatch.map = newMap;
                    Deathmatch.world = player.world;
                    Deathmatch.gamePhase = Phase.WARMUP;
                    commandSource.sendFeedback("Team Deathmatch map loaded: " + strings[1].toLowerCase());
                    Deathmatch.allPlayerToLobby();
                }else{
                    commandSource.sendFeedback("Wrong map name");
                }
            }
        } else {
            manual(commandSource);
        }
    }

    @Override
    public String name() {
        return "loadmap";
    }

    @Override
    public void manual(SharedCommandSource commandSource) {
        commandSource.sendFeedback("Usage: /loadmap {map_name}");
        commandSource.sendFeedback("Current Map: " + Deathmatch.map);
    }
}
