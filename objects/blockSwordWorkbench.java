package adventurePlus.objects;

import adventurePlus.Core;
import adventurePlus.gui.GuiSword;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class blockSwordWorkbench extends blockEmpty {

	public blockSwordWorkbench(String par1, int par2, int par3, Material par4Material) {
		super(par1, par2, par3, par4Material);
	}
	
	@Override
	public int getBlockTextureFromSide(int par1)
    {
        if(par1 == 1)
        	return 0;
        else if(par1 == 0)
        	return 2;
        else
        	return 1;
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
        	Core.mc.displayGuiScreen(new GuiSword());
            return true;
        }
    }
	
}
