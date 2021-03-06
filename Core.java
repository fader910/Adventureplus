package adventurePlus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;

import adventurePlus.objects.*;

import net.minecraft.client.Minecraft;
import net.minecraft.src.BaseMod;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemCloth;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.Property;
import net.minecraft.src.forge.EnumHelper;

public class Core {
	public static String terrainPath = "/adventurePlus/resources/terrain.png", itemsPath = "/adventurePlus/resources/items.png";
	public static ArrayList<KeyBinding> keyBindings = new ArrayList<KeyBinding>();
	public static Configuration config;
	private static String log = "";	
	private static BaseMod mod;
	public static Minecraft mc;
	
	// Loading the mod
	public static void init(BaseMod _mod) {
		log("loading adventurePlus...");
		
		// Loading handy stuff
		mc = ModLoader.getMinecraftInstance();
		mod = _mod;
		
		// Checking Files and load config
		File folder = new File(Minecraft.getMinecraftDir(), "/adventurePlus");
		if(!folder.exists())
			folder.mkdir();
		File log = new File(Minecraft.getMinecraftDir(), "/adventurePlus/log.txt");
		if(!log.exists()) 
			try { log.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		File file = new File(Minecraft.getMinecraftDir(), "/adventurePlus/adventurePlus.cfg");
		config = new Configuration(file);
		config.load();
		
		// Loading the textures
		MinecraftForgeClient.preloadTexture(terrainPath);
		MinecraftForgeClient.preloadTexture(itemsPath);
		
		// Loading blocks, items and entities
		blockSwordWorkbench swordBench = new blockSwordWorkbench("swordbench", getBlockID("swordbench"), 0, Material.wood); 															ModLoader.addName(swordBench, "Sword Workbench"); 				ModLoader.registerBlock(swordBench);
		blockGrindstone grindStone = new blockGrindstone("grindstone", getBlockID("grindstone"), 0, Material.wood, tileEntityGrindstone.class); 										ModLoader.addName(grindStone, "Grindstone"); 					ModLoader.registerBlock(grindStone);						Item.itemsList[grindStone.blockID] = (new itemBlock("grindstone", getItemID("grindstone"), 1, grindStone.blockID));
		itemObsidianSword obsidianSword = new itemObsidianSword("obsidiansword", getItemID("obsidiansword"), 0, EnumHelper.addToolMaterial("OBSIDIAN", 3, 2000, 7F, 3, 10)); 			ModLoader.addName(obsidianSword, "Obsidian Sword"); 			MinecraftForge.setToolClass(obsidianSword, "sword", 3);
		
		tileEntityGrindstoneRenderer teGrindstone = new tileEntityGrindstoneRenderer();
		ModLoader.registerTileEntity(tileEntityGrindstone.class, "tilegrindstone", teGrindstone);
		
		// Loading recipes
		ModLoader.addRecipe(new ItemStack(swordBench), new Object[] {"PPP", "PSP", "PPP", 'P', Block.planks, 'S', Item.swordWood});
		ModLoader.addShapelessRecipe(new ItemStack(Item.itemsList[grindStone.blockID]), new Object[] { Block.dirt });
		
		// Loading keys
		Core.addKey("information", 50, "Show Information");
		
		log("adventurePlus has been loaded!");
	}
	
	// Getting easy a property
	public static Property getProperty(String name, String value) {
		Property prop = new Property();
		prop.name = name;
		prop.value = value;
		return prop;
	}
	
	// Logging to Log file
	public static void log(String output) {
		System.out.println("Adventure+ log: " + output);
		
		Calendar cal = Calendar.getInstance();
		log += cal.getTime() + ": " + output + "\n";
		
		Writer out = null;
	    try {
	    	out = new OutputStreamWriter(new FileOutputStream(Minecraft.getMinecraftDir() + "/adventurePlus/log.txt"), "UTF-8");
	    	out.write(log);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("We got a problem. Log has throwed a error!");
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				out.close();
			} catch (IOException e) {
				System.out.println("We got a problem. Log has throwed a error!");
				e.printStackTrace();
			}
	    }
	}
	
	// Some handy commands
	public static void addKey(String name, int standard, String value) {
		KeyBinding key = new KeyBinding("adventureplus." + name, standard);
		ModLoader.registerKey(mod, key, false);
        ModLoader.addLocalization(key.keyDescription, value);
        keyBindings.add(key);
	}
	
	// Loading Items and blocks
	// Always use this to load a block, for a custom ID in the config
	public static int getBlockID(String name) {
		int id = 0;
		if(config.blockProperties.containsKey(name))
			id = config.blockProperties.get(name).getInt();	
		
		if(id == 0)
		{
			for(int i = 1; i < Block.blocksList.length; i++)
			{
				if(Block.blocksList[i] == null)
				{
					id = i;
					break;
				}
			}
			
			config.blockProperties.put(name, getProperty(name, Integer.toString(id)));
			config.save();
		}
		
		log("Block ID " + id + " loaded for " + name);
		return id;
	}
	
	private static int startID = 200;
	public static int getItemID(String name) {
		int id = 0;
		if(config.itemProperties.containsKey(name))
			id = config.itemProperties.get(name).getInt();
		
		if(id == 0)
		{
			if(startID == 200 || Item.itemsList[startID] != null)
			{
				for(int i = startID; i < Item.itemsList.length; i++)
				{
					if(Item.itemsList[i] == null)
					{
						startID = i;
						break;
					}
				}
			}
			id = startID;
			startID++;
			
			config.itemProperties.put(name, getProperty(name, Integer.toString(id)));
			config.save();
		}
		
		log("Item ID " + id + " loaded for " + name);
		return id;
	}
}
