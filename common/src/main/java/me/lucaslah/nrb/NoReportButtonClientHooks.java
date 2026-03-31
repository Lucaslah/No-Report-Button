package me.lucaslah.nrb;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import java.util.List;

public final class NoReportButtonClientHooks {
    private NoReportButtonClientHooks() {
    }

    public static void updatePauseMenu(Screen screen, Minecraft minecraft, List<? extends GuiEventListener> listeners) {
        if (!(screen instanceof PauseScreen)) {
            return;
        }

        String reportButtonText = I18n.get("menu.playerReporting");
        for (GuiEventListener listener : listeners) {
            if (listener instanceof AbstractWidget widget && widget.getMessage().getString().equals(reportButtonText)) {
                if (minecraft != null && minecraft.hasSingleplayerServer()) {
                    var server = minecraft.getSingleplayerServer();
                    widget.active = server != null && !server.isPublished();
                }

                widget.setMessage(Component.translatable("menu.shareToLan"));
            }
        }
    }
}