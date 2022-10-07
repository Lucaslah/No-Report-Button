package me.lucaslah.ncrb.mixin;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

	protected GameMenuScreenMixin(Text title) {
		super(title);
	}

	@Inject(method = "initWidgets()V", at = @At(value = "RETURN"))
	public void initWidgets(CallbackInfo ci) {
		final List<ClickableWidget> buttons = Screens.getButtons(this);

		ClickableWidget reportButton = null;
		boolean reportButtonFound = false;

		for (ClickableWidget button : buttons) {
			if (button.getMessage().getString().equals(I18n.translate("menu.playerReporting"))) {
				button.setWidth(-9999);
				button.active = false;
				reportButton = button;
				reportButtonFound = true;
			}
		}

		if (reportButtonFound) {
			ButtonWidget openToLanButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"), (button) -> {
				assert this.client != null;
				this.client.setScreen(new OpenToLanScreen(this));
			}));

			assert this.client != null;
			openToLanButton.active = this.client.isIntegratedServerRunning() && !Objects.requireNonNull(this.client.getServer()).isRemote();
		}
	}
}
