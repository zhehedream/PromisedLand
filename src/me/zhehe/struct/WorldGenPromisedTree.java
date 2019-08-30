package me.zhehe.struct;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.Leaves;

public class WorldGenPromisedTree {
    private static Leaves leave = null;
    public static boolean generate(World world, Random random, int xx, int yy, int zz) {
        int var6 = random.nextInt(9) + 9;
        int var7 = 2 + random.nextInt(4);
        int var8 = var6 - var7;
        int var9 = 2 + random.nextInt(2);
        if (leave == null) {
            leave = (Leaves) Bukkit.createBlockData(Material.SPRUCE_LEAVES);
            leave.setDistance(1);
        }

        if (yy >= 1 && yy + var6 + 1 <= 256) {
            int var13;
            int var21;

            {
                Material mat = world.getBlockAt(xx, yy - 1, zz).getType();

                if ((mat == Material.GRASS_BLOCK) && yy < 256 - var6 - 1) {
                 world.getBlockAt(xx, yy - 1, zz).setType(Material.DIRT, false);
                 var21 = random.nextInt(2);
                 var13 = 1;
                 byte var22 = 0;
                 int var17;
                 int var16;
                 int i15;

                 for (i15 = 0; i15 <= var8; ++i15) {
                    var16 = yy + var6 - i15;

                    for (var17 = xx - var21; var17 <= xx + var21; ++var17) {
                        int var18 = var17 - xx;

                        for (int var19 = zz - var21; var19 <= zz + var21; ++var19) {
                            int var20 = var19 - zz;

                            if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && world.getBlockAt(var17, var16, var19).getType() == Material.AIR) {
                                world.getBlockAt(var17, var16, var19).setBlockData(leave, false);
                            }
                        }
                    }

                    if (var21 >= var13) {
                        var21 = var22;
                        var22 = 1;
                        ++var13;

                        if (var13 > var9) {
                            var13 = var9;
                        }
                    } else {
                        ++var21;
                    }
                 }

                 i15 = random.nextInt(3);

                 for (var16 = 0; var16 < var6 - i15; ++var16) {
                    Material mat2 = world.getBlockAt(xx, yy + var16, zz).getType();

                    if (mat2 == Material.AIR || mat2 == Material.SPRUCE_LEAVES) {
                        world.getBlockAt(xx, yy + var16, zz).setType(Material.SPRUCE_LOG, false);
                    }
                 }

                 return true;
                } else return false;
            }
        } else return false;
    }
}