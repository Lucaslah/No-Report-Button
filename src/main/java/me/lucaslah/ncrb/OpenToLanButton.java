package me.lucaslah.ncrb;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;

public class OpenToLanButton extends PressableWidget {
    private final Screen screen;
    public OpenToLanButton(Screen screen, int i, int j, int k, int l, Text text) {
        super(i, j, k, l, text);
        this.screen = screen;
    }

    @Override
    public void onPress() {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client != null;
        client.setScreen(new OpenToLanScreen(screen));
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
