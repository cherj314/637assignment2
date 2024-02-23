package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    /*
     * choose 5 out of 15 methods for testing:
     * combine
     * constrain
     * contains
     * equals
     * expand
     * expandToInclude
     * getCentralValue
     * getLength
     * getLowerBound
     * getUpperBound
     * hashCode
     * intersects
     * shift
     * shift overloaded
     * toString
     * 
     * Testing most of the others relies on the method get upper which is broken....
     */
    @Test
    public void testContructor_CreatesObj() {
    	Range range = new Range(0.0, 2.0);
    	assertNotNull("No object was created", range);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructor_LowerGreaterThanUpperParameter() {
    	Range range = new Range(3.0, 2.0);
    }
  
    // HOW/SHOULD_WE TEST HOW THE CODE HANDLES INVALID PARAMETERS I.E. INT, STRING
    // the javadoc isnt exactly project requirements...
    // hard to know the intention of some of the methods just from javadoc.
    
    /* 
     * Lower bound method tests
     */
    @Test
    public void testGetLowerBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			2.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_WithOneNegitiveParameter() {
    	Range range = new Range(-603.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound with a single negitive paramter",
    			-603.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_WithBothNegitiveParameter() {
    	Range range = new Range(-60.5,-2.7);
    	assertEquals("Unexpected behaviour in method getLowerBound with two negitive parameters",
    			-60.5, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_MinValue() {
    	Range range = new Range(Double.MIN_VALUE, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			Double.MIN_VALUE, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBound_NegInfValue() {
    	Range range = new Range(Double.NEGATIVE_INFINITY, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			Double.NEGATIVE_INFINITY, range.getLowerBound(),0.0000001);
    }
    
    
    /* upper bounds method tests 
     * Seems to be returning lower bound 
     */
    @Test
    public void testGetUpperBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound",
    			5.0, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_WithLargeValue() {
    	Range range = new Range(2.1, 123456789.5);
    	assertEquals("Unexpected behaviour in method getUpperBound with large upper value",123456789.5, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_WithMaxValue() {
    	Range range = new Range(5.0,Double.MAX_VALUE);
    	assertEquals("Unexpected behaviour in method getUpperBound with max double value",
    			Double.MAX_VALUE, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBound_PosInfValue() {
    	Range range = new Range(5.0, Double.POSITIVE_INFINITY);
    	assertEquals("Unexpected behaviour in method getUpperBound with positive infinity",
    			Double.POSITIVE_INFINITY, range.getUpperBound(),0.0000001);
    }

    
    /* getCentralValue method tests 
     * 
     */
    @Test
    public void testGetCentralValue_WtihRangeNegOneAndOne() {
    	Range range = new Range(-1,1);
        assertEquals("The central value of -1 and 1 should be 0",
        0, range.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testGetCentralValue_NegMedianCalc() {
    	Range range = new Range(-9,0);
    	assertEquals("The central value of -9 and 0 should be -4.5",
    	-4.5, range.getCentralValue(), .000000001d);		    
    }
    
    @Test
    public void testGetCentralValue_PosMedianCalc() {
    	Range range = new Range(45.0,6900.0);
    	assertEquals("The central value of -9 and 0 should be 3427.5",
    	3472.5, range.getCentralValue(), .000000001d);		    
    }

    
    /* getLength method tests
     * 
     */
    @Test
    public void testGetLength() {
        Range range = new Range(0.0, 10.0);
        assertEquals("Unexpected behaviour in get length method",10.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithNegitiveValues() {
        Range range = new Range(-30.0, -10.0);
        assertEquals("get length fails with negitive values",20.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithNegLowerBound() {
        Range range = new Range(-30.0, 30.0);
        assertEquals("get length fails with negitive lower bound",60.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithSmallValues() {
        Range range = new Range(0.55555, 0.55556);
        assertEquals("get length fails with length = 0.00001",0.00001, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLength_WithMaxValue() {
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertEquals("get length fails on overflow",Double.MAX_VALUE - Double.MIN_VALUE, range.getLength(), 0.001);
    }
    
    /*
     * equals method tests
     * Fails, seems to only check if lower is equal. 
     * probably relies on getUpperBound which incorrectly returns lower. 
     */
    @Test
    public void testEquals() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(0.0, 5.0);
        assertTrue("equals method fails with both lower = 0.0 and upper = 5.0",range1.equals(range2));
    }
    
    @Test
    public void testEquals_WithUpperBoundZeroFiveAndZeroSix() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse("equals method fails with upper = 5.0 and upper2 = 6.0",range1.equals(range2));  
    }
    
    @Test
    public void testEquals_WithRangesOne_FiveAndZero_Six() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse("equals method fails with range1 (1.0,5.0) and range2 (0.0,6.0)",range1.equals(range2));  
    }
    
    @Test
    public void testEquals_WithNegOne_FiveAndZero_SixHundred() {
        Range range1 = new Range(-1.0, 5.0);
        Range range2 = new Range(-1.0, 600.0);
        assertFalse("equals method fails with Range(-1.0, 5.0) and Range(-1.0, 600.0)",range1.equals(range2));  
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}