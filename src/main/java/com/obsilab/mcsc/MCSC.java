package com.obsilab.mcsc;

import com.mojang.logging.LogUtils;
import com.obsilab.mcsc.block.ModBlocks;
import com.obsilab.mcsc.block.custom.CrystalIngotBlock;
import com.obsilab.mcsc.event.CreativeTabEvents;
import com.obsilab.mcsc.item.ModItems;
import com.obsilab.mcsc.networking.ModMessages;
import com.obsilab.mcsc.world.feature.ModConfiguredFeatures;
import com.obsilab.mcsc.world.feature.ModPlacedFeatures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Collection;

import static net.minecraft.core.registries.Registries.BLOCK;
import static net.minecraft.core.registries.Registries.ITEM;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MCSC.MOD_ID)
public class MCSC
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "mcsc";
    public static final String MCSC = MOD_ID; // 🤷‍♂️
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    /*
    // Create a Deferred Register to hold Blocks which will all be registered under the "mcsc" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "mcsc" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Creates a new Block with the id "mcsc:example_block", combining the namespace
    // and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register(
            "example_block", () -> new Block(
                    BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "mcsc:example_block", combining the
    // namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register(
            "example_block", () -> new BlockItem(
                    EXAMPLE_BLOCK.get(), new Item.Properties()));

    // SHOULD PUT THINGS IN SUB-PACKAGES

    // Creates a new Item with the id "mcsc:empty_wafer", combining the namespace
    // and path
    public static final RegistryObject<Item> EMPTY_WAFER_ITEM = ITEMS.register(
            "empty_wafer", () -> new Item(
                    new Item.Properties()));
    // Creates a new Item with the id "mcsc:etched_wafer", combining the namespace
    // and path
    public static final RegistryObject<Item> ETCHED_WAFER_ITEM = ITEMS.register(
            "etched_wafer", () -> new Item(
                    new Item.Properties()));
    */

    public MCSC()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register the custom creative tab ("MCSC") event
        modEventBus.addListener(CreativeTabEvents::onCreativeTabEvent);
        // old: Register the item to a creative tab
        //      modEventBus.addListener(this::addCreative);

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
            // ModVillagers.registerPOIs(); // to be implemented?
        });

    }

    /* replaced by CreativeTabEvents.java (custom tab)
    private void addCreative(CreativeModeTabEvent.BuildContents event) {



        Collection<RegistryObject<Item>> ModItemsList = ModItems.ITEMS.getEntries();
        Collection<RegistryObject<Block>> ModBlocksList = ModBlocks.BLOCKS.getEntries();
        if (event.getTab() == CreativeModeTabs.SEARCH) {
            for (RegistryObject<Item> mod_item : ModItemsList) {
                event.accept(mod_item.get());
            }

            for (RegistryObject<Block> mod_block : ModBlocksList) {
                if (mod_block.get() instanceof CrystalIngotBlock) { // if(mod_block.get().getName().equals("crystal_ingot"))
                  //continue; // remove crop block, prevents crashing
                } else {
                  event.accept(mod_block.get());
                }

                //event.accept(mod_block.get());
            }


            //event.accept(ModItems.EMPTY_WAFER_ITEM.get());
        }

        //if (event.getTab() == CreativeModeTabs.MCSC) {
        //    event.accept(ModItems.EMPTY_WAFER_ITEM.get());
        //}
        //

    }

    */

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

            //ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_INGOT_BLOCK.get(), RenderType.cutout()); // deprecated, replaced by "render_layer": "cutout" in block model json
        }
    }
}
