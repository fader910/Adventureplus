package adventurePlus;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BaseMod;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import adventurePlus.Core;
import adventurePlus.gui.GuiInformation;

public class mod_adventurePlus extends BaseMod {
	private World lastWorld = null;
	
	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void load() {
		Core.init(this);
		ModLoader.setInGameHook(this, true, true);
	}
	
	@Override
	public void keyboardEvent(KeyBinding event)
	{
		if(event == Core.keyBindings.get(0) && Core.mc.theWorld != null)
			Core.mc.displayGuiScreen(new GuiInformation());
	}
	
	@Override
	public boolean onTickInGame(float time, Minecraft minecraftInstance)
    {
		if(minecraftInstance.theWorld != lastWorld)
		{
			String worldName = minecraftInstance.theWorld.getWorldInfo().getWorldName();
			if(Core.config.generalProperties.containsKey("money." + worldName))
				Stats.money = Core.config.generalProperties.get("money." + worldName).getInt();
			else
				Stats.money = 0;
			 
			if(Core.config.generalProperties.containsKey("warning." + worldName))
				Stats.warning = Core.config.generalProperties.get("warning." + worldName).getInt();
			else
				Stats.warning = 0;
			lastWorld = minecraftInstance.theWorld;
		}
        return false;
    }

}
