package tk.yteditors.requiredstuffz.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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

public class BlockOven extends Block {
	
	enum Texture {
		ON_HAS_ITEM, OFF_HAS_ITEM, ON_EMPTY, OFF_EMPTY
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon			blockIconFront, blockIconTop, blockIconSide;
	
	int						rotation;
	
	public final boolean	burning;
	public final boolean	hasPizza;
	
	public BlockOven(boolean burning, boolean hasPizza) {
		super(Material.rock);
		setStepSound(Block.soundTypeStone);
		setHardness(2f);
		setResistance(3.5f);
		setHarvestLevel("pickaxe", 0);
		this.burning = burning;
		this.hasPizza = hasPizza;
		
		if (burning) {
			this.setLightLevel(0.857f);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		if (burning) {
			if (hasPizza) {
				blockIconFront = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_on_filled");
			} else {
				blockIconFront = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_on_empty");
			}
		} else {
			if (hasPizza) {
				blockIconFront = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_off_filled");
			} else {
				blockIconFront = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_front_off_empty");
			}
		}
		blockIconTop = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_top");
		blockIconSide = register.registerIcon(ModInfo.modId + ":" + BlockNames.blockOven + "_side");
	}
	
	/**
	 * Return icons
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (metadata == 0 && side == 3) {
			return blockIconFront;
		} else if (side == 0 || side == 1) {
			return blockIconTop;
		} else if (metadata == side) {
			return blockIconFront;
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
		world.setBlockMetadataWithNotify(x, y, z, direction, 2);
		
	}
	
	public Item getItemDropped(World world, int x, int y, int z) {
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(RequiredStuffz.blockOvenOffEmpty);
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
			int l = world.getBlockMetadata(x, y, z);
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
}