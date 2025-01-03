package top.bearcabbage.annoyingeffects.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.bearcabbage.annoyingeffects.AnnoyingEffects;

import java.util.Objects;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(
            method = {"tick"},
            at = {@At("RETURN")}
    )
    private void injectMouseTickForSpinAndOppressed(CallbackInfo ci){
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player == null || !player.isAlive()) return;
        double deltaYaw = 0F;
        double deltaPitch = 0F;
        if(player.hasStatusEffect(AnnoyingEffects.SPIN)){
            int amplifier = Objects.requireNonNull(player.getStatusEffect(AnnoyingEffects.SPIN)).getAmplifier();
//            float yaw = player.getYaw();
            deltaYaw += -2F * (1 + amplifier);
//            player.setYaw(yaw);
        }
        if(player.hasStatusEffect(AnnoyingEffects.OPPRESSED)){
//            int amplifier = Objects.requireNonNull(player.getStatusEffect(AnnoyingEffects.OPPRESSED)).getAmplifier();
            float pitch = player.getPitch();
            if(pitch < 0F) deltaPitch = -pitch;
            if(pitch < 45F) deltaPitch += 0.2F * (45F - pitch);
        }
        player.changeLookDirection(deltaYaw / 0.15F, deltaPitch / 0.15F);

    }
}