package adventurePlus.gui;

import java.net.URI;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import adventurePlus.Core;
import adventurePlus.Stats;

import net.minecraft.src.GameSettings;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;

public class GuiSword extends GuiScreen{
	
    public void initGui()
    {
        controlList.clear();
        byte byte0 = -16;
        
        int i = (width - 248) / 2 + 10;
        int k = (height - 166) / 2 + 9;
        controlList.add(new GuiButtonItem(1, i, k));
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
            default:
                break;

            case 1: // Obsidian Sword
            	//if(!mc.thePlayer.inventory.hasItemStack(new ItemStack(???)))
            	//	mc.thePlayer.addChatMessage("You don't have the needed items");
            	
                mc.displayGuiScreen(null);
                mc.setIngameFocus();
                break;
        }
    }
    
    @Override
    public void drawDefaultBackground()
    {
        super.drawDefaultBackground();
        int i = mc.renderEngine.getTexture("/adventurePlus/resources/guiInformation.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - 248) / 2;
        int k = (height - 166) / 2;
        drawTexturedModalRect(j, k, 0, 0, 248, 166);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        int i = (width - 248) / 2 + 35;
        int k = (height - 166) / 2 + 9;
        fontRenderer.drawString("Obsidian Sword", i, k + 2, 0xFFFFFF);
        fontRenderer.drawString("Needs: Obsidian Recipe.", i, k + 10, 0xCCCCCC);
        super.drawScreen(par1, par2, par3);
    }
    
    @Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
