//package evaluate_streets;
/*-  ******************************************************************************
 *  @author PEAK_
 *  ****************************************************************************** */

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MadeHands implements Constants {

	private static int x;
	private static int y;
	/*- Headers for the table. */
	private static final Object[] columnsOpAction = { "  Hand     " };
	/*- Actual data for the table in a 1d array. */
	private static final Object[][] dataHand = { { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " },
			{ "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " },
			{ "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " },
			{ "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " },
			{ "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " },
			{ "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " }, { "    " } };
	/*- True if frame has been opened already. */
	private boolean frameOpen;
	/*- The display frame and panels. */
	private JFrame frame;
	private JTable table;

	/*- Constructor */
	MadeHands(int xx, int yy) {
		x = xx;
		y = yy;
		doFrame();
		// TODO Evaluate.evaluateBoard();

	}

	/*- Close frame. */
	public void closeFrame() {
		frame.dispose();
	}

	/*- Create frame. */
	private void doFrame() {
		if (frameOpen) {
			return;
		}
		frameOpen = true;

		frame = new JFrame("Made hands ");
		final var f = new Font(Font.SERIF, Font.BOLD, 14);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent j0) {
				j0.getWindow().dispose();
			}
		});
		frame.setLocation(x, y);
		frame.setSize(300, 250);

		JFrame.setDefaultLookAndFeelDecorated(true);
		table = new JTable(dataHand, columnsOpAction);
		table.setFont(f);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(dataHand, columnsOpAction));

		final var pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(300, 250));

		table.getColumnModel().getColumn(0).setMinWidth(50);

		for (int i = MADE_NONE; i <= MADE_ROYAL_FLUSH; ++i) {
			setData("   ", i, 0);
		}
		int index = 0;
		for (int i = 0; i <= MADE_SIZE; ++i) {
	//			setData(BOARD_ARRAY_ST[i], index, 0);
	//			++index;
			}
		}
		++index;
		for (int i = 0; i < Evaluate.BOARD_SIZE; ++i) {
			if (EvalData.boardArray[i]) {
				setData(BOARD_ARRAY_ST[i], index, 0);
				dataHand[index][0] = BOARD_ARRAY_ST[i];
				++index;
			}
		}
		++index;
		for (int i = 0; i < Evaluate.HML_FLOP_SIZE; ++i) {
			// TODO if (EvalData.HML_Array[i]) {
			// setData(HML_FLOP_ST[i], index, 0);
			// ++index;
			// }
		}
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}

	public void setData(String s0, int i1, int i2) {
		table.getModel().setValueAt(s0, i1, i2);
	}

}
