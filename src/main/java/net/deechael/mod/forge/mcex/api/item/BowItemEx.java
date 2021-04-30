package net.deechael.mod.forge.mcex.api.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.deechael.mod.forge.mcex.item.ItemGroupEx;
import net.deechael.mod.forge.mcex.attribute.AttributesEx;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class BowItemEx extends BowItem {

    protected static final UUID SHOOT_DAMAGE_MODIFIER = UUID.fromString("FA8B66A4-69DA-89CE-A698-9A58C89D4EFB");

    private final IItemTier tier;
    private double attackDamage;

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public BowItemEx(int damage, IItemTier itemTier) {
        super(getProperties(damage));
        this.tier = itemTier;
        this.attackDamage = tier.getAttackDamage() + 2.0;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributesEx.SHOOT_DAMAGE, new AttributeModifier(SHOOT_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        init();
    }

    public BowItemEx(int damage, IItemTier itemTier, int attackDamage) {
        super(getProperties(damage));
        this.tier = itemTier;
        this.attackDamage = attackDamage;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributesEx.SHOOT_DAMAGE, new AttributeModifier(SHOOT_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
        init();
    }

    private static Properties getProperties(int damage) {
        Properties properties = new Properties().maxDamage(damage).group(ItemGroupEx.WEAPON);
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

    public IItemTier getTier() {
        return tier;
    }

    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return attributeModifiers;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customArrow(abstractarrowentity);
                        abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        double shootDamage = 0.0D;
                        if (this.attributeModifiers.containsKey(AttributesEx.SHOOT_DAMAGE)) {
                            for (AttributeModifier attributeModifier : this.attributeModifiers.get(AttributesEx.SHOOT_DAMAGE)) {
                                shootDamage += attributeModifier.getAmount();
                            }
                        }
                        if (playerentity.getAttribute(AttributesEx.SHOOT_DAMAGE) != null) {
                            shootDamage += playerentity.getAttribute(AttributesEx.SHOOT_DAMAGE).getValue();
                        }
                        abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (shootDamage));

                        if (f == 1.0F) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });
                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void init() {
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("pull"), (itemStack, clientWorld, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getActiveItemStack() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - livingEntity.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.func_239418_a_(this, new ResourceLocation("pulling"), (itemStack, clientWorld, livingEntity) -> livingEntity != null && livingEntity.isHandActive() && livingEntity.getActiveItemStack() == itemStack ? 1.0F : 0.0F);
    }

}
