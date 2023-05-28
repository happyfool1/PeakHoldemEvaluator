//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_ ****************************************************************************** */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*-
 * This class will read an image file. Use new
 * ReadPNG("C:\\PeakHoldemDatabase\\CardsPNG\\kc.png");
 */
public class DisplayPNG extends Component implements Constants {
	private static BufferedImage img2;
	BufferedImage img;

	public DisplayPNG(String path) {
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
	}

	@Override
	public void paint(Graphics i) {
		i.drawImage(img, 0, 0, null);
	}

	public BufferedImage readPNG2(String path) {
		BufferedImage $ = null;
		try {
			$ = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		return $;
	}

	@Override
	public Dimension getPreferredSize() {
		return img == null ? new Dimension(100, 100) : new Dimension(img.getWidth(null), img.getHeight(null));
	}

	public ImageIcon loadImage() {
		return new ImageIcon("images/kc.png");
	}

	public static void main(String[] args) {
		final var f = new JFrame("Load Image Sample");

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent __) {
				System.exit(0);
			}
		});

		new ImageIcon("images/images/deck/kc.png");
		f.add(new DisplayPNG("images/images/deck/qh.png"));

		f.pack();
		f.setVisible(true);
	}

	private static void test() {
		/* Create an ARGB BufferedImage */
		BufferedImage img4 = null;
		final var path = "C:\\PeakHoldemDatabase\\CardsPNG\\kc.png";
		try {
			img4 = ImageIO.read(new File(path));
		} catch (IOException e) {
		}

		final int h = img4.getHeight(null);
		new BufferedImage(img4.getWidth(null), h, BufferedImage.TYPE_INT_ARGB).getGraphics().drawImage(img4, 0, 0,
				null);
	}
}
