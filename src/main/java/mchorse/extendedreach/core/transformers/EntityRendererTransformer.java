package mchorse.extendedreach.core.transformers;

import java.util.Iterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import mchorse.mclib.utils.coremod.ClassTransformer;

public class EntityRendererTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            String methodName = this.checkName(method, "a", "(F)V", "getMouseOver", "(F)V");

            if (methodName != null)
            {
                this.processGetMouseOver(method);
            }
        }
    }

    private void processGetMouseOver(MethodNode method)
    {
        Iterator<AbstractInsnNode> it = method.instructions.iterator();
        boolean found = false;

        while (it.hasNext())
        {
            AbstractInsnNode node = it.next();

            if (found)
            {
                it.remove();
                System.out.println("Extended Reach: successfully patched getMouseOver!");

                break;
            }

            if (node.getOpcode() == Opcodes.LDC)
            {
                LdcInsnNode ldc = (LdcInsnNode) node;

                if (ldc.cst.equals(Double.valueOf(6.0D)))
                {
                    it.remove();
                    found = true;
                }
            }
        }
    }
}