package diggypt.mod.objects.items;

import java.util.List;

import javax.annotation.Nullable;

import diggypt.mod.Main;
import diggypt.mod.init.ItemInit;
import diggypt.mod.proxy.ClientProxy;
import diggypt.mod.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEatNow extends ItemFood implements IHasModel
{
	public ItemEatNow(String name, int amount, float saturation, boolean isWolfFood)
	{
		super(amount, saturation, isWolfFood); //BASICALLY it extends itemfood instead of item so now its a food somehow
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.diggyindustrytab);
		
		
		ItemInit.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	/**
	* With hurricanes and terrorists
	*Its been hard to just get by
	*Heres hopin the year 2 0 6
	*Turns out better than 2 0 5
	*
	*Well Kim Jong is throwin fits
	*And the shuttles on the fritz
	*Theres an intifada brewin in the Gaza Strip
	*
	*Oh the deficits a risin
	*Half o Europe hates my guts
	*And industrial America is goin bankrupt
	*
	*There is a great sense of urgency
	*Weve got to squash the insurgency
	*My approval ratings in a dive
	*Hope its not another year like 2 0 5
	*
	*Oh a leak investigations got my White House in a snarl
	*Theres a special prosecutor after my friend Karl
	*And our energy dependency has put me in a bind
	*Ah dont worry about Alaska itll be just fine

	*My appointee was a big flop
	*The housing market is bout to pop
	*With record profits at the pump
	*I drive an SUV and take it in the rump
	*All of our jobs gone overseas
	*Each week I make one dollar seventy three
	*From Katrina FEMA Gitmo too
	*The last thing I need now is the Avian Flu
	*
	*Cause every problem in the world
	*Lands right here on my desk
	*I tried to get to Crawford
	*Even there couldnt get no rest
	*
	*From pirates in Somalia
	*To that nut job in Iran
	*Its hard to rule the free world
	*But Im doin the best I can (Hard work)
	*
	*The ocean levels risin
	*As the ice caps melt awayyyy
	*Heres hopin the year 2 0 6
	*Has a few more brighter days
	*
	*With same sex marriage, stemcells
	*Scooter Libby Tom DeLay
	*Heres hopin the year 2 0 6
	*Brings a few more brighter days (Hah say that again)
	 */
}
