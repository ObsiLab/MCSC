package com.obsilab.mcsc.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.obsilab.mcsc.MCSC;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class CleanlinessHudOverlay {
    private static final ResourceLocation CLEAN = new ResourceLocation(MCSC.MOD_ID,
            "textures/cleanliness/clean.png"); // or "textures/gui/clean.png"
    private static final ResourceLocation NOT_CLEAN = new ResourceLocation(MCSC.MOD_ID,
            "textures/cleanliness/not_clean.png"); // or "textures/gui/not_clean.png"

    public static final IGuiOverlay HUD_CLEANLINESS = ((gui, poseStack, partialTick, width, height) -> {
        // draw overlay if this player is on survival mode or adventure mode
        //if (!(Minecraft.getInstance().player.isSpectator() || Minecraft.getInstance().player.isCreative())) {
        if(!gui.getMinecraft().options.hideGui && gui.shouldDrawSurvivalElements()) {
            int x = width / 2;
            int y = height ;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, NOT_CLEAN);
            for(int i = 0; i < 10; i++) {
                GuiComponent.blit(poseStack,
                        x-64 * (i+9), // x
                        y-54, // y
                        0, // offset x
                        0, // offset y
                        12, // size of what we're drawing x
                        12, // size of what we're drawing y
                        12, // size of the image that we're drawing x (reduced from 16x16 ?)
                        12 // size of the image that we're drawing y
                );
            }


            RenderSystem.setShaderTexture(0, CLEAN);
            for(int i = 0; i < 10; i++) {
                if(ClientCleanlinessData.getPlayerCleanliness() > i) {
                    GuiComponent.blit(poseStack,
                            x-64 * (i+9), // x
                            y-54, // y
                            0, // offset x
                            0, // offset y
                            12, // size of what we're drawing x
                            12, // size of what we're drawing y
                            12, // size of the image that we're drawing x (reduced from 16x16 ?)
                            12 // size of the image that we're drawing y
                    );
                } else {
                    break;
                }
            }
        }
    });

}
