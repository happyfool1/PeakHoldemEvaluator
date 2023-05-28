import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Gaps implements Constants, java.io.Serializable{
static final long serialVersionUID = 1234567L;
/*- ******************************************************************************
 * This class defines a range of hands The hands are in a Container class. 
 * This array represents the commonly used 13 X 13 matrix to represent suited,
 * ofsuit, and pairs
 * An element is true if that card is in the range. * 
 * @author PEAK_
 ****************************************************************************** */

/*- ******************************************************************************
 * This array represents the commonly used 13 X 13 matrix to represent suited,
 * ofsuit, and pairs
 * -1 if not in range
 ****************************************************************************** */
int[] gapsArray = new int[HANDS];
int gapCount = 0;
boolean debug = false; // For test and debug of one specific instance

// constructor
Gaps() {
        for (int i = 0; i < HANDS; i++) {
            gapsArray[i] = -1;
        }
    }

    /*- ****************************************************************************
    * - Write Object to file - this
    * **************************************************************************** */
    void writeToFile(String path) {
        final var filename = path;
        // Saving of object in a file
        try (var file = new FileOutputStream(filename);
                var out = new ObjectOutputStream(file)) {
            // Method for serialization of object
            out.writeObject(this);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /*- ****************************************************************************
     * Read an object from file - this
     **************************************************************************** */
    void readFromFileX(String path) {
        Gaps object1 = null;

        // Reading the object from a file
        try (var file = new FileInputStream(path); var in = new ObjectInputStream(file)) {
            // Method for deserialization of object
            object1 = (Gaps) in.readObject();
        } catch (ClassNotFoundException | IOException i) {
            i.printStackTrace();
        }
    }

    /*- ****************************************************************************
     * Read a Range from a disk file. 
     * Arg0 - The full path name.
      *****************************************************************************/
    Gaps readFromFile(String path) {
        Gaps r = null;
        try (var fileIn = new FileInputStream(path); var in = new ObjectInputStream(fileIn)) {
            r = (HoleCardAction) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
            return r;
        }
        return r;
    }

}