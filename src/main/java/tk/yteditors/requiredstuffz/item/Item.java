package tk.yteditors.requiredstuffz.item;

import tk.yteditors.requiredstuffz.reference.ItemNames;
import tk.yteditors.requiredstuffz.util.RegisterHelper;

public class Item {

	public static net.minecraft.item.Item itemUnbakedPizza;
	public static net.minecraft.item.Item itemBakedPizza;

	public static void registerItems() {
		itemUnbakedPizza = new ItemUnbakedPizza().setUnlocalizedName(ItemNames.itemUnbakedPizza);
		itemBakedPizza = new ItemBakedPizza().setUnlocalizedName(ItemNames.itemBakedPizza);

		RegisterHelper.registerItem(itemUnbakedPizza);
		RegisterHelper.registerItem(itemBakedPizza);
	}
}
