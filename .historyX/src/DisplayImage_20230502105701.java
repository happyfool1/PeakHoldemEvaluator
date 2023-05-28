//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayImage extends JFrame implements Constants {

	public DisplayImage() {

		initUI();
	}

	private void initUI() {

		final var ii = loadImage();

		createLayout(new JLabel(ii));

		setTitle("Image");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private ImageIcon loadImage() {

		return new ImageIcon("images/images/deck/qh.png");
	}

	private void createLayout(JComponent... arg) {

		final var pane = getContentPane();
		final var gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

		gl.setVerticalGroup(gl.createParallelGroup().addComponent(arg[0]));

		pack();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> new DisplayImage().setVisible(true));
	}
}
