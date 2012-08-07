package gerard.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityEgg;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderLiving;
import net.minecraft.src.RenderPlayer;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import net.minecraft.src.WorldGenerator;
import gerard.adventureplus.EntityAdventure;
import gerard.adventureplus.GenChest;
import gerard.adventureplus.GuiMoney;
import gerard.loader.Core;
import gerard.loader.EntityInformation;
import gerard.loader.Plugin;

public class adventurePlus extends Plugin {
	private static int money = 0;
	private static int warning = 0;
	private int tkey = 0;
	private boolean hasDraw = false;
	public static int countDown = 10000;
	
	@Override
	public String pluginName() {
		return "adventurePlus";
	}
	
	@Override
	public String pluginVersion() {
		return "1.0.0";
	}

	@Override
	public void loadPlugin() {
		this.gameTick = true;
		this.onKeyChange = true;
		this.onBiomeDecorator = true;
		
		Block block = new Block(200, 224, Material.rock).setBlockName("thing").setHardness(2.0F).setResistance(10F);
		Core.registerBlock(block);
		
		Core.addShapelessRecipe(new ItemStack(block, 4), new Object[]{ Block.dirt });
		Core.addEntity(new EntityInformation(100, "Human", gerard.adventureplus.EntityAdventure.class, EntityInformation.Type.Monster, null).setSpawn(30, 20, 20));
		
		keyMoney = new KeyBinding("money", 50);
		keyGen = new KeyBinding("gen", 49);
		Core.addKey(this, keyMoney);
		Core.addKey(this, keyGen);
	}
	
	@Override
	public void onBiomeDecorator(World par1World, Random par2Random, int par3, int par4) { 
		
	}
	
	@Override
	public void onTranslate(String currentLanguage) {
		Core.addTranslate(keyMoney.keyDescription, "Money GUI");
		Core.addTranslate(keyGen.keyDescription, "Generate");
	}
	
	private KeyBinding keyMoney, keyGen;
	@Override
	public void gameTick() {
		minecraft.ingameGUI.drawString(minecraft.fontRenderer, "Adventure+", 5, 5, 0xffffff);
		minecraft.ingameGUI.drawString(minecraft.fontRenderer, "Warning level: " + getWarning() + "/10", 5, 15, 0xffffff);
		minecraft.ingameGUI.drawString(minecraft.fontRenderer, "Money: " + getMoney(), 5, 25, 0xffffff);
		minecraft.ingameGUI.drawString(minecraft.fontRenderer, "Count down: " + countDown, 5, 35, 0xffffff);
		
		countDown--;
		if(countDown == 0)
		{
			countDown = 10000;
			setWarning(-1);
		}
	}
	
	@Override
	public void onKeyChange(KeyBinding key) {
		Random rand = new Random();
		if(key == keyMoney)
			minecraft.displayGuiScreen(new GuiMoney(this));
	}

	public static int getMoney() {
		return money;
	}

	public static void addMoney(int _money) {
		money += _money;
	}

	public static int getWarning() {
		return warning;
	}

	public static void setWarning(int _warning) {
		if(_warning < 0 && warning != 0 || _warning > 0 && warning != 10)
			warning += _warning;
	}
	
}
