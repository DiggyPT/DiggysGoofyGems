package diggypt.mod.world.gen;

import java.util.Random;

import diggypt.mod.Config;
import diggypt.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenerateOresBro implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if(world.provider.getDimension() == 0)
		{
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider)
	{
		//Basically, for the last numbers: min Y level, max Y level, then min vein size, max vein size - min vein size, amount of times it generates per chunk
		//generateOre(BlockInit.ORE_COPPER.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 4, 56, Config.rubyChances);
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int chances)
	{
		int deltaY = maxY - minY;
		
		/*for(int i = 0; i < chances; i++)
		{
			int posX = x + random.nextInt(16);
	        int posY = minY + random.nextInt(deltaY);
	        int posZ = z + random.nextInt(16);
	        
	        BlockPos pos = new BlockPos(posX, posY, posZ);
			
			WorldGenMinable generator = new WorldGenMinable(ore, veinSize, state -> {
		        Block block = state.getBlock();
		        return block == Blocks.STONE || block.getMaterial(state) == Material.ROCK;
		    });
			generator.generate(world, random, pos);
			System.out.println("Generated ore at: " + pos + "at block " + world.getBlockState(pos).getBlock().getRegistryName());
			
			//world.setBlockState(pos, ore, 2);
			//System.out.println("Manually set block at " + pos);
		}*/
		
		for (int i = 0; i < chances; i++) {
		    int posX = x + random.nextInt(16);
		    int posY = minY + random.nextInt(deltaY);
		    int posZ = z + random.nextInt(16);

		    BlockPos pos = new BlockPos(posX, posY, posZ);
		    IBlockState current = world.getBlockState(pos);

		    if (current.getBlock() == Blocks.STONE) {
		        world.setBlockState(pos, ore, 2);
		    }
		}
	}
}
