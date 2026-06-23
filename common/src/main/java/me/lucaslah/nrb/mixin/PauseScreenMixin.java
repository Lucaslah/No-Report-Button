package me.lucaslah.nrb.mixin;

import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin {
    @Redirect(
        method = "createPauseMenu",
        slice = @Slice(
            from = @At(
                value = "FIELD",
                target = "Lnet/minecraft/client/gui/screens/PauseScreen;PLAYER_REPORTING:Lnet/minecraft/network/chat/Component;"
            )
        ),
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/layouts/LinearLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;"
        )
    )
    private LayoutElement nrb$skipPlayerReportingIcon(LinearLayout layout, LayoutElement element) {
        return element;
    }
}