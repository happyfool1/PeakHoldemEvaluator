//package peakholdemevaluator;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Crash extends RuntimeException implements Constants {

	/*-  ******************************************************************************
	 * This Class is used to assist in testing and debug. 
	 * When a terminal error ( program bug ) is detected then this method can be 
	 * called to write a message to the log file.
	 * 		A message supplied by the calling method.
	 * 		A stack trace caused by a forced runtime exception.
	 * 
	 * The stack trace will show the path of calls that got us here.
	 * 	 
	 * Arg0 - Message to add
	 * 
	 * @author PEAK_
	 ****************************************************************************** */
	private static final int[] k = { 0 };

	private Crash() {
		throw new IllegalStateException("Utility class");
	}

	public static void log(String message) {
		try {
			// Throw a RuntimeException to force an exception
			throw new RuntimeException("Forced exception");
		} catch (Exception e) {
			// Save stack trace to a file
			final var sw = new StringWriter();
			final var pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			final var stackTrace = sw.toString();
			final var st = new StringBuilder().append("\r\n//").append(message).append("\r\nStack trace\r\n")
					.append(stackTrace).toString();
			System.out.println(st);
			Logger.log(st);
		}
		k[99] = 0;
		System.exit(0);
	}

}
