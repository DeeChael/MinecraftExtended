package net.deechael.mod.forge.mcex.api.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.deechael.mod.forge.mcex.attribute.AttributesEx;
import net.deechael.mod.forge.mcex.item.ItemGroupEx;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.reflect.Field;
import java.util.UUID;

public class FishingRodItemEx extends FishingRodItem {

    protected static final UUID LUCK_MODIFIER = UUID.fromString("B55295AB-C272-47E2-BB75-AD209F1F2372");
    protected static final UUID LURE_MODIFIER = UUID.fromString("9E702735-02B2-4A5E-9476-B152B771A665");

    private final IItemTier tier;

    private int luckLevel;
    private int lureLevel;

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public FishingRodItemEx(int damage, IItemTier itemTier) {
        super(getProperties(damage));
        this.tier = itemTier;
        this.luckLevel = this.tier.getHarvestLevel() + 1;
        this.lureLevel = this.tier.getHarvestLevel() + 1;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributesEx.FISHING_LUCK, new AttributeModifier(LUCK_MODIFIER, "Fishing rod modifier", this.luckLevel, AttributeModifier.Operation.ADDITION));
        builder.put(AttributesEx.FISHING_LURE, new AttributeModifier(LURE_MODIFIER, "Fishing rod modifier", this.lureLevel, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        this.init();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (playerIn.fishingBobber != null) {
            if (!worldIn.isRemote) {
                int i = playerIn.fishingBobber.handleHookRetraction(itemstack);
                itemstack.damageItem(i, playerIn, (playerEntity) -> {
                    playerEntity.sendBreakAnimation(handIn);
                });
            }

            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!worldIn.isRemote) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack) + this.lureLevel;
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack) + this.luckLevel;
                worldIn.addEntity(new FishingBobberEntity(playerIn, worldIn, j, k));
            }

            playerIn.addStat(Stats.ITEM_USED.get(this));
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }

    private static Properties getProperties(int damage) {
        Properties properties = new Properties().maxDamage(damage).group(ItemGroupEx.TOOL);
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

    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
    }

    @OnlyIn(Dist.CLIENT)
    private void init() {
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("cast"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean flag = livingEntity.getHeldItemMainhand() == itemStack;
                boolean flag1 = livingEntity.getHeldItemOffhand() == itemStack;
                if (livingEntity.getHeldItemMainhand().getItem() instanceof FishingRodItem) {
                    flag1 = false;
                }

                return (flag || flag1) && livingEntity instanceof PlayerEntity && ((PlayerEntity)livingEntity).fishingBobber != null ? 1.0F : 0.0F;
            }
        });
    }

}
