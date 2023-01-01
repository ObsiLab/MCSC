package com.obsilab.mcsc.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.data.worldgen.BootstapContext;

import java.util.List;


public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, MCSC.MOD_ID);


    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BORAX_ORES =
            Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.BORAX_ORE.get().defaultBlockState())//,
                //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_BORAX_ORE.get().defaultBlockState())
            ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_PHOSPHATE_ORES =
            Suppliers.memoize(() -> List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.PHOSPHATE_ORE.get().defaultBlockState())//,
                //OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_PHOSPHATE_ORE.get().defaultBlockState())
            ));


    public static final RegistryObject<ConfiguredFeature<?, ?>> BORAX_ORE =
            CONFIGURED_FEATURES.register("borax_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_BORAX_ORES.get(),
                    10 // vein size
            )));

    // public static final RegistryObject<OreFeatures> BORAX_ORE =
    //         CONFIGURED_FEATURES.register("borax_ore",
    //                 () -> new OreFeatures(new OreConfiguration(
    //                 new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD),
    //                 ModBlocks.BORAX_ORE.get().defaultBlockState(),
    //                 10 // vein size
    //         )
    // ));



    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_BORAX = FeatureUtils.createKey("ore_borax");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest ruletest1 = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> list6 = List.of(OreConfiguration.target(ruletest1, ModBlocks.BORAX_ORE.get().defaultBlockState()));
        FeatureUtils.register(context, ORE_BORAX, Feature.ORE, new OreConfiguration(list6, 10));
    }

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
