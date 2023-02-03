package com.obsilab.mcsc.item;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.fluid.ModFluids;
import com.obsilab.mcsc.item.custom.TestItem;
import com.obsilab.mcsc.item.custom.ToolItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.obsilab.mcsc.MCSC.LOGGER;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "mcsc" namespace
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCSC.MOD_ID);

    /*
    // ----- ITEMS : ------
     */
    public static final RegistryObject<Item> SILICON_ITEM = registerItem(
            "silicon", () -> new Item(
                    new Item.Properties()
                            //.stacksTo(1)

            ));

    public static final RegistryObject<Item> EMPTY_WAFER_ITEM = registerItem(
            "empty_wafer", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()
            ));
    public static final RegistryObject<Item> EXPOSED_WAFER_ITEM = registerItem(
                "exposed_wafer", () -> new Item(
                        new Item.Properties()
                                .durability(100)
                                //.stacksTo(1)
                                .setNoRepair()
            ));

    public static final RegistryObject<Item> ETCHED_WAFER_ITEM = registerItem(
            "etched_wafer", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()

            ));

    public static final RegistryObject<Item> MONOCRYSTALLINE_SILICON_BOULE_ITEM = registerItem(
            "monocrystalline_silicon_boule", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> WAFER_CASSETTE_ITEM = registerItem(
            "wafer_cassette", () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> FOUP_ITEM = registerItem(
            "foup", () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> SILICON_CRYSTAL_SEED = registerItem(
            "silicon_crystal_seed", () -> new Item(
                    new Item.Properties()
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> RAW_BORAX = registerItem(
            "raw_borax", () -> new Item(
                    new Item.Properties()
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> RAW_PHOSPHATE = registerItem(
            "raw_phosphate", () -> new Item(
                    new Item.Properties()
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> WRENCH_ITEM = registerItem(
            "wrench", () -> new ToolItem(
                    new ToolItem.Properties()
                            .stacksTo(1)
                            .durability(100)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            , true
            ));

    public static final RegistryObject<Item> TOOLKIT_ITEM = registerItem(
            "toolkit", () -> new ToolItem(
                    new ToolItem.Properties()
                            .stacksTo(1)
                            .durability(1000)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            , false
            ));

    public static final RegistryObject<Item> TEST_ITEM_ITEM = registerItem(
            "test_item", () -> new TestItem(
                    // new Item.Properties()
                    new TestItem.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> TEST_FLUID_BUCKET = registerItem(
            "test_fluid_bucket", () -> new BucketItem(
                    ModFluids.SOURCE_TEST_FLUID,
                    new Item.Properties()
                            .craftRemainder(Items.BUCKET)
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item){
        LOGGER.info("MCSC: Registering Item >> {} : {}", name, item.get()); //? .get() ?
        return ITEMS.register(name, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
