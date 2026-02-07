package diggypt.mod.objects.tools;

import diggypt.mod.Main;
import diggypt.mod.init.ItemInit;
import diggypt.mod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel {
	public ToolSword(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		
		
		ItemInit.ITEMS.add(this);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
