package mchorse.extendedreach.core.transformers;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import mchorse.mclib.utils.coremod.ClassTransformer;

public class PlayerInteractionManagerTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            // System.out.println("Extended Reach: trying " + method.name + " for REACH_DISTANCE!");
            ItemTransformer.processBlockReach(method);
        }
    }
}