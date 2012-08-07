package adventurePlus.gui;

import java.net.URI;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import adventurePlus.Stats;

import net.minecraft.src.GameSettings;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.StatCollector;

public class GuiInformation extends GuiScreen{
	
    public void initGui()
    {
        controlList.clear();
        byte byte0 = -16;
        controlList.add(new GuiButton(1, width / 2 + 2, (height - 166) / 2 + 132, 114, 20, "Exit"));
        controlList.add(new GuiButton(2, width / 2 - 116, (height - 166) / 2 + 132, 114, 20, "+ Warninglevel"));
        controlList.add(new GuiButton(3, width / 2 - 116, (height - 166) / 2 + 109, 233, 20, "Give money to another player"));
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id)
        {
            default:
                break;

            case 2: // + Warninglevel
            	mc.thePlayer.addChatMessage("Oh no... People saw what you did!");
            	Stats.addWarning(1);
                mc.displayGuiScreen(null);
                mc.setIngameFocus();
                break;

            case 1: // Exit
                par1GuiButton.enabled = false;
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
        int i = (width - 248) / 2 + 10;
        int k = (height - 166) / 2 + 9;
        fontRenderer.drawString("Adventure+ Information", i, k, 0xFFFFFF);
        fontRenderer.drawString("Currently you have: " + Stats.getMoney() + " money.", i, k + 10, 0xCCCCCC);
        fontRenderer.drawString("With a warning level of: " + Stats.getWarning() + ".", i, k + 20, 0xCCCCCC);
        
        fontRenderer.drawString("Help", i, k + 34, 0xFFFFFF);
        fontRenderer.drawString("You can get money with trade or killing people.", i, k + 44, 0xCCCCCC);
        fontRenderer.drawString("You get warning levels with killing people.", i, k + 54, 0xCCCCCC);
        super.drawScreen(par1, par2, par3);
    }
    
    @Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
