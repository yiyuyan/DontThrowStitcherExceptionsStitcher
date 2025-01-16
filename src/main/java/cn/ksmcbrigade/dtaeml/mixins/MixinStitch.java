package cn.ksmcbrigade.dtaeml.mixins;

import net.minecraft.client.renderer.texture.Stitcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Stitcher.class)
public abstract class MixinStitch {
    @Shadow protected abstract boolean allocateSlot(Stitcher.Holder p_94310_1_);

    @Redirect(method = "doStitch",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/Stitcher;allocateSlot(Lnet/minecraft/client/renderer/texture/Stitcher$Holder;)Z"))
    public boolean no_exceptions(Stitcher instance, Stitcher.Holder holder){
        if(!this.allocateSlot(holder)){
            String s = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution resourcepack?", holder.getAtlasSprite().getIconName(), Integer.valueOf(holder.getAtlasSprite().getIconWidth()), Integer.valueOf(holder.getAtlasSprite().getIconHeight()));
            net.minecraftforge.fml.common.FMLLog.info(s);
        }
        return true;
    }
}
