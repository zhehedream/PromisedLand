package me.zhehe.struct;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.Leaves;


public class WorldGenPromisedShrub
{
    private static Leaves leave = null;
    public static boolean generate(World world, Random random, int xx, int yy, int zz)
    {
        Material var15;

        for (; ((var15 = world.getBlockAt(xx, yy, zz).getType()) == Material.AIR || var15 == Material.SPRUCE_LEAVES) && yy > 0; --yy);

        Material var7 = world.getBlockAt(xx, yy, zz).getType();

        if (var7 == Material.GRASS_BLOCK)
        {
            ++yy;
            if(leave == null) {
                leave = (Leaves) Bukkit.createBlockData(Material.SPRUCE_LEAVES);
                leave.setDistance(1);
            }
            world.getBlockAt(xx, yy, zz).setType(Material.SPRUCE_LOG);
                        

            for (int var8 = yy; var8 <= yy + 1; ++var8)
            {
                int var9 = var8 - yy;
                int var10 = 2 - var9;

                for (int var11 = xx - var10; var11 <= xx + var10; ++var11)
                {
                    int var12 = var11 - xx;

                    for (int var13 = zz - var10; var13 <= zz + var10; ++var13)
                    {
                        int var14 = var13 - zz;
                        if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || random.nextInt(2) != 0) && world.getBlockAt(var11, var8, var13).getType() == Material.AIR)
                        {
                            world.getBlockAt(var11, var8, var13).setBlockData(leave);
                        }
                    }
                }
            }
        }

        return true;
    }
}
