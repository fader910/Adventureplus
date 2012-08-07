package adventurePlus.objects;

import java.util.ArrayList;

import adventurePlus.Core;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.forge.ITextureProvider;

public class itemEmpty extends Item implements ITextureProvider {
	private boolean hasEffect = false;
	
	public itemEmpty(String par1, int par2, int texture) {
		super(par2);
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
	
	public boolean hasEffect(ItemStack par1ItemStack)
    {
        return hasEffect;
    }
	
	public itemEmpty setStackSize(int max) {
		this.maxStackSize = max;
		return this;
	}
	
	public itemEmpty setEffect(boolean effect) {
		this.hasEffect = effect;
		return this;
	}
}