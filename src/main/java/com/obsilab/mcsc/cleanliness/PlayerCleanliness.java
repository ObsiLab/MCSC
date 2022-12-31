package com.obsilab.mcsc.cleanliness;

import net.minecraft.nbt.CompoundTag;

public class PlayerCleanliness {
    private int cleanliness;
    private final int MIN_CLEANLINESS = 0;
    private final int MAX_CLEANLINESS = 10;

    public PlayerCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void addCleanliness(int add) {
        this.cleanliness = Math.min(cleanliness + add, MAX_CLEANLINESS);
    }

    public void subCleanliness(int sub) {
        this.cleanliness = Math.max(cleanliness - sub, MIN_CLEANLINESS);
    }

    public void copyFrom(PlayerCleanliness source) {
        this.cleanliness = source.cleanliness;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("cleanliness", cleanliness);
    }

    public void loadNBTData(CompoundTag nbt) {
        cleanliness = nbt.getInt("cleanliness");
    }

    /*
    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }
    */
}
