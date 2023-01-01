package com.obsilab.mcsc.event;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.ModBlocks;
import com.obsilab.mcsc.block.custom.CrystalIngotBlock;
import com.obsilab.mcsc.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.List;

public class CreativeTabEvents {

    public static CreativeModeTab MCSC_TAB;

    @SubscribeEvent
    public static void onCreativeTabEvent(CreativeModeTabEvent.Register event) {
        MCSC_TAB = event.registerCreativeModeTab(new ResourceLocation(MCSC.MOD_ID, "mcsc_tab"),
                builder -> builder
                        .icon(() -> new ItemStack(ModItems.ETCHED_WAFER_ITEM.get()))
                        .title(Component.literal("item_group." + MCSC.MOD_ID + ".tab"))
                        .displayItems((features, output, hasPermissions) -> {
                            //onCreativeTabBuildContents((CreativeModeTabEvent.BuildContents) output); //? doable?
                            Collection<RegistryObject<Item>> ModItemsList = ModItems.ITEMS.getEntries();
                            Collection<RegistryObject<Block>> ModBlocksList = ModBlocks.BLOCKS.getEntries();
                            for (RegistryObject<Item> mod_item : ModItemsList) {
                                output.accept(new ItemStack(mod_item.get()));
                            }
                            for (RegistryObject<Block> mod_block : ModBlocksList) {
                                //check if the block is a crop block
                                if (mod_block.get() instanceof CrystalIngotBlock) { // if(mod_block.get().getName().equals("crystal_ingot"))
                                    //continue; // remove crop block, prevents crashing
                                } else {
                                    output.accept(new ItemStack(mod_block.get()));
                                }

                                //event.accept(mod_block.get());
                            }
                        })
                        .build()
        );
    }


    //? needed ?
    @SubscribeEvent
    public static void onCreativeTabBuildContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == MCSC_TAB || event.getTab() == CreativeModeTabs.SEARCH) {
                Collection<RegistryObject<Item>> ModItemsList = ModItems.ITEMS.getEntries();
                Collection<RegistryObject<Block>> ModBlocksList = ModBlocks.BLOCKS.getEntries();
                //List ignoredBlocks = [CrystalIngotBlock]; //! test
                for (RegistryObject<Item> mod_item : ModItemsList) {
                    event.accept(new ItemStack(mod_item.get()));
                }
                for (RegistryObject<Block> mod_block : ModBlocksList) {
                    //check if the block is a crop block
                    if (mod_block.get() instanceof CrystalIngotBlock) { // if(mod_block.get().getName().equals("crystal_ingot"))
                        //continue; // remove crop block, prevents crashing
                    } else {
                        event.accept(new ItemStack(mod_block.get()));
                    }

                    //event.accept(mod_block.get());
                }
        }
    }

}
