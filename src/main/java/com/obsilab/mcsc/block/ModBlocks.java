package com.obsilab.mcsc.block;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.custom.TestBlock;
import com.obsilab.mcsc.item.ModItems;
import com.obsilab.mcsc.item.custom.TestItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MCSC.MOD_ID);


    public static final RegistryObject<Block> FOUP_STORAGE_BLOCK = registerBlock(
            "foup_storage",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(6f)
                    .requiresCorrectToolForDrops()
            ));

    public static final RegistryObject<Block> TEST_BLOCK_BLOCK = registerBlock(
            "test_block",
            () -> new TestBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(6f)
            ));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){//, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);//, tab);
        return toReturn;

        //return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){//, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));//.tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
