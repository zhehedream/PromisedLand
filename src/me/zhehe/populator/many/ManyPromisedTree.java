/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zhehe.populator.many;

import me.zhehe.struct.WorldGenGiantFlowerRed;
import me.zhehe.struct.WorldGenGiantFlowerYellow;
import me.zhehe.struct.WorldGenPromisedShrub;
import me.zhehe.struct.WorldGenPromisedTree;
import me.zhehe.struct.WorldGenPromisedTree2;
import me.zhehe.struct.WorldGenPromisedTree3;
import me.zhehe.struct.WorldGenPromisedWillowTree;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

/**
 *
 * @author Zhehe
 */
public class ManyPromisedTree {
    public static void populate(World world, Random random, Chunk source, int[][] minHeightMap) {
            int rand = 0;
            if(rand != 0) return;
            
            Map<Integer, Integer> tree = new HashMap<>();
            
            int cx = source.getX() * 16, cz = source.getZ() * 16;

                int treecount = 3;
		for (int t = 0; t <= treecount; t++) {
			final int tree_x = random.nextInt(15);
			final int tree_z = random.nextInt(15);

			Block block = world.getHighestBlockAt(tree_x + source.getX() * 16, tree_z + source.getZ() * 16);
//			while (block.getY() > minHeightMap[tree_x][tree_z] && !block.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)) {
//				block = block.getRelative(BlockFace.DOWN);
//			}
                       
                        //if(!block.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)) continue;
                        //Location high = block.getLocation();
                        Biome biome = block.getBiome();
                switch (biome) {
                    case FLOWER_FOREST:
                        if(random.nextInt(4) == 0) {
                            //WorldGenPromisedTree3.generate(world, random, high.getBlockX(), high.getBlockY(), high.getBlockZ());
                            int rx = random.nextInt(6) + 5 + 1, rz = random.nextInt(6) + 5 + 1;
                            int nx = rx + cx, nz = rz + cz;
                            int ny = world.getHighestBlockYAt(nx, nz);
                            if(ny > 10) {
                                //WorldGenPromisedTree3.generate(world, random, nx, ny, nz);
                                int index = rx * 32 + rz + ny * 1024;
                                tree.put(index, 0);
                            }
                        } else if(random.nextInt(8) == 0) {
                            int rx = random.nextInt(10) + 3, rz = random.nextInt(10) + 3;
                            int nx = rx + cx, nz = rz + cz;
                            int ny = world.getHighestBlockYAt(nx, nz);
                            if(ny > 10) {
                                //WorldGenPromisedTree2.generate(world, random, nx, ny, nz);
                                int index = rx * 32 + rz + ny * 1024;
                                tree.put(index, 1);
                            }
                        } else {
                            int rx = random.nextInt(12) + 2, rz = random.nextInt(12) + 2;
                            int nx = rx + cx, nz = rz + cz;
                            int ny = world.getHighestBlockYAt(nx, nz);
                            if(ny > 10) {
                                //WorldGenPromisedTree.generate(world, random, nx, ny, nz);
                                int index = rx * 32 + rz + ny * 1024;
                                tree.put(index, 2);
                            }
                        }
                        break;
                    case LUKEWARM_OCEAN:
                    {   int rx = random.nextInt(10) + 3, rz = random.nextInt(10) + 3;
                        int nx = rx + cx, nz = rz + cz;
                        int ny = world.getHighestBlockYAt(nx, nz);
                        if(ny > 10) {
                            //WorldGenPromisedWillowTree.generate(world, random, nx, ny, nz);
                            int index = rx * 32 + rz + ny * 1024;
                            tree.put(index, 3);
                        }
                    }
                        break;
                    case JUNGLE:
                    {   int rx = random.nextInt(12) + 2, rz = random.nextInt(12) + 2;
                        int nx = rx + cx, nz = rz + cz;
                        int ny = world.getHighestBlockYAt(nx, nz);
                        if(ny > 10) {
                            //WorldGenPromisedShrub.generate(world, random, nx, ny, nz);
                            int index = rx * 32 + rz + ny * 1024;
                            tree.put(index, 4);
                        }
                    }
                        break;
                    case SUNFLOWER_PLAINS:
                        if(random.nextInt(30) == 0) {
                            int rx = random.nextInt(10) + 3, rz = random.nextInt(10) + 3;
                            int nx = rx + cx, nz = rz + cz;
                            int ny = world.getHighestBlockYAt(nx, nz);
                            if(ny > 10) {
                                //Bukkit.getLogger().log(Level.SEVERE, nx+","+ny+","+nz);
                                if(random.nextBoolean()) WorldGenGiantFlowerYellow.generate(world, random, nx, ny, nz);
                                else WorldGenGiantFlowerRed.generate(world, random, nx, ny, nz);
                            }
                        }
                        break;
                    default:
                        break;
                }
		}
                
                for(Map.Entry<Integer, Integer> entry : tree.entrySet()) {
                    int index = entry.getKey();
                    int nx = ((index / 32) & 0xF) + cx;
                    int nz = (index & 0xF) + cz;
                    int ny = (index / 1024) & 0xFF;
                    //Bukkit.getLogger().log(Level.SEVERE, nx + "#"+ny + "#" + nz);
                    int type = entry.getValue();
                    if(type == 0) {
                        WorldGenPromisedTree3.generate(world, random, nx, ny, nz);
                    } else if(type == 1) {
                        WorldGenPromisedTree2.generate(world, random, nx, ny, nz);
                    } else if(type == 2) {
                        WorldGenPromisedTree.generate(world, random, nx, ny, nz);
                    } else if(type == 3) {
                        WorldGenPromisedWillowTree.generate(world, random, nx, ny, nz);
                    } else {
                        WorldGenPromisedShrub.generate(world, random, nx, ny, nz);
                    }
                }
	}
    
}

