package net.deechael.mod.forge.mcex.renderer;

import net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow;
import net.deechael.mod.forge.mcex.entity.arrow.IronArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronArrowRenderer extends ArrowRenderer<IronArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("minecraft_extended:textures/entity/iron_arrow.png");

    public IronArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getEntityTexture(IronArrow entity) {
        return TEXTURE;
    }

}
