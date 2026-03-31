package me.lucaslah.nrb;

import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.fml.common.Mod;

@Mod("noreportbutton")
public class NoReportButtonNeoForge {
	public NoReportButtonNeoForge() {
		NeoForge.EVENT_BUS.addListener(NoReportButtonNeoForge::onScreenInit);
	}

	private static void onScreenInit(ScreenEvent.Init.Post event) {
		NoReportButtonClientHooks.updatePauseMenu(event.getScreen(), event.getScreen().getMinecraft(), event.getListenersList());
	}
}
