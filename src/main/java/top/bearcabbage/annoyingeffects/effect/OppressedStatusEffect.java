package top.bearcabbage.annoyingeffects.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import top.bearcabbage.annoyingeffects.effecttags.AdaptableStatusEffectTag;
import top.bearcabbage.annoyingeffects.effecttags.UncomfortableStatusEffectTag;

public class OppressedStatusEffect extends StatusEffect implements AdaptableStatusEffectTag, UncomfortableStatusEffectTag {
    public static final float PITCH = 18.0F;
    public OppressedStatusEffect() {
        super(
                StatusEffectCategory.HARMFUL, // 药水效果是有益的还是有害的
                0x98D982); // 显示的颜色
    }

    // 这个方法在每个 tick 都会调用，以检查是否应应用药水效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    // 这个方法在应用药水效果时会被调用，所以我们可以在这里实现自定义功能。
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isPlayer()) return true;
        float pitch = entity.getPitch();
        float min_pitch = Math.min(PITCH * (1+amplifier), 90F);
        entity.setPitch(Math.max(pitch, min_pitch));
        return true;
    }
}
