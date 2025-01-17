package cn.ksmcbrigade.dtaeml.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Stitcher.class)
public abstract class MixinStitch {
    @Shadow protected abstract boolean allocateSlot(Stitcher.Holder p_94310_1_);

    @Shadow @Final private int mipmapLevelStitcher;

    @Unique
    private TextureAtlasSprite miss = null;

    @Redirect(method = "doStitch",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/Stitcher;allocateSlot(Lnet/minecraft/client/renderer/texture/Stitcher$Holder;)Z"))
    public boolean no_exceptions(Stitcher instance, Stitcher.Holder holder){
        if(!this.allocateSlot(holder)){
            String s = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution resourcepack?", holder.getAtlasSprite().getIconName(), holder.getAtlasSprite().getIconWidth(), holder.getAtlasSprite().getIconHeight());
            net.minecraftforge.fml.common.FMLLog.info(s);
        }
        return true;
    }

    @ModifyVariable(method = "addSprite", at = @At("HEAD"),ordinal = 0, argsOnly = true)
    public TextureAtlasSprite sprite(TextureAtlasSprite value){
        if(miss==null) miss=Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        if(value!=miss && !this.allocateSlot(new Stitcher.Holder(value,this.mipmapLevelStitcher))){
            return miss;
        }
        return value;
    }
}
