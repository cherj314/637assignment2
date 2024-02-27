**SENG 637 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#6:          |     |
| --------------     | --- |
| Student Names:     |     |
| Sean Temple        |     |
| Nicholas Langley   |     |
| John Chernoff      |     |
| Eric Yoon          |     |
| Raise Mehjabin Anzi|     |

# 1 Introduction

In this lab, we focused on mastering automated unit testing techniques using JUnit, specifically through requirements-based test generation. Our goal was to understand how to design and implement effective test cases based on the specifications of the DataUtilities and Range classes from the JFreeChart library. This exercise was designed to enhance our skills in black-box testing, enabling us to create robust and reliable software components.

# 2 Detailed description of unit test strategy

Our approach to unit testing DataUtilities and Range classes was based on black-box testing principles. This means we designed our tests based on the specifications and requirements provided by the JavaDocs. We did not consider white-box testing methods which would require looking at the internal workings of the methods. Here’s how we applied two primary black-box testing techniques:

> Equivalence Class Partitioning (ECP)
We divided the input data for each method into groups (or classes) that we expected to be treated similarly by the method under test. This helped us minimize the number of test cases while maximizing coverage.

> For DataUtilities:

Positive Values: Ensured the methods handled positive inputs correctly.
Negative Values: Tested the methods' ability to process negative values.
Mixed Values: Tested objects with positive and negative values.
Zero Values: Checked how methods dealt with zero values, crucial for mathematical operations.
Null Inputs: Tested methods' resilience against null inputs, particularly important for methods expecting object parameters.

> For Range:

Valid Ranges: Included ranges with a lower bound less than the upper bound.
Invalid Ranges: Ranges where the lower bound was > the upper bound. 
Negative Ranges: Focused on ranges entirely below zero.
Zero and Near-Zero Conditions: Ranges starting or ending with zero, and ranges that crossed zero
Zero Length Ranges: Ranges where lower bound = upper bound (length of zero)

For Range.constrain (takes a double input)
    Below Range: Input that was lower than the lower bound of the range
    In Range: Input value was within the bounds of the range (Boundary tests were written for values at a ranges bound(s))
    Above Range: Input was above the ranges upper bound
    zero length range: The above 3 classes were repeated with a range of length zero (lower bound = upper bound)

> Boundary Value Analysis (BVA)

We designed test cases at the edges of these equivalence classes. This technique is based on the observation that errors tend to occur at the boundaries of input ranges.

> For DataUtilities:

Zero Values: input objects with values of zero
Empty Objects: Objects with no row/columns/values, etc.
Large Objects: Objects with a large number (10000+) values
Index of 0: test first element of arrays
Index = n-1: test last element of arrays
Index out of bounds: test index of of bounds errors

> For Range:

Special Values: Tested ranges with Double.MIN_VALUE, Double.MAX_VALUE, Infinity and NEGATIVE_INFINITY to ensure methods correctly handled extreme values.
Zero Values: Ranges bounded at or across zero
Zero length Range: lower bound = upper bound

For Range.constrain (takes a double input)
  Lower bounds: input = range's lower bound
  Upper bounds: input = range's lower bound
  Zero length range: input = lower bound = upper bound

# 3 Test cases developed

Range Test Cases:

> Constructor:

> - testContructor_CreatesObj 
Partition: Valid Inputs
Strategy: Verifies that the Range object is created when valid parameters are passed.
Current Status: Passes

> - testContructor_CreatesObj_whenBoundsAreEqual 
Partition: Valid Inputs
Strategy: Verifies that the Range object is created when valid parameters 0.0 and 0.0 are passed.
Current Status: Passes

> - testContructor_LowerGreaterThanUpperParameter 
Partition: Invalid Input
Strategy: Verifies that the Range object throws IllegalArgumentException when lower is greater then upper value.
Current Status: Passes
Note that the exact behavour for this case is not defined by javadoc.

> getLowerBound:

> - testGetLowerBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the lower bound of a range, testing basic functionality and correctness.
Current Status: Passes

> - testGetLowerBound_WithOneNegitiveParameter
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the negitive lower bound of a range, testing basic functionality and correctness.
Current Status: Passes

