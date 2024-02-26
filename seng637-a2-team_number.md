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

DataUtilities Test Cases:

calculateColumnTotal_WithPositiveValues
Partition: Positive Values Equivalence Class.
Strategy: Tests how the method sums positive values across a column, ensuring correct handling of normal, positive input.

calculateColumnTotal_WithNullInput
Partition: Null Inputs Equivalence Class.
Strategy: Verifies the method throws an expected exception or handles null input gracefully, testing error handling capabilities.

calculateColumnTotal_WithNegativeValues
Partition: Negative Values Equivalence Class.
Strategy: Ensures that the method accurately sums negative values, important for correctness in datasets that include negative numbers.

calculateColumnTotal_WithMixedValues
Partition: Mixed Values (including zero).
Strategy: Tests the method's ability to correctly sum a mixture of positive, negative, and zero values, checking for accuracy in varied datasets.

calculateColumnTotal_WithZeroValues
Partition: Zero Values Equivalence Class.
Strategy: Ensures correct handling of columns where all values are zero, important for edge cases in data processing.

calculateColumnTotal_WithLargeDataset
Partition: Large Datasets.
Strategy: Tests performance and accuracy when summing a large number of values, relevant for scalability and efficiency.

calculateRowTotal_WithMixedValues
Partition: Mixed Values (including zero) for row calculations.
Strategy: Similar to column testing, this ensures accurate summing across rows with diverse data types.

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
Note that this behavour is not defined by javadoc.

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
Current Status: **Check me**

> - testGetCentralValue_NegMedianCalc
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value is -9 and upper value is 0, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetCentralValue_PosMedianCalc
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value is 45 and upper value is 6900, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetCentralValue_BoundsEqual
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for the central value of range where the lower value and upper value are the same, testing basic functionality and correctness.
Current Status: **Check me**

> getLength

> - testGetLength
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 0 and upper is 10, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetLength_WithNegitiveValues
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is -30 and upper is -10, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetLength_WithNegLowerBound
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is -30 and upper is 30, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetLength_WithSmallValues(
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 0.55555 and upper is 0.55556, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetLength_ZeroLength(
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is 10 and upper is 10, testing basic functionality and correctness.
Current Status: **Check me**

> - testGetLength_WithMaxValue
Partition: Valid Ranges.
Strategy: Verifies that the method correctly calculates and returns correct value for length of the range where lower is Double.MIN_VALUE and upper is Double.MAX_VALUE, testing basic functionality and correctness.
Current Status: **Check me**

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
Current Status: Failed

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
Current Status: **check me**

> - testConstrainFromWithinUnitRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains to a range with lower bound = upper bound should match the lower/upper bound of the range, testing basic functionality and correctness.
Current Status: **check me**

> - testConstrainFromAboveUnitRange
Partition: Valid Ranges.
Strategy: Verifies that the method correctly constrains to a range with lower bound = upper bound should match the lower/upper bound of the range, testing basic functionality and correctness.
Current Status: **check me**

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


RUBRIC:
4.1 Lab Report (30%)
To be consistent, please use the template Word file “seng438-a2-team_number.md” provided in the reopsitory.

Marking scheme	
A detailed description of the testing strategy for unit testing and your test-case design approach, i.e., how you used the black-box test-case design techniques equivalence classes, and boundary value analysis. Also list the name of the test cases you have designed and identify which one covers which parts of the strategy (which partition, which class, etc.) Include a discussion about what you feel are the benefits and drawbacks about using mocking.	25%
A discussion on how the team work/effort was divided and managed. Any lessons learned from your teamwork on this lab?	2%
Difficulties encountered, challenges overcome, and lessons learned from performing the lab	2%
Comments/feedback on the lab and lab document itself. (Did you find it a useful practice? Was it easy to follow?) Please try to keep comments and feedback constructive.	1%
4.2 Junit Test Suite (70%)
Your Eclipse project including any external library for mocking and all test suite Java files should be submitted along with the lab report in GitHub. The JUnit test should be executable AS IS. No restricting, importing, etc. should be needed.

The grading criteria for JUnit test suite are as follows:

Marking scheme	
Clarity: are they easy to follow, through commenting or style, etc.? The test cases should also have comments that shows which test follows which part of the test strategy. E.g., this test covers a maximum value for variable X and normal values for variable Y and Z in method A(X,Y,Z)	10%
Adherence to requirements: do they cover all the classes and partitions described in the test strategy?)	10%
Completeness: are there any obvious requirements which have not been tested? Have equivalence classes, and boundary value analysis been followed carefully? Is your unit test suite clearly match the test-case design section in your lab report?	25%
Correctness: do test run without error? do the tests actually test what they are intended to test?	25%
Important note: Please store the JUnit test suite you have developed in this lab in a known location. It will be re-used in the next Labs.
