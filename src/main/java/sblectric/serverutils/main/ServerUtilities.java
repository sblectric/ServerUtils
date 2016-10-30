package sblectric.serverutils.main;

import java.util.Collection;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

import sblectric.serverutils.config.UtilConfig;
import sblectric.serverutils.events.PlayerMessageHandler;
import sblectric.serverutils.filter.SpamFilter;
import sblectric.serverutils.ref.Log;
import sblectric.serverutils.ref.RefStrings;

/** Main mod class */
@Mod(modid = RefStrings.MODID, name = RefStrings.NAME, version = RefStrings.VERSION, serverSideOnly = true)
public class ServerUtilities {

	@Instance(RefStrings.MODID)
	public static ServerUtilities modInstance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		// get the config
		UtilConfig.getConfig(event.getSuggestedConfigurationFile());
		
		// apply the log filter
		try {
			LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
			Collection<LoggerConfig> lc = ctx.getConfiguration().getLoggers().values();
			for(LoggerConfig c : lc) {
				try {
					c.addFilter(new SpamFilter());
				} catch (Exception e) {
					Log.logger.error("Spam filter instance failed to apply: " + e);
				}
			}
			
			Log.logger.info("Spam filters enabled");
		} catch(Exception e) {
			Log.logger.error("Spam filters failed to apply: " + e);
		}
		
		// register the handler for chat events
		MinecraftForge.EVENT_BUS.register(new PlayerMessageHandler());
	}
	
	// make sure to accept clients without this mod!
	@NetworkCheckHandler
	public boolean networkCheck(Map<String, String> map, Side side) {
		return true;
	}

}
