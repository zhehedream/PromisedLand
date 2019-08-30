/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.zhehe.base;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;

public class BiomeGenBase {
    public Material topBlock, fillerBlock;
    public Biome rb;
    public static Set<Biome> forest, shrub, swamp, plains;
    
    private void init() {
        forest = new HashSet<>();
        shrub = new HashSet<>();
        swamp = new HashSet<>();
        plains = new HashSet<>();
        
        for(Biome b : Biome.values()) {
            String name = b.toString().toUpperCase();
            if(name.contains("PLAIN")) {
                plains.add(b);
            } else if(name.contains("OCEAN")) {
                shrub.add(b);
            } else if(name.contains("SWAMP") || name.contains("SNOW") || name.contains("MOUNTAINS") || name.contains("HILL")) {
                swamp.add(b);
            } else {
                forest.add(b);
            }
        }
        //Bukkit.getLogger().log(Level.INFO, swamp.toString());
    }
    
    public BiomeGenBase(Biome biome) {
        if(forest == null) init();
        fillerBlock = Material.DIRT;
        topBlock = Material.GRASS_BLOCK;
        
        if(plains.contains(biome)) {
            rb = Biome.SUNFLOWER_PLAINS;
        } else if(swamp.contains(biome)) {
            rb = Biome.LUKEWARM_OCEAN;
            //topBlock = Material.STONE;
        } else if(forest.contains(biome)) {
            rb = Biome.FLOWER_FOREST;
        } else {
            rb = Biome.JUNGLE;
        }
        
//        if(biome == Biome.BADLANDS || biome == Biome.BADLANDS_PLATEAU || biome == Biome.ERODED_BADLANDS || biome == Biome.MODIFIED_BADLANDS_PLATEAU || biome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU) {
//            fillerBlock = Material.TERRACOTTA;
//            topBlock = Material.RED_SAND;
//            rb = Biome.BADLANDS;
//        } else if(biome == Biome.DESERT || biome == Biome.DESERT_HILLS || biome == Biome.DESERT_LAKES) {
//            fillerBlock = Material.SANDSTONE;
//            topBlock = Material.SAND;
//            rb = Biome.DESERT_LAKES;
//        } else if(biome == Biome.MUSHROOM_FIELDS || biome == Biome.MUSHROOM_FIELD_SHORE) {
//            fillerBlock = Material.DIRT;
//            topBlock = Material.MYCELIUM;
//            rb = Biome.MUSHROOM_FIELDS;
//        } else if(biome == Biome.ICE_SPIKES) {
//            fillerBlock = Material.PACKED_ICE;
//            topBlock = Material.ICE;
//            rb = Biome.ICE_SPIKES;
//        }
    }
}
