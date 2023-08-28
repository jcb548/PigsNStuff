package net.chuck.pigsnstuff.client;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.awt.*;

public class ArmorHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        int playerArmor = 0;
        int armorValue = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null) {
            if(client.player != null){
                playerArmor = client.player.getArmor();
                armorValue = (int) Math.round((400*Math.log(playerArmor+1))/17);
            }
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            x = width/2;
            y = height;
            context.drawText(client.textRenderer, Text.literal("Armor: " + playerArmor +
                    " (-" + armorValue + "% Damage)"), x-86, y-48, 0xFFFFFF, true);
        }
    }
}
