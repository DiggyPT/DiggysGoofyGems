package diggypt.mod.proxy;

import java.io.File;

import diggypt.mod.Config;
import diggypt.mod.Main;
import diggypt.mod.init.BlockInit;
import diggypt.mod.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "diggysgoofygems.cfg"));
        Config.readConfig();
    }
    
    public void init(FMLInitializationEvent e) {
    	System.out.println("Main.instance = " + Main.instance);
    }
    
    public void postInit(FMLPostInitializationEvent f) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
