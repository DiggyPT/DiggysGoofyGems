package diggypt.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import diggypt.mod.objects.tileentities.DataTileEntity;
import diggypt.mod.proxy.CommonProxy;
import diggypt.mod.tabs.DiggyIndustryTab;
import diggypt.mod.util.Reference;
import diggypt.mod.world.gen.GenerateOresBro;
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
	@Instance
	public static Main instance;
	
	public static final CreativeTabs diggyindustrytab = new DiggyIndustryTab("diggyindustrytab");
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println("Diggy's Goofy Gems is loading!");
		GameRegistry.registerWorldGenerator(new GenerateOresBro(), 3);
		GameRegistry.registerTileEntity(DataTileEntity.class, Reference.MODID + "_data_tile_entity");

	}
	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {}
	
	//John Pork Edging Challenge 24 hourse..... GONE WRONG????? (NOT CLICKBAIT) (GONE SEXUAL) (BIG BOX MART???)
	//for testing ores: /effect @s minecraft:night_vision 1000000 255 true
}

