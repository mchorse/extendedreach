package mchorse.extendedreach.config;

import mchorse.extendedreach.ExtendedReach;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExtendedReachConfig
{
    /**
     * How far the player can interact with world (items, blocks and entities) 
     */
    public float reach_distance = 5.0F;

    /**
     * Forge configuration
     */
    public Configuration config;

    public ExtendedReachConfig(Configuration config)
    {
        this.config = config;
        this.reload();
    }

    public void reload()
    {
        this.reach_distance = this.config.getFloat("reach_distance", Configuration.CATEGORY_GENERAL, 5.0F, 5.0F, 1024.0F, "How far the player can interact with world (items, blocks and entities)", "extendedreach.config.props.reach_distance");

        if (this.config.hasChanged())
        {
            this.config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(ExtendedReach.MOD_ID) && this.config.hasChanged())
        {
            this.reload();
        }
    }
}