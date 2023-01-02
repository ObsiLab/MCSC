package com.obsilab.mcsc.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ToolItem extends Item {
    private boolean HAS_COOLDOWN = true;
    private int COOLDOWN = 20; //20 ticks = 1s of cooldown

    public ToolItem(Properties properties, boolean hasCooldown) {
        super(properties);
        this.HAS_COOLDOWN = hasCooldown;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()) {
            components.add(Component.literal("This is a test item").withStyle(ChatFormatting.UNDERLINE, ChatFormatting.YELLOW));
            components.add(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.WHITE));
            components.add(Component.literal("TestItem tooltip and description").withStyle(ChatFormatting.BOLD));
        } else {
            components.add(Component.literal("This is a test item").withStyle(ChatFormatting.UNDERLINE, ChatFormatting.YELLOW));
            components.add(Component.literal("Hold SHIFT for more info").withStyle(ChatFormatting.WHITE));

        }

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if(!level.isClientSide() && hand==InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("ToolItem used"));
            outputMessage(player);
            if(this.HAS_COOLDOWN) {
                player.getCooldowns().addCooldown(this, COOLDOWN);
                //player.sendSystemMessage(Component.literal());// get the bloc kthe player is looking at
            }
            //player.getCooldowns().addCooldown(this, COOLDOWN); //20 ticks = 1s of cooldown
            //return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        return super.use(level, player, hand);
    }

    private void outputMessage(Player player) {
        player.sendSystemMessage(Component.literal(player.getName().getString() + " interacted with TestItem"));

    }
}
