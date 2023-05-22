//package evaluate_streets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/*- ******************************************************************************
 *  @author PEAK_ 
 * ****************************************************************************** */
 public class GUIEditRanges extends JFrame implements ActionListener ,Constants {

    /*- **************************************************************************** 
	* Main
	*******************************************************************************/
	public static void main(String[] args) {
	
		final var myFrame = new GUI();
		 myFrame();
	}

    
/*- **************************************************************************** 
	* Frame
*******************************************************************************/
	public static void myFrame() {
		private JPanel mainPanel;
		mainPanel = new JPanel(null);
		mainPanel.setPreferredSize(new Dimension(1150, 550));
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		final var action = e.getActionCommand();
	}

 }
