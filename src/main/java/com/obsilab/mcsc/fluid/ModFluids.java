package com.obsilab.mcsc.fluid;


import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.ModBlocks;
import com.obsilab.mcsc.item.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// TODO
// fluids: NF3 gas (Nitrogen Trifluoride) for dry (plasma) etching, Nitrogen gas for internal FOUP atmosphere ...
public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, MCSC.MOD_ID);

    /*
    public static final RegistryObject<FlowingFluid> SOURCE_TEST_FLUID =
            FLUIDS.register("test_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.TEST_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_TEST_FLUID =
            FLUIDS.register("flowing_test_fluid", () -> new ForgeFlowingFluid.Flowing(ModFluids.TEST_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties TEST_FLUID_PROPERTIES =
            new ForgeFlowingFluid.Properties(
                    ModFluidTypes.TEST_FLUID_TYPE,
                    SOURCE_TEST_FLUID,
                    FLOWING_TEST_FLUID)
                    .slopeFindDistance(2)
                    .levelDecreasePerBlock(2)
                    .block(ModBlocks.TEST_FLUID_BLOCK)
                    .bucket(ModItems.TEST_FLUID_BUCKET)
            ;
     */

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
