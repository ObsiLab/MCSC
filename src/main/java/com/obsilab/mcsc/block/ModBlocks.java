package com.obsilab.mcsc.block;

import com.obsilab.mcsc.MCSC;
import com.obsilab.mcsc.block.custom.CrystalIngotBlock;
import com.obsilab.mcsc.block.custom.TestBlock;
import com.obsilab.mcsc.fluid.ModFluids;
import com.obsilab.mcsc.item.ModItems;
import com.obsilab.mcsc.item.custom.TestItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
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

    public static final RegistryObject<Block> BORAX_ORE = registerBlock( // => boric acid => boron for p-type doping
            "borax_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(3f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(3,7)
            ));


    public static final RegistryObject<Block> PHOSPHATE_ORE = registerBlock(
            "phosphate_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties
                    .of(Material.STONE)
                    .strength(3f)
                    .requiresCorrectToolForDrops(),
                    UniformInt.of(3,7)
            ));

    public static final RegistryObject<Block> TEST_BLOCK_BLOCK = registerBlock(
            "test_block",
            () -> new TestBlock(BlockBehaviour.Properties
                    .of(Material.METAL)
                    .strength(6f)
                    .lightLevel(state -> state.getValue(TestBlock.ACTIVE) ? 8 : 0) // light level of 8 if block's ACTIVE property is true, else light level of 0
            ));

    public static final RegistryObject<LiquidBlock> TEST_FLUID_BLOCK = BLOCKS.register("test_fluid_block",
            () -> new LiquidBlock(
                    ModFluids.SOURCE_TEST_FLUID,
                    BlockBehaviour.Properties
                            .copy(Blocks.WATER)
            ));

    public static final RegistryObject<Block> CRYSTAL_INGOT_BLOCK = BLOCKS.register( // crop block, so not register BlockItem
            "crystal_ingot",
            () -> new CrystalIngotBlock(BlockBehaviour.Properties
                    .copy(Blocks.WHEAT)
                    //.of(Material.METAL)
                    .strength(4f)
                    //.requiresCorrectToolForDrops() // TODO
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
