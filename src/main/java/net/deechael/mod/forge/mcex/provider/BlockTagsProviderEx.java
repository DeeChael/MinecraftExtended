package net.deechael.mod.forge.mcex.provider;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsProviderEx extends BlockTagsProvider {

    public BlockTagsProviderEx(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, "minecraft_extended", existingFileHelper);
    }

    public String getName() {
        return "Minecraft Extended Block Tags";
    }

    protected void registerTags() {}

}
