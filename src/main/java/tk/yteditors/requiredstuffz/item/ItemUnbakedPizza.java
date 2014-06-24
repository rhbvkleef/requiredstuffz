package tk.yteditors.requiredstuffz.item;

import tk.yteditors.requiredstuffz.reference.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemUnbakedPizza extends ItemFood {
	public ItemUnbakedPizza() {
		super(2, 0.5f, false);
		setUnlocalizedName("itemUnbakedPizza");
		setMaxStackSize(1);
		setTextureName(ModInfo.modId + ":" + getUnlocalizedName());
	}
}