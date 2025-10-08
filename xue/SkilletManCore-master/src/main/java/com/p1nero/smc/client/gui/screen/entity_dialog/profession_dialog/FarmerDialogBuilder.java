package com.p1nero.smc.client.gui.screen.entity_dialog.profession_dialog;

import com.p1nero.smc.capability.SMCCapabilityProvider;
import com.p1nero.smc.capability.SMCPlayer;
import com.p1nero.smc.client.gui.TreeNode;
import com.p1nero.smc.client.gui.screen.LinkListStreamDialogueScreenBuilder;
import com.p1nero.smc.client.gui.screen.entity_dialog.VillagerDialogScreenHandler;
import com.p1nero.smc.datagen.lang.SMCLangGenerator;
import com.p1nero.smc.entity.custom.npc.start_npc.StartNPC;
import com.p1nero.smc.util.ItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FarmerDialogBuilder extends VillagerDialogScreenHandler.VillagerDialogBuilder {
    public FarmerDialogBuilder() {
        super(VillagerProfession.FARMER);
    }

    @Override
    public void handle(ServerPlayer serverPlayer, Villager villager, byte interactionID) {
        super.handle(serverPlayer, villager, interactionID);
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(serverPlayer);
        switch (interactionID) {
            case 3 -> ItemUtil.tryAddRandomItem(serverPlayer, StartNPC.STAPLE_SET, (int) (500 * smcPlayer.getLevelMoneyRate()), 100);
            case 4 -> ItemUtil.tryAddRandomItem(serverPlayer, StartNPC.VEG_SET, (int) (500 * smcPlayer.getLevelMoneyRate()), 100);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void createDialog(LinkListStreamDialogueScreenBuilder builder, Villager self) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if(localPlayer == null) {
            return;
        }
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(localPlayer);
        TreeNode root = new TreeNode(answer(0));
        TreeNode buy = new TreeNode(answer(1), choice(0));
        buy.addChild(new TreeNode(answer(2), choice(2))
                        .addChild(buy))
                .addLeaf(choice(3, 500 * smcPlayer.getLevelMoneyRate()), (byte) 3)
                .addLeaf(choice(4, 500 * smcPlayer.getLevelMoneyRate()), (byte) 4)
                .addLeaf(choice(1));
        root.addChild(buy);
        root.addLeaf(choice(1));

        builder.setAnswerRoot(root);
    }

    @Override
    public void onGenerateLang(SMCLangGenerator generator) {
        generator.addVillagerName(this.profession, " §e勤恳的农民§r ");
        generator.addVillagerAns(this.profession, 0, "锄禾日当午，汗滴禾下土，谁知我农民的苦。（虽然它此刻也许并没有在劳作，  因为作者懒得读取村民工作状态，那成本可太高了，我们是《平底锅侠》，并不是MCA）");
        generator.addVillagerOpt(this.profession, 0, "尝试购买");
        generator.addVillagerOpt(this.profession, 1, "离开");
        generator.addVillagerAns(this.profession, 1, "店里直接订购已经不能满足你了吗，少侠要来些什么？");
        generator.addVillagerOpt(this.profession, 2, "怎么还是大礼包？");
        generator.addVillagerAns(this.profession, 2, "是这样的，本整合包无处不在的抽卡系统。但是我这儿能抽到的果蔬的种类比订购到的多多了！");
        generator.addVillagerOpt(this.profession, 3, "主食超级大礼包 %d 绿宝石");
        generator.addVillagerOpt(this.profession, 4, "果蔬超级大礼包 %d 绿宝石");
    }


}
