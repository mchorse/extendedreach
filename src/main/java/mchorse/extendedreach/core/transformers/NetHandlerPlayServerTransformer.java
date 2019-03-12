package mchorse.extendedreach.core.transformers;

import java.util.Iterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import mchorse.mclib.utils.coremod.ClassTransformer;

public class NetHandlerPlayServerTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            String methodName = this.checkName(method, "a", "(Lix;)V", "processUseEntity", "(Lnet/minecraft/network/play/client/CPacketUseEntity;)V");

            if (methodName != null)
            {
                this.processGetMouseOver(method);
            }
        }
    }

    private void processGetMouseOver(MethodNode method)
    {
        Iterator<AbstractInsnNode> it = method.instructions.iterator();

        Double shortRange = Double.valueOf(9.0D);
        Double longRange = Double.valueOf(36.0D);
        int counter = 0;

        while (it.hasNext())
        {
            AbstractInsnNode node = it.next();

            if (node.getOpcode() == Opcodes.LDC)
            {
                LdcInsnNode ldc = (LdcInsnNode) node;

                if (ldc.cst.equals(shortRange) || ldc.cst.equals(longRange))
                {
                    method.instructions.insert(node, new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/extendedreach/utils/BlockReach", "getDoubleReachSq", "()D", false));
                    it.remove();

                    counter++;
                }
            }
        }

        if (counter == 2)
        {
            System.out.println("Extended Reach: successfully patched processUseEntity!");
        }
    }
}