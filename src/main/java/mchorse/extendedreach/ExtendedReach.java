package mchorse.extendedreach;

import java.io.File;

import mchorse.extendedreach.config.ExtendedReachConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Extended reach mod
 * 
 * Allows player to have a configurable reach
 */
@Mod.EventBusSubscriber
@Mod(modid = ExtendedReach.MOD_ID, name = "ExtendedReach", version = ExtendedReach.VERSION, guiFactory = ExtendedReach.GUI_FACTORY, dependencies = "required-after:mclib@[%MCLIB%,)", updateJSON = "https://raw.githubusercontent.com/mchorse/extendedreach/master/version.json")
public class ExtendedReach
{
    public static final String MOD_ID = "extendedreach";
    public static final String VERSION = "%VERSION%";
    public static final String GUI_FACTORY = "mchorse.extendedreach.config.GuiFactory";

    public static ExtendedReachConfig config;
    public static Configuration forge;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        File file = new File(event.getModConfigurationDirectory(), "extendedreach.cfg");

        forge = new Configuration(file);
        config = new ExtendedReachConfig(forge);

        MinecraftForge.EVENT_BUS.register(config);
    }
}