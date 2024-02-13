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
    
    
    
    // Lower bound method tests
    @Test
    public void testGetLowerBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",2.0, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBoundMinValue() {
    	Range range = new Range(Double.MIN_VALUE, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",Double.MIN_VALUE, range.getLowerBound(),0.0000001);
    }
    
    @Test
    public void testGetLowerBoundNegInfValue() {
    	Range range = new Range(Double.NEGATIVE_INFINITY, 5.0);
    	assertEquals("Unexpected behaviour in method getLowerBound",Double.NEGATIVE_INFINITY, range.getLowerBound(),0.0000001);
    }
    
    
    // upper bounds method tests
    @Test
    public void testGetUpperBound() {
    	Range range = new Range(2.0, 5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound",5.0, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBoundMaxValue() {
    	Range range = new Range(Double.MAX_VALUE, 5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound with max double value",Double.MAX_VALUE, range.getUpperBound(),0.0000001);
    }
    
    @Test
    public void testGetUpperBoundPosInfValue() {
    	Range range = new Range(Double.POSITIVE_INFINITY, 5.0);
    	assertEquals("Unexpected behaviour in method getUpperBound with positive infinity",Double.POSITIVE_INFINITY, range.getUpperBound(),0.0000001);
    }

    
    // getCentralValue method tests
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }

    
    // getLength method tests
    @Test
    public void testGetLength() {
        Range range = new Range(0.0, 10.0);
        assertEquals(10.0, range.getLength(), 0.001);
    }
    
    // choose one more method
    
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}