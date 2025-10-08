package com.p1nero.smc.registrate;

import com.p1nero.smc.SkilletManCoreMod;
import com.p1nero.smc.effect.SMCEffects;
import com.p1nero.smc.item.custom.*;
import com.p1nero.smc.item.custom.skillets.*;
import com.p1nero.smc.item.custom.spatulas.*;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import dev.xkmc.cuisinedelight.init.data.TagGen;
import dev.xkmc.cuisinedelight.init.registrate.CDBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.client.model.generators.ModelFile;

@MethodsReturnNonnullByDefault
public class SMCRegistrateItems {

    public static final ItemEntry<SkilletV2> IRON_SKILLET_LEVEL2 = SkilletManCoreMod.REGISTRATE.item("cuisine_skillet_v2", p -> new SkilletV2(CDBlocks.SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/cuisine_skillet_base")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<SkilletV3> IRON_SKILLET_LEVEL3 = SkilletManCoreMod.REGISTRATE.item("cuisine_skillet_v3", p -> new SkilletV3(CDBlocks.SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/cuisine_skillet_base")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<SkilletV4> IRON_SKILLET_LEVEL4 = SkilletManCoreMod.REGISTRATE.item("cuisine_skillet_v4", p -> new SkilletV4(CDBlocks.SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/cuisine_skillet_base")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<SkilletV5> IRON_SKILLET_LEVEL5 = SkilletManCoreMod.REGISTRATE.item("cuisine_skillet_v5", p -> new SkilletV5(CDBlocks.SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/cuisine_skillet_base")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();

    public static final ItemEntry<GoldenSkilletItem> GOLDEN_SKILLET = SkilletManCoreMod.REGISTRATE.item("golden_cuisine_skillet", p -> new GoldenSkilletItem(SMCRegistrateBlocks.GOLDEN_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<GoldenSkilletItemV2> GOLDEN_SKILLET_V2 = SkilletManCoreMod.REGISTRATE.item("golden_cuisine_skillet_v2", p -> new GoldenSkilletItemV2(SMCRegistrateBlocks.GOLDEN_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<GoldenSkilletItemV3> GOLDEN_SKILLET_V3 = SkilletManCoreMod.REGISTRATE.item("golden_cuisine_skillet_v3", p -> new GoldenSkilletItemV3(SMCRegistrateBlocks.GOLDEN_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<GoldenSkilletItemV4> GOLDEN_SKILLET_V4 = SkilletManCoreMod.REGISTRATE.item("golden_cuisine_skillet_v4", p -> new GoldenSkilletItemV4(SMCRegistrateBlocks.GOLDEN_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<GoldenSkilletItemV5> GOLDEN_SKILLET_V5 = SkilletManCoreMod.REGISTRATE.item("golden_cuisine_skillet_v5", p -> new GoldenSkilletItemV5(SMCRegistrateBlocks.GOLDEN_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<DiamondSkilletItem> DIAMOND_SKILLET = SkilletManCoreMod.REGISTRATE.item("diamond_cuisine_skillet", p -> new DiamondSkilletItem(SMCRegistrateBlocks.DIAMOND_SKILLET.get(), p.stacksTo(1)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<DiamondSkilletItem> DIAMOND_SKILLET_V2 = SkilletManCoreMod.REGISTRATE.item("diamond_cuisine_skillet_v2", p -> new DiamondSkilletItem(SMCRegistrateBlocks.DIAMOND_SKILLET.get(), p.stacksTo(1), 2))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<DiamondSkilletItem> DIAMOND_SKILLET_V3 = SkilletManCoreMod.REGISTRATE.item("diamond_cuisine_skillet_v3", p -> new DiamondSkilletItem(SMCRegistrateBlocks.DIAMOND_SKILLET.get(), p.stacksTo(1), 3))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();

    public static final ItemEntry<DiamondSkilletItem> DIAMOND_SKILLET_V4 = SkilletManCoreMod.REGISTRATE.item("diamond_cuisine_skillet_v4", p -> new DiamondSkilletItem(SMCRegistrateBlocks.DIAMOND_SKILLET.get(), p.stacksTo(1), 4))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();

    public static final ItemEntry<DiamondSkilletItem> DIAMOND_SKILLET_V5 = SkilletManCoreMod.REGISTRATE.item("diamond_cuisine_skillet_v5", p -> new DiamondSkilletItem(SMCRegistrateBlocks.DIAMOND_SKILLET.get(), p.stacksTo(1), 5))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")))
            .setData(ProviderType.LANG, NonNullBiConsumer.noop()).register();
    public static final ItemEntry<SpatulaV2> SPATULA_V2 = SkilletManCoreMod.REGISTRATE.item("spatula_v2", p -> new SpatulaV2(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/spatula")))
            .defaultLang().register();
    public static final ItemEntry<SpatulaV3> SPATULA_V3 = SkilletManCoreMod.REGISTRATE.item("spatula_v3", p -> new SpatulaV3(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/spatula")))
            .defaultLang().register();
    public static final ItemEntry<SpatulaV4> SPATULA_V4 = SkilletManCoreMod.REGISTRATE.item("spatula_v4", p -> new SpatulaV4(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/spatula")))
            .defaultLang().register();
    public static final ItemEntry<SpatulaV5> SPATULA_V5 = SkilletManCoreMod.REGISTRATE.item("spatula_v5", p -> new SpatulaV5(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("cuisinedelight:item/spatula")))
            .defaultLang().register();
    public static final ItemEntry<GoldenSpatulaBase> GOLDEN_SPATULA = SkilletManCoreMod.REGISTRATE.item("golden_spatula", p -> new GoldenSpatulaBase(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.handheld(ctx))
            .defaultLang().register();
    public static final ItemEntry<GoldenSpatulaV2> GOLDEN_SPATULA_V2 = SkilletManCoreMod.REGISTRATE.item("golden_spatula_v2", p -> new GoldenSpatulaV2(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/golden_spatula")))
            .defaultLang().register();
    public static final ItemEntry<GoldenSpatulaV3> GOLDEN_SPATULA_V3 = SkilletManCoreMod.REGISTRATE.item("golden_spatula_v3", p -> new GoldenSpatulaV3(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/golden_spatula")))
            .defaultLang().register();
    public static final ItemEntry<GoldenSpatulaV4> GOLDEN_SPATULA_V4 = SkilletManCoreMod.REGISTRATE.item("golden_spatula_v4", p -> new GoldenSpatulaV4(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/golden_spatula")))
            .defaultLang().register();
    public static final ItemEntry<GoldenSpatulaV5> GOLDEN_SPATULA_V5 = SkilletManCoreMod.REGISTRATE.item("golden_spatula_v5", p -> new GoldenSpatulaV5(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/golden_spatula")))
            .defaultLang().register();
    public static final ItemEntry<DiamondSpatulaBase> DIAMOND_SPATULA = SkilletManCoreMod.REGISTRATE.item("diamond_spatula", p -> new DiamondSpatulaBase(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.handheld(ctx))
            .defaultLang().register();
    public static final ItemEntry<DiamondSpatulaV2> DIAMOND_SPATULA_V2 = SkilletManCoreMod.REGISTRATE.item("diamond_spatula_v2", p -> new DiamondSpatulaV2(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/diamond_spatula")))
            .defaultLang().register();
    public static final ItemEntry<DiamondSpatulaV3> DIAMOND_SPATULA_V3 = SkilletManCoreMod.REGISTRATE.item("diamond_spatula_v3", p -> new DiamondSpatulaV3(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/diamond_spatula")))
            .defaultLang().register();
    public static final ItemEntry<DiamondSpatulaV4> DIAMOND_SPATULA_V4 = SkilletManCoreMod.REGISTRATE.item("diamond_spatula_v4", p -> new DiamondSpatulaV4(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/diamond_spatula")))
            .defaultLang().register();
    public static final ItemEntry<DiamondSpatulaV5> DIAMOND_SPATULA_V5 = SkilletManCoreMod.REGISTRATE.item("diamond_spatula_v5", p -> new DiamondSpatulaV5(p.stacksTo(1)))
            .tag(TagGen.UTENSILS)
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile(SkilletManCoreMod.MOD_ID + ":item/diamond_spatula")))
            .defaultLang().register();
    public static final ItemEntry<DirtPlateItem> DIRT_PLATE = SkilletManCoreMod.REGISTRATE.item("dirt_plate", DirtPlateItem::new)
            .defaultModel()
            .tag(TagGen.UTENSILS)
            .defaultLang().register();

    public static final ItemEntry<Item> DODGE_ICON = SkilletManCoreMod.REGISTRATE.item("dodge_icon", Item::new)
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<Item> PARRY_ICON = SkilletManCoreMod.REGISTRATE.item("parry_icon", Item::new)
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<Item> TASK_TIP_ICON = SkilletManCoreMod.REGISTRATE.item("task_tip_icon", Item::new)
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> WEAPON_RAFFLE_TICKET = SkilletManCoreMod.REGISTRATE.item("weapon_raffle_ticket", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> ARMOR_RAFFLE_TICKET = SkilletManCoreMod.REGISTRATE.item("armor_raffle_ticket", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> SKILL_BOOK_RAFFLE_TICKET = SkilletManCoreMod.REGISTRATE.item("skill_book_raffle_ticket", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> DISC_RAFFLE_TICKET = SkilletManCoreMod.REGISTRATE.item("disk_raffle_ticket", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> PET_RAFFLE_TICKET = SkilletManCoreMod.REGISTRATE.item("pet_raffle_ticket", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<SimpleDescriptionFoilItem> CREATE_RAFFLE = SkilletManCoreMod.REGISTRATE.item("create_raffle", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<SimpleDescriptionFoilItem> REDSTONE_RAFFLE = SkilletManCoreMod.REGISTRATE.item("redstone_raffle", properties -> new SimpleDescriptionFoilItem(properties.fireResistant().rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<TeleporterItem> END_TELEPORTER = SkilletManCoreMod.REGISTRATE.item("end_teleporter", p -> new TeleporterItem(p.stacksTo(1).rarity(Rarity.EPIC)))
            .model((ctx, pvd) -> pvd.getBuilder(ctx.getName()).parent(new ModelFile.UncheckedModelFile("minecraft:item/ender_eye")))
            .defaultLang().register();
    public static final ItemEntry<CookGuideBookItem> COOK_GUIDE_BOOK_ITEM = SkilletManCoreMod.REGISTRATE.item("cook_guide_book", p -> new CookGuideBookItem(p.stacksTo(1).rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<CreateCookGuideBookItem> CREATE_COOK_GUIDE_BOOK_ITEM = SkilletManCoreMod.REGISTRATE.item("create_cook_guide_book", p -> new CreateCookGuideBookItem(p.stacksTo(1).rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<CreateFuelGuideBookItem> CREATE_FUEL_GUIDE_BOOK = SkilletManCoreMod.REGISTRATE.item("create_fuel_guide_book", p -> new CreateFuelGuideBookItem(p.stacksTo(1).rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<EpicFightGuideBookItem> EPIC_FIGHT_GUIDE_BOOK = SkilletManCoreMod.REGISTRATE.item("epic_fight_guide_book", p -> new EpicFightGuideBookItem(p.stacksTo(1).rarity(Rarity.EPIC)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<RumorItem> RUMOR_ITEM = SkilletManCoreMod.REGISTRATE.item("rumor_item", p -> new RumorItem(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<LuckyCat> LUCKY_CAT = SkilletManCoreMod.REGISTRATE.item("lucky_cat", p -> new LuckyCat(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();

    public static final ItemEntry<BadCat> BAD_CAT = SkilletManCoreMod.REGISTRATE.item("bad_cat", p -> new BadCat(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<GuoChaoBoxItem> GUO_CHAO = SkilletManCoreMod.REGISTRATE.item("guo_chao", p -> new GuoChaoBoxItem(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<SimpleDescriptionFoilItem> SUPER_CHEF_PILL = SkilletManCoreMod.REGISTRATE.item("super_chef", p ->
                    new SimpleDescriptionFoilItem(p.food(new FoodProperties.Builder().effect(()-> new MobEffectInstance(SMCEffects.SUPER_CHEF.get(), 1200), 1.0F).build()).rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<SimpleDescriptionFoilItem> CONTRACT = SkilletManCoreMod.REGISTRATE.item("contract", p ->
                    new SimpleDescriptionFoilItem(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();
    public static final ItemEntry<PiShuangItem> PI_SHUANG = SkilletManCoreMod.REGISTRATE.item("pi_shuang", p ->
                    new PiShuangItem(p.rarity(Rarity.RARE)))
            .defaultModel()
            .defaultLang().register();

    //房子图标
    public static final ItemEntry<Item> PLAIN_1 = SkilletManCoreMod.REGISTRATE.item("plain1", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> PLAIN_2 = SkilletManCoreMod.REGISTRATE.item("plain2", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> PLAIN_3 = SkilletManCoreMod.REGISTRATE.item("plain3", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> PLAIN_4 = SkilletManCoreMod.REGISTRATE.item("plain4", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SAVANNA_1 = SkilletManCoreMod.REGISTRATE.item("savanna1", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SAVANNA_2 = SkilletManCoreMod.REGISTRATE.item("savanna2", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SAVANNA_3 = SkilletManCoreMod.REGISTRATE.item("savanna3", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SAVANNA_4 = SkilletManCoreMod.REGISTRATE.item("savanna4", Item::new)
            .defaultModel().register();

    public static final ItemEntry<Item> TAIGA_1 = SkilletManCoreMod.REGISTRATE.item("taiga1", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> TAIGA_2 = SkilletManCoreMod.REGISTRATE.item("taiga2", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> TAIGA_3 = SkilletManCoreMod.REGISTRATE.item("taiga3", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> TAIGA_4 = SkilletManCoreMod.REGISTRATE.item("taiga4", Item::new)
            .defaultModel().register();

    public static final ItemEntry<Item> SNOWY_1 = SkilletManCoreMod.REGISTRATE.item("snowy1", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SNOWY_2 = SkilletManCoreMod.REGISTRATE.item("snowy2", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SNOWY_3 = SkilletManCoreMod.REGISTRATE.item("snowy3", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> SNOWY_4 = SkilletManCoreMod.REGISTRATE.item("snowy4", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> DESERT_1 = SkilletManCoreMod.REGISTRATE.item("desert1", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> DESERT_2 = SkilletManCoreMod.REGISTRATE.item("desert2", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> DESERT_3 = SkilletManCoreMod.REGISTRATE.item("desert3", Item::new)
            .defaultModel().register();
    public static final ItemEntry<Item> DESERT_4 = SkilletManCoreMod.REGISTRATE.item("desert4", Item::new)
            .defaultModel().register();
    public static void register() {
    }

}
