package sblectric.serverutils.events;

import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import sblectric.serverutils.config.UtilConfig;

public class PlayerMessageHandler {
	
	// Send player a logon message
	@SubscribeEvent
	public void onPlayerLogon(ServerConnectionFromClientEvent e) {
		for(String msg : UtilConfig.logonMessages) {
			e.getManager().sendPacket(new SPacketChat(new TextComponentString(msg)));
		}
	}

}
