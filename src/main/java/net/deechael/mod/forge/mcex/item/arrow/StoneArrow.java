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

public class StoneArrow extends ArrowItemEx {

    public StoneArrow() {
        super(ItemTier.STONE, 0, (byte) 0);
        DispenserBlock.registerDispenseBehavior(this, new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                net.deechael.mod.forge.mcex.entity.arrow.StoneArrow arrowEntity = StoneArrow.this.createArrow(worldIn, stackIn, position.getX(), position.getY(), position.getZ());
                arrowEntity.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
                return arrowEntity;
            }
        });
    }

    @Override
    public net.deechael.mod.forge.mcex.entity.arrow.StoneArrow createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
        net.deechael.mod.forge.mcex.entity.arrow.StoneArrow arrow = new net.deechael.mod.forge.mcex.entity.arrow.StoneArrow(worldIn, shooter, this.damage, this.knockback, this.perice);
        return arrow;
    }

    @Override
    public net.deechael.mod.forge.mcex.entity.arrow.StoneArrow createArrow(World worldIn, ItemStack stack, double x, double y, double z) {
        net.deechael.mod.forge.mcex.entity.arrow.StoneArrow arrow = new net.deechael.mod.forge.mcex.entity.arrow.StoneArrow(worldIn, x, y, z, this.damage, this.knockback, this.perice);
        return arrow;
    }

}
