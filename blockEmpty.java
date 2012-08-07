package adventurePlus;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ITextureProvider;

public class blockEmpty extends Block implements ITextureProvider {
	
	protected blockEmpty(String par1, int par2, int par3, Material par4Material) {
		super(par2, par3, par4Material);
		this.setBlockName(par1);
	}
	
	public String getTextureFile()
	{
		return Core.terrainPath;
	}
	
	public void addCreativeItems(ArrayList itemList)
    {
        itemList.add(new ItemStack(this));
    }
	
	public blockEmpty setName(String value) {
		ModLoader.addName(this, value);
		return this;
	}
	
	public blockEmpty setHardness(float par1)
    {
        this.blockHardness = par1;
        if (this.blockResistance < par1 * 5.0F)
        {
            this.blockResistance = par1 * 5.0F;
        }
        return this;
    }
	
	public blockEmpty setTickRandomly(boolean par1)
    {
        this.needsRandomTick = par1;
        return this;
    }
}