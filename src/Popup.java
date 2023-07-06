//package peakholdemevaluator;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import javax.swing.JOptionPane;

public class Popup implements Constants {
	private Popup() {
		throw new IllegalStateException("Utility class");
	}

	/*- - Popup message. */
	public static void popup(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/*- - Popup message Yes or No Returns true if yes or cancel;. */
	public static boolean popupYes(String message) {
		final int result = JOptionPane.showConfirmDialog(null, message);
		return result == 0 || result == 1;
	}
}
