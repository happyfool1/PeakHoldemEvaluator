//package evaluate_streets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchAll implements Constants {
	/*- **************************************************************************** 
	* This is a simple but very useful utility that will search all files in this projects
	* directory for a String and display where that String is found. 
	* Got an error message and can not find it? Try this.
	* @author PEAK_
	*******************************************************************************/

	public static void main(String[] args) {
		// "C:\\users\\PEAK_\\git\\repository\\hand_history_analysis\\src\\hand_history_analysis";

		final var directoryPath = "C:\\PeakHoldemEvaluator\\PeakHoldemEvaluator\\src";
		// "C:\\users\\PEAK_\\git\\repository\\evaluate_streets\\src\\evaluate_streets";
		final var searchString = "EvalData.hi;

		searchInDirectory(directoryPath, searchString);
	}

	public static void searchInDirectory(String directoryPath, String searchString) {
		final var directory = new File(directoryPath);

		// Check if the given directory exists
		if (!directory.exists() || !directory.isDirectory()) {
			System.out.println("Invalid directory: " + directoryPath);
			return;
		}

		// Get all files in the directory
		final var files = directory.listFiles();

		for (var file : files) {
			// Check if the current file is a directory
			if (file.isDirectory()) {
				searchInDirectory(file.getAbsolutePath(), searchString);
			} else {
				// Check if the current file contains the search string
				if (fileContainsString(file, searchString)) {
					System.out.println("// " + file.getAbsolutePath());
				}
			}
		}
	}

	public static boolean fileContainsString(File file, String searchString) {
		try (var br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(searchString)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
