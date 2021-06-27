package mchorse.extendedreach;

import mchorse.mclib.McLib;
import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueDouble;
import mchorse.mclib.config.values.ValueFloat;
import mchorse.mclib.events.RegisterConfigEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Extended reach mod
 * 
 * Allows player to configure the block, item or entity reach
 */
@Mod.EventBusSubscriber
@Mod(modid = ExtendedReach.MOD_ID, name = "ExtendedReach", version = ExtendedReach.VERSION, dependencies = "required-after:mclib@[%MCLIB%,)", updateJSON = "https://raw.githubusercontent.com/mchorse/extendedreach/master/version.json")
public class ExtendedReach
{
    public static final String MOD_ID = "extendedreach";
    public static final String VERSION = "%VERSION%";

    public static ValueFloat reach;

    @SubscribeEvent
    public void onConfigRegister(RegisterConfigEvent event)
    {
        ConfigBuilder builder = event.createBuilder(MOD_ID);

        reach = builder.category("general").getFloat("reach", 5F, 0F, 1024F);
        reach.syncable();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        McLib.EVENT_BUS.register(this);
    }
}