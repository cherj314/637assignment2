package org.jfree.data.test;


import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
	}
	
	/*
calculateColumnTotal

public static double calculateColumnTotal(Values2D data, int column)
    Returns the sum of the values in one column of the supplied data table. With invalid input, a total of zero will be returned.

    Parameters:
        data - the table of values (null not permitted).
        column - the column index (zero-based). 
    Returns:
        The sum of the values in the specified column. 
    Throws:
        InvalidParameterException - if invalid data object is passed in.
	 */
	
	@Test
	public void calcColTotalTest1() {
		fail("git good")
	}
	
	/*	
	calculateRowTotal

	public static double calculateRowTotal(Values2D data, int row)

    Returns the sum of the values in one row of the supplied data table. With invalid input, a total of zero will be returned.

    Parameters:
        data - the table of values (null not permitted).
        row - the row index (zero-based). 
    Returns:
        The total of the values in the specified row. 
    Throws:
        InvalidParameterException - if invalid data object is passed in.
 	*/
	@Test
	public void calcRowTotalTest1() {
		fail("git good")
	}

	
	/*
	createNumberArray

	public static java.lang.Number[] createNumberArray(double[] data)

    Constructs an array of Number objects from an array of double primitives.

    Parameters:
        data - An array of double primitives (null not permitted). 
    Returns:
        An array of Number objects. 
    Throws:
        InvalidParameterException - if invalid data object is passed in.
	*/
	@Test
	public void createNumArrTest1() {
		fail("git good")
	}

	
	/*
	createNumberArray2D

	public static java.lang.Number[][] createNumberArray2D(double[][] data)

    Constructs an array of arrays of Number objects from a corresponding structure containing double primitives.

    Parameters:
        data - An array of double primitives (null not permitted). 
    Returns:
        An array of Number objects. 
    Throws:
        InvalidParameterException - if invalid data object is passed in.
	*/
	
	@Test
	public void createNumArr2DTest1() {
		fail("git good")
	}

	
	/*
	getCumulativePercentages

	public static KeyedValues getCumulativePercentages(KeyedValues data)

    Returns a KeyedValues instance that contains the cumulative percentage values for the data in another KeyedValues instance. The cumulative percentage is each value's cumulative sum's portion of the sum of all the values.
    eg:
    Input:

    Key  Value
    0        5
    1        9
    2        2

    Returns:

    Key  Value
    0     0.3125 (5 / 16)
    1     0.875 ((5 + 9) / 16)
    2     1.0 ((5 + 9 + 2) / 16)

    The percentages are values between 0.0 and 1.0 (where 1.0 = 100%).

    Parameters:
        data - the data (null not permitted). 
    Returns:
        The cumulative percentages. 
    Throws:
        InvalidParameterException - if invalid data object is passed in.


	 */
	
	@Test
	public void testCumPercent() {
		fail("Not yet implemented");
	}
	
	
	
	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}
	

}
