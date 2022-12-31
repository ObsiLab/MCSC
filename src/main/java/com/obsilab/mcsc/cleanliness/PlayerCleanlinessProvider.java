package com.obsilab.mcsc.cleanliness;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerCleanlinessProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerCleanliness> PLAYER_CLEANLINESS = CapabilityManager.get(new CapabilityToken<PlayerCleanliness>() { });

    private PlayerCleanliness cleanliness = null;
    private final LazyOptional<PlayerCleanliness> optional = LazyOptional.of(this::createPlayerCleanliness);

    private PlayerCleanliness  createPlayerCleanliness() {
        if(this.cleanliness == null) {
            this.cleanliness = new PlayerCleanliness(0); //? argument empty
        }
        return this.cleanliness;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_CLEANLINESS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerCleanliness().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerCleanliness().loadNBTData(nbt);
    }
}
