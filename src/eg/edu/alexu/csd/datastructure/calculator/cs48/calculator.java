package eg.edu.alexu.csd.datastructure.calculator.cs48;

import eg.edu.alexu.csd.datastructure.calculator.ICalculator;

/**
 *
 * @author Aboeleneen
 *
 */
public class calculator implements ICalculator {
	/**
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * @param x
	 * @param y
	 */
	public int add(final int x, final int y) {
		// TODO Auto-generated method stub
		return (x + y);
	}

	/**
	 * @param x
	 * @param y
	 *
	 */

	@Override
	public float divide(final int x, final int y) {
		// TODO Auto-generated method stub
		if (y == 0) {
			throw new RuntimeException("Division by zero!");
		}

		return (float) x / y;
	}

}
