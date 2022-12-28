package com.obsilab.mcsc.item;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.item.custom.TestItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "mcsc" namespace
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCSC.MOD_ID);

    /*
    // ----- ITEMS : ------
     */
    public static final RegistryObject<Item> SILICON_ITEM = ITEMS.register(
            "silicon", () -> new Item(
                    new Item.Properties()
                            //.stacksTo(1)

            ));

    public static final RegistryObject<Item> EMPTY_WAFER_ITEM = ITEMS.register(
            "empty_wafer", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()
            ));
    public static final RegistryObject<Item> EXPOSED_WAFER_ITEM = ITEMS.register(
                "exposed_wafer", () -> new Item(
                        new Item.Properties()
                                .durability(100)
                                //.stacksTo(1)
                                .setNoRepair()
            ));

    public static final RegistryObject<Item> ETCHED_WAFER_ITEM = ITEMS.register(
            "etched_wafer", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()

            ));

    public static final RegistryObject<Item> MONOCRYSTALLINE_SILICON_BOULE_ITEM = ITEMS.register(
            "monocrystalline_silicon_boule", () -> new Item(
                    new Item.Properties()
                            .durability(100)
                            //.stacksTo(1)
                            .setNoRepair()
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> WAFER_CASSETTE_ITEM = ITEMS.register(
            "wafer_cassette", () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> FOUP_ITEM = ITEMS.register(
            "foup", () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static final RegistryObject<Item> TEST_ITEM_ITEM = ITEMS.register(
            "test_item", () -> new TestItem(
                    // new Item.Properties()
                    new TestItem.Properties()
                            .stacksTo(1)
                            //.group(MCSC.MCSC_GROUP)
                            //.tab(ModCreativeModeTab.MCSC_TAB)
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
