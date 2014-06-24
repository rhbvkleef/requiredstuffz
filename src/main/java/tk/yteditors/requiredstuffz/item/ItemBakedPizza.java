package tk.yteditors.requiredstuffz.item;

import tk.yteditors.requiredstuffz.reference.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemBakedPizza extends ItemFood{
	
	public ItemBakedPizza() {
		super(10, 4.0f, false);
		setUnlocalizedName("itemBakedPizza");
		setMaxStackSize(1);
		setTextureName(ModInfo.modId + ":" + getUnlocalizedName());
	}
	
}
