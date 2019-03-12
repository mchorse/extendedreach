package mchorse.extendedreach.utils;

import mchorse.extendedreach.ExtendedReach;

/**
 * Block reach static util class
 * 
 * This class is mostly used for ASM hooks
 */
public class BlockReach
{
    public static float getReach()
    {
        return ExtendedReach.config.reach_distance;
    }

    public static double getDoubleReach()
    {
        return getReach();
    }

    public static double getDoubleReachSq()
    {
        return getDoubleReach() * getDoubleReach();
    }

    public static boolean isExtended()
    {
        return true;
    }
}