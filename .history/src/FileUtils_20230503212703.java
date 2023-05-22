//package evaluate_streets;

/*-  *****************************************************************************
 * Some simple file utilities.
* @author PEAK_
  ******************************************************************************/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

class FileUtils implements Constants {

	FileUtils() {
	}

	/*- Write a file */
	static <T> void writeFile(String filePath, T ret) {
		try {
			final var fileOut = new FileOutputStream(filePath);
			final var out = new ObjectOutputStream(fileOut);
			out.writeObject(ret);
			out.close();
		} catch (IOException i) {
			Logger.log("FileUtils() writeFile() IO Exception  " + filePath);
			i.printStackTrace();
		}
	}

	/*- Read file and return file rread or Null */
	static <T> T readFile(String filePath, T $) {
		if (!new File(filePath).exists()) {
			Logger.log("FileUtils() readFile() File does not exist  " + filePath);
			return null;
		}

		try (var fileIn = new FileInputStream(filePath); var in = new ObjectInputStream(fileIn)) {
			// check cast 
			$ = (T) in.readObject();
			
			} catch (IOException i) {
			Logger.log("FileUtils() readFile() IO Exception  " + filePath);
			i.printStackTrace();
		} catch (ClassNotFoundException i) {
			Logger.log("FileUtils() readFile() File not found " + filePath);
			i.printStackTrace();
			return null;
		}
		return $;
	}

	/*-
	 * Delete a file
	 * Arg0 - Path to file
	 */
	static boolean deleteFile(String filePath) {
		final var f = new File(filePath);
		return f.exists() && f.delete();
	}

	/*-
	* Delete all files an a directory.
	* Recursive, deletes all levels
	* Arg0 - File 
	*/
	static void deleteDir(File f) {
		for (var subFile : f.listFiles()) {
			if (!subFile.isDirectory()) {
				subFile.delete();
			} else {
				deleteDir(subFile);
			}
		}
		f.delete();
	}

	/*-
	* Delete all files an a directory.
	* Recursive, deletes all levels
	* Arg0 - Path to file
	*/
	static void deleteDir(String path) {
		final var file = new File(path);
		for (var subFile : file.listFiles()) {
			if (!subFile.isDirectory()) {
				subFile.delete();
			} else {
				deleteDir(subFile);
			}
		}
		file.delete();
	}

	/*-
	 * Create a new directory
	 */
	static boolean makeDirectory(String name) {
		final var filex = new File(name);
		if (!filex.exists()) {
			filex.mkdirs();
		}
		return filex.exists();
	}


	// method tounzip a file
	static void unzipFile(String zipFilePath, String unzipFilePath) {	
		try {
            new ZipFile(zipFilePath).extractAll(unzipFilePath);
        } catch (IOException i) {
            i.printStackTrace();
            System.exit(1);
        }
    }
//extract all 
	
	
 // method to zip a file
    static void zipFile(String zipFilePath, String zipFilePath2) {
        try {
            new ZipFile(zipFilePath).addFile(zipFilePath2);
        } catch (IOException i) {
            i.printStackTrace();
            System.exit(1);
        }
    }

	 }
    }


}
	
