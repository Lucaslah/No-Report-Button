package me.lucaslah.ncrb.mixin;

import me.lucaslah.ncrb.IScreen;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

	protected GameMenuScreenMixin(Text title) {
		super(title);
	}

	@Inject(method = "initWidgets()V", at = @At(value = "RETURN"))
	public void initWidgets(CallbackInfo ci) {
		ClickableWidget reportButton = null;
		boolean reportButtonFound = false;

		for (Drawable drawable : ((IScreen) this).getButtons()) {
			if (!(drawable instanceof ClickableWidget)) continue;

			ClickableWidget button = (ClickableWidget) drawable;

			if (!button.getMessage().getString().equals(I18n.translate("menu.playerReporting"))) continue;

			reportButton = button;
			reportButtonFound = true;
			break;
		}

		if (reportButtonFound) {
			reportButton.visible = false;

			ButtonWidget openToLanButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"), (button) -> {
				this.client.setScreen(new OpenToLanScreen(this));
			}));

			openToLanButton.active = this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote();
		}
	}
}
