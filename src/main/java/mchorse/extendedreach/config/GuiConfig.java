package mchorse.extendedreach.config;

import java.util.ArrayList;
import java.util.List;

import mchorse.extendedreach.ExtendedReach;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Config GUI
 *
 * This config GUI is responsible for managing Blockbuster's config. Most of
 * the code that implements config features is located in the parent of the
 * class.
 */
@SideOnly(Side.CLIENT)
public class GuiConfig extends net.minecraftforge.fml.client.config.GuiConfig
{
    public GuiConfig(GuiScreen parent)
    {
        super(parent, getConfigElements(), ExtendedReach.MOD_ID, false, false, "Extended Reach");
    }

    private static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();

        for (String name : ExtendedReach.forge.getCategoryNames())
        {
            ConfigCategory category = ExtendedReach.forge.getCategory(name);
            category.setLanguageKey("extendedreach.config." + name + ".title");

            for (Property prop : category.getOrderedValues())
            {
                elements.add(new ConfigElement(prop));
            }
        }

        return elements;
    }
}