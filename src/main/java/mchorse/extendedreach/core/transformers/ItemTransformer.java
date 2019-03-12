package mchorse.extendedreach.core.transformers;

import java.util.Iterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import mchorse.mclib.utils.coremod.ClassTransformer;
import mchorse.mclib.utils.coremod.CoreClassTransformer;

public class ItemTransformer extends ClassTransformer
{
    public static InsnList GET_REACH;
    public static InsnList GET_REACH_OBF;

    static
    {
        GET_REACH = new InsnList();
        GET_REACH.add(new FieldInsnNode(Opcodes.GETSTATIC, "net/minecraft/entity/player/EntityPlayer", "REACH_DISTANCE", "Lnet/minecraft/entity/ai/attributes/IAttribute;"));
        GET_REACH.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/entity/player/EntityPlayer", "getEntityAttribute", "(Lnet/minecraft/entity/ai/attributes/IAttribute;)Lnet/minecraft/entity/ai/attributes/IAttributeInstance;", false));
        GET_REACH.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "net/minecraft/entity/ai/attributes/IAttributeInstance", "getAttributeValue", "()D", true));

        GET_REACH_OBF = new InsnList();
        GET_REACH_OBF.add(new FieldInsnNode(Opcodes.GETSTATIC, "aed", "REACH_DISTANCE", "Lwc;"));
        GET_REACH_OBF.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "aed", "a", "(Lwc;)Lwd;", false));
        GET_REACH_OBF.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "wd", "e", "()D", true));
    }

    public static void processBlockReach(MethodNode method)
    {
        Iterator<AbstractInsnNode> it = method.instructions.iterator();
        InsnList list = CoreClassTransformer.obfuscated ? GET_REACH_OBF : GET_REACH;
        AbstractInsnNode first = list.getFirst();
        int counter = 0;

        while (it.hasNext())
        {
            AbstractInsnNode node = it.next();

            if (equals(node, first))
            {
                if (first == list.getLast())
                {
                    AbstractInsnNode target = node.getPrevious().getPrevious().getPrevious();

                    /* Fixes shit that has ALOAD in front */
                    if (target.getPrevious() instanceof VarInsnNode)
                    {
                        target = target.getPrevious();
                    }

                    while (node != target)
                    {
                        AbstractInsnNode next = target.getNext();
                        method.instructions.remove(target);
                        target = next;
                    }

                    method.instructions.insert(target, new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/extendedreach/utils/BlockReach", "getDoubleReach", "()D", false));
                    method.instructions.remove(target);

                    first = list.getFirst();
                    counter++;
                }
                else
                {
                    first = first.getNext();
                }
            }
            else
            {
                first = list.getFirst();
            }
        }

        if (counter > 0)
        {
            System.out.println("Extended Reach: successfully patched method " + method.name + " for REACH_DISTANCE!");
        }
    }

    public static boolean equals(AbstractInsnNode first, AbstractInsnNode second)
    {
        if (first.getOpcode() == second.getOpcode())
        {
            if (first instanceof FieldInsnNode)
            {
                FieldInsnNode field1 = (FieldInsnNode) first;
                FieldInsnNode field2 = (FieldInsnNode) second;

                return field1.name.equals(field2.name) && field1.owner.equals(field2.owner) && field1.desc.equals(field2.desc);
            }
            else if (first instanceof MethodInsnNode)
            {
                MethodInsnNode method1 = (MethodInsnNode) first;
                MethodInsnNode method2 = (MethodInsnNode) second;

                return method1.name.equals(method2.name) && method1.desc.equals(method2.desc) && method1.itf == method2.itf;
            }
        }

        return false;
    }

    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            String methodName = this.checkName(method, "a", "(Lamu;Laed;Z)Lbhc;", "rayTrace", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;Z)Lnet/minecraft/util/math/RayTraceResult;");

            if (methodName != null)
            {
                processBlockReach(method);
            }
        }
    }
}