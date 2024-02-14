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
    public void testContructorCreatesObj() {
    	Range range = new Range(0.0, 2.0);
    	assertNotNull("No object was created", range);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testContructorLowerGreaterThanUpperParameter() {
    	Range range = new Range(3.0, 2.0);
    }
  
    // HOW/SHOULD_WE TEST HOW THE CODE HANDLES INVALID PARAMETERS I.E. INT, STRING
    // test overflow and/or underflow?
    // the javadoc isnt exactly project requirements...
    // hard to know the intention of some of the methods just from javadoc.
    // don't know if the datatypes handling was a requirement 
    
    
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
    public void testGetLowerBoundWithOneNegitiveParameter() {
    	Range range = new Range(-603.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound with a single negitive paramter",
    			-603.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBoundWithBothNegitiveParameter() {
    	Range range = new Range(-60.5,-2.7);
    	assertEquals("Unexpected behaviour in method getLowerBound with two negitive parameters",
    			-60.5, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBoundMinValue() {
    	Range range = new Range(Double.MIN_VALUE, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",
    			Double.MIN_VALUE, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBoundNegInfValue() {
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
    public void testGetUpperBoundBigger() {
    	Range range = new Range(2.1, 123456789.5);
    	assertEquals("Unexpected behaviour in method getUpperBound",123456789.5, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBoundMaxValue() {
    	Range range = new Range(5.0,Double.MAX_VALUE);
    	assertEquals("Unexpected behaviour in method getUpperBound with max double value",
    			Double.MAX_VALUE, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBoundPosInfValue() {
    	Range range = new Range(5.0, Double.POSITIVE_INFINITY);
    	assertEquals("Unexpected behaviour in method getUpperBound with positive infinity",
    			Double.POSITIVE_INFINITY, range.getUpperBound(),0.0000001);
    }

    
    /* getCentralValue method tests 
     * 
     */
    @Test
    public void centralValueShouldBeZero() {
    	Range range = new Range(-1,1);
        assertEquals("The central value of -1 and 1 should be 0",
        0, range.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testNegMedianCalc() {
    	Range range = new Range(-9,0);
    	assertEquals("The central value of -9 and 0 should be -4.5",
    	-4.5, range.getCentralValue(), .000000001d);		    
    }
    
    @Test
    public void testPosMedianCalc() {
    	Range range = new Range(45.0,6900.0);
    	assertEquals("The central value of -9 and 0 should be 3427.5",
    	3472.5, range.getCentralValue(), .000000001d);		    
    }

    
    /* getLength method tests
     * Given that equals is bugged likely due to getupper,
     * I thought length would be as well...
     */
    @Test
    public void testGetLength() {
        Range range = new Range(0.0, 10.0);
        assertEquals("Unexpected behaviour in get length method",10.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLengthNegitiveValues() {
        Range range = new Range(-30.0, -10.0);
        assertEquals("get length fails with negitive values",20.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLengthWithNegLowerBound() {
        Range range = new Range(-30.0, 30.0);
        assertEquals("get length fails with negitive lower bound",60.0, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLengthSmall() {
        Range range = new Range(0.55555, 0.55556);
        assertEquals("get length fails with length = 0.00001",0.00001, range.getLength(), 0.001);
    }
    
    @Test
    public void testGetLengthMax() {
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
        assertTrue(range1.equals(range2));
    }
    
    @Test
    public void testEqualsZeroFiveAndZeroSix() {
        Range range1 = new Range(0.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse(range1.equals(range2));  
    }
    
    @Test
    public void testEqualsOneFiveAndZeroSix() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(0.0, 6.0);
        assertFalse(range1.equals(range2));  
    }
    
    @Test
    public void testEqualsNegOneFiveAndZeroSixHundred() {
        Range range1 = new Range(-1.0, 5.0);
        Range range2 = new Range(-1.0, 600.0);
        assertFalse(range1.equals(range2));  
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}