package diggypt.mod.objects.blocks;

import java.util.Random;

import diggypt.mod.Main;
import diggypt.mod.init.BlockInit;
import diggypt.mod.init.ItemInit;
import diggypt.mod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRubyOre extends Block implements IHasModel
{
	public BlockRubyOre(String name, Material material, float hardness)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		setHardness(hardness);
		this.setDefaultState(this.blockState.getBaseState());
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
	public IBlockState getStateFromMeta(int meta) {
	    return this.getDefaultState(); // if you only have one variant
	}

	@Override
	public int getMetaFromState(IBlockState state) {
	    return 0;
	}
	
	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
	    return new net.minecraft.block.state.BlockStateContainer(this);
	}
}
