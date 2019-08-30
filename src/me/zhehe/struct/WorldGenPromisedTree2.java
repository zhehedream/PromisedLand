package me.zhehe.struct;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.Leaves;

public class WorldGenPromisedTree2
{
    private static Leaves leave = null;
    public static boolean generate(World world, Random random, int xx, int yy, int zz)
    {
        int var6 = random.nextInt(3) + 5;
        if(leave == null) {
            leave = (Leaves) Bukkit.createBlockData(Material.SPRUCE_LEAVES);
            leave.setDistance(1);
        }

        if (yy >= 1 && yy + var6 + 1 <= 256)
        {
            int xrange;
            int zrange;

            {
                Material mat = world.getBlockAt(xx, yy - 1, zz).getType();

                if ((mat == Material.GRASS_BLOCK) && yy < 256 - var6 - 1)
                {
                    world.getBlockAt(xx, yy - 1, zz).setType(Material.DIRT, false);
                    int var16;

                    for (var16 = yy - 3 + var6; var16 <= yy + var6; ++var16)
                    {
                        xrange = var16 - (yy + var6);
                        zrange = 1 - xrange / 2;

                        for (int x = xx - zrange; x <= xx + zrange; ++x)
                        {
                            int xdiff = x - xx;
                            for (int z = zz - zrange; z <= zz + zrange; ++z)
                            {
                                int diff = z - zz;
                                if ((Math.abs(xdiff) != zrange || Math.abs(diff) != zrange || random.nextInt(2) != 0 && xrange != 0) && world.getBlockAt(x, var16, z).getType() == Material.AIR)
                                {
                                    world.getBlockAt(x, var16, z).setBlockData(leave, false);
                                }
                            }
                        }
                    }

                    for (var16 = 0; var16 < var6; ++var16)
                    {
                        Material mat2 = world.getBlockAt(xx, yy + var16, zz).getType();

                        if (mat2 == Material.AIR || mat2 == Material.SPRUCE_LEAVES)
                        {
                            world.getBlockAt(xx, yy + var16, zz).setType(Material.SPRUCE_LOG, false);
                        }
                    }

                    return true;
                } else return false;
            }
        } else return false;
    }
}
