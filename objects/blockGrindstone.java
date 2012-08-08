package adventurePlus.objects;

import adventurePlus.Core;
import adventurePlus.gui.GuiSword;
import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemSword;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class blockGrindstone extends BlockContainer {
	private Class theClass;
	
	public blockGrindstone(String par1, int par2, int par3, Material par4Material, Class tClass) {
		super(par2, par3, par4Material);
		this.setBlockName(par1);
		theClass = tClass;
	}
		
	@Override
	public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	if(par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().isItemEnchantable() && !par5EntityPlayer.getCurrentEquippedItem().isItemEnchanted())
        	{
        		par5EntityPlayer.getCurrentEquippedItem().addEnchantment(Enchantment.sharpness, 2);
        		return true;
        	}
        	return false;
        }
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public int getRenderType() {
	  return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }

	@Override
	public TileEntity getBlockEntity() {
		try{
            return (TileEntity)theClass.newInstance();
	    }
	    catch(Exception exception){
	            throw new RuntimeException(exception);
	    }
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
        }
    }
	
}
