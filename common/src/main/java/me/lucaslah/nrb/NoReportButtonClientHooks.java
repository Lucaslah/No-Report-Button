package me.lucaslah.nrb;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class NoReportButtonClientHooks {
    private static final String PLAYER_REPORTING_TEXT_KEY = "menu.playerReporting";

    private NoReportButtonClientHooks() {
    }

    @Nullable
    public static AbstractWidget findPauseMenuPlayerReportingControl(Screen screen, List<? extends GuiEventListener> listeners) {
        if (!(screen instanceof PauseScreen)) {
            return null;
        }

        String reportButtonText = I18n.get(PLAYER_REPORTING_TEXT_KEY);
        for (GuiEventListener listener : listeners) {
            if (listener instanceof AbstractWidget widget && widget.getMessage().getString().equals(reportButtonText)) {
                return widget;
            }
        }

        return null;
    }
}