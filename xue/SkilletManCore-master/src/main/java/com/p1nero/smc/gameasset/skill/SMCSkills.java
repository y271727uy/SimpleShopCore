package com.p1nero.smc.gameasset.skill;

import com.p1nero.smc.SkilletManCoreMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

@Mod.EventBusSubscriber(modid = SkilletManCoreMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCSkills {
    public static Skill BETTER_DODGE_DISPLAY;
    public static Skill GOLDEN_SPATULA_INNATE;
    public static Skill GOLDEN_SKILLET_INNATE;
    @SubscribeEvent
    public static void BuildSkills(SkillBuildEvent event){
        SkillBuildEvent.ModRegistryWorker registryWorker = event.createRegistryWorker(SkilletManCoreMod.MOD_ID);
        BETTER_DODGE_DISPLAY = registryWorker.build("better_dodge_display", BetterDodgeDisplaySkill::new, PassiveSkill.createPassiveBuilder());
        GOLDEN_SPATULA_INNATE = registryWorker.build("golden_spatula_innate", GoldenSpatulaInnate::new, WeaponInnateSkill.createWeaponInnateBuilder());
        GOLDEN_SKILLET_INNATE = registryWorker.build("golden_skillet_innate", GoldenSkilletInnate::new, WeaponInnateSkill.createWeaponInnateBuilder());
    }
}
