package diggypt.mod.init;

import java.util.ArrayList;
import java.util.List;

import diggypt.mod.objects.items.ItemBase;
import diggypt.mod.objects.tools.ToolHoe;
import diggypt.mod.objects.tools.ToolMultitool;
import diggypt.mod.objects.tools.ToolPickaxe;
import diggypt.mod.objects.tools.ToolShovel;
import diggypt.mod.objects.tools.ToolSword;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Ingots
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot");

    public static void register(IForgeRegistry<Item> registry) {
        for (Item item : ITEMS) {
            registry.register(item);
        }
    }
}
