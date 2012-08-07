package adventurePlus.objects;

import java.util.ArrayList;

import adventurePlus.Core;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.forge.ITextureProvider;

public class itemObsidianSword extends ItemSword implements ITextureProvider {

	public itemObsidianSword(String par1, int par2, int texture, EnumToolMaterial material) {
		super(par2, material);
		this.iconIndex = texture;
		this.setItemName(par1);
	}
	
	public String getTextureFile()
	{
		return Core.itemsPath;
	}
	
	public void addCreativeItems(ArrayList itemList)
    {
        itemList.add(new ItemStack(this));
    }
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
	    par1ItemStack.damageItem(1, par3EntityLiving);
	    par2EntityLiving.setFire(1);
	    return true;
	}

}
