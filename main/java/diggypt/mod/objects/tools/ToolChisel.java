package diggypt.mod.objects.tools;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import diggypt.mod.Main;
import diggypt.mod.init.ItemInit;
import diggypt.mod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolChisel extends ItemTool implements IHasModel{
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

	//float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn
	public ToolChisel(String name, ToolMaterial material) {
		super(1.0F, 1.6F, material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		this.setMaxDamage(material.getMaxUses());
		
		ItemInit.ITEMS.add(this);
		// TODO Auto-generated constructor stub
	}
    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        Material material = state.getMaterial();
        return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
    
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	IBlockState graniteState = Blocks.STONE.getStateFromMeta(1);
    	IBlockState pGraniteState = Blocks.STONE.getStateFromMeta(2);
    	IBlockState dioriteState = Blocks.STONE.getStateFromMeta(3);
    	IBlockState pDioriteState = Blocks.STONE.getStateFromMeta(4);
    	IBlockState andesiteState = Blocks.STONE.getStateFromMeta(5);
    	IBlockState pAndesiteState = Blocks.STONE.getStateFromMeta(6);
    	
		//basically what to do when right click on a block???
        
        IBlockState blockState = worldIn.getBlockState(pos);
        Block block = blockState.getBlock();
        
        changeState(worldIn, pos, graniteState, pGraniteState, blockState, player, hand);
        changeState(worldIn, pos, dioriteState, pDioriteState, blockState, player, hand);
        changeState(worldIn, pos, andesiteState, pAndesiteState, blockState, player, hand);
        changeState(worldIn, pos, Blocks.SAND.getDefaultState(), Blocks.SANDSTONE.getDefaultState(), blockState, player, hand);
        changeState(worldIn, pos, Blocks.SAND.getStateFromMeta(1), Blocks.RED_SANDSTONE.getDefaultState(), blockState, player, hand);
        
        return EnumActionResult.PASS;
    }

    @Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
    
    public void changeState(World worldIn, BlockPos pos, IBlockState state1, IBlockState state2, IBlockState exiState, EntityPlayer player, EnumHand hand) //exiState = existent state
    {
    	ItemStack itemstack = player.getHeldItem(hand);
    	if (exiState == state1)
        {
        	worldIn.setBlockState(pos, state2);
        	itemstack.damageItem(1, player);
        } else { if (exiState == state2)
        	{
        	worldIn.setBlockState(pos, state1);
        	itemstack.damageItem(1, player);
        	}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Polishes rocks like granite, diorite and andesite. Also chisels sand.");
        tooltip.add("Can also revert polished stones and sandstone into their original versions.");
    }
}
