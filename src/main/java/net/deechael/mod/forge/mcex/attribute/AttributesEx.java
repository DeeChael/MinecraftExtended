package net.deechael.mod.forge.mcex.attribute;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AttributesEx {

    public final static Attribute SHOOT_DAMAGE = new RangedAttribute("attribute.name.generic.shoot_damage", 0.0D, 0.0D, 2048.0D);
    public final static Attribute FISHING_LUCK = new RangedAttribute("attribute.name.generic.fishing_luck", 0.0D, 0.0D, 2048.0D);
    public final static Attribute FISHING_LURE = new RangedAttribute("attribute.name.generic.fishing_lure", 0.0D, 0.0D, 2048.0D);

}
