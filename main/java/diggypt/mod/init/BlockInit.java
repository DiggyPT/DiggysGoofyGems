package diggypt.mod.init;

import java.util.ArrayList;
import java.util.List;

import diggypt.mod.objects.blocks.BlockBase;
import diggypt.mod.objects.blocks.BlockCounter;
import diggypt.mod.objects.blocks.BlockExploding;
import diggypt.mod.objects.blocks.BlockOres;
import diggypt.mod.objects.blocks.BlockRotateable;
import diggypt.mod.objects.blocks.BlockRubyOre;
import diggypt.mod.objects.blocks.BlockTurboCounter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block COPPER_BLOCK = new BlockBase("copper_block", Material.IRON, 5f);

}