package com.p1nero.smc.entity.custom.npc.customer.customer_data.special;

import com.p1nero.smc.client.gui.DialogueComponentBuilder;
import com.p1nero.smc.client.gui.TreeNode;
import com.p1nero.smc.datagen.lang.SMCLangGenerator;
import com.p1nero.smc.entity.custom.npc.customer.Customer;
import com.p1nero.smc.entity.custom.npc.customer.customer_data.SpecialCustomerData;
import com.p1nero.smc.item.SMCItems;
import com.p1nero.smc.registrate.SMCRegistrateItems;
import com.p1nero.smc.util.BookManager;
import com.p1nero.smc.util.ItemUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SpecialCustomerData12 extends SpecialCustomerData {

    public SpecialCustomerData12() {
        super(12);
    }

    public void generateTranslation(SMCLangGenerator generator) {// 健忘村民
        generator.add(nameTranslationKey, "健忘的村民");
        generator.add(choicePre(-3), "马上做！");
        generator.add(answerPre(-2), "（挠头）这个...是不是...（突然拍脑袋）啊！又忘了！反正肯定不是我要吃的！麻烦重做一份~");
        generator.add(choicePre(-2), "重新准备");

        generator.add(answerPre(-1), "（眼前这个村民欲言又止，似乎脑子不太好使）");
        generator.add(choicePre(-1), "客官要点啥？");

        generator.add(answerPre(0), "有没有...（看纸条） %s ？（突然又忘）啊对，就是这个...");
        generator.add(choicePre(0), "提交");

        generator.add(answerPre(1), "（突然眼睛一亮）哦！美味！对了！我要送你什么来着...但是我忘了...不管了，再见！ （此时你发现他的小本本留在了店里，你迫不及待地想捡起来看）");
        generator.add(choicePre(1), "告辞！（快走！我要偷窥小本本！）");

        generator.add(answerPre(2), "好像...差点意思...（看纸条）啊对，差点意思...这菜烧糊了可不好吃");
        generator.add(choicePre(2), "下次一定改进！");

        generator.add(answerPre(3), "（惊慌）啊！这是什么？！（突然又忘）啊对，这是...这么难吃真的是我要的吗");
        generator.add(choicePre(3), "（无奈）下次一定！");
    }

    @Override
    protected void onBest(ServerPlayer serverPlayer, Customer self) {
        super.onBest(serverPlayer, self);
        ItemUtil.addItem(serverPlayer, new ItemStack(SMCRegistrateItems.ARMOR_RAFFLE_TICKET.get(), 5), true);


        ItemStack book = BookManager.getDefaultTextBook(1, "健忘的村民", "记事本"
                , "早上去找铁匠铺磨刀 ：上周砍树时刀卷了，铁匠铺在村子东边？还是西边来着......（此处有地图涂鸦，但画得像个烤肠）"
                , "早上去还图书给村民 A ：从他那儿借了本《如何不迷路》"
                , "中午去收集野花给村民 B ：她说想要红、黄、蓝三种颜色的花，我得去花丛那（花丛在村子北边？还是南边......），等等，我是不是已经采过了。"
                , "中午去面包房买面包 需要 2 个"
                , "傍晚去去农田帮村民 C 收麦子 收完麦子还要帮着堆草垛"
                , "傍晚去村子中央广场集合 ：村长说有要事宣布。"
                , "傍晚去河边钓鱼 ：带好鱼竿（鱼竿在床底下？还是院外......），顺便洗衣服。"
                , "晚上回家做饭 ：看看菜园里有啥能吃的（昨天菜园里长着草和几株野花，我摘了野花煮汤，拉肚子了）。");
        ItemUtil.addItem(serverPlayer, book);

    }

    @Override
    protected void onBad(ServerPlayer serverPlayer, Customer self) {
        super.onBad(serverPlayer, self);
        serverPlayer.hurt(serverPlayer.damageSources().magic(), 0.5F);
    }

}
