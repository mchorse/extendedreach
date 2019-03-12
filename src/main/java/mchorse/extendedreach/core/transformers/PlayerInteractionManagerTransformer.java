package mchorse.extendedreach.core.transformers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import mchorse.mclib.utils.coremod.ClassTransformer;

public class PlayerInteractionManagerTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            String methodName = this.checkName(method, "getBlockReachDistance", "()D", "getBlockReachDistance", "()D");

            if (methodName != null)
            {
                this.processGetBlockReachDistance(method);
            }
        }
    }

    private void processGetBlockReachDistance(MethodNode method)
    {
        InsnList list = new InsnList();
        String desc = "()D";

        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/extendedreach/utils/BlockReach", "getDoubleReach", desc, false));
        list.add(new InsnNode(Opcodes.DRETURN));

        method.instructions.clear();
        method.instructions.insert(list);

        System.out.println("Extended Reach: successfully patched getBlockReachDistance!");
    }
}