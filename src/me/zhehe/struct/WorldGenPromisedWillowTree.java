package me.zhehe.struct;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;

public class WorldGenPromisedWillowTree
{
        private static class IWorld {
            public World world;
            public IWorld(World world) {
                this.world = world;
            }
            public boolean isAirBlock(int x, int y, int z) {
                return world.getBlockAt(x, y, z).getType() == Material.AIR;
            }
            public void setBlock(int x, int y, int z, BlockData bd) {
                world.getBlockAt(x, y, z).setBlockData(bd, false);
            }
            public Material getMaterial(int x, int y, int z) {
                return world.getBlockAt(x, y, z).getType();
            }
            public void setBlock(int x, int y, int z, Material material) {
                world.getBlockAt(x, y, z).setType(material, false);
            } 
        }
        
        private static Leaves leave = null;
        public static boolean generate(World world, Random random, int xx, int yy, int zz)
	{
		int var6;
                IWorld iworld = new IWorld(world);
                if(leave == null) {
                    leave = (Leaves) Bukkit.createBlockData(Material.OAK_LEAVES);
                    leave.setDistance(1);
                }

		for (var6 = random.nextInt(6) + 8; iworld.getMaterial(xx, yy - 1, zz) == Material.WATER; --yy)
		{
			;
		}

		if (yy >= 1 && yy + var6 + 1 <= 128)
		{
			int var10;
			int var11;
			int var12;

			{
				Material mvar8 = iworld.getMaterial(xx, yy - 1, zz);

				if ((mvar8 == Material.GRASS_BLOCK || mvar8 == Material.DIRT) && yy < 128 - var6 - 1)
				{
                                        iworld.setBlock(xx, yy - 1, zz, Material.DIRT);
					int var13;
					int var16;

					for (var16 = yy - 3 + var6; var16 <= yy + var6; ++var16)
					{
						var10 = var16 - (yy + var6);
						var11 = 2 - var10 / 2;

						for (var12 = xx - var11; var12 <= xx + var11; ++var12)
						{
							var13 = var12 - xx;

							for (int var14 = zz - var11; var14 <= zz + var11; ++var14)
							{
								int var15 = var14 - zz;

								if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || random.nextInt(2) != 0 && var10 != 0) && iworld.isAirBlock(var12, var16, var14))
								{
									iworld.setBlock(var12, var16, var14, leave);
								}
							}
						}
					}

					for (var16 = 0; var16 < var6; ++var16)
					{
						Material mvar10 = iworld.getMaterial(xx, yy + var16, zz);

						if (mvar10 == Material.AIR || mvar10 == Material.OAK_LEAVES || mvar10 == Material.WATER)
						{
							iworld.setBlock(xx, yy + var16, zz, Material.SPRUCE_LOG);
						}
					}

					for (var16 = yy - 3 + var6; var16 <= yy + var6; ++var16)
					{
						var10 = var16 - (yy + var6);
						var11 = 2 - var10 / 2;

						for (var12 = xx - var11; var12 <= xx + var11; ++var12)
						{
							for (var13 = zz - var11; var13 <= zz + var11; ++var13)
							{
								if (iworld.getMaterial(var12, var16, var13) == Material.OAK_LEAVES)
								{
									if (random.nextInt(3) == 0 && iworld.isAirBlock(var12 - 1, var16, var13))
									{
										generateVines(iworld, var12 - 1, var16, var13, 8);
									}

									if (random.nextInt(3) == 0 && iworld.isAirBlock(var12 + 1, var16, var13))
									{
										generateVines(iworld, var12 + 1, var16, var13, 2);
									}

									if (random.nextInt(3) == 0 && iworld.isAirBlock(var12, var16, var13 - 1))
									{
										generateVines(iworld, var12, var16, var13 - 1, 1);
									}

									if (random.nextInt(3) == 0 && iworld.isAirBlock(var12, var16, var13 + 1))
									{
										generateVines(iworld, var12, var16, var13 + 1, 4);
									}
								}
							}
						}
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	 private static void generateVines(IWorld iw, int par2, int xx, int yy, int zz)
	{
            World iworld = iw.world;
            if(!init) {
                init = true;
                vine1 = Bukkit.createBlockData("minecraft:vine[east=false,south=true,north=false,west=false,up=false]");
                vine2 = Bukkit.createBlockData("minecraft:vine[east=false,south=false,north=false,west=true,up=false]");
                vine4 = Bukkit.createBlockData("minecraft:vine[east=false,south=false,north=true,west=false,up=false]");
                vine8 = Bukkit.createBlockData("minecraft:vine[east=true,south=false,north=false,west=false,up=false]");
            }
            
		//this.setBlockAndMetadata(iworld, par2, xx, yy, Blocks.ivy.get().blockID, zz);
                BlockData vine;
            switch (zz) {
                case 1:
                    vine = vine1;
                    break;
                case 2:
                    vine = vine2;
                    break;
                case 4:
                    vine = vine4;
                    break;
                default:
                    vine = vine8;
                    break;
            }
                iworld.getBlockAt(par2, xx, yy).setBlockData(vine);
		int var6 = 8;

		while (true)
		{
			--xx;

			if (iworld.getBlockAt(par2, xx, yy).getType() != Material.AIR || var6 <= 0)
				return;

                        iworld.getBlockAt(par2, xx, yy).setBlockData(vine);
			--var6;
		}
	}
         
        private static boolean init = false;
        private static BlockData vine1, vine2, vine4, vine8;

}
