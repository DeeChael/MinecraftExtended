package net.deechael.mod.forge.mcex.entity.arrow;

import net.deechael.mod.forge.mcex.api.entity.ArrowEntityEx;
import net.deechael.mod.forge.mcex.entity.EntitiesEx;
import net.deechael.mod.forge.mcex.item.ItemsEx;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class NetheriteArrow extends ArrowEntityEx {

    public NetheriteArrow(EntityType<?> a, World b) {
        super(a, b);
    }

    public NetheriteArrow(World worldIn, double attackDamage, int knockback, byte perice) {
        super(EntitiesEx.NETHERITE_ARROW, worldIn, attackDamage, knockback, perice);
    }

    public NetheriteArrow(World worldIn, LivingEntity shooter, double attackDamage, int knockback, byte perice) {
        super(EntitiesEx.NETHERITE_ARROW, worldIn, shooter, attackDamage, knockback, perice);
    }

    public NetheriteArrow(World worldIn, double x, double y, double z, double attackDamage, int knockback, byte perice) {
        super(EntitiesEx.NETHERITE_ARROW, worldIn, x, y, z, attackDamage, knockback, perice);
    }

    @Override
    public ItemStack getArrowStack() {
        return ItemsEx.NETHERITE_ARROW.getDefaultInstance();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}