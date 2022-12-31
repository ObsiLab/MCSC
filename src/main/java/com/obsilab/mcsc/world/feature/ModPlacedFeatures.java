package com.obsilab.mcsc.world.feature;

import com.obsilab.mcsc.MCSC;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, MCSC.MOD_ID);

    // public static final RegistryObject<PlacedFeature> BORAX_ORE_PLACED = PLACED_FEATURES.register("borax_ore_placed",
    //         () -> new PlacedFeature(ModConfiguredFeatures.BORAX_ORE.getHolder().get(),
    //                 commonOrePlacement(6, // VeinsPerChunk
    //                         HeightRangePlacement.triangle(
    //                                 VerticalAnchor.absolute(0),
    //                                 VerticalAnchor.absolute(160)
    //                         ))));


    public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier1, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }

    public static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), placementModifier);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }

}
