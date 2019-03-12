package mchorse.extendedreach.core;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@Name("Extended Reach core mod")
@MCVersion("1.12.2")
@SortingIndex(1)
public class ExtendedReachCM implements IFMLLoadingPlugin
{
    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] {ExtendedReachCMClassTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass()
    {
        return ExtendedReachCMInfo.class.getName();
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {}

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }
}