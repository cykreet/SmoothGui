package ezzenix.smoothgui.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import ezzenix.smoothgui.SmoothGui;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(TexturedButtonWidget.class)
public class TexturedButtonWidgetMixin {
    @Final
    @Shadow
    protected Identifier texture;
    private static final Identifier RECIPE_BUTTON_TEXTURE = new Identifier("textures/gui/recipe_button.png");
    
    @Inject(method="renderButton", at=@At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (SmoothGui.isInMenu()) return;
        //TexturedButtonWidget t = (TexturedButtonWidget)(Object)this;
        if (this.texture == RECIPE_BUTTON_TEXTURE) {
            context.getMatrices().translate(0.0, -SmoothGui.getOffsetY(), 0.0);
        }
    }
    @Inject(method="renderButton", at=@At("TAIL"))
    private void onRenderEnd(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (SmoothGui.isInMenu()) return;
        if (this.texture == RECIPE_BUTTON_TEXTURE) {
            context.getMatrices().translate(0.0, SmoothGui.getOffsetY(), 0.0);
        }
    }
}