package me.lucaslah.ncrb.mixin;

import me.lucaslah.ncrb.IScreen;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin extends AbstractParentElement implements Drawable, IScreen {
    @Shadow
    @Final
    private List<Drawable> drawables;

    @Override
    public List<Drawable> getButtons() {
        return drawables;
    }
}
