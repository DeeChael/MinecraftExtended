package net.deechael.mod.forge.mcex.api.entity;

import net.minecraft.command.impl.data.EntityDataAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

import java.io.IOException;
import java.lang.reflect.Field;

public abstract class ArrowEntityEx extends AbstractArrowEntity implements IEntityAdditionalSpawnData {

    private int shooterId = -1;

    private int knockback;
    private byte perice;

    public ArrowEntityEx(EntityType<? extends ArrowEntityEx> type, World worldIn, double attackDamage, int knockback, byte perice) {
        super(type, worldIn);
        this.setDamage(attackDamage);
        this.knockback = knockback;
        this.perice = perice;
        Class<ArrowEntityEx> clazz = ArrowEntityEx.class;
        try {
            Field ID = clazz.getDeclaredField("field_234610_c");
            ID.setAccessible(true);
            this.shooterId = (int) ID.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ArrowEntityEx(EntityType<? extends ArrowEntityEx> type, World worldIn, LivingEntity shooter, double attackDamage, int knockback, byte perice) {
        super(type, shooter, worldIn);
        this.setDamage(attackDamage);
        this.knockback = knockback;
        this.perice = perice;
        Class<ArrowEntityEx> clazz = ArrowEntityEx.class;
        try {
            Field ID = clazz.getDeclaredField("field_234610_c");
            ID.setAccessible(true);
            Object object = ID.get(this);
            if (object != null) {
                this.shooterId = (int) object;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ArrowEntityEx(EntityType<? extends ArrowEntityEx> type, World worldIn, double x, double y, double z, double attackDamage, int knockback, byte perice) {
        super(type, x, y, z, worldIn);
        this.setDamage(attackDamage);
        this.knockback = knockback;
        this.perice = perice;
        Class<ArrowEntityEx> clazz = ArrowEntityEx.class;
        try {
            Field ID = clazz.getDeclaredField("field_234610_c");
            ID.setAccessible(true);
            Object object = ID.get(this);
            if (object != null) {
                this.shooterId = (int) object;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ArrowEntityEx(EntityType<?> a, World b) {
        super((EntityType<? extends AbstractArrowEntity>) a, b);
    }

    @Override
    public void setPierceLevel(byte level) {
        super.setPierceLevel((byte) (this.perice + level));
    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {
        super.setKnockbackStrength(this.knockback + knockbackStrengthIn);
    }

    @Override
    public abstract ItemStack getArrowStack();

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeInt(this.shooterId);
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        try {
            EntityDataManager.readEntries(additionalData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
