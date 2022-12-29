package com.obsilab.mcsc.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;


import java.util.List;


public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, MCSC.MOD_ID);

    /*
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BORAX_ORES =
            Suppliers.memoize(() -> List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BORAX_ORE.get().defaultBlockState())//,
                //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_BORAX_ORE.get().defaultBlockState())
            ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_PHOSPHATE_ORES =
            Suppliers.memoize(() -> List.of(
                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.PHOSPHATE_ORE.get().defaultBlockState())//,
                //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PHOSPHATE_ORE.get().defaultBlockState())
            ));
    */

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
