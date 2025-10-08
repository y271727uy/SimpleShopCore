package com.p1nero.smc.entity.custom.npc.customer.customer_data;

import com.p1nero.smc.SkilletManCoreMod;
import com.p1nero.smc.capability.SMCCapabilityProvider;
import com.p1nero.smc.capability.SMCPlayer;
import com.p1nero.smc.client.gui.DialogueComponentBuilder;
import com.p1nero.smc.client.gui.TreeNode;
import com.p1nero.smc.client.gui.screen.LinkListStreamDialogueScreenBuilder;
import com.p1nero.smc.client.sound.SMCSounds;
import com.p1nero.smc.datagen.lang.SMCLangGenerator;
import com.p1nero.smc.entity.custom.npc.customer.Customer;
import dev.xkmc.cuisinedelight.content.item.BaseFoodItem;
import dev.xkmc.cuisinedelight.content.logic.CookedFoodData;
import dev.xkmc.cuisinedelight.content.logic.FoodType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class NormalCustomerData extends Customer.CustomerData {
    protected int smcId;
    protected final String answerPre = "normal_customer_answer_";
    protected final String choicePre = "normal_customer_choice_";

    protected final String nameTranslationKey;

    public NormalCustomerData(int smcId){
        this.smcId = smcId;
        this.nameTranslationKey = "normal_customer_" + smcId;
    }

    @Override
    public void onInteract(ServerPlayer player, Customer self) {

    }

    @Override
    public void onGatherServerData(ServerPlayer player, Customer self) {

    }

    @Override
    public void generateTranslation(SMCLangGenerator generator) {
    }

    @Override
    public Component getTranslation() {
        return Component.translatable(this.nameTranslationKey);
    }

    protected String answerPre(int id) {
        return answerPre + smcId + "_" + id;
    }

    protected String choicePre(int id) {
        return choicePre + smcId + "_" + id;
    }

    protected Component answer(int id, Object... objects) {
        Component component = Component.translatable(answerPre(id), objects);
        return Component.literal("\n").append(component);
    }

    protected Component choice(int id, Object... objects) {
        return Component.translatable(choicePre(id), objects);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void getDialogScreen(CompoundTag serverData, LinkListStreamDialogueScreenBuilder screenBuilder, DialogueComponentBuilder dialogueComponentBuilder, boolean canSubmit, int foodLevel) {
        TreeNode root;

        String foodName = "§6" + I18n.get(serverData.getString("food_name")) + "§r";

        if (!canSubmit) {
            root = new TreeNode(answer(0, foodName))
                    .addChild(new TreeNode(answer(-1), choice(0))
                            .addLeaf(choice(-1), (byte) -3))
                    .addLeaf(choice(-2), (byte) -3);
        } else {
            root = switch (foodLevel) {
                case BEST -> new TreeNode(answer(1), choice(0))
                                .addExecutable(BEST)
                                .addLeaf(choice(1));
                case MIDDLE -> new TreeNode(answer(2), choice(0))
                                .addExecutable(MIDDLE)
                                .addLeaf(choice(2));
                default -> new TreeNode(answer(0, foodName))
                        .addChild(new TreeNode(answer(3), choice(0))
                                .addExecutable(BAD)
                                .addLeaf(choice(3)));
            };
        }
        screenBuilder.setAnswerRoot(root);
    }

    @Override
    public void handle(ServerPlayer serverPlayer, Customer self, byte interactId) {
        switch (interactId) {
            case BEST:
                onBest(serverPlayer, self);
                break;
            case MIDDLE:
                onMiddle(serverPlayer, self);
                break;
            case BAD:
                onBad(serverPlayer, self);
            default: self.displayRecipeInfo(serverPlayer);
        }
    }

    protected void onBest(ServerPlayer serverPlayer, Customer self){
        CookedFoodData cookedFoodData = BaseFoodItem.getData(self.getOrder());
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(serverPlayer);
        float mul = 1.0F + smcPlayer.getLevel();
        serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("level_mul", smcPlayer.getLevel() + 1), false);
        if(cookedFoodData != null) {
            mul *= cookedFoodData.types.size();
            serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("type_mul", cookedFoodData.types.size()), false);
            if(cookedFoodData.types.contains(FoodType.MEAT)) {
                mul += 2.0F;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("meat_mul", 2.0F), false);
            }
            if(cookedFoodData.types.contains(FoodType.SEAFOOD)) {
                mul += 5.0F;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("seafood_mul", 5.0F), false);
            }
            if(cookedFoodData.size > 0){
                mul *= cookedFoodData.size;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("size_mul", cookedFoodData.size), false);
            }
            if(cookedFoodData.entries.stream().anyMatch(entry -> {
                ItemStack itemStack = entry.stack();
                return itemStack.is(Items.HONEY_BOTTLE) && itemStack.hasTag() && itemStack.getOrCreateTag().getBoolean(SkilletManCoreMod.MUL);
            })){
                mul *= 2;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("honey_mul",2.0F), false);
            }
        }
        SMCPlayer.addMoney((int) (20 * mul), serverPlayer);
        serverPlayer.serverLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SMCSounds.VILLAGER_YES.get(), serverPlayer.getSoundSource(), 1.0F, 1.0F);
        self.level().broadcastEntityEvent(self, (byte)14);//播放开心的粒子
    }


    protected void onMiddle(ServerPlayer serverPlayer, Customer self){
        CookedFoodData cookedFoodData = BaseFoodItem.getData(self.getOrder());
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(serverPlayer);
        float mul = 1.0F + (smcPlayer.getLevel() / 2.0F);
        serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("level_mul", (smcPlayer.getLevel() + 1) / 2.0F), false);
        if (cookedFoodData != null) {
            mul += cookedFoodData.types.size();
            serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("type_mul", cookedFoodData.types.size()), false);
            if (cookedFoodData.types.contains(FoodType.MEAT)) {
                mul += 0.4F;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("meat_mul", 0.4F), false);
            }
            if (cookedFoodData.types.contains(FoodType.SEAFOOD)) {
                mul += 0.8F;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("seafood_mul", 0.8F), false);
            }
            if(cookedFoodData.size > 0){
                mul *= (cookedFoodData.size / 2.0F);
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("size_mul", cookedFoodData.size / 2), false);
            }
            if(cookedFoodData.entries.stream().anyMatch(entry -> {
                ItemStack itemStack = entry.stack();
                return itemStack.is(Items.HONEY_BOTTLE) && itemStack.hasTag() && itemStack.getOrCreateTag().getBoolean(SkilletManCoreMod.MUL);
            })){
                mul *= 2;
                serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("honey_mul",2.0F), false);
            }
        }
        SMCPlayer.addMoney((int) (10 * mul), serverPlayer);
        serverPlayer.serverLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.VILLAGER_TRADE, serverPlayer.getSoundSource(), 1.0F, 1.0F);
    }


    protected void onBad(ServerPlayer serverPlayer, Customer self){
        SMCPlayer.consumeMoney(200, serverPlayer);
        serverPlayer.serverLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.VILLAGER_NO, serverPlayer.getSoundSource(), 1.0F, 1.0F);
        self.setUnhappyCounter(40);
    }
}
