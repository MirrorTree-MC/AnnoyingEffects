package top.bearcabbage.annoyingeffects.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.bearcabbage.annoyingeffects.AnnoyingEffects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    /**
     * {@code @Author} Mnky
     * {@code @reason} If the player has the crawler effect, stop them from standing up.
     */
    @Overwrite
    public boolean canChangeIntoPose(EntityPose pose) {
        if(this.hasStatusEffect(AnnoyingEffects.CRAWLER)) return false;
        return this.getWorld().isSpaceEmpty(this, this.getDimensions(pose).getBoxAt(this.getPos()).contract(1.0E-7));
    }

}