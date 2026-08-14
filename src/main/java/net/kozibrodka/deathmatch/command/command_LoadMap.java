package net.kozibrodka.deathmatch.command;
import com.matthewperiut.retrocommands.api.Command;
import com.matthewperiut.retrocommands.util.SharedCommandSource;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.kozibrodka.deathmatch.maps.TestMapv1;
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
            switch(strings[1].toLowerCase()) { /// funckja ustawienia mapy....

                case "null":
                    Deathmatch.map = null;
                    System.out.println("Team Deathmatch map is NULL");
                    break;
                case "testv1":
                    TestMapv1 newMap = new TestMapv1();
                    Deathmatch.map = newMap;
                    commandSource.sendFeedback("Team Deathmatch map loaded: TestMapv1");
                    UtilsTDM.allPlayersToSpawn(player.world);
                    break;
                    /// KOMUNIKAT O ZŁEJ MAPIE
//                commandSource.sendFeedback("zla mapa");
            }
            System.out.println("PRÓBA ZAŁADOWANIA MAPY:" + strings[1]);
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
        commandSource.sendFeedback("Info: twitch.tv/aliasbrave");
    }
}
