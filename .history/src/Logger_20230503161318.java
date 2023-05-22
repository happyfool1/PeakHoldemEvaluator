//package evaluate_streets;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/*- *********************************************************************************************
 * This Class is a better alternative than just System.out.println 
 * The log data is written to System.out.println and to two disk files.
 * 		A file that is temporary. A new one is created for each run of the application.
 * 		A cumulative file that is never erased and has new data appensed to it.
 * 
  * This class is called when there is an error and logs and analyzes a lot of data.
 * Invaluable in debugging.
 * It also is a great help in debug and testing.
 * 
 * @author PEAK_
 * 
 **********************************************************************************************/

public class Logger implements Constants {
	private static PrintStream logFile;

	static void logError(String msg) {
		final var reportSt = msg;

		System.out.println(reportSt);

		final var file = EvalData.applicationDirectory + "Log";

			final var filex = new File(file);
		if (!filex.exists()) {
			filex.mkdirs();
		}
	
		if (logFile == null) {
			try {
				logFile = new PrintStream(file);
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
	
		logFile.append(reportSt + "\r\n");
		logFile.flush();
		
		// Now append current log file to cumlative log file
		final var fileC = file + "logCumlative.txt";
		final var st = FileReadWrite.readFile(file);
		FileReadWrite.writeFileAppend(fileC, st + reportSt);
	}

	/*- 
	 *  Log messages to log file when there is no error.  
	 *  Write non error message to log file.
	 */
	static void log(String msg) {
		var reportSt = "";
		if (!msg.startsWith("//")) {
			reportSt = "//" + msg;
		} else {
			reportSt = msg;
		}

		System.out.println(reportSt);

		final var file = EvalData.applicationDirectory + "Log";
		final var filex = new File(file);
		if (!filex.exists()) {
			System.out.println("// makedir " + file);
			filex.mkdirs();
			System.out.println("//" + file);
			if (!filex.exists()) {
				System.out.println("// error " + file);
			}
		}
		System.out.println("//zzzA " + logFile + " " + file);
		if (logFile == null) {
			try {
				logFile = new PrintStream(file);
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		System.out.println("//zzzB " + logFile + " "+ file);
		logFile.append(reportSt + "\r\n");
		logFile.flush();

		// Now append current log file to cumlative log file
		final var fileC = EvalData.applicationDirectory + "logCumlative.txt";
		final var st = FileReadWrite.readFile(file);
		FileReadWrite.writeFileAppend(fileC, st + reportSt);
	}

	// Overloaded very rare usage
	static void log(double d) {
		log(String.valueOf(d));
	}

	static void log(int i) {
		log(String.valueOf(i));
	}

	static void log(boolean b) {
		if (b) {
			log("true");
		} else {
			log("fales");
		}
	}

	/*- -- Close log file. */
	static void closeLog() {
		if (logFile == null) {
			return;
		}
		logFile.flush();
		logFile.close();
	}

}
