/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zhehe.base;

import eu.over9000.nordic.populators.PopulatorLakes;
import java.util.Random;
import me.zhehe.populator.many.ManyPromisedTree;
import me.zhehe.populator.many.PopulatorManyFlowers;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class BiomePopulator extends BlockPopulator {
    public static PopulatorLakes lake = null;
    @Override
    public void populate(World world, Random random, Chunk source) {
//        boolean flag = false;
//        for(int x = 0; x < 16; x++) {
//            for(int z = 0; z < 16; z++) {
//                if(world.getBiome(x, z) == Biome.FLOWER_FOREST) {
//                    flag = true;
//                    break;
//                }
//            }
//        }
//        
//        if(flag) 
//        if(source.getBlock(0, 0, 0).getType() == Material.BARRIER) {
//            int x = source.getX(), z = source.getZ();
//            if(
//                isChunkGenerated(world, x - 1, z) &&
//                isChunkGenerated(world, x + 1, z) &&
//                isChunkGenerated(world, x - 1, z + 1) &&
//                isChunkGenerated(world, x, z + 1) &&
//                isChunkGenerated(world, x + 1, z + 1) &&
//                isChunkGenerated(world, x - 1, z - 1) &&
//                isChunkGenerated(world, x, z - 1) &&
//                isChunkGenerated(world, x + 1, z - 1)
//            ) {
//                source.getBlock(0, 0, 0).setType(Material.AIR, false);
                
                int minHeightMap[][] = new int[16][16];
//                for(int i = 0; i < 16; i++) {
//                    for(int j = 0; j < 16; j++) {
//                        int max = world.getHighestBlockYAt(x * 16 + i, z * 16 + j);
//                        for(int k = 0; k < max; k++) {
//                            if(source.getBlock(i, k, j).getType() != Material.AIR) {
//                                minHeightMap[i][j] = k;
//                                break;
//                            }
//                        }
//                    }
//                }
                
                if(lake == null) {
                    lake = new PopulatorLakes();
                    //cloud = new WorldGenCloud();
                }
                int rx = source.getX() * 16 + random.nextInt(16);
                int rz = source.getZ() * 16 + random.nextInt(16);
                if(world.getHighestBlockYAt(rx, rz) > 18) {
                    if(random.nextInt(7) > 1) lake.populate(world, random, source);
                }
                ManyPromisedTree.populate(world, random, source, minHeightMap);
                PopulatorManyFlowers.populate(world, random, source, minHeightMap);
                //HugeMushroom.populate(world, random, source);
                //cloud.populate(world, random, source);
                
//                if (source.getX() > 2 && source.getZ() > 2 && random.nextInt(700) < 5) {
//                    int randx = source.getX() * 16 + random.nextInt(16);
//                    int randz = source.getZ() * 16 + random.nextInt(16);
//                    StructPopulator.make(random, new Location(world, randx, 200, randz));
//                }
                
                if(source.getX() == 0 && source.getZ() == 0) {
                    //try {
                        PromisedLandPortal.makePortal(world);
//                    } catch (Exception ex) {
//                        StringWriter sw = new StringWriter();
//                        PrintWriter pw = new PrintWriter(sw);
//                        ex.printStackTrace(pw);
//                        Bukkit.getLogger().log(Level.SEVERE, sw.toString());
//                    }
                }
//            }
//        }
//        
//        if(source.getBlock(1, 0, 0).getType() == Material.BARRIER) {
//            int x = source.getX(), z = source.getZ();
//            source.getBlock(1, 0, 0).setType(Material.AIR, false);
//            if(isChunkGenerated(world, x - 1, z)) populate(world, random, world.getChunkAt(x - 1, z));
//            if(isChunkGenerated(world, x + 1, z)) populate(world, random, world.getChunkAt(x + 1, z));
//            if(isChunkGenerated(world, x - 1, z + 1)) populate(world, random, world.getChunkAt(x - 1, z + 1));
//            if(isChunkGenerated(world, x, z + 1)) populate(world, random, world.getChunkAt(x, z + 1));
//            if(isChunkGenerated(world, x + 1, z + 1)) populate(world, random, world.getChunkAt(x + 1, z + 1));
//            if(isChunkGenerated(world, x - 1, z - 1)) populate(world, random, world.getChunkAt(x - 1, z - 1));
//            if(isChunkGenerated(world, x, z - 1)) populate(world, random, world.getChunkAt(x, z - 1));
//            if(isChunkGenerated(world, x + 1, z - 1)) populate(world, random, world.getChunkAt(x + 1, z - 1));
//        }
        
        
    }
    
    private static boolean isChunkGenerated(World world, int x, int z) {
        if(world.isChunkLoaded(x,z)) return true;
        boolean res = world.loadChunk(x, z, false);
        if(res) world.unloadChunk(x, z, false);
        return res;
    }
}
