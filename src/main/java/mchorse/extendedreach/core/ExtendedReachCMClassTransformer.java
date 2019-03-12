package mchorse.extendedreach.core;

import mchorse.extendedreach.core.transformers.EntityRendererTransformer;
import mchorse.extendedreach.core.transformers.ItemTransformer;
import mchorse.extendedreach.core.transformers.NetHandlerPlayServerTransformer;
import mchorse.extendedreach.core.transformers.PlayerControllerMPTransformer;
import mchorse.extendedreach.core.transformers.PlayerInteractionManagerTransformer;
import mchorse.mclib.utils.coremod.CoreClassTransformer;

public class ExtendedReachCMClassTransformer extends CoreClassTransformer
{
    private PlayerControllerMPTransformer playerControllerMP = new PlayerControllerMPTransformer();
    private PlayerInteractionManagerTransformer playerInteractionManager = new PlayerInteractionManagerTransformer();
    private EntityRendererTransformer entityRenderer = new EntityRendererTransformer();
    private NetHandlerPlayServerTransformer netHandlerPlayServer = new NetHandlerPlayServerTransformer();
    private ItemTransformer item = new ItemTransformer();

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass)
    {
        if (checkName(name, "bsa", "net.minecraft.client.multiplayer.PlayerControllerMP"))
        {
            System.out.println("Extended Reach: Transforming PlayerControllerMP class (" + name + ")");

            return this.playerControllerMP.transform(name, basicClass);
        }
        else if (checkName(name, "or", "net.minecraft.server.management.PlayerInteractionManager"))
        {
            System.out.println("Extended Reach: Transforming PlayerInteractionManager class (" + name + ")");

            return this.playerInteractionManager.transform(name, basicClass);
        }
        else if (checkName(name, "buq", "net.minecraft.client.renderer.EntityRenderer"))
        {
            System.out.println("Extended Reach: Transforming EntityRenderer class (" + name + ")");

            return this.entityRenderer.transform(name, basicClass);
        }
        else if (checkName(name, "pa", "net.minecraft.network.NetHandlerPlayServer"))
        {
            System.out.println("Extended Reach: Transforming NetHandlerPlayServer class (" + name + ")");

            return this.netHandlerPlayServer.transform(name, basicClass);
        }
        else if (checkName(name, "", "net.minecraft.item.Item"))
        {
            System.out.println("Extended Reach: Transforming Item class (" + name + ")");

            return this.item.transform(name, basicClass);
        }

        return basicClass;
    }
}