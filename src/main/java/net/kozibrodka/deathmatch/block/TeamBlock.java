package net.kozibrodka.deathmatch.block;

import net.kozibrodka.deathmatch.events.ClientListener;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class TeamBlock extends TemplateBlock {
    public TeamBlock(Identifier identifier, boolean flag) {
        super(identifier, Material.METAL);
        this.isRed = flag;
    }

    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        player.swingHand();
        Deathmatch.addPlayerToTeam(isRed, player);
        return false;
    }

    @Override
    public int getTexture(int i)
    {
        if(i == 1 || i == 0){
            return isRed ? ClientListener.cleanRed : ClientListener.cleanBlue;
        }else{
            return isRed ? ClientListener.teamRed : ClientListener.teamBlue;
        }
    }

    protected boolean isRed;
}
