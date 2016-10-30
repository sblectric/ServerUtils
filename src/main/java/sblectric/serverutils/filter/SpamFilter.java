package sblectric.serverutils.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import sblectric.serverutils.config.UtilConfig;

/** The spam filter class for all logging */
public class SpamFilter implements Filter {
	
	/** All other filtering methods call this one */
	public static Result filter(String message) {
		for(String s : UtilConfig.spamBlackList) {
			if (message.toLowerCase().contains(s.toLowerCase())) {
				return Result.DENY;
			}
		}
		return null;
	}

	@Override
	public Result filter(LogEvent event) {
		return filter(event.getMessage().getFormattedMessage());
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
		return filter(msg);
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Message msg, Throwable arg4) {
		return filter(msg.getFormattedMessage());
	}

	@Override
	public Result filter(Logger arg0, Level arg1, Marker arg2, Object arg3, Throwable arg4) {
		return null;
	}

	@Override
	public Result getOnMatch() {
		return null;
	}

	@Override
	public Result getOnMismatch() {
		return null;
	}

}
