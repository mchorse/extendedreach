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
            ItemTransformer.processBlockReach(method);
        }
    }
}