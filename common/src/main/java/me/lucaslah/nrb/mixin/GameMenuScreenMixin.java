package me.lucaslah.nrb.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Widget;
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
		final List<Drawable> drawables = ((ScreenAccessor) this).getDrawables();

		for (Drawable drawable : drawables) {
			if (drawable instanceof ClickableWidget widget) {
				if (widget.getMessage().getString().equals(I18n.translate("menu.playerReporting"))) {
					if (this.client != null) {
						widget.active = this.client.isIntegratedServerRunning() && !Objects.requireNonNull(this.client.getServer()).isRemote();
					}

					widget.setMessage(Text.translatable("menu.shareToLan"));
				}
			}
		}
	}
}
