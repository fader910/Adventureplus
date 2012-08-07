package adventurePlus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.Property;

public class Core {
	public static String terrainPath = "/adventurePlus/resources/terrain.png", itemsPath = "/adventurePlus/resources/items.png";
	public static Configuration config;
	private static String log = "";
	
	// Loading the mod
	public static void init() {
		log("loading adventurePlus...");
		
		File folder = new File(Minecraft.getMinecraftDir(), "/adventurePlus");
		if(!folder.exists())
			folder.mkdir();
		File log = new File(Minecraft.getMinecraftDir(), "/adventurePlus/log.txt");
		if(!log.exists()) 
			try { log.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		File file = new File(Minecraft.getMinecraftDir(), "/adventurePlus/adventurePlus.cfg");
		config = new Configuration(file);
		config.load();
		
		MinecraftForgeClient.preloadTexture(terrainPath);
		MinecraftForgeClient.preloadTexture(itemsPath);
		
		Block newMelon = Core.loadBlock("newMelon", 1, Material.glass).setName("Omnom");
				
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
	
	// Loading Items and blocks
	// Always use this to load a block, for a custom ID in the config
	public static int getBlockID() {
		for(int i = 1; i < Block.blocksList.length; i++)
		{
			if(Block.blocksList[i] == null)
				return i;
		}
		return 0;
	}
	
	public static int getItemID() {
		for(int i = 1; i < Item.itemsList.length; i++)
		{
			if(Item.itemsList[i] == null)
				return i;
		}
		return 0;
	}
	
	public static blockEmpty loadBlock(String name, int texture, Material material) {
		int id = 0;
		if(config.blockProperties.containsKey(name)) {
			id = config.blockProperties.get(name).getInt();
		} else {
			id = getBlockID();
			config.blockProperties.put(name, getProperty(name, Integer.toString(id)));
			config.save();
		}
		blockEmpty block = new blockEmpty(name, id, texture, material);
		ModLoader.registerBlock(block);
		log("Block " + name + " with ID " + id + " has been loaded.");
		return block;
	}
	
	public static itemEmpty loadItem(String name, int texture) {
		int id = 0;
		if(config.itemProperties.containsKey(name)) {
			id = config.itemProperties.get(name).getInt();
		} else {
			id = getItemID();
			config.itemProperties.put(name, getProperty(name, Integer.toString(id)));
			config.save();
		}
		itemEmpty item = new itemEmpty(name, id, texture);
		log("Item " + name + " with ID " + id + " has been loaded.");
		return item;
	}
}