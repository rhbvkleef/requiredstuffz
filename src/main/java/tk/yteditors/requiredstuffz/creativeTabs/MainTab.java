package tk.yteditors.requiredstuffz.creativeTabs;

import tk.yteditors.requiredstuffz.RequiredStuffz;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MainTab extends CreativeTabs{

	public MainTab() {
		super("Required stuffz");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(RequiredStuffz.blockOvenBurningFilled);
	}
	
}