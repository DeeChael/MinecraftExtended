package net.deechael.mod.forge.mcex.api.item;

import net.deechael.mod.forge.mcex.item.ItemGroupEx;
import net.deechael.mod.forge.mcex.renderer.ItemStackTileEntityRendererEx;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;

public class ShieldItemEx extends ShieldItem {

    private final IItemTier tier;

    public ShieldItemEx(int damage, IItemTier itemTier) {
        super(getProperties(damage));
        this.tier = itemTier;
        init();
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;
    }

    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
    }

    public IItemTier getTier() {
        return tier;
    }

    private static Properties getProperties(int damage) {
        Properties properties = new Properties().maxDamage(damage).group(ItemGroupEx.WEAPON).setISTER(() -> ItemStackTileEntityRendererEx::new);
        Class<Properties> propertiesClass = Properties.class;
        try {
            Field field = propertiesClass.getDeclaredField("canRepair");
            field.setAccessible(true);
            try {
                field.set(properties, true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @OnlyIn(Dist.CLIENT)
    private void init() {
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("blocking"), (stack, world, living) -> (living != null && living.isHandActive() && living.getActiveItemStack() == stack) ? 1.0F : 0.0F);
    }

}
