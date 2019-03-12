package mchorse.extendedreach.core.transformers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import mchorse.mclib.utils.coremod.ClassTransformer;

public class PlayerControllerMPTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            String methodName = this.checkName(method, "d", "()F", "getBlockReachDistance", "()F");

            if (methodName != null)
            {
                this.processGetBlockReachDistance(method);
            }

            methodName = this.checkName(method, "i", "()Z", "extendedReach", "()Z");

            if (methodName != null)
            {
                this.processExtendedReach(method);
            }
        }
    }

    private void processGetBlockReachDistance(MethodNode method)
    {
        InsnList list = new InsnList();
        String desc = "()F";

        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/extendedreach/utils/BlockReach", "getReach", desc, false));
        list.add(new InsnNode(Opcodes.FRETURN));

        method.instructions.clear();
        method.instructions.insert(list);

        System.out.println("Extended Reach: successfully patched getBlockReachDistance!");
    }

    private void processExtendedReach(MethodNode method)
    {
        InsnList list = new InsnList();
        String desc = "()Z";

        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/extendedreach/utils/BlockReach", "isExtended", desc, false));
        list.add(new InsnNode(Opcodes.IRETURN));

        method.instructions.clear();
        method.instructions.insert(list);

        System.out.println("Extended Reach: successfully patched extendedReach!");
    }
}