package com.p1nero.smc.item.custom.skillets;

import com.p1nero.smc.item.custom.SMCCuisineSkilletItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class SkilletV2 extends SMCCuisineSkilletItem {
    public SkilletV2(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack itemStack) {
        return super.getDescription().copy().append(Component.literal(" ⭐⭐").withStyle(ChatFormatting.GREEN));
    }
}
