package mchorse.extendedreach.core;

import net.minecraftforge.fml.common.DummyModContainer;

public class ExtendedReachCMInfo extends DummyModContainer
{
    @Override
    public String getName()
    {
        return "Extended Reach core mod";
    }

    @Override
    public String getModId()
    {
        return "extendedreach_core";
    }

    @Override
    public Object getMod()
    {
        return null;
    }

    @Override
    public String getVersion()
    {
        return "%VERSION%";
    }
}