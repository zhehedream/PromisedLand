package me.zhehe.base;

import java.util.Random;

import com.github.barteks2x.b173gen.oldnoisegen.NoiseGeneratorOctaves3D;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class ChunkGeneratorPromised extends ChunkGenerator
{
    private Random rng;
    private NoiseGeneratorOctaves3D octaves1;
    private NoiseGeneratorOctaves3D octaves2;
    private NoiseGeneratorOctaves3D octaves3;
    public NoiseGeneratorOctaves3D octaves4;
    public NoiseGeneratorOctaves3D octaves5;
    private double[] densities;

    double field1[];
    double field2[];
    double field3[];
    double field4[];
    double field5[];

    List<BlockPopulator> populators;

    public ChunkGeneratorPromised() {
        populators = new ArrayList<>();
        populators.add(new BiomePopulator());
    }

    public void init(World world)
    {
        rng = new Random(world.getSeed());
        octaves1 = new NoiseGeneratorOctaves3D(rng, 16, true);
        octaves2 = new NoiseGeneratorOctaves3D(rng, 16, true);
        octaves3 = new NoiseGeneratorOctaves3D(rng, 8, true);
        octaves4 = new NoiseGeneratorOctaves3D(rng, 10, true);
        octaves5 = new NoiseGeneratorOctaves3D(rng, 16, true);
    }
    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 6, 141, 10);
    }
        
    @Override
    public List<BlockPopulator> getDefaultPopulators(final World world) {
        return populators;
    }
        
    private static int[] toXYZ(int index) {
        int y = index % 128;
        int temp = index / 128;
        int z = temp % 16;
        int x = temp / 16;
        return new int[] {x,y,z};
    }

    public void generateTerrain(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkdata)
    {
        byte b1 = 2;
        int i1 = b1 + 1;
        byte b2 = 33;
        int i2 = b1 + 1;
        densities = this.initializeNoiseField(densities, chunkX * b1, 0, chunkZ * b1, i1, b2, i2);

        for (int x = 0; x < b1; ++x)
        {
            for (int y = 0; y < b1; ++y)
            {
                for (int z = 0; z < 32; ++z)
                {
                    double d = 0.25D;
                    double d1 = densities[((x + 0) * i2 + y + 0) * b2 + z + 0];
                    double d2 = densities[((x + 0) * i2 + y + 1) * b2 + z + 0];
                    double d3 = densities[((x + 1) * i2 + y + 0) * b2 + z + 0];
                    double d4 = densities[((x + 1) * i2 + y + 1) * b2 + z + 0];
                    double d5 = (densities[((x + 0) * i2 + y + 0) * b2 + z + 1] - d1) * d;
                    double d6 = (densities[((x + 0) * i2 + y + 1) * b2 + z + 1] - d2) * d;
                    double d7 = (densities[((x + 1) * i2 + y + 0) * b2 + z + 1] - d3) * d;
                    double d8 = (densities[((x + 1) * i2 + y + 1) * b2 + z + 1] - d4) * d;

                    for (int l1 = 0; l1 < 4; ++l1)
                    {
                        double var31 = 0.125D;
                        double var33 = d1;
                        double var35 = d2;
                        double var37 = (d3 - d1) * var31;
                        double var39 = (d4 - d2) * var31;

                        for (int y2 = 0; y2 < 8; ++y2)
                        {
                            int index = y2 + x * 8 << 11 | 0 + y * 8 << 7 | z * 4 + l1;
                            short var43 = 128;
                            double var44 = 0.125D;
                            double var46 = var33;
                            double var48 = (var35 - var33) * var44;

                            for (int k2 = 0; k2 < 8; ++k2)
                            {
                                Material b11 = Material.AIR;

                                if (var46 > 0.0D)
                                {
                                    b11 = Material.STONE;
                                }

                                int[] xyz = toXYZ(index);
                                chunkdata.setBlock(xyz[0], xyz[1], xyz[2], b11);
                                index += var43;
                                var46 += var48;
                            }

                            var33 += var37;
                            var35 += var39;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }
    
    public void replaceBlocksForBiome(int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkdata, ChunkGenerator.BiomeGrid biomes)
    {
        byte sealevel = 63;

        for (int xx = 0; xx < 16; ++xx)
        {
            for (int zz = 0; zz < 16; ++zz)
            {
                BiomeGenBase biome_element = new BiomeGenBase(biomes.getBiome(zz, xx));
                biomes.setBiome(zz, xx, biome_element.rb);
                byte var7 = 1;
                int var8 = -1;
                Material top_mat = biome_element.topBlock;
                Material fill_mat = biome_element.fillerBlock;

                for (int yy = 127; yy >= 0; --yy)
                {
                    Material var13 = chunkdata.getType(zz, yy, xx);

                    if (var13 == Material.AIR)
                    {
                        var8 = -1;
                    }
                    else if (var13 == Material.STONE)
                    {
                        if (var8 == -1)
                        {
                            if (var7 <= 0)
                            {
                                top_mat = Material.AIR;
                                fill_mat = Material.STONE;
                            }
                            else if (yy >= sealevel - 4 && yy <= sealevel + 1)
                            {
                                top_mat = biome_element.topBlock;
                                fill_mat = biome_element.fillerBlock;
                            }

                            if (yy < sealevel && top_mat == Material.AIR)
                            {
                                top_mat = Material.WATER;
                            }

                            var8 = var7;

                            if (yy >= 0)
                            {
                                chunkdata.setBlock(zz, yy, xx, top_mat);
                            }
                            else
                            {
                                chunkdata.setBlock(zz, yy, xx, fill_mat);
                            }
                        }
                        else if (var8 > 0)
                        {
                            --var8;
                            chunkdata.setBlock(zz, yy, xx, fill_mat);
                        }
                    }
                }
            }
        }
    }


    @Override
    public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int par1, int par2, ChunkGenerator.BiomeGrid biomes)
    {
        if(rng == null) this.init(world);
         rng.setSeed(par1 * 341873128712L + par2 * 132897987541L);
         ChunkGenerator.ChunkData var3 = Bukkit.createChunkData(world);
         this.generateTerrain(par1, par2, var3);
         this.replaceBlocksForBiome(par1, par2, var3, biomes);
         return var3;
    }

     private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
     {
         if(ad == null)
         {
             ad = new double[l * i1 * j1];
         }
         double d0 = 684.41200000000003D;
         double d1 = 684.41200000000003D;
         //double temp[] = this.wcm.temperatures;
         //double rain[] = this.wcm.rain;

         field4 = octaves4.generateNoiseArray(field4, i, k, l, j1, 1.121D, 1.121D, 0.5D);
         field5 = octaves5.generateNoiseArray(field5, i, k, l, j1, 200D, 200D, 0.5D);
         d0 *= 2D;
         field1 = octaves3.generateNoiseArray(field1, i, j, k, l, i1, j1, d0 / 80D, d1 / 160D, d0 / 80D);
         field2 = octaves1.generateNoiseArray(field2, i, j, k, l, i1, j1, d0, d1, d0);
         field3 = octaves2.generateNoiseArray(field3, i, j, k, l, i1, j1, d0, d1, d0);
         int k1 = 0;
         int l1 = 0;
         for(int j2 = 0; j2 < l; j2++)
         {
             for(int l2 = 0; l2 < j1; l2++)
             {
                 double d3;
                 d3 = 0.5D;
                 double d4 = 1.0D - d3;
                 d4 *= d4;
                 d4 *= d4;
                 d4 = 1.0D - d4;
                 double d5 = (field4[l1] + 256D) / 512D;
                 d5 *= d4;
                 if(d5 > 1.0D)
                 {
                     d5 = 1.0D;
                 }
                 if(d5 < 0.0D)
                 {
                     d5 = 0.0D;
                 }
                 d5 += 0.5D;
                 l1++;
                 double d7 = i1 / 2D;
                 for(int j3 = 0; j3 < i1; j3++)
                 {
                     double d8;
                     double d9 = ((j3 - d7) * 8D) / d5;
                     if(d9 < 0.0D)
                     {
                         d9 *= -1D;
                     }
                     double d10 = field2[k1] / 512D;
                     double d11 = field3[k1] / 512D;
                     double d12 = (field1[k1] / 10D + 1.0D) / 2D;
                     if(d12 < 0.0D)
                     {
                         d8 = d10;
                     } else
                         if(d12 > 1.0D)
                         {
                             d8 = d11;
                         } else
                         {
                             d8 = d10 + (d11 - d10) * d12;
                         }
                     d8 -= 8D;
                     int k3 = 32;
                     if(j3 > i1 - k3)
                     {
                         double d13 = (j3 - (i1 - k3)) / (k3 - 1.0F);
                         d8 = d8 * (1.0D - d13) + -30D * d13;
                     }
                     k3 = 8;
                     if(j3 < k3)
                     {
                         double d14 = (k3 - j3) / (k3 - 1.0F);
                         d8 = d8 * (1.0D - d14) + -30D * d14;
                     }
                     ad[k1] = d8;
                     k1++;
                 }

             }

         }

         return ad;
     }

}