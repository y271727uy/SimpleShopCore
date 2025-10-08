package com.p1nero.smc.item.custom.skillets;

import com.p1nero.smc.SkilletManCoreMod;
import com.p1nero.smc.datagen.SMCAdvancementData;
import com.p1nero.smc.item.custom.SMCCuisineSkilletItem;
import dev.xkmc.cuisinedelight.content.logic.CookingData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoldenSkilletItem extends SMCCuisineSkilletItem {
    protected String star = "⭐";
    protected final int level;
    public GoldenSkilletItem(Block block, Properties properties) {
        this(block, properties, 1);
    }

    public GoldenSkilletItem(Block block, Properties properties, int level) {
        super(block, properties);
        star = star.repeat(level);
        this.level = level;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
        return Tiers.GOLD.getRepairIngredient().test(repair);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack itemStack) {
        ChatFormatting formatting;
        formatting = switch (level) {
            case 2 -> ChatFormatting.GREEN;
            case 3 -> ChatFormatting.AQUA;
            case 4 -> ChatFormatting.DARK_PURPLE;
            case 5 -> ChatFormatting.GOLD;
            default -> ChatFormatting.GRAY;
        };
        if(this.level > 1) {
            return super.getDescription().copy().withStyle(ChatFormatting.YELLOW).append(Component.literal(" " + star).withStyle(formatting));
        }
        return super.getDescription().copy().withStyle(ChatFormatting.YELLOW);
    }

    @Override
    public void onCraftedBy(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Player player) {
        super.onCraftedBy(itemStack, level, player);
        if(itemStack.getItem() instanceof GoldenSkilletItem goldenSkilletItem && goldenSkilletItem.level == 5) {
            if(player instanceof ServerPlayer serverPlayer) {
                SMCAdvancementData.finishAdvancement("first_5star_skillet", serverPlayer);
            }
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        super.use(level, player, hand);
        ItemStack skilletStack = player.getItemInHand(hand);
        if(canUse(skilletStack, player, level)){
            CookingData data = getData(skilletStack);
            if(data != null){
                data.setSpeed(2.0F);
                setData(skilletStack, data);
            }
        }
        return InteractionResultHolder.fail(skilletStack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);
        list.add(SkilletManCoreMod.getInfo("golden_skillet_tip"));
    }

}
