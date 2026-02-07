package diggypt.mod.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import diggypt.mod.Main;
import diggypt.mod.init.ItemInit;
import diggypt.mod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ToolMultitool extends ItemTool implements IHasModel {
	public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, 
			Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	//basically all of the blocks pickaxes, shovels and axes are good at destroying
	
	public ToolMultitool(String name, ToolMaterial material) {
		super(1.0F, -2.8F, material, EFFECTIVE_ON); //idk what the numbers mean
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		this.setMaxDamage(material.getMaxUses() * 5); //more durability!!
		this.attackDamage = 3.0F + material.getAttackDamage(); //more attack damage bc its also a sword
		
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		//basically what to do when right click on a block???
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
        	if (player.isSneaking())
        	{
        		IBlockState iblockstate = worldIn.getBlockState(pos);
        		Block block = iblockstate.getBlock();
        		
        		if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && block == Blocks.GRASS)
        		{
        			IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
        			worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

        			if (!worldIn.isRemote)
        			{
        				worldIn.setBlockState(pos, iblockstate1, 11);
        				itemstack.damageItem(1, player);
        			}
        			return EnumActionResult.SUCCESS;
        			//Basically if ure sneaking u make path block
        		}
        		else
            	{
        			return EnumActionResult.PASS;
            	}
        		} else {
        			int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
        			if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

        			IBlockState iblockstate = worldIn.getBlockState(pos);
        			Block block = iblockstate.getBlock();

        			if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up()))
        			{
        				if (block == Blocks.GRASS || block == Blocks.GRASS_PATH)
        				{
              	         this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
              	         return EnumActionResult.SUCCESS;
        				}

        				if (block == Blocks.DIRT)
        				{
        					switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
        					{
        					case DIRT:
        						this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
        						return EnumActionResult.SUCCESS;
        					case COARSE_DIRT:
                            	this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            	return EnumActionResult.SUCCESS;
							default:
								return EnumActionResult.FAIL;
        					}
        				}
        			}

                return EnumActionResult.PASS;
                //And basically if youre not sneaking you make farmland! So now the multitool can make both path and farmland
        	}
        }
    }
	
	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE 
				? super.getDestroySpeed(stack, state) : this.efficiency;
		//idk what this is lol but its in the axe code so i added it
	}
	
	public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        return MathHelper.hsvToRGB(Math.max(0.1F, (float) (3.0F - getDurabilityForDisplay(stack))) / 2.0F, 1.5F, 1.0F);
        // Change durability bar colour for shits and giggles
    }
	
	protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }
	
	public boolean canHarvestBlock(IBlockState blockIn)
    {
		//basically use harvest levels to check if the block can be harvested
        Block block = blockIn.getBlock();

        if (block == Blocks.OBSIDIAN)
        {
            return this.toolMaterial.getHarvestLevel() == 3;
        }
        else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE)
        {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK)
            {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE)
                {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE)
                    {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE)
                        {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE)
                            {
                                Material material = blockIn.getMaterial();

                                if (material == Material.ROCK || material == Material.IRON || block == Blocks.SNOW_LAYER || block == Blocks.SNOW || material == Material.ANVIL)
                                {
                                    return true;
                                }
                                //fixed your fucking code mojang. bestselling game ever and a little idiot can do better. smh my head
                            }
                            else
                            {
                                return this.toolMaterial.getHarvestLevel() >= 2;
                            }
                        }
                        else
                        {
                            return this.toolMaterial.getHarvestLevel() >= 1;
                        }
                    }
                    else
                    {
                        return this.toolMaterial.getHarvestLevel() >= 1;
                    }
                }
                else
                {
                    return this.toolMaterial.getHarvestLevel() >= 2;
                }
            }
            else
            {
                return this.toolMaterial.getHarvestLevel() >= 2;
            }
        }
        else
        {
            return this.toolMaterial.getHarvestLevel() >= 2;
        }
        return this.toolMaterial.getHarvestLevel() >= 2;
    }
	
	 public float getDestroySpeed(ItemStack stack, IBlockState state)
	    {
	        Material material = state.getMaterial();
	        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
	    }
}
