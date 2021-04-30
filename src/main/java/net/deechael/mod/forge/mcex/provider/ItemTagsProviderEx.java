package net.deechael.mod.forge.mcex.provider;

import net.deechael.mod.forge.mcex.item.ItemsEx;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsProviderEx extends ItemTagsProvider {

    public ItemTagsProviderEx(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, "minecraft_extended", existingFileHelper);
    }

    public String getName() {
        return "Minecraft Extended Item Tags";
    }

    protected void registerTags() {
        func_240522_a_(ItemTags.ARROWS).func_240534_a_(ItemsEx.WOODEN_ARROW, ItemsEx.STONE_ARROW, ItemsEx.IRON_ARROW, ItemsEx.GOLD_ARROW, ItemsEx.DIAMOND_ARROW, ItemsEx.NETHERITE_ARROW);
    }

}
