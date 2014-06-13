package client.utils;

import org.apache.log4j.Logger;

public class LoggingHelper {

	public static void debug(Logger log, String s, Object... args) {
		log.debug(String.format(s, args));
	}

	public static void info(Logger log, String s, Object... args) {
		log.info(String.format(s, args));
	}

	public static void warn(Logger log, String s, Object... args) {
		log.warn(String.format(s, args));
	}

	public static void error(Logger log, String s, Object... args) {
		log.error(String.format(s, args));
	}
}
