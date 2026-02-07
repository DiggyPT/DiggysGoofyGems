package diggypt.mod.objects.blocks;

import diggypt.mod.Main;
import diggypt.mod.init.BlockInit;
import diggypt.mod.init.ItemInit;
import diggypt.mod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExploding extends Block implements IHasModel
{
	public BlockExploding(String name, Material material, float hardness)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		setHardness(hardness);
		System.out.println("Adding block: " + name + " | " + this.getRegistryName());
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
		//Basically when the block is destroyed create an explosion
		System.out.println("omg block destroyed");
		worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 10, true);
    }
}
