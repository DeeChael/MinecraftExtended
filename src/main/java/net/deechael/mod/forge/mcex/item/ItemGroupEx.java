package net.deechael.mod.forge.mcex.item;

import net.deechael.mod.forge.mcex.item.ItemsEx;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupEx {

    public final static ItemGroup WEAPON = new ItemGroup("mcex_weapon") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsEx.DIAMOND_BOW);
        }

    };

    public final static ItemGroup TOOL = new ItemGroup("mcex_tool") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemsEx.DIAMOND_FISHING_ROD);
        }

    };

}
