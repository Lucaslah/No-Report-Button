package me.lucaslah.ncrb.mixin;

import me.lucaslah.ncrb.OpenToLanButton;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.GridWidget;
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
		final List<ClickableWidget> widgets = Screens.getButtons(this);
		List<? extends Element> buttons = null;

		for (ClickableWidget clickableWidget : widgets) {
			if (clickableWidget instanceof GridWidget widget) {
				List<? extends Element> children = widget.children();

				if (children != null) {
					buttons = children;
				}
			}
		}

		boolean reportButtonFound = false;

		if (buttons != null) {
			for (Element element : buttons) {
				if (element instanceof ClickableWidget button) {
					if (button.getMessage().getString().equals(I18n.translate("menu.playerReporting"))) {
						button.visible = false;
						button.active = false;
						reportButtonFound = true;
					}
				}
			}
		}

		if (reportButtonFound) {
			OpenToLanButton openToLanButton = new OpenToLanButton(this,this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"));

			if (this.client != null) {
				openToLanButton.active = this.client.isIntegratedServerRunning() && !Objects.requireNonNull(this.client.getServer()).isRemote();
			}

			widgets.add(openToLanButton);
		}
	}
}
