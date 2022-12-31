package com.obsilab.mcsc.networking.packet;


import com.obsilab.mcsc.client.ClientCleanlinessData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CleanlinessDataSyncS2CPacket {

    private final int cleanliness;

    public CleanlinessDataSyncS2CPacket(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public CleanlinessDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.cleanliness = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(cleanliness);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // on the client!
            ClientCleanlinessData.set(cleanliness);
        });
        return true;
    }

    // TODO
}
