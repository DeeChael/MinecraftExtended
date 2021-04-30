package net.deechael.mod.forge.mcex.api.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.deechael.mod.forge.mcex.item.ItemGroupEx;
import net.deechael.mod.forge.mcex.api.entity.ArrowEntityEx;
import net.deechael.mod.forge.mcex.attribute.AttributesEx;
import net.deechael.mod.forge.mcex.entity.EntitiesEx;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.UUID;

public class ArrowItemEx extends ArrowItem {

    protected static final UUID SHOOT_DAMAGE_MODIFIER = UUID.fromString("FA8B66A4-69DA-89CE-A698-9A58C89D4EFB");

    protected final IItemTier tier;
    protected final double damage;

    protected int knockback = 0;
    protected byte perice = 0;

    private final Multimap<Attribute, AttributeModifier> attributeModifiers;

    public ArrowItemEx(IItemTier itemTier, int knockback, byte perice) {
        super(new Properties().group(ItemGroupEx.WEAPON));
        this.tier = itemTier;
        this.damage = this.tier.getAttackDamage();
        this.knockback = knockback;
        this.perice = perice;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributesEx.SHOOT_DAMAGE, new AttributeModifier(SHOOT_DAMAGE_MODIFIER, "Arrow modifier", this.damage, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public ArrowItemEx(IItemTier itemTier, int knockback, byte perice, double damage) {
        super(new Properties().group(ItemGroupEx.WEAPON));
        this.tier = itemTier;
        this.damage = damage;
        this.knockback = knockback;
        this.perice = perice;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(AttributesEx.SHOOT_DAMAGE, new AttributeModifier(SHOOT_DAMAGE_MODIFIER, "Arrow modifier", this.damage, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }


    public IItemTier getTier() {
        return tier;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        return equipmentSlot == EquipmentSlotType.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return attributeModifiers;
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new ArrowEntity(worldIn, shooter);
    }

    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, double x, double y, double z) {
        return new ArrowEntity(worldIn, x, y, z);
    }

}
