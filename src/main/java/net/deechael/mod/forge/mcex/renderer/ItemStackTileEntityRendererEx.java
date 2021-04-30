package net.deechael.mod.forge.mcex.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import net.deechael.mod.forge.mcex.api.item.ShieldItemEx;
import net.deechael.mod.forge.mcex.item.ItemsEx;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.BannerTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.tileentity.BannerTileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ItemStackTileEntityRendererEx extends ItemStackTileEntityRenderer {

    private final ShieldModel modelShield = new ShieldModel();

    @Override
    public void func_239207_a_(ItemStack itemStack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int combinedLight, int combinedOverlay) {
        if (itemStack.getItem() instanceof ShieldItemEx) {
            boolean flag = itemStack.getChildTag("BlockEntityTag") != null;
            matrixStack.push();
            matrixStack.scale(1.0F, -1.0F, -1.0F);
            RenderMaterial rendermaterial = flag ? ModelBakery.LOCATION_SHIELD_BASE : ModelBakery.LOCATION_SHIELD_NO_PATTERN;
            if (itemStack.getItem() == ItemsEx.WOODEN_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/wooden_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/wooden_shield_base_nopattern"));
            } else if (itemStack.getItem() == ItemsEx.STONE_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/stone_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/stone_shield_base_nopattern"));
            } else if (itemStack.getItem() == ItemsEx.IRON_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/iron_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/iron_shield_base_nopattern"));
            } else if (itemStack.getItem() == ItemsEx.GOLD_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/gold_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/gold_shield_base_nopattern"));
            } else if (itemStack.getItem() == ItemsEx.DIAMOND_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/diamond_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/diamond_shield_base_nopattern"));
            } else if (itemStack.getItem() == ItemsEx.NETHERITE_SHIELD) {
                rendermaterial = flag ? new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/netherite_shield_base")) : new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, new ResourceLocation("minecraft_extended", "entity/netherite_shield_base_nopattern"));
            }
            IVertexBuilder ivertexbuilder = rendermaterial.getSprite().wrapBuffer(ItemRenderer.func_239391_c_(renderTypeBuffer, this.modelShield.getRenderType(rendermaterial.getAtlasLocation()), true, itemStack.hasEffect()));
            this.modelShield.func_228294_b_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            if (flag) {
                List<Pair<BannerPattern, DyeColor>> list = BannerTileEntity.func_230138_a_(ShieldItem.getColor(itemStack), BannerTileEntity.func_230139_a_(itemStack));
                BannerTileEntityRenderer.func_241717_a_(matrixStack, renderTypeBuffer, combinedLight, combinedOverlay, this.modelShield.func_228293_a_(), rendermaterial, false, list, itemStack.hasEffect());
            } else {
                this.modelShield.func_228293_a_().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            }

            matrixStack.pop();
        }
    }

}
