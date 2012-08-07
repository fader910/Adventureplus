package adventurePlus.objects;

import java.util.ArrayList;
import java.util.List;

import adventurePlus.Core;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ITextureProvider;

public class blockEmpty extends Block implements ITextureProvider {
	
	public blockEmpty(String par1, int par2, int par3, Material par4Material) {
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
}