package cn.ksmcbrigade.dtaeml.mixins;

import net.minecraft.client.renderer.texture.Stitcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Stitcher.class)
public class MixinStitch {
    @Inject(method = "doStitch",at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",shift = At.Shift.BEFORE),cancellable = true)
    public void no_exceptions(CallbackInfo ci){
        ci.cancel();
    }
}
