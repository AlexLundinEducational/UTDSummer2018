package myTest;
import myClass.DaysDurationCalculator;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertTrue;

//Alex Lundin
//06-28-2018
//SE-4367-Testing

//HW5 10 tests
//this design pattern uses a parameterized data driven testing approach
//the tests focus on normal behavior of the cal method

@RunWith(Parameterized.class)
public class TestCalc {
	
	@Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { {1, 1, 2, 1, 2018, 31},
					{1, 1, 2, 1, 2018, 32} });
    }
    
    // private fields, used only in the test Class
    private int month1;
    private int day1;
    private int month2;
    private int day2;
    private int year;
    private int expectedDaysBetween;
    private int actualDaysBetween;

    public TestCalc(int argMonth1, int argDay1, int argMonth2, int argDay2, int argYear, int argExpectedDaysBetween) {
        // store values from parameter array
    	this.month1 = argMonth1;
        this.day1 = argDay1;
        this.month2 = argMonth2;
        this.day2 = argDay2;
        this.year = argYear;
        this.expectedDaysBetween = argExpectedDaysBetween;
        
    }
    
    @Test
    public void AssertFixture(){
        // actually use the cal method
    	this.actualDaysBetween = DaysDurationCalculator.cal (month1, day1, month2, day2, year);
    	System.out.println ("Result is: " + this.actualDaysBetween);    	
    	  	
    	// test the actual and the expected results
    	assertTrue("Test failed:", this.actualDaysBetween == this.expectedDaysBetween);
    }
    
}
