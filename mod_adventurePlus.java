package adventurePlus;

import net.minecraft.src.BaseMod;
import adventurePlus.Core;

public class mod_adventurePlus extends BaseMod {

	@Override
	public String getVersion() {
		return "1.0.0";
	}

	@Override
	public void load() {
		Core.init();
	}

}
