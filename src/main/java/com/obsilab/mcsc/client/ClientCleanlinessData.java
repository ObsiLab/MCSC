package com.obsilab.mcsc.client;

public class ClientCleanlinessData {
    private static int playerCleanliness;

    public static void set(int cleanliness) {
        ClientCleanlinessData.playerCleanliness = cleanliness;
        // playerCleanliness = cleanliness; //? why not this ?
    }

    public static int getPlayerCleanliness() {
        return playerCleanliness;
    }

}
