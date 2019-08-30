package me.zhehe.struct;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.Leaves;

public class WorldGenPromisedTree3
{
    private static Leaves leave = null;
    public static boolean generate(World world, Random random, int xx, int yy, int zz)
    {
        int var6 = random.nextInt(20) + 30;
        int var7 = random.nextInt(5) + 10;
        int var8 = var6 - var7;
        int var9 = 2 + random.nextInt(3);
        if(leave == null) {
            leave = (Leaves) Bukkit.createBlockData(Material.SPRUCE_LEAVES);
            leave.setDistance(1);
        }

        if (yy >= 1 && yy + var6 + 1 <= 256)
        {
            {
                Material mat1 = world.getBlockAt(xx, yy - 1, zz).getType();
                Material mat2 = world.getBlockAt(xx - 1, yy - 1, zz).getType();
                Material mat3 = world.getBlockAt(xx, yy - 1, zz - 1).getType();
                Material mat4 = world.getBlockAt(xx - 1, yy - 1, zz - 1).getType();

                if ((mat1 == Material.GRASS_BLOCK) && yy < 256 - var6 - 1)
                {
                    if ((mat2 == Material.GRASS_BLOCK) && yy < 256 - var6 - 1)
                    {
                        if ((mat3 == Material.GRASS_BLOCK) && yy < 256 - var6 - 1)
                        {
                            if ((mat4 == Material.GRASS_BLOCK) && yy < 256 - var6 - 1)
                            {
                                world.getBlockAt(xx, yy - 1, zz).setType(Material.DIRT, false);
                                world.getBlockAt(xx - 1, yy - 1, zz).setType(Material.DIRT, false);
                                world.getBlockAt(xx, yy - 1, zz - 1).setType(Material.DIRT, false);
                                world.getBlockAt(xx - 1, yy - 1, zz - 1).setType(Material.DIRT, false);
                                int rand = random.nextInt(2);
                                int i0 = 1;
                                boolean b1 = false;
                                int world9;
                                int world8;
                                int random0;

                                for (world8 = 0; world8 <= var8; ++world8)
                                {
                                    world9 = yy + var6 - world8;

                                    for (random0 = xx - rand; random0 <= xx + rand; ++random0)
                                    {
                                        int random1 = random0 - xx;

                                        for (int random2 = zz - rand; random2 <= zz + rand; ++random2)
                                        {
                                            int random3 = random2 - zz;

                                            if ((Math.abs(random1) != rand || Math.abs(random3) != rand || rand <= 0) && world.getBlockAt(random0, world9, random2).getType() == Material.AIR)
                                            {
                                                world.getBlockAt(random0, world9, random2).setBlockData(leave, false);
                                                world.getBlockAt(random0 - 1, world9, random2).setBlockData(leave, false);
                                                world.getBlockAt(random0, world9, random2 - 1).setBlockData(leave, false);
                                                world.getBlockAt(random0 - 1, world9, random2 - 1).setBlockData(leave, false);
                                            }
                                        }
                                    }

                                    if (rand >= i0)
                                    {
                                        rand = b1 ? 1 : 0;
                                        b1 = true;
                                        ++i0;

                                        if (i0 > var9)
                                        {
                                            i0 = var9;
                                        }
                                    }
                                    else
                                    {
                                        ++rand;
                                    }
                                }

                                world8 = random.nextInt(3);

                                for (world9 = 0; world9 < var6 - world8; ++world9)
                                {
                                    Material mrandom0 = world.getBlockAt(xx, yy + world9, zz).getType();

                                    if (mrandom0 == Material.AIR || mrandom0 == Material.SPRUCE_LEAVES)
                                    {
                                        world.getBlockAt(xx, yy + world9, zz).setType(Material.SPRUCE_LOG, false);
                                        world.getBlockAt(xx - 1, yy + world9, zz).setType(Material.SPRUCE_LOG, false);
                                        world.getBlockAt(xx, yy + world9, zz - 1).setType(Material.SPRUCE_LOG, false);
                                        world.getBlockAt(xx - 1, yy + world9, zz - 1).setType(Material.SPRUCE_LOG, false);
                                    }
                                }

                                return true;
                            } else
                                return false;
                        } else
                            return false;
                    } else
                        return false;
                } else
                    return false;
            }
        } else
            return false;
    }
}
