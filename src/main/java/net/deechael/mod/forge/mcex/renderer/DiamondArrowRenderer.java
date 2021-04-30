package net.deechael.mod.forge.mcex.renderer;

import net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DiamondArrowRenderer extends ArrowRenderer<DiamondArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft_extended:textures/entity/diamond_arrow.png");

    public DiamondArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(DiamondArrow entity) {
        return TEXTURE;
    }

}
