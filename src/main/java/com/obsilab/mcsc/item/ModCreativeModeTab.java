/*package com.obsilab.mcsc.item;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class ModCreativeModeTab {

    //protected ModCreativeModeTab(Builder builder) {
    //    super(builder);
    //}


    public static final CreativeModeTab MCSC_TAB = new CreativeModeTab("mcsctab") {
        @Override
        public void fillItemList(NonNullList<ItemStack> list) {
            super.fillItemList(list);
            list.add(new ItemStack(ModItems.EMPTY_WAFER_ITEM.get()));
            list.add(new ItemStack(ModItems.EXPOSED_WAFER_ITEM.get()));
            list.add(new ItemStack(ModItems.ETCHED_WAFER_ITEM.get()));
            list.add(new ItemStack(ModItems.MONOCRYSTALLINE_SILICON_BOULE_ITEM.get()));
        }
        @Override
        public ItemStack makeIcon() {
            //return ModItems.ETCHED_WAFER_ITEM.get().getDefaultInstance();
            return new ItemStack(ModItems.ETCHED_WAFER_ITEM.get());
        }
    };
}
*/
