/*
 * Copyright 2012 s1mpl3x
 * 
 * This file is part of Nordic.
 * 
 * Nordic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Nordic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Nordic If not, see <http://www.gnu.org/licenses/>.
 */
package me.zhehe.populator.many;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.Random;
import static org.bukkit.Bukkit.createBlockData;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;

public class PopulatorManyFlowers {

	public static void populate(final World world, final Random random, final Chunk source, final int[][] minHeightMap) {
		final int chance = 1;
		if (chance < 10) {
			final int flowercount = random.nextInt(13) + 10; 
			for (int t = 0; t <= flowercount; t++) {
                        final int choose = random.nextInt(2);
                        if(choose == 1) {
                                    final int flower_x = random.nextInt(15);
                                    final int flower_z = random.nextInt(15);
                                    final int type = random.nextInt(300);

//                                    Block handle = world.getBlockAt(flower_x + source.getX() * 16, world.getHighestBlockYAt(flower_x + source.getX() * 16, flower_z + source.getZ() * 16), flower_z + source.getZ() * 16);
                                    Block handle = world.getHighestBlockAt(flower_x + source.getX() * 16, flower_z + source.getZ() * 16);
//                                    while (handle.getY() > minHeightMap[flower_x][flower_z] && !handle.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)) {
//                                        handle = handle.getRelative(BlockFace.DOWN);
//                                    }
                                    if (handle.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK) && handle.getType() == Material.AIR) {
                                            if (type < 40) {
                                                handle.setType(Material.POPPY, false);
                                            } else if(type < 120) {
                                                handle.setType(Material.DANDELION, false);
                                            } else if(type < 130) {
                                                handle.setType(Material.BLUE_ORCHID, false);
                                            } else if(type < 140) {
                                                handle.setType(Material.ALLIUM, false);
                                            } else if(type < 150) {
                                                handle.setType(Material.AZURE_BLUET, false);
                                            } else if(type < 160) {
                                                handle.setType(Material.RED_TULIP, false);
                                            } else if(type < 170) {
                                                handle.setType(Material.ORANGE_TULIP, false);
                                            } else if(type < 180) {
                                                handle.setType(Material.WHITE_TULIP, false);
                                            } else if(type < 190) {
                                                handle.setType(Material.PINK_TULIP, false);
                                            } else {
                                                handle.setType(Material.OXEYE_DAISY, false);
                                            }
                            }
                        } else {
                                    final int flower_x = random.nextInt(15);
                                    final int flower_z = random.nextInt(15);
                                    final int type = random.nextInt(300);

//                                    Block handle = world.getBlockAt(flower_x + source.getX() * 16, world.getHighestBlockYAt(flower_x + source.getX() * 16, flower_z + source.getZ() * 16), flower_z + source.getZ() * 16);
                                    Block handle = world.getHighestBlockAt(flower_x + source.getX() * 16, flower_z + source.getZ() * 16);
//                                    while (handle.getY() > minHeightMap[flower_x][flower_z] && !handle.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)) {
//                                        handle = handle.getRelative(BlockFace.DOWN);
//                                    }
                                    if (handle.getRelative(BlockFace.DOWN).getType().equals(Material.GRASS_BLOCK)) {
                                        if(type < 40) {
                                            BlockData data = createBlockData(Material.SUNFLOWER);
                                            ((Bisected)data).setHalf(Bisected.Half.BOTTOM);
                                            handle.setBlockData(data, false);
                                            
                                            handle = handle.getRelative(BlockFace.UP);
                                            data = createBlockData(Material.SUNFLOWER);
                                            ((Bisected)data).setHalf(Bisected.Half.TOP);
                                            handle.setBlockData(data, false);
                                        } else if(type < 80) {
                                            BlockData data = createBlockData(Material.LILAC);
                                            ((Bisected)data).setHalf(Bisected.Half.BOTTOM);
                                            handle.setBlockData(data, false);
                                            
                                            handle = handle.getRelative(BlockFace.UP);
                                            data = createBlockData(Material.LILAC);
                                            ((Bisected)data).setHalf(Bisected.Half.TOP);
                                            handle.setBlockData(data, false);
                                        } else if(type < 120) {
                                            BlockData data = createBlockData(Material.LARGE_FERN);
                                            ((Bisected)data).setHalf(Bisected.Half.BOTTOM);
                                            handle.setBlockData(data, false);
                                            
                                            handle = handle.getRelative(BlockFace.UP);
                                            data = createBlockData(Material.LARGE_FERN);
                                            ((Bisected)data).setHalf(Bisected.Half.TOP);
                                            handle.setBlockData(data, false);
                                        } else if(type < 160) {
                                            BlockData data = createBlockData(Material.ROSE_BUSH);
                                            ((Bisected)data).setHalf(Bisected.Half.BOTTOM);
                                            handle.setBlockData(data, false);
                                            
                                            handle = handle.getRelative(BlockFace.UP);
                                            data = createBlockData(Material.ROSE_BUSH);
                                            ((Bisected)data).setHalf(Bisected.Half.TOP);
                                            handle.setBlockData(data, false);
                                        } else {
                                            BlockData data = createBlockData(Material.PEONY);
                                            ((Bisected)data).setHalf(Bisected.Half.BOTTOM);
                                            handle.setBlockData(data, false);
                                            
                                            handle = handle.getRelative(BlockFace.UP);
                                            data = createBlockData(Material.PEONY);
                                            ((Bisected)data).setHalf(Bisected.Half.TOP);
                                            handle.setBlockData(data, false);
                                        }
                        }
                        }
                        
//                        for(int i = 0; i < 16; i++) {
//                            for(int j = 0; j < 16; j++) {
////                                int h = world.getHighestBlockYAt(i + source.getX() * 16, j + source.getZ() * 16);
////                                if(h > 100) h = 100;
////                                for(int k = minHeightMap[i][j]; k < h; k++) {
////                                    if(source.getBlock(i, k, j).getType() == Material.GRASS_BLOCK && source.getBlock(i, k + 1, j).getType() == Material.AIR) {
////                                        if(random.nextInt(50) == 0) source.getBlock(i, k + 1, j).setType(Material.GRASS, false);
////                                        k++;
////                                    }
////                                }
//                                Block block = world.getHighestBlockAt(i + source.getX() * 16, j + source.getZ() * 16);
//                                if(block.getRelative(BlockFace.DOWN).getType() == Material.GRASS_BLOCK && block.getType() == Material.AIR) {
//                                    if(random.nextInt(50) == 0) block.setType(Material.GRASS, false);
//                                }
//                            }
//                        }
                        int grass_amount = random.nextInt(5) + 5;
                        int xx = source.getX(), zz = source.getZ();
                        for(int i = 0; i < grass_amount; i++) {
                            int randx = random.nextInt(16);
                            int randz = random.nextInt(16);
                            
                            Block block = world.getHighestBlockAt(xx * 16 + randx, zz * 16 + randz);
                            if(block.getY() > 20 && block.getType() == Material.AIR && block.getRelative(BlockFace.DOWN).getType() == Material.GRASS_BLOCK) {
                                block.setType(Material.GRASS, false);
                            }
                        }
		}
	}
}}
