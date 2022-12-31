package com.obsilab.mcsc.event;


import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.cleanliness.PlayerCleanliness;
import com.obsilab.mcsc.cleanliness.PlayerCleanlinessProvider;
import com.obsilab.mcsc.networking.ModMessages;
import com.obsilab.mcsc.networking.packet.CleanlinessDataSyncS2CPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCSC.MOD_ID)
public class ModEvents {

    // TODO ?

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerCleanlinessProvider.PLAYER_CLEANLINESS).isPresent()) {
                event.addCapability(new ResourceLocation(MCSC.MOD_ID, "properties"), new PlayerCleanlinessProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerCleanlinessProvider.PLAYER_CLEANLINESS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerCleanlinessProvider.PLAYER_CLEANLINESS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCleanliness.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerCleanlinessProvider.PLAYER_CLEANLINESS).ifPresent(cleanliness -> {
                // example : reduce cleanliness with time
                if (cleanliness.getCleanliness() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once every 10 seconds on average
                    cleanliness.subCleanliness(1);

                    // optional send chat message :
                    event.player.sendSystemMessage(Component.literal("Cleanliness reduced").withStyle(ChatFormatting.GRAY));

                    ModMessages.sendToPlayer(new CleanlinessDataSyncS2CPacket(cleanliness.getCleanliness()), ((ServerPlayer) event.player));
                }

            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerCleanlinessProvider.PLAYER_CLEANLINESS).ifPresent(cleanliness -> {
                    ModMessages.sendToPlayer(new CleanlinessDataSyncS2CPacket(cleanliness.getCleanliness()), player);
                });
            }
        }
    }

}
