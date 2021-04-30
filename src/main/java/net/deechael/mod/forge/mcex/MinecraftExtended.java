package net.deechael.mod.forge.mcex;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.TypeRewriteRule;
import net.deechael.mod.forge.mcex.api.item.BowItemEx;
import net.deechael.mod.forge.mcex.api.item.FishingRodItemEx;
import net.deechael.mod.forge.mcex.api.item.ShieldItemEx;
import net.deechael.mod.forge.mcex.attribute.AttributesEx;
import net.deechael.mod.forge.mcex.entity.EntitiesEx;
import net.deechael.mod.forge.mcex.entity.arrow.*;
import net.deechael.mod.forge.mcex.item.ItemsEx;
import net.deechael.mod.forge.mcex.renderer.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("minecraft_extended")
public class MinecraftExtended {

    public final static DeferredRegister<Item> ITEMS_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft_extended");
    public final static DeferredRegister<Attribute> ATTRIBUTES_REGISTER = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, "minecraft_extended");
    public final static DeferredRegister<EntityType<?>> ENTITIES_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITIES, "minecraft_extended");

    public MinecraftExtended() {
        ITEMS_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ATTRIBUTES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());

        //Registers
          //Items
            //Bows
              ITEMS_REGISTER.register("wooden_bow", () -> new BowItemEx(59, ItemTier.WOOD));
              ITEMS_REGISTER.register("stone_bow", () -> new BowItemEx(131, ItemTier.STONE));
              ITEMS_REGISTER.register("iron_bow", () -> new BowItemEx(250, ItemTier.IRON));
              ITEMS_REGISTER.register("gold_bow", () -> new BowItemEx(32, ItemTier.GOLD));
              ITEMS_REGISTER.register("diamond_bow", () -> new BowItemEx(1561, ItemTier.DIAMOND));
              ITEMS_REGISTER.register("netherite_bow", () -> new BowItemEx(2031, ItemTier.NETHERITE));
            //Arrows
              ITEMS_REGISTER.register("wooden_arrow", net.deechael.mod.forge.mcex.item.arrow.WoodenArrow::new);
              ITEMS_REGISTER.register("stone_arrow", net.deechael.mod.forge.mcex.item.arrow.StoneArrow::new);
              ITEMS_REGISTER.register("iron_arrow", net.deechael.mod.forge.mcex.item.arrow.IronArrow::new);
              ITEMS_REGISTER.register("gold_arrow", net.deechael.mod.forge.mcex.item.arrow.GoldArrow::new);
              ITEMS_REGISTER.register("diamond_arrow", net.deechael.mod.forge.mcex.item.arrow.DiamondArrow::new);
              ITEMS_REGISTER.register("netherite_arrow", net.deechael.mod.forge.mcex.item.arrow.NetheriteArrow::new);
            //Shields
              ITEMS_REGISTER.register("wooden_shield", () -> new ShieldItemEx(59, ItemTier.WOOD));
              ITEMS_REGISTER.register("stone_shield", () -> new ShieldItemEx(131, ItemTier.STONE));
              ITEMS_REGISTER.register("iron_shield", () -> new ShieldItemEx(250, ItemTier.IRON));
              ITEMS_REGISTER.register("gold_shield", () -> new ShieldItemEx(32, ItemTier.GOLD));
              ITEMS_REGISTER.register("diamond_shield", () -> new ShieldItemEx(1561, ItemTier.DIAMOND));
              ITEMS_REGISTER.register("netherite_shield", () -> new ShieldItemEx(2031, ItemTier.NETHERITE));
            //FishingRods
              ITEMS_REGISTER.register("wooden_fishing_rod", () -> new FishingRodItemEx(59, ItemTier.WOOD));
              ITEMS_REGISTER.register("stone_fishing_rod", () -> new FishingRodItemEx(131, ItemTier.STONE));
              ITEMS_REGISTER.register("iron_fishing_rod", () -> new FishingRodItemEx(250, ItemTier.IRON));
              ITEMS_REGISTER.register("gold_fishing_rod", () -> new FishingRodItemEx(32, ItemTier.GOLD));
              ITEMS_REGISTER.register("diamond_fishing_rod", () -> new FishingRodItemEx(1561, ItemTier.DIAMOND));
              ITEMS_REGISTER.register("netherite_fishing_rod", () -> new FishingRodItemEx(2031, ItemTier.NETHERITE));
          //Attributes
            ATTRIBUTES_REGISTER.register("generic.shoot_damage", AttributesEx.SHOOT_DAMAGE.delegate);
            ATTRIBUTES_REGISTER.register("generic.fishing_luck", AttributesEx.FISHING_LUCK.delegate);
            ATTRIBUTES_REGISTER.register("generic.fishing_lure", AttributesEx.FISHING_LURE.delegate);
          //Entities
            ENTITIES_REGISTER.register("wooden_arrow", () -> EntityType.Builder.create(WoodenArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("wooden_arrow"));
            ENTITIES_REGISTER.register("stone_arrow", () -> EntityType.Builder.create(StoneArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("stone_arrow"));
            ENTITIES_REGISTER.register("iron_arrow", () -> EntityType.Builder.create(IronArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("iron_arrow"));
            ENTITIES_REGISTER.register("gold_arrow", () -> EntityType.Builder.create(GoldArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("gold_arrow"));
            ENTITIES_REGISTER.register("diamond_arrow", () -> EntityType.Builder.create(DiamondArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("diamond_arrow"));
            ENTITIES_REGISTER.register("netherite_arrow", () -> EntityType.Builder.create(NetheriteArrow::new, EntityClassification.MISC).size(0.5F, 0.5F).build("netherite_arrow"));

        //Output message
        System.out.println("MinecraftExtended has been enabled");

    }

}
