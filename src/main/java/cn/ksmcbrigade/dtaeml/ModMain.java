package cn.ksmcbrigade.dtaeml;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
@Mod(modid = ModMain.MODID, version = ModMain.VERSION)
public class ModMain {
    public static final String MODID = "dtaeml";
    public static final String VERSION = "1.0";
    /**
     * If everything goes to plan, you should see this message in the console.
     *
     * This is the main class and method of your mod. It is the entry point for the mod and
     * is used to register all the things that your mod needs to function.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}