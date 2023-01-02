package com.obsilab.mcsc.fluid;

import com.obsilab.mcsc.MCSC;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes { // liquid, gas, plasma ?

    public static final ResourceLocation TEST_FLUID_STILL_RL = new ResourceLocation("block/test_fluid_still");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MCSC.MOD_ID);

    public static final RegistryObject<FluidType> TEST_FLUID_TYPE =
            register("test_fluid", FluidType.Properties.create()
                    .viscosity(1)
                    .canConvertToSource(false)
                    .fallDistanceModifier(1f)
                    // try out other properties
            );

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(TEST_FLUID_STILL_RL, properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }

}
