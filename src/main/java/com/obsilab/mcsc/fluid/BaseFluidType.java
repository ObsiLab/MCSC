package com.obsilab.mcsc.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.joml.Vector3f;

import java.util.function.Consumer;

public class BaseFluidType extends FluidType {
    private final ResourceLocation stillTexture; // only this for gas, not placeable
    //private final ResourceLocation flowingTexture;
    //private final ResourceLocation overlayTexture;
    //private final int tintColor;
    //private final Vector3f fogColor; // fog when inside the fluid

    //contructor:
    public BaseFluidType(final ResourceLocation stillTexture, final Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
    }

    //getter(s):
    public ResourceLocation getStillTexture() {
        return stillTexture;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            // @Nullable for the getOverlayTexture override, if used
            // @NotNull for the modifyFogColor override, if used

        });
    }
}
