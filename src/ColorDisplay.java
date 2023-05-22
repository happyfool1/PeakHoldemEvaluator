//package evaluate_streets;

/*-
	Here's an example Java class that displays the 50 popular colors 
	we listed earlier using the JLabel component:

 */
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ColorDisplay extends JFrame {

	public ColorDisplay() {
		super("Popular Colors");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(10, 5, 5, 5)); // 10 rows, 5 columns

		add(new ColoredLabel("red", Color.red));
		add(new ColoredLabel("blue", Color.blue));
		add(new ColoredLabel("green", Color.green));
		add(new ColoredLabel("yellow", Color.yellow));
		add(new ColoredLabel("orange", Color.orange));
		add(new ColoredLabel("pink", Color.pink));
		add(new ColoredLabel("magenta", Color.magenta));
		add(new ColoredLabel("cyan", Color.cyan));
		add(new ColoredLabel("gray", Color.gray));
		add(new ColoredLabel("darkGray", Color.darkGray));
		add(new ColoredLabel("lightGray", Color.lightGray));
		add(new ColoredLabel("white", Color.white));
		add(new ColoredLabel("black", Color.black));
		add(new ColoredLabel("ivory", new Color(255, 255, 240)));
		add(new ColoredLabel("beige", new Color(245, 245, 220)));
		add(new ColoredLabel("wheat", new Color(245, 222, 179)));
		add(new ColoredLabel("tan", new Color(210, 180, 140)));
		add(new ColoredLabel("khaki", new Color(195, 176, 145)));
		add(new ColoredLabel("silver", new Color(192, 192, 192)));
		add(new ColoredLabel("darkSlateGray", new Color(47, 79, 79)));
		add(new ColoredLabel("slateGray", new Color(112, 128, 144)));
		add(new ColoredLabel("lightSlateGray", new Color(119, 136, 153)));
		add(new ColoredLabel("navy", new Color(0, 0, 128)));
		add(new ColoredLabel("midnightBlue", new Color(25, 25, 112)));
		add(new ColoredLabel("cornflowerBlue", new Color(100, 149, 237)));
		add(new ColoredLabel("steelBlue", new Color(70, 130, 180)));
		add(new ColoredLabel("royalBlue", new Color(65, 105, 225)));
		add(new ColoredLabel("dodgerBlue", new Color(30, 144, 255)));
		add(new ColoredLabel("deepSkyBlue", new Color(0, 191, 255)));
		add(new ColoredLabel("skyBlue", new Color(135, 206, 235)));
		add(new ColoredLabel("lightSkyBlue", new Color(135, 206, 250)));
		add(new ColoredLabel("teal", new Color(0, 128, 128)));
		add(new ColoredLabel("darkCyan", new Color(0, 139, 139)));
		add(new ColoredLabel("cadetBlue", new Color(95, 158, 160)));
		add(new ColoredLabel("aquamarine", new Color(127, 255, 212)));
		add(new ColoredLabel("turquoise", new Color(64, 224, 208)));
		add(new ColoredLabel("mediumTurquoise", new Color(72, 209, 204)));
		add(new ColoredLabel("paleTurquoise", new Color(175, 238, 238)));
		add(new ColoredLabel("greenYellow", new Color(173, 255, 47)));
		add(new ColoredLabel("chartreuse", new Color(127, 255, 0)));
		add(new ColoredLabel("lawnGreen", new Color(124, 252, 0)));
		add(new ColoredLabel("limeGreen", new Color(50, 205, 50, 34)));
		add(new ColoredLabel("forestGreen", new Color(34, 139, 34)));
		add(new ColoredLabel("oliveDrab", new Color(107, 142, 35)));
		add(new ColoredLabel("darkKhaki", new Color(189, 183, 107)));
		add(new ColoredLabel("goldenRod", new Color(218, 165, 32)));
		add(new ColoredLabel("darkGoldenRod", new Color(184, 134, 11)));
		add(new ColoredLabel("saddleBrown", new Color(139, 69, 19)));
		add(new ColoredLabel("sienna", new Color(160, 82, 45)));
		add(new ColoredLabel("chocolate", new Color(210, 105, 30)));
		add(new ColoredLabel("peru", new Color(205, 133, 63)));
		add(new ColoredLabel("rosyBrown", new Color(188, 143, 143)));
		add(new ColoredLabel("indianRed", new Color(205, 92, 92)));
		add(new ColoredLabel("sandyBrown", new Color(244, 164, 96)));
		add(new ColoredLabel("lightSalmon", new Color(255, 160, 122)));
		add(new ColoredLabel("salmon", new Color(250, 128, 114)));
		add(new ColoredLabel("darkSalmon", new Color(233, 150, 122)));
		add(new ColoredLabel("orangeRed", new Color(255, 69, 0)));
		add(new ColoredLabel("tomato", new Color(255, 99, 71)));
		add(new ColoredLabel("coral", new Color(255, 127, 80)));
		add(new ColoredLabel("darkOrange", new Color(255, 140, 0)));
		add(new ColoredLabel("lightCoral", new Color(240, 128, 128)));
		add(new ColoredLabel("hotPink", new Color(255, 105, 180)));
		add(new ColoredLabel("deepPink", new Color(255, 20, 147)));
		add(new ColoredLabel("pink", new Color(255, 192, 203)));
		add(new ColoredLabel("lightPink", new Color(255, 182, 193)));
		add(new ColoredLabel("plum", new Color(221, 160, 221)));
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ColorDisplay().setVisible(true));
	}

	class ColoredLabel extends JLabel {
		public ColoredLabel(String name, Color color) {
			super(name);
			setOpaque(true);
			setBackground(color);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
}
