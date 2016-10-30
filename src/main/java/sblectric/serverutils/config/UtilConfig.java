package sblectric.serverutils.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

/** The mod configuration */
public class UtilConfig {
	
	private static Configuration config;
	
	public static void getConfig(File f) {
		config = new Configuration(f);
		readConfig();
	}
	
	public static List<String> spamBlackList;
	public static List<String> logonMessages;
	
	private static void readConfig() {
		config.load();
		spamBlackList = Arrays.asList(config.getStringList("Spam Blacklist", config.CATEGORY_GENERAL, new String[]{}, 
				"A list of strings, that if found in a logged message, will get filtered out."));
		logonMessages = Arrays.asList(config.getStringList("Logon Message", config.CATEGORY_GENERAL, new String[]{"Welcome to the Server!"}, 
				"A list of strings shown to connecting players on logon. Works with formatting codes."));
		if(config.hasChanged()) config.save();
	}

}
