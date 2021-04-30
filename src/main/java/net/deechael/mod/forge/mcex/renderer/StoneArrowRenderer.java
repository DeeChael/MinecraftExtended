package net.deechael.mod.forge.mcex.renderer;

import net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow;
import net.deechael.mod.forge.mcex.entity.arrow.StoneArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneArrowRenderer extends ArrowRenderer<StoneArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft_extended:textures/entity/stone_arrow.png");

    public StoneArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(StoneArrow entity) {
        return TEXTURE;
    }

}
