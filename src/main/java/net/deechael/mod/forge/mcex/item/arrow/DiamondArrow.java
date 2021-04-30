package net.deechael.mod.forge.mcex.item.arrow;

import net.deechael.mod.forge.mcex.api.item.ArrowItemEx;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.world.World;

public class DiamondArrow extends ArrowItemEx {

    public DiamondArrow() {
        super(ItemTier.DIAMOND, 0, (byte) 0);
        DispenserBlock.registerDispenseBehavior(this, new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow arrowEntity = DiamondArrow.this.createArrow(worldIn, stackIn, position.getX(), position.getY(), position.getZ());
                arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
                return arrowEntity;
            }
        });
    }

    @Override
    public net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        return new net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow(worldIn, shooter, this.damage, this.knockback, this.perice);
    }

    @Override
    public net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow createArrow(World worldIn, ItemStack stack, double x, double y, double z) {
        return new net.deechael.mod.forge.mcex.entity.arrow.DiamondArrow(worldIn, x, y, z, this.damage, this.knockback, this.perice);
    }

}
