package com.p1nero.smc.block.entity;

import com.p1nero.smc.SkilletManCoreMod;
import com.p1nero.smc.archive.DataManager;
import com.p1nero.smc.block.SMCBlockEntities;
import com.p1nero.smc.block.custom.INpcDialogueBlock;
import com.p1nero.smc.capability.SMCCapabilityProvider;
import com.p1nero.smc.capability.SMCPlayer;
import com.p1nero.smc.client.gui.screen.LinkListStreamDialogueScreenBuilder;
import com.p1nero.smc.effect.SMCEffects;
import com.p1nero.smc.entity.custom.npc.customer.Customer;
import com.p1nero.smc.entity.custom.npc.special.HeShen;
import com.p1nero.smc.entity.custom.npc.special.Thief1;
import com.p1nero.smc.entity.custom.npc.special.Thief2;
import com.p1nero.smc.entity.custom.npc.special.TwoKid;
import com.p1nero.smc.entity.custom.npc.special.virgil.VirgilVillager;
import com.p1nero.smc.entity.custom.npc.start_npc.StartNPC;
import com.p1nero.smc.event.ServerEvents;
import com.p1nero.smc.network.PacketRelay;
import com.p1nero.smc.network.SMCPacketHandler;
import com.p1nero.smc.network.packet.clientbound.AddWaypointPacket;
import com.p1nero.smc.network.packet.clientbound.NPCBlockDialoguePacket;
import com.p1nero.smc.registrate.SMCRegistrateBlocks;
import com.p1nero.smc.util.SMCRaidManager;
import dev.xkmc.cuisinedelight.content.item.CuisineSkilletItem;
import hungteen.htlib.common.world.entity.DummyEntityManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import xaero.hud.minimap.waypoint.WaypointColor;
import yesman.epicfight.api.utils.math.MathUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MainCookBlockEntity extends BlockEntity implements INpcDialogueBlock {
    @Nullable
    private StartNPC startNPC;
    private boolean isWorking;
    private boolean npcSummoned;
    public static final int WORKING_RADIUS = 8;
    private final List<Customer> customers = new ArrayList<>();
    public static final List<VillagerProfession> PROFESSION_LIST = ForgeRegistries.VILLAGER_PROFESSIONS.getValues().stream().toList();
    public boolean firstCustomerSummoned = false;//摆锅马上就有客户
    public static final int SEARCH_DIS = 5;

    public MainCookBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(SMCBlockEntities.MAIN_COOK_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public @Nullable StartNPC getStartNPC() {
        return startNPC;
    }

    public void setStartNPC(@Nullable StartNPC startNPC) {
        this.startNPC = startNPC;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        if(level.isClientSide) {
            return;
        }
        if (t instanceof MainCookBlockEntity mainCookBlockEntity) {
            if (mainCookBlockEntity.startNPC == null) {
                //优先附近找
                int offset = SEARCH_DIS;
                StartNPC startNPC = level.getNearestEntity(StartNPC.class, TargetingConditions.DEFAULT, null, pos.getX(), pos.getY(), pos.getZ(), new AABB(pos.offset(offset, offset, offset), pos.offset(-offset, -offset, -offset)));
                if (startNPC != null) {
                    mainCookBlockEntity.startNPC = startNPC;
                } else if (!mainCookBlockEntity.npcSummoned){
                    mainCookBlockEntity.npcSummoned = true;
                    StartNPC startNPC1 = new StartNPC(((ServerLevel) level), pos.above(3));
                    //互相通知
                    mainCookBlockEntity.startNPC = startNPC1;
                    startNPC1.setHomePos(pos);
                    startNPC1.setVillagerData(startNPC1.getVillagerData().setType(VillagerType.byBiome(startNPC1.level().getBiome(pos))));
                    level.addFreshEntity(startNPC1);
                }
            }
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.putBoolean("isWorking", isWorking);
        compoundTag.putBoolean("npcSummoned", npcSummoned);
    }

    @Override
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        isWorking = compoundTag.getBoolean("isWorking");
        npcSummoned = compoundTag.getBoolean("npcSummoned");
    }

    public void updateWorkingState(ServerPlayer serverPlayer) {
        if (this.isWorking) {
            SMCPlayer.updateWorkingState(true, serverPlayer);
        } else {
            firstCustomerSummoned = false;
            SMCPlayer.updateWorkingState(false, serverPlayer);
            this.clearCustomers();
        }
    }

    public BlockPos getSkilletPos(){
        return this.getBlockPos().above(2);
    }

    public BlockState getSkilletBlock() {
        if(level == null){
            return null;
        }
        BlockState blockState = level.getBlockState(this.getSkilletPos());
        return blockState.getBlock().asItem() instanceof CuisineSkilletItem ? blockState : null;
    }

    public boolean hasSkillet() {
        return this.getSkilletBlock() != null;
    }

    public void workingTick(ServerPlayer owner){
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(owner);
        if(!hasSkillet() || !isWorkingTime()){
            isWorking = false;
            return;
        }
        if(owner.hasEffect(SMCEffects.RUMOR.get())) {
            isWorking = false;
            owner.displayClientMessage(SkilletManCoreMod.getInfo("rumor_no_work"), false);
            if(hasSkillet()){
                owner.level().destroyBlock(this.getSkilletPos(), true);
            }
            return;
        }
        int dayTime = this.getDayTime();
        //第一天后开始生成随机事件
        if(level != null && dayTime > 0 && dayTime % 2 == 0 && this.hasSkillet() && this.startNPC != null && DataManager.hasAnySpecialEvent(owner) && !DataManager.specialSolvedToday.get(owner)){
            if(this.getBlockPos().getCenter().distanceTo(owner.position()) > WORKING_RADIUS){
                Vec3 targetPos = this.startNPC.getSpawnPos().getCenter();
                owner.teleportTo(targetPos.x, targetPos.y, targetPos.z);
            }
            level.destroyBlock(this.getSkilletPos(), true);
            isWorking = false;
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("special_event", true);
            PacketRelay.sendToPlayer(SMCPacketHandler.INSTANCE, new NPCBlockDialoguePacket(this.getBlockPos(), tag), owner);
            owner.serverLevel().playSound(null, owner.getX(), owner.getY(), owner.getZ(), SoundEvents.FIRE_EXTINGUISH, owner.getSoundSource(), 1.0F, 1.0F);
            if (!DataManager.inSpecial.get(owner)) {
                if(summonSpecial(owner, this.getBlockPos(), 10, 12)) {
                    DataManager.inSpecial.put(owner, true);
                } else {
                    DataManager.specialSolvedToday.put(owner, true);
                }
            }
            return;
        }

        //抓回来上班
        if(this.getBlockPos().getCenter().distanceTo(owner.position()) > WORKING_RADIUS && !this.canPlayerLeave(owner) && this.startNPC != null && !DataManager.inSpecial.get(owner)) {
            Vec3 targetPos = startNPC.getSpawnPos().getCenter();
            owner.teleportTo(targetPos.x, targetPos.y, targetPos.z);
            owner.playSound(SoundEvents.VILLAGER_NO);
            CompoundTag tag = new CompoundTag();
            tag.putBoolean("is_catching_escaping_player", true);
            PacketRelay.sendToPlayer(SMCPacketHandler.INSTANCE, new NPCBlockDialoguePacket(this.getBlockPos(), tag), owner);
            owner.serverLevel().playSound(null, owner.getX(), owner.getY(), owner.getZ(), SoundEvents.VILLAGER_NO, owner.getSoundSource(), 1.0F, 1.0F);
        }

        this.customers.removeIf(customer -> customer == null || customer.isRemoved() || !customer.isAlive());
        if(owner.tickCount % (1200 - smcPlayer.getStage() * 100) == 0 || !firstCustomerSummoned){
            summonCustomer(owner, 6);
        }

    }

    public void summonCustomer(ServerPlayer owner, int sizeLimit){
        //生成顾客，30s一只，最多6只
        if(this.customers.size() < sizeLimit) {
            firstCustomerSummoned = true;
            BlockPos spawnPos = getRandomPos(owner, 15, 20, this.getBlockPos());
            Customer customer = new Customer(owner, spawnPos.getCenter());
            customer.setHomePos(this.getBlockPos());
            customer.setSpawnPos(spawnPos);
            customer.getNavigation().moveTo(customer.getNavigation().createPath(this.getBlockPos(), 3), 1.0);
            VillagerProfession profession = PROFESSION_LIST.get(customer.getRandom().nextInt(PROFESSION_LIST.size()));//随机抽个职业，换皮肤好看
            customer.setVillagerData(customer.getVillagerData().setType(VillagerType.byBiome(owner.serverLevel().getBiome(this.getBlockPos()))).setProfession(profession));
            this.customers.add(customer);
            owner.serverLevel().addFreshEntity(customer);
        }
    }

    public static BlockPos getRandomPos(ServerPlayer owner, int min, int max, BlockPos centerPos){
        double centerX = centerPos.getX() + 0.5;
        double centerZ = centerPos.getZ() + 0.5;

        double angle = Math.random() * 2 * Math.PI;
        double radius = owner.getRandom().nextInt(min, max);

        double spawnX = centerX + Math.cos(angle) * radius;
        double spawnZ = centerZ + Math.sin(angle) * radius;

        return ServerEvents.getSurfaceBlockPos(owner.serverLevel(), (int) spawnX, (int) spawnZ);
    }

    /**
     * 生成特殊事件npc
     */
    public static boolean summonSpecial(ServerPlayer owner, BlockPos center, int minDis, int maxDis){
        BlockPos randomSpawnPos = getRandomPos(owner, minDis, maxDis, center).above(2);
        BlockPos randomHomePos = getRandomPos(owner, minDis, maxDis, center).above(2);
        if(!DataManager.specialEvent1Solved.get(owner)) {
            HeShen heShen = new HeShen(owner, randomHomePos.getCenter());
            heShen.setSpawnPos(randomSpawnPos);
            heShen.setHomePos(randomHomePos);
            heShen.setOwner(owner);
            return owner.serverLevel().addFreshEntity(heShen);
        } else if(!DataManager.specialEvent2Solved.get(owner)){
            TwoKid twoKid1 = new TwoKid(owner, randomHomePos.getCenter());
            twoKid1.setSpawnPos(randomSpawnPos);
            twoKid1.setHomePos(randomHomePos);
            TwoKid twoKid2 = new TwoKid(owner, randomHomePos.getCenter().offsetRandom(twoKid1.getRandom(), 2));
            twoKid2.setSpawnPos(randomSpawnPos);
            twoKid2.setHomePos(randomHomePos);
            twoKid1.setOwner(owner);
            twoKid2.setOwner(owner);
            boolean flag = owner.serverLevel().addFreshEntity(twoKid1);
            return flag && owner.serverLevel().addFreshEntity(twoKid2);
        } else if(!DataManager.specialEvent3Solved.get(owner)) {
            Thief1 thief1 = new Thief1(owner, randomHomePos.getCenter());
            thief1.setSpawnPos(randomSpawnPos);
            thief1.setHomePos(randomHomePos);
            Thief2 thief2 = new Thief2(owner, randomHomePos.getCenter().offsetRandom(thief1.getRandom(), 2));
            thief2.setSpawnPos(randomSpawnPos);
            thief2.setHomePos(randomHomePos);
            thief1.setOwner(owner);
            thief2.setOwner(owner);
            thief2.setThief1(thief1);
            thief1.setThief2(thief2);
            boolean flag = owner.serverLevel().addFreshEntity(thief1);
            return flag && owner.serverLevel().addFreshEntity(thief2);
        } else if(!DataManager.specialEvent4Solved.get(owner)){
            //V哥算敌对，和平生不了
            if(owner.getServer() != null && owner.getServer().getWorldData().getDifficulty() == Difficulty.PEACEFUL) {
                return false;
            }
            double yRot = MathUtils.getYRotOfVector(randomSpawnPos.getCenter().subtract(owner.position()));
            Direction direction = Direction.fromYRot(yRot);
            BlockState blockState = SMCRegistrateBlocks.CHAIR.getDefaultState().setValue(BlockStateProperties.HORIZONTAL_FACING, direction.getOpposite());//不知道为嘛反了
            owner.serverLevel().setBlockAndUpdate(randomSpawnPos, blockState);
            VirgilVillager villager = new VirgilVillager(owner, randomSpawnPos.above(1).getCenter());
            float dir = direction.toYRot();
            villager.setYRot(dir);
            villager.setYBodyRot(dir);
            villager.setYHeadRot(dir);
            return owner.serverLevel().addFreshEntity(villager);
        }
        return false;
    }

    public void clearCustomers(){
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            iterator.next().setTraded(true);//遣散
            iterator.remove();
        }
    }

    public void summonRandomRaidFor(ServerPlayer serverPlayer){
        clearCustomers();
        SMCRaidManager.startRandomRaid(serverPlayer);
        if(hasSkillet()){
            serverPlayer.serverLevel().destroyBlock(getSkilletPos(), true);
        }
    }

    /**
     * 点击顶上的炉子的时候
     * 对话，发起进阶挑战以继续迎客
     */
    public void onClickStove(ServerPlayer serverPlayer, BlockPos below, Direction face, InteractionHand hand) {
        SMCPlayer smcPlayer = SMCCapabilityProvider.getSMCPlayer(serverPlayer);
        int stage = smcPlayer.getStage();

    }

    public void onNPCFinishTrade(ServerPlayer serverPlayer, Customer customer, int result){
        this.customers.remove(customer);
        int stage = SMCCapabilityProvider.getSMCPlayer(serverPlayer).getStage();
        summonCustomer(serverPlayer, stage + 1);
    }

    public void onSkilletPlace(ServerPlayer serverPlayer) {
        if(this.startNPC == null) {
            return;
        }
        if(!this.startNPC.isOwner(serverPlayer)) {
            SMCPlayer.updateWorkingState(false, serverPlayer);
            this.clearCustomers();
            serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("already_has_owner"), true);
            return;
        }
//        if(!isWorkingTime()){
//            CompoundTag tag = new CompoundTag();
//            tag.putBoolean("pre_cook_tip", true);
//            PacketRelay.sendToPlayer(SMCPacketHandler.INSTANCE, new NPCBlockDialoguePacket(this.getBlockPos(), tag), serverPlayer);
//        }
        this.isWorking = true;
    }

    public boolean tryBreakSkillet(ServerPlayer serverPlayer) {
        if(this.startNPC == null) {
            return true;
        }
        if(!this.startNPC.isOwner(serverPlayer)) {
            SMCPlayer.updateWorkingState(false, serverPlayer);
            this.clearCustomers();
            serverPlayer.displayClientMessage(SkilletManCoreMod.getInfo("already_has_owner"), true);
            return false;
        }
        this.isWorking = false;
        this.updateWorkingState(serverPlayer);
        return true;
    }

    public int getDayTime(){
        if(level == null){
            return 0;
        }
        return (int) (level.getDayTime() / 24000);
    }

    /**
     * 判断是否开始营业
     * 有袭击暂停营业
     */
    public boolean isWorkingTime() {
        if (this.level == null) {
            return false;
        }
        if(this.level instanceof ServerLevel serverLevel && !DummyEntityManager.getDummyEntities(serverLevel).isEmpty() && this.isWorking) {
            for(ServerPlayer player : serverLevel.players()) {
                player.displayClientMessage(SkilletManCoreMod.getInfo("raid_no_work"), true);
                if(hasSkillet()){
                    level.destroyBlock(this.getSkilletPos(), true);
                }
            }
            return false;
        }
        return level.isDay();
    }

    public boolean canPlayerLeave(ServerPlayer serverPlayer) {
        //TODO 在试炼则可以离开？
        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void openDialogueScreen(CompoundTag senderData) {
        LinkListStreamDialogueScreenBuilder builder = new LinkListStreamDialogueScreenBuilder(null, ModBlocks.STOVE.get().getName().withStyle(ChatFormatting.AQUA));

        if(senderData.getBoolean("is_catching_escaping_player")) {
            builder.start(SkilletManCoreMod.getInfo("cannot_left_customers"))
                    .addFinalChoice(SkilletManCoreMod.getInfo("alr"), (byte) 0)
                    .addFinalChoice(SkilletManCoreMod.getInfo("god_stove_talk"), (byte) 0);
        }

        if(senderData.getBoolean("is_first_food_bad")) {
            //第一次炒糊了
            builder.start(SkilletManCoreMod.getInfo("first_food_bad"))
                    .addFinalChoice(SkilletManCoreMod.getInfo("sorry"), (byte) 0)
                    .addFinalChoice(SkilletManCoreMod.getInfo("give_me_another_chance"), (byte) 0);
        }

        if(senderData.getBoolean("special_event")){
            builder.start(SkilletManCoreMod.getInfo("special_event_ans"))
                    .addFinalChoice(SkilletManCoreMod.getInfo("special_event_opt1"), (byte) 0)
                    .addFinalChoice(SkilletManCoreMod.getInfo("special_event_opt2"), (byte) 0);
        }

        if(senderData.getBoolean("pre_cook_tip")){
            builder.start(SkilletManCoreMod.getInfo("no_pre_cook_here"))
                    .addFinalChoice(SkilletManCoreMod.getInfo("special_event_opt1"), (byte) 0)
                    .addFinalChoice(SkilletManCoreMod.getInfo("special_event_opt2"), (byte) 0);
        }

        if(!builder.isEmpty()){
            Minecraft.getInstance().setScreen(builder.build());
        }
    }

    @Override
    public void handleNpcInteraction(Player player, byte interactionID) {

    }
}
