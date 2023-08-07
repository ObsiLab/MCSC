package com.obsilab.mcsc;

import com.mojang.logging.LogUtils;
import com.obsilab.mcsc.block.ModBlocks;
//import com.obsilab.mcsc.block.custom.CrystalIngotBlock;
import com.obsilab.mcsc.fluid.ModFluidTypes;
import com.obsilab.mcsc.fluid.ModFluids;
import com.obsilab.mcsc.item.ModItems;
import com.obsilab.mcsc.networking.ModMessages;
import com.obsilab.mcsc.world.feature.ModConfiguredFeatures;
import com.obsilab.mcsc.world.feature.ModFeatures;
import com.obsilab.mcsc.world.feature.ModPlacedFeatures;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Collection;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCSC.MOD_ID)
public class MCSC
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mcsc";
    public static final String MODID = MOD_ID;
    //public static final String MCSC = MOD_ID; // 🤷‍♂️

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MCSC()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus); // registers items
        ModBlocks.register(modEventBus); // registers blocks

        //ModConfiguredFeatures.register(modEventBus); // registers configured features
        //ModPlacedFeatures.register(modEventBus); // registers placed features

        //ModFeatures.register(modEventBus); // registers features

        //ModFluids before ModFluidTypes?
        //ModFluids.register(modEventBus); // registers fluids
        //ModFluidTypes.register(modEventBus); // registers fluid types

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the custom creative tab ("MCSC") event
        //modEventBus.addListener(CreativeTabEvents::onCreativeTabEvent);
        //modEventBus.addListener(CreativeTabEvents::onCreativeTabBuildContents);
        // old: Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        /* see ModBlocks.java and ModItems.java
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        */

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        /*
        CreativeModeTabs.tabs().add(new CreativeModeTabs("mcsc") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ModItems.EMPTY_WAFER_ITEM.get());
            }
        });
        */
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        event.enqueueWork(() -> {
            ModMessages.register();
            //? ModPlacedFeatures.register((IEventBus) event);
            //? ModPlacedFeatures.register((IEventBus) event);
            // ModVillagers.registerPOIs(); // to be implemented?
        });

    }

    // duplicate of CreativeTabEvents.onCreativeTabBuildContents (custom tab)
    private void addCreative(CreativeModeTabEvent.BuildContents event) {

        if (event.getTab() == ModCreativeModeTabs.MCSC_TAB || event.getTab() == CreativeModeTabs.SEARCH) {

            Collection<RegistryObject<Item>> ModItemsList = ModItems.ITEMS.getEntries();
            Collection<RegistryObject<Block>> ModBlocksList = ModBlocks.BLOCKS.getEntries();
            //List ignoredBlocks = [CrystalIngotBlock]; //! test
            for (RegistryObject<Item> mod_item : ModItemsList) {
                event.accept(new ItemStack(mod_item.get()));
            }
            for (RegistryObject<Block> mod_block : ModBlocksList) {
                //check if the block is a crop block => needed? since did not register blockItem for crop blocks
                /*
                if (mod_block.get() instanceof CrystalIngotBlock) { // if(mod_block.get().getName().equals("crystal_ingot"))
                    //continue; // remove crop block, prevents crashing
                } else {
                    event.accept(new ItemStack(mod_block.get()));
                }
                */
                event.accept(new ItemStack(mod_block.get())); // all from the list
            }
        }
    }

    /*
    @SubscribeEvent
    public void buildContents(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "mcsc"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + MOD_ID + ".mcsc"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ModItems.ETCHED_WAFER_ITEM.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(ModItems.ETCHED_WAFER_ITEM.get());
                            //populator.accept(BLOCK.get());
                        })
        );
    }

    */

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
        LOGGER.info("*MCSC* : Hi!");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            //set render layer for fluid? in json?

        }
    }
}
