package com.teampotato.enhanceddodge.mixin;

import com.elenai.elenaidodge2.client.animation.DodgeAnimator;
import com.elenai.elenaidodge2.util.DodgeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DodgeHandler.class, remap = false)
public abstract class MixinDodgeHandler {
    @Inject(method = "handleDodge", at = @At(value = "INVOKE", target = "Lcom/elenai/elenaidodge2/networking/ED2Messages;sendToServer(Ljava/lang/Object;)V", shift = At.Shift.AFTER))
    private static void onDodge(DodgeAnimator.DodgeDirection direction, CallbackInfo ci) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) return;
        localPlayer.addTag("enhanced_dodge_tag");
    }
}
