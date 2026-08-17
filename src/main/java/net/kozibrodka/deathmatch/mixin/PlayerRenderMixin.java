package net.kozibrodka.deathmatch.mixin;

import net.kozibrodka.deathmatch.events.Deathmatch;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRenderMixin extends LivingEntityRenderer {

    public PlayerRenderMixin(EntityModel entityModel, float shadowRadius) {
        super(entityModel, shadowRadius);
    }

    @Override
    protected void renderNameTag(LivingEntity entity, String name, double dx, double dy, double dz, int range) {
        float var10 = entity.getDistance(this.dispatcher.cameraEntity);
        if (!(var10 > (float)range)) {
            TextRenderer var11 = this.getTextRenderer();
            float var12 = 1.6F;
            float var13 = 0.016666668F * var12;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)dx + 0.0F, (float)dy + 2.3F, (float)dz);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.dispatcher.yaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.dispatcher.pitch, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var13, -var13, var13);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            Tessellator var14 = Tessellator.INSTANCE;
            byte var15 = 0;
            if (name.equals("deadmau5")) {
                var15 = -10;
            }

            int color = -1;
            int colorTranslucent = 553648127;
            if(Deathmatch.TEAM_RED.contains(name)){
                color = -65536;
                colorTranslucent = 553582592;
            }
            if(Deathmatch.TEAM_BLUE.contains(name)){
                color = -16776961;
                colorTranslucent = 536871167;
            }

            GL11.glDisable(3553);
            var14.startQuads();
            int var16 = var11.getWidth(name) / 2;
            var14.color(0.0F, 0.0F, 0.0F, 0.25F);
            var14.vertex((double)(-var16 - 1), (double)(-1 + var15), (double)0.0F);
            var14.vertex((double)(-var16 - 1), (double)(8 + var15), (double)0.0F);
            var14.vertex((double)(var16 + 1), (double)(8 + var15), (double)0.0F);
            var14.vertex((double)(var16 + 1), (double)(-1 + var15), (double)0.0F);
            var14.draw();
            GL11.glEnable(3553);
            var11.draw(name, -var11.getWidth(name) / 2, var15, colorTranslucent);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            var11.draw(name, -var11.getWidth(name) / 2, var15, color);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
}
