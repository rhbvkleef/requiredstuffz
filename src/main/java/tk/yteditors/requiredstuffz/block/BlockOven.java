package tk.yteditors.requiredstuffz.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tk.yteditors.requiredstuffz.RequiredStuffz;
import tk.yteditors.requiredstuffz.reference.BlockNames;
import tk.yteditors.requiredstuffz.reference.ModInfo;
import tk.yteditors.requiredstuffz.tileentity.TileEntityOven;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOven extends BlockContainer {
	
	enum Texture {
		ON_HAS_ITEM, OFF_HAS_ITEM, ON_EMPTY, OFF_EMPTY
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon			blockIconFrontOffEmpty, blockIconFrontOffFilled, blockIconFrontOnEmpty, blockIconFrontOnFilled,
							blockIconTop, blockIconSide;
	
	int						rotation;
	
	public final boolean	burning;
	
	public BlockOven(boolean burning) {
		super(Material.rock);
		setStepSound(Block.soundTypeStone);
		setHardness(2f);
		setResistance(3.5f);
		setHarvestLevel("pickaxe", 0);
		this.burning = burning;
		
		if (burning) {
			this.setLightLevel(0.857f);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		
		blockIconFrontOnFilled= register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_on_filled");
		blockIconFrontOnEmpty = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_on_empty");
		blockIconFrontOffFilled = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_off_filled");
		blockIconFrontOffEmpty = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_off_empty");
		
		blockIconTop = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_top");
		blockIconSide = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_side");
	}
	
	/**
	 * Return icons
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		int sideMeta = getDirection(metadata);
		boolean hasItem = getHasItem(metadata);
		
		if (metadata == 0 && side == 3) {
			return burning ? blockIconFrontOnEmpty : blockIconFrontOffEmpty;
		} else if (side == 0 || side == 1) {
			return blockIconTop;
		} else if (sideMeta == side) {
			if(burning){
				return hasItem ? blockIconFrontOnFilled : blockIconFrontOnEmpty;
			}else{
				return hasItem ? blockIconFrontOffFilled : blockIconFrontOffEmpty;
			}
		} else {
			return blockIconSide;
		}
	}
	
	/**
	 * Set block orientation according to player's face
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
		byte direction = 0;
		int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
		
		if (facing == 0) {
			direction = 2;
		}
		if (facing == 1) {
			direction = 5;
		}
		if (facing == 2) {
			direction = 3;
		}
		if (facing == 3) {
			direction = 4;
		}
		
		world.setBlockMetadataWithNotify(x, y, z, getMetadata(false, getMetadata(false, direction-2)), 2);
	}
	
	public Item getItemDropped(World world, int x, int y, int z) {
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(RequiredStuffz.blockOvenOff);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityOven();
	}
	
	public static void updateBlockState(boolean burning, World world, int x, int y, int z) {
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		
		if (burning) {
			int l = getDirection(world.getBlockMetadata(x, y, z));
			float f = x + 0.5F;
			float f1 = y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float f2 = z + 0.5F;
			float f3 = 0.52F;
			float f4 = random.nextFloat() * 0.6F - 0.3F;
			
			if (l == 4) {
				world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (l == 5) {
				world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			} else if (l == 2) {
				world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			} else if (l == 3) {
				world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
		
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityOven();
	}
	
	public boolean getHasItem(int metadata){
		return metadata < 4 ? false : true;
	}
	
	
	
	public int getDirection(int metadata){
		switch(metadata) {
			case 0: case 4:
				return 2;
			case 1: case 5:
				return 3;
			case 2: case 6:
				return 4;
			case 3: case 7:
				return 5;
			default:
				return 5;
		}
	}
	
	public int getMetadata(boolean hasItem, int direction){
		return hasItem ? direction + 4 : direction;
	}
	
}
