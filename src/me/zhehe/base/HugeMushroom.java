/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zhehe.base;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;

public class HugeMushroom {
    public static void populate(World world, Random random, Chunk chunk) {
        //if(world.getBiome(chunk.getX() * 16, chunk.getZ() * 16) != Biome.SNOWY_TUNDRA) return;
        int rand = random.nextInt(7);
        if(rand == 0) {
            int tree = 2 + random.nextInt(4);
            for(int i = 0; i < tree; i++) {
                int X = random.nextInt(15);
                int Z = random.nextInt(15);
                int rX = chunk.getX() * 16 + X;
                int rZ = chunk.getZ() * 16 + Z;
                int Y = world.getHighestBlockYAt(rX, rZ);
                if(Y > 250 || Y < 1) continue;
                for(;Y > 10 && isTrans(chunk.getBlock(X, Y, Z).getType()); Y--);
                Material base = chunk.getBlock(X, Y, Z).getType();
                if(base == Material.MYCELIUM) {
                    TreeType mushroom = random.nextBoolean() ? TreeType.BROWN_MUSHROOM : TreeType.RED_MUSHROOM;
                    world.generateTree(chunk.getBlock(X, Y + 1, Z).getLocation(), mushroom);
                }
            }
        }
    }
    
    public static boolean isTrans(Material mat) {
        return mat == Material.OAK_LEAVES || mat == Material.SPRUCE_LEAVES || mat == Material.BIRCH_LEAVES
                || mat == Material.OAK_LOG || mat == Material.SPRUCE_LOG || mat == Material.BIRCH_LOG
                || mat == Material.SNOW || mat == Material.AIR;
    }
}
