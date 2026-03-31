package me.lucaslah.nrb;

import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("noreportbutton")
public class NoReportButtonForge {
	public NoReportButtonForge() {
		ScreenEvent.Init.Post.BUS.addListener(NoReportButtonForge::onScreenInit);
	}

	private static void onScreenInit(ScreenEvent.Init.Post event) {
		NoReportButtonClientHooks.updatePauseMenu(event.getScreen(), event.getScreen().getMinecraft(), event.getListenersList());
	}
}
