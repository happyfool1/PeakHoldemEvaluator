//package evaluate_streets;
/*- ******************************************************************************
 * This is a Class that contains several helper methods.
 * What they all have in common is that they perform various calculations.
 * 
 * All of them were first generated bt ChatGPT and then edited by me to
 * be specific to definitions in this project.
 * 
 * @author PEAK_
*******************************************************************************/

public class RangeComparator implements Constants {

	/*-
	 * This class includes methods for getting the range advantage, 
	 * range intersection, range ratio, and combined range of two input ranges. 
	 * The getArrayIndexCard method you provided is not included in this 
	 * class as it is not necessary for the range comparison logic.
	 *	To use this class, you would first create an instance of RangeComparator 
	 * with two range arrays as inputs:
	 *
	 * int[] range1 = new int[169];
	 * int[] range2 = new int[169];
	 * RangeComparator rangeComparator = new RangeComparator(range1, range2);
	 * You could then call the methods on the rangeComparator object to get the desired comparison results:
	 */

	private final int[] range1;
	private final int[] range2;

	public RangeComparator(int[] range1, int[] range2) {
		this.range1 = range1;
		this.range2 = range2;
	}

	public double getRangeAdvantage() {

		double range1Total = 0;
		double range2Total = 0;
		for (int i = 0; i < range1.length; i++) {
			range1Total += range1[i];
			range2Total += range2[i];
		}
		final double range1Percentage = range1Total / (range1Total + range2Total);
		return (range1Percentage * 100) - 50;
	}

	public double getRangeIntersection() {
		for (int i = 0; i < range1.length; i++) {
		}
		// TODO double range1Percentage = intersectionTotal / range1Total;
		// double range2Percentage = intersectionTotal / range2Total;
		// return (range1Percentage * 100) + " / " + (range2Percentage * 100);
		return 0.0;
	}

	public double getRangeRatio() {
		double range1Total = 0;
		double range2Total = 0;
		for (int i = 0; i < range1.length; i++) {
			range1Total += range1[i];
			range2Total += range2[i];
		}
		final double ratio = range1Total / range2Total;
		// TODO if (ratio < 1) {
		// return "1:" + (1 / ratio);
		// } else {
		// return ratio + ":1";
		// }
		return 0.0;
	}

	public int[] getCombinedRange() {
		final int[] combinedRange = new int[range1.length];
		for (int i = 0; i < range1.length; i++) {
			combinedRange[i] = Math.max(range1[i], range2[i]);
		}
		return combinedRange;
	}

	private static void test() {
		final int[] range1 = new int[169];
		final int[] range2 = new int[169];
		final var rangeComparator = new RangeComparator(range1, range2);

		
			final double rangeRatio = rangeComparator.getRangeRatio();
			}

}
