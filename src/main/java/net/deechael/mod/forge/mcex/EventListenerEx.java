package net.deechael.mod.forge.mcex;

import net.deechael.mod.forge.mcex.entity.EntitiesEx;
import net.deechael.mod.forge.mcex.provider.BlockTagsProviderEx;
import net.deechael.mod.forge.mcex.renderer.*;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = "minecraft_extended")
public class EventListenerEx {

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre ev) {
        if (ev.getMap().getTextureLocation() == AtlasTexture.LOCATION_BLOCKS_TEXTURE) {
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/wooden_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/wooden_shield_base"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/stone_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/stone_shield_base"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/iron_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/iron_shield_base"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/gold_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/gold_shield_base"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/diamond_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/diamond_shield_base"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/netherite_shield_base_nopattern"));
            ev.addSprite(new ResourceLocation("minecraft_extended", "entity/netherite_shield_base"));
        }
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        if (event.includeServer()) {
            DataGenerator dataGenerator = event.getGenerator();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            BlockTagsProviderEx blockTags = new BlockTagsProviderEx(dataGenerator, existingFileHelper);
            dataGenerator.addProvider(blockTags);
        }
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.WOODEN_ARROW, WoodenArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.STONE_ARROW, StoneArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.IRON_ARROW, IronArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.GOLD_ARROW, GoldArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.DIAMOND_ARROW, DiamondArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitiesEx.NETHERITE_ARROW, NetheriteArrowRenderer::new);
    }

}
