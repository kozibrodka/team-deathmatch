package net.kozibrodka.deathmatch.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ItemCoordStick extends TemplateItem {
    public ItemCoordStick(Identifier identifier) {
        super(identifier);
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        user.sendMessage("Coords " + x + " " + y + " " + z);

        // Wpisz tutaj tekst, który chcesz skopiować
        String tekstDoSchowka = x+", "+y+", "+z;

        // Tworzenie obiektu reprezentującego zaznaczony tekst
        StringSelection stringSelection = new StringSelection(tekstDoSchowka);

        // Pobranie schowka systemowego
        Clipboard schowek = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Umieszczenie tekstu w schowku
        schowek.setContents(stringSelection, null);

        System.out.println("Tekst został skopiowany do schowka. Możesz go teraz wkleić!");
        return false;
    }

    public boolean preMine(ItemStack stack, BlockState state, int x, int y, int z, int side, PlayerEntity player) {
        System.out.println("HIT");
        System.out.println(player.world.getSeed());
        return false;
    }

    protected enum Function{
        COORDS,
        SEED
    }


}
