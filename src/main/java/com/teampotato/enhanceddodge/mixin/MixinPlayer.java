package com.teampotato.enhanceddodge.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {

    protected MixinPlayer(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (this.getTags().contains("enhanced_dodge_tag")) {
            this.getTags().remove("enhanced_dodge_tag");
            Item mainHandItem = this.getMainHandItem().getItem();
            if (mainHandItem instanceof SwordItem || mainHandItem instanceof AxeItem) {
                this.attackStrengthTicker = 114514;
            }
        }
    }
}
