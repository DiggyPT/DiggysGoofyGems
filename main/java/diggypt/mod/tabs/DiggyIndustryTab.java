package diggypt.mod.tabs;

import diggypt.mod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class DiggyIndustryTab extends CreativeTabs
{
	//ahhhh
	public DiggyIndustryTab(String label)
	{
		super("diggyindustrytab");
		this.setBackgroundImageName("diggy_industry.png");
	}
	
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ItemInit.COPPER_INGOT);
	}
}
