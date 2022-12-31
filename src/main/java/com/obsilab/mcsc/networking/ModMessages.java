package com.obsilab.mcsc.networking;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.networking.packet.CleanlinessC2SPacket;
import com.obsilab.mcsc.networking.packet.CleanlinessDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(MCSC.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(CleanlinessDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT) // /!\ PLAY_TO_CLIENT
                .decoder(CleanlinessDataSyncS2CPacket::new)
                .encoder(CleanlinessDataSyncS2CPacket::toBytes)
                .consumerMainThread(CleanlinessDataSyncS2CPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }


}
