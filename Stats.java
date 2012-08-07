package adventurePlus;

import net.minecraft.src.ModLoader;

public class Stats {
	public static int money = 0, warning = 0;

	public static int getMoney() {
		return money;
	}

	public static void addMoney(int _money) {
		Core.log("Adding money: " + _money);
		money += _money;
		
		String worldName = ModLoader.getMinecraftInstance().theWorld.getWorldInfo().getWorldName();
		Core.config.generalProperties.put("money." + worldName, Core.getProperty("money." + worldName, Integer.toString(warning)));
		Core.config.save();
	}

	public static int getWarning() {
		return warning;
	}

	public static void addWarning(int _warning) {
		Core.log("Adding a warning: " + _warning);
		if(_warning < 0 && warning != 0 || _warning > 0 && warning != 10)
		{
			warning += _warning;
			
			String worldName = ModLoader.getMinecraftInstance().theWorld.getWorldInfo().getWorldName();
			Core.config.generalProperties.put("warning." + worldName, Core.getProperty("warning." + worldName, Integer.toString(warning)));
			Core.config.save();
		}
	}
}
