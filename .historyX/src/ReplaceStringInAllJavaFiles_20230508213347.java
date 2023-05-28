//package evaluate_streets;

/*-  ******************************************************************************
 * This is a simple utility to scan all java files in a directory and replace one string 
 * with another string for every occurance in every file.
 * To use, edit the directoryPath, searchString, and replaceString.
 * Code written by ChatGPT.
 * 
 * @author PEAK_
 ****************************************************************************** */
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*- 
* The main method takes the path to the directory, the search string, and the replace string as arguments. 
* It then uses the DirectoryStream class to get a stream of all .java files in the directory.
* For each .java file in the directory, the replaceInFile method is called with the file path, 
* search string, and replace string as arguments.
* The replaceInFile method reads the contents of the file as a string using the Files.readAllBytes method, 
* replaces all occurrences of the search string with the replace string using the replaceAll method, and writes 
* the replaced content back to the file using the Files.write method.
* Note that this implementation uses regular expressions to perform the string replacement, 
* so you need to make sure that the search string does not contain any regular expression special characters. 
* If the search string does contain regular expression special characters, you need to escape 
* them using the Pattern.quote method. Similarly, if the replace string contains any backslashes, 
* you need to escape them using the Matcher.quoteReplacement method.
 */
public class ReplaceStringInAllJavaFiles {
	public static void main(String[] args) {
		  final var directoryPath =
		 "C:\\users\\PEAK_\\git\\repository\\evaluate_streets\\src\\evaluate_streets";
		// final var directoryPath =
		// "C:\\users\\PEAK_\\git\\repository\\hand_history_analysis\\src\\hand_history_analysis";
		//
		// String directoryPath = "C:\\users\\PEAK_\\git\\repository\\game\\src\\game";
	//	final var directoryPath = "C:\\evaluate_streets\\evaluate\\src";
		final var searchString = "//package evaluate_streets;";
		final var replaceString = "i";

		try (var stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.java")) {
			for (var path : stream) {
				replaceInFile(path, searchString, replaceString);
			}
		} catch (IOException | DirectoryIteratorException e) {
			System.err.println(e);
		}
		System.out.println("ReplaceStringInAllJavaFiles complete " + directoryPath);
	}

	public static void replaceInFile(Path filePath, String searchString, String replaceString) throws IOException {
		final var fileContent = new String(Files.readAllBytes(filePath));
		final var replacedContent = fileContent.replaceAll(Pattern.quote(searchString),
				Matcher.quoteReplacement(replaceString));
		Files.write(filePath, replacedContent.getBytes());
	}
}