> - testGetLowerBound_WithBothNegitiveParameter\
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the negitive lower bound of a range with both lower and upper as negitive values, testing basic functionality and correctness.
Current Status: Passes

> - testGetLowerBound_ZeroValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the 0.0 value lower bound of a range, testing basic functionality and correctness.
Current Status: Passes

> - testGetLowerBound_MinValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the Double.MIN_VALUE for lower bound of a range, testing basic functionality and correctness.
Current Status: Passes

> - testGetLowerBound_NegInfValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the Double.NEGATIVE_INFINITY for lower bound of a range, testing basic functionality and correctness.
Current Status: Passes

> getUpperBound
> Note this method is broken and returns the lower bound rather then the upper. May be due to copy pasting the getLowerBound method and failing to change varibles.

> - testGetUpperBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the upper bound of a range, testing basic functionality and correctness.
Current Status: Fail

> - testGetUpperBound_WithLargeValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns the large value "123456789.5" for the upper bound of a range, testing basic functionality and correctness.
Current Status: Fail

> - testGetUpperBound_WithMaxValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns Double.MAX_VALUE for the upper bound of a range, testing basic functionality and correctness.
Current Status: Fail

> - testGetUpperBound_PosInfValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns Double.POSITIVE_INFINITY for the upper bound of a range, testing basic functionality and correctness.
Current Status: Fail

> - testGetUpperBound_negativeValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns correct value for the upper bound of a range where the values are negitive, testing basic functionality and correctness.
Current Status: Fail

> - testGetUpperBound_zeroValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly identifies and returns correct value for the upper bound of a range where the value is 0.0, testing basic functionality and correctness.
Current Status: Fail

> getCentralValue

> - testGetCentralValue_WtihRangeNegOneAndOne
Partition: Valid Ranges.
calculates: Verifies that the method correctly identifies and returns correct value for the central value of range where the lower value is -1 and upper value is 1, testing basic functionality and correctness.
Current Status: Passes

> - testGetCentralValue_NegMedianCalc
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value is -9 and upper value is 0, testing basic functionality and correctness.
Current Status: Passes

> - testGetCentralValue_PosMedianCalc
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value is 45 and upper value is 6900, testing basic functionality and correctness.
Current Status: Passes

> - testGetCentralValue_BoundsEqual
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value and upper value are the same, testing basic functionality and correctness.
Current Status: Passes

> getLength

> - testGetLength
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 0 and upper is 10, testing basic functionality and correctness.
Current Status: Passes

> - testGetLength_WithNegitiveValues
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is -30 and upper is -10, testing basic functionality and correctness.
Current Status: Passes

> - testGetLength_WithNegLowerBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is -30 and upper is 30, testing basic functionality and correctness.
Current Status: Passes

