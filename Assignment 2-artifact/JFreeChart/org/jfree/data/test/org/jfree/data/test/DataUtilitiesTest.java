package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {
    
    private Mockery mockingContext;

    @Before
    public void setUp() {
        mockingContext = new Mockery();
    }

    // Test for calculateColumnTotal
    @Test
    public void calculateColumnTotal_WithPositiveValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(5.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(15.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithNegativeValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(-5.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(-10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(-15.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithNullValue() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(null));
            oneOf(values).getValue(1, 0);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithZeroValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(0.0));
            oneOf(values).getValue(1, 0);
            will(returnValue(0.0));
            oneOf(values).getValue(2, 0);
            will(returnValue(0.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateColumnTotal_WithLargeDataset() {
        final Values2D values = mockingContext.mock(Values2D.class);
        final int rowCount = 10000; 
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(rowCount));
            for (int i = 0; i < rowCount; i++) {
                final int rowIndex = i;
                oneOf(values).getValue(rowIndex, 0);
                will(returnValue(1.0)); 
            }
        }});
        
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(rowCount, result, 0.000000001d); 
    }

    // Test for calculateRowTotal
    @Test
    public void calculateRowTotal_WithMixedValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(5.0));
            oneOf(values).getValue(0, 1);
            will(returnValue(-2.5));
            oneOf(values).getValue(0, 2);
            will(returnValue(0.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(2.5, result, 0.000000001d);
    }

    @Test
    public void calculateRowTotal_WithNullValues() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(3));
            oneOf(values).getValue(0, 0);
            will(returnValue(null));
            oneOf(values).getValue(0, 1);
            will(returnValue(null));
            oneOf(values).getValue(0, 2);
            will(returnValue(10.0));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(10.0, result, 0.000000001d);
    }
    
    @Test
    public void calculateRowTotal_WithRowIndexOutOfRange() {
        final Values2D values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount();
            will(returnValue(1));
        }});
        
        double result = DataUtilities.calculateRowTotal(values, 5); // Assuming index 5 is out of range
        assertEquals(0.0, result, 0.000000001d);
    }

    // Test for createNumberArray
    @Test
    public void createNumberArray_WithValidInput() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals(Number.class, result[0].getClass());
        assertEquals(1.0, result[0].doubleValue(), 0.0);
        assertEquals(2.0, result[1].doubleValue(), 0.0);
        assertEquals(3.0, result[2].doubleValue(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArray_WithNullInput() {
        DataUtilities.createNumberArray(null);
    }
    
    // Test for createNumberArray2D
    @Test
    public void createNumberArray2D_WithValidInput() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(Number.class, result[0][0].getClass());
        assertEquals(1.0, result[0][0].doubleValue(), 0.0);
        assertEquals(2.0, result[0][1].doubleValue(), 0.0);
        assertEquals(3.0, result[1][0].doubleValue(), 0.0);
        assertEquals(4.0, result[1][1].doubleValue(), 0.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createNumberArray2D_WithNullInput() {
        DataUtilities.createNumberArray2D(null);
    }
    
    @Test
    public void createNumberArray2D_WithEmptyArray() {
        double[][] data = new double[0][0];
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    // Test for getCumulativePercentages
    @Test
    public void getCumulativePercentages_WithValidInput() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(3));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(1.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(2.0));
            allowing(values).getKey(2);
            will(returnValue("C"));
            allowing(values).getValue(2);
            will(returnValue(3.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(3, result.getItemCount());
        assertEquals(0.16666666666666666, result.getValue("A").doubleValue(), 0.000000001d);
        assertEquals(0.5, result.getValue("B").doubleValue(), 0.000000001d);
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.000000001d);
    }
    
    @Test
    public void getCumulativePercentages_WithZeroTotal() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(2));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(0.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(0.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        assertEquals(0.0, result.getValue("A").doubleValue(), 0.000000001d);
        assertEquals(0.0, result.getValue("B").doubleValue(), 0.000000001d);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentages_WithNullInput() {
        DataUtilities.getCumulativePercentages(null);
    }
    
    @Test
    public void getCumulativePercentages_WithNegativeValues() {
        final KeyedValues values = mockingContext.mock(KeyedValues.class);
        
        mockingContext.checking(new Expectations() {{
            allowing(values).getItemCount();
            will(returnValue(2));
            allowing(values).getKey(0);
            will(returnValue("A"));
            allowing(values).getValue(0);
            will(returnValue(-10.0));
            allowing(values).getKey(1);
            will(returnValue("B"));
            allowing(values).getValue(1);
            will(returnValue(20.0));
        }});
        
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertNotNull(result);
        // Expecting specific behavior for negative values, e.g., treating them as part of the total or excluding them
    }
}
