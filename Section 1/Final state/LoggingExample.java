package com.packt;

import org.apache.log4j.*;

public class LoggingExample
{
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(LoggingExample.class.getName());

	public static void main(String[] args)
	{
		BasicConfigurator.configure();

		log.setLevel(Level.ERROR);

		log.debug("Hello this is a debug message");
		log.info("Hello this is an info message");
		log.trace("Trace Message!");
		log.warn("Warning Message!");
		log.error("Error Message!");
		log.fatal("Fatal Error Message!");
	}
}
