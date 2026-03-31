package me.lucaslah.nrb;

import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("noreportbutton")
public class NoReportButtonForge {
	public NoReportButtonForge() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onScreenInit(ScreenEvent.Init.Post event) {
		NoReportButtonClientHooks.updatePauseMenu(event.getScreen(), event.getScreen().getMinecraft(), event.getListenersList());
	}
}