> - testGetLength_WithSmallValues(
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 0.55555 and upper is 0.55556, testing basic functionality and correctness.
Current Status: Passes

> - testGetLength_ZeroLength(
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 10 and upper is 10, testing basic functionality and correctness.
Current Status: Passes

> - testGetLength_WithMaxValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is Double.MIN_VALUE and upper is Double.MAX_VALUE, testing basic functionality and correctness.
Current Status: Passes

> equals

> - testEquals
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns true when two different Range objects are created with the same bounds, testing basic functionality and correctness.
Current Status: Passed

> - testEquals_Unequal_Upper_Bounds
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns false when two different Range objects are created with the different upper bounds, testing basic functionality and correctness.
Current Status: Failed

> - testEquals_Unequal_Lower_Bounds
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns false when two different Range objects are created with the different lower bounds, testing basic functionality and correctness.
Current Status: Passed

> - testEquals_Unequal_Lower_Bounds
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns false when two different Range objects are created with the different lower bounds, testing basic functionality and correctness.
Current Status: Passed

> - testEquals_Unequal_Upper_and_lower_bounds
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns false when two different Range objects are created with the different bounds, testing basic functionality and correctness.
Current Status: Passed

> - testEquals_WithNegOne_FiveAndZero_SixHundred
Partition: Valid Ranges.
Strategy: Verifies that the method correctly returns false when two different Range objects are created with the different bounds, testing basic functionality and correctness.
Current Status: Failed

> constrain 

> - testConstrainFromBelowRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains from below range, should return lower bound, testing basic functionality and correctness.
Current Status: Failed - Returns central value instead of lower bound

> - testConstrainAtLowerBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains at lower bound should return lower bound, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainWithinRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains an in-range value should return the in-range value, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainAtUpperBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains at upper bound should return upper bound, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainFromAboveRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains from above range, should return upper bound, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainFromBelowUnitRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains to a range with lower bound = upper bound should return the lower bound, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainFromWithinUnitRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains to a range with lower bound = upper bound should match the lower/upper bound of the range, testing basic functionality and correctness.
Current Status: Passed

> - testConstrainFromAboveUnitRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains to a range with lower bound = upper bound should match the lower/upper bound of the range, testing basic functionality and correctness.
Current Status: Passed



DataUtilities Test Cases:

> calculateColumnTotal

> - calculateColumnTotal_WithPositiveValues
Partition: Positive Values Equivalence Class.
Strategy: Tests how the method sums positive values across a column, ensuring correct handling of normal, positive input.
Current Status: Passes

> - calculateColumnTotal_WithNullInput
Partition: Null Inputs Equivalence Class - Invalid input
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.
Current Status: Fails: method throws an IllegalArgumentException instead of an InvalidParameterException

> - calculateColumnTotal_WithNegativeValues
Partition: Negative Values Equivalence Class.
Strategy: Ensures that the method accurately sums negative values, important for correctness in datasets that include negative numbers.
Current Status: Passes

> - calculateColumnTotal_WithMixedValues
Partition: Mixed Values (including zero).
Strategy: Tests the method's ability to correctly sum a mixture of positive, negative, and zero values, checking for accuracy in varied datasets.
Current Status: Passes

> - calculateColumnTotal_WithNullValues
Partition: Null Values Equivalence Class.
Strategy: Ensures correct handling of columns where some values are null, important for edge cases in data processing.
Current Status: Failed - expected 10 but got 11 - seems like null values add 1 to the sum

> - calculateColumnTotal_WithZeroValues
Partition: Zero Values Equivalence Class.
Strategy: Ensures correct handling of columns where all values are zero, important for edge cases in data processing.
Current Status: Passes

> - calculateColumnTotal_WithLargeDataset
Partition: Large Datasets.
Strategy: Tests performance and accuracy when summing a large number of values, relevant for scalability and efficiency.
Current Status: Passes

> - calculateColumnTotal_WithNoRows
Partition: Empty Datasets
Strategy: Tests ability of method to handle inputs with no values to add. Tests robustness and input tolerance
Current Status: Passes

> CalculateRowTotal
Note: the last row seems to be left of the calculations, so any test that does not have a final column value of 0.0 fails as a result

> - calculateRowTotal_WithMixedValues
Partition: Mixed Values (including zero) for row calculations.
Strategy: Similar to column testing, this ensures accurate summing across rows with diverse data types.
Current Status: Fails - expected 3.5 but was 2.5

> - calculateRowTotal_WithNullInput
Partition: Null Inputs Equivalence Class - Invalid input
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.
Current Status: Fails: method throws an IllegalArgumentException instead of an InvalidParameterException

> - calculateRowTotal_WithNullValues
Partition: Null Values Equivalence Class
Strategy: Ensures correct handling of rows where some values are null, important for edge cases in data processing.
Current Status: Fails - expected 10.0 but was 0.0

> - calculateRowTotal_WithZeroValues
Partition: Zero Value Boundary Class
Strategy: Ensures correct handling of rows where values are zero, important for edge cases in data processing.
Current Status: Fails - Expected 10 but was 0.0

> - calculateRowTotal_WithRowIndexOutOfRange
Partition: Index out of range boundary class
Strategy: Ensures correct handling improper input, important for fault tolerance and error handeling.
Current Status: Passes

> - calculateRowTotal_WithNoColumns
Partition: Empty Datasets
Strategy: Tests ability of method to handle inputs with no values to add. Tests robustness and input tolerance
Current Status: Passes

> - calculateRowTotal_WithLargeDataset
Partition: Large Datasets.
Strategy: Tests performance and accuracy when summing a large number of values, relevant for scalability and efficiency.
Current Status: Fails - Expected 10000.0 but was 9999.0 - 

> createNumberArray
 Note: this class has 2 major issues that make almost every test fail
Issue 1: Returned value is of class Double instead of class Number (specification for the Javadocs)
Issue 2: The final value of the returned array is always null instead of the provided double causing nullPointerExceptions when trying to access that element

> - createNumberArray_WithValidInput()
Partition: Valid input - positive values equivalence class.
Strategy: Tests base behaviour and accuracy.
Current Status: Fails - expected class Number but was class Double

> - createNumberArray_WithNullInput
Partition: Null Inputs Equivalence Class - Invalid input
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.
Current Status: Fails: method throws an IllegalArgumentException instead of an InvalidParameterException

> - createNumberArray_WithNegativeValue()
Partition: Valid input - negative values equivalence class.
Strategy: Tests base behaviour and accuracy with negative values
Current Status: Fails - null pointer exception

> - createNumberArray_WithZeroValue()
Partition: Valid input - zero value boundary class.
Strategy: Tests base behaviour and accuracy remains when input contains zeros
Current Status: Fails - null pointer exception

> - createNumberArray_LargeDataset()
Partition: Large datasets
Strategy: Tests scalability and reliability
Current Status: Fails - Null pointer exception

> - createNumberArray_EmptyArray()
Partition: empty datasets
Strategy: Tests ability of method to handle inputs with no values. Tests robustness and input tolerance
Current Status: Passes (note does not check returned class which is actually double and would cause a fail if checked)

> createNumberArray2D
Note: this class has the same two major issues as the 1D array class
returned class is double
final value is null

> - createNumberArray2D_WithValidInput()
Partition: Valid input - positive values equivalence class.
Strategy: Tests base behaviour and accuracy.
Current Status: Fails - Expected Class Number but was class Double

> - createNumberArray2D_WithNullInput
Partition: Null Inputs Equivalence Class - Invalid input
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.
Current Status: Fails: method throws an IllegalArgumentException instead of an InvalidParameterException

> - createNumberArray2D_EmptyArray()
Partition: empty datasets
Strategy: Tests ability of method to handle inputs with no values. Tests robustness and input tolerance
Current Status: Passes

> - createNumberArray_WithNegativeInput()
Partition: Valid input - negative values equivalence class.
Strategy: Tests base behaviour and accuracy with negative values
Current Status: fails - null pointer exception

> - createNumberArray2D_LargeDataset()
Partition: Large datasets
Strategy: Tests scalability and reliability
Current Status: Fails - null pointer exception

> getCumulativePercentages

> - getCumulativePercentages_WithValidInput()
Partition: Valid Input 
Strategy: Tests base behaviour and accuracy
Current Status: fails - expected 0.1666666666666 but was 0.2

> - getCumulativePercentages_WithZeroValues()
Partition: Zero boundary class 
Strategy: Tests input tolerance and reliability
Current Status: Passes

> - getCumulativePercentages_WithZeroTotal()
Partition: Zero boundary class 
Strategy: Tests input tolerance and reliability
Current Status: Passes

> - getCumulativePercentages_WithNullInput()
Partition: Null Inputs Equivalence Class - Invalid input
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.
Current Status: Fails: method throws an IllegalArgumentException instead of an InvalidParameterException

> - getCumulativePercentages_WithNegativeValues()
Partition: Negative Values Equivalence class 
Strategy: Tests robustness/ input handeling
Current Status: Passes
NOTE: Expected behaviour is undefined in Javadoc for inputs containing negative values so the test just checks that an object is created without issue, otherwise would likely fail as final value was not 1.0 as would be expected for positive values

> - getCumulativePercentages_WithSingleValue()
Partition: Single input size - boundary class 
Strategy: Tests input robustness for small input
Current Status: fails - expected 1.0 but was Infinity

> - getCumulativePercentages_LargeDataset()
Partition: Large datasets
Strategy: Tests scalability and reliability
Current Status: Passes




Each test case was designed to address specific conditions as dictated by the strategies of Equivalence Class Partitioning (ECP) and Boundary Value Analysis (BVA). This comprehensive approach ensures robust coverage across a wide array of scenarios, enhancing confidence in the correctness and reliability of the DataUtilities and Range functionalities.

**Benefits and Drawbacks of Using Mocking**

Benefits:
1. Isolation: Mocking allows us to isolate the method under test, ensuring that the test outcomes are not affected by external dependencies or the behavior of other parts of the system.
2. Control: It provides control over the test environment by allowing us to specify the exact conditions under which the test runs, including the behavior of any dependent interfaces or methods.

Drawbacks:
1. Complexity: Implementing mocking correctly can add complexity to the test setup, making it harder to read and maintain the test code.
2. Over-reliance: There's a risk of over-reliance on mocking, which might lead to tests that pass despite issues in the actual integration between components or in the production environment.

In summary, our unit testing strategy was carefully designed to cover a broad spectrum of scenarios, leveraging black-box testing techniques to ensure thoroughness and effectiveness. While mocking provided valuable control over the testing environment, we remained mindful of its potential pitfalls, striving for a balanced and comprehensive testing approach.

# 4 How the team work/effort was divided and managed

As there were 10 methods to test, evenly divided between two classes, We decided to split them equally betweeen group members. Each member designed and created the tests for 1 range method and 1 data utilities method
This ensured that each group member got experience with the simpler tests and the ones that involved using a mocking object.

Once the initial tests were designed and implemented. Our group took a look at eachothers tests and offer input/ constructive criticism, this allowed us to improve our understanding of the material, and to fill out the test methods with anything that may have been missed.

Lessons Learned:
1. Regular and clear communication was key to overcoming obstacles and ensuring that everyone was aligned on the project's goals and progress.
2. Sharing and reviewing each other's work not only helped catch mistakes early but also fostered a learning environment where feedback was used constructively.
3. Being adaptable in our roles and willing to assist outside our primary focus areas ensured that no task became a bottleneck.

# 5 Difficulties encountered, challenges overcome, and lessons learned

Implementing mocking for DataUtilities tests was a significant hurdle. The unfamiliarity with mocking frameworks meant that our initial attempts led to tests that were less effective and, in some cases, incorrect. This challenge was compounded by the complexity of the DataUtilities class's dependencies, which required sophisticated mocking to test effectively.

There was also some difficulty with designing tests based only on the Javadocs. The docs were very useful but often seemed to be missing some info that would have been useful for more thorough testing. 
For example: DataUtilities.calculateColumnTotal specifies that for invalid input, a zero value is returned, but does not fully specify what valid/invalid input is for that method, other than that a null object should not be passed in (which is a different case that throws an output instead). We attempted to make best guesses for these cases to cover them as thoroughly as possible. For the previous example this entailed tests such as out of range indexes or empty (but not null) data objects

Overcoming Challenges:
1. Team members dedicated time to research and understand mocking frameworks better. This effort included reviewing documentation, tutorials, and community forums.
2. We adopted a trial-and-error approach, which, while time-consuming, eventually led to a deeper understanding and correct implementation of mocking.
3. Seeking guidance from external sources, including online communities and existing literature, provided us with the insights needed to overcome our initial difficulties.

Lessons Learned:
1. Understanding the tools and frameworks before diving into implementation can save time and frustration.
2. Facing and overcoming challenges through perseverance was a valuable lesson in problem-solving and determination.

# 6 Comments/feedback on the lab itself

The lab served as a practical application of automated testing principles, offering hands-on experience with JUnit and mocking frameworks within the context of the JFreeChart library. The complexity of the tasks provided a realistic insight into software testing challenges, making the experience highly beneficial.

Constructive Feedback:

More Examples on Mocking: While the lab was well-structured, a common stumbling block was the effective use of mocking. Providing additional examples or a dedicated session on mocking, including common pitfalls and best practices, would be immensely helpful.

Incremental Complexity: Gradually increasing the complexity of the tasks or offering optional advanced challenges could cater to a wider range of skill levels within the class.

Interactive Sessions: Incorporating more interactive sessions or workshops where students can work through challenges in real-time with instructors or peers could enhance the learning experience.

The lab was a valuable educational tool that struck a good balance between theory and practice. The clear instructions and logical progression through the tasks facilitated learning, though there is room for improvement in support materials related to more complex concepts like mocking. Java Docs are not specifications and requirements and can leave some things open to interpritation. This lab not only enhanced our technical skills but also fostered soft skills such as teamwork, communication, and problem-solving, proving to be a comprehensive learning experience.
