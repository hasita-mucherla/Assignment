package com.qa.jive.JiveAssignment;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class CalculatorPageTest extends BasePageTest{

	public static CalculatorPage calPage = null;

	@BeforeTest
	void setup() {
		calPage = BasePageTest.initialization();
	}
	
	@BeforeMethod
	void refresh() {
		driver.navigate().refresh();
	}
	
	@AfterTest
	void quit() {
		driver.close();
	}

	@Test(priority = 1, description = "Add two numbers")
	void addTwoNumbers() {
		//calPage.performMathOperation(151, 131, "+");
		calPage.processSimpleMathOpration("151+131");
		Assert.assertEquals(calPage.getPolishedResult(), 282);
	}
	
	@Test(priority = 2, description = "Subtract two numbers")
	void subtractTwoNumbers() {
		//calPage.performMathOperation(151, 131, "-");
		calPage.processSimpleMathOpration("151-131");
		Assert.assertEquals(calPage.getPolishedResult(), 20);
	}
	
	@Test(priority = 3, description = "Multiply two numbers")
	void multiplyTwoNumbers() {
		//calPage.performMathOperation(151, 131, "*");
		calPage.processSimpleMathOpration("151*131");
		Assert.assertEquals(calPage.getPolishedResult(), 19781);
	}
	
	@Test(priority = 4, description = "Divide two numbers")
	void divideTwoNumbers() {
		//calPage.performMathOperation(780, 20, "/");
		calPage.processSimpleMathOpration("780/20");
		Assert.assertEquals(calPage.getPolishedResult(), 39);
	}
	
	@Test(priority = 5, description = "Divide another set of two numbers")
	void divideTwoNumbers2() {
		//calPage.performMathOperation(19, 19, "/");
		calPage.processSimpleMathOpration("19/19");
		Assert.assertEquals(calPage.getPolishedResult(), 1);
	}
	
	@Test(priority = 6, description = "Backspace and continue to add")
	void backspaceAndAddNumbers() {
		calPage.performMathOperation(21, 2, "BS");
		calPage.performMathOperation(0, 23, "+");
		Assert.assertEquals(calPage.getPolishedResult(), 243);
	}
	
	@Test(priority = 7, description = "Invalid operation results in ERR")
	void invalidOperation() {
		calPage.performInvalidOperation("+", "-");
		Assert.assertEquals(calPage.getRawResult(), "ERR");
	}
	
	@Test(priority = 8, description = "Invalid operation results in Infinity")
	void invalidOperationInfinity() {
		calPage.performMathOperation(1, 0, "/");
		Assert.assertEquals(calPage.getRawResult(), "Infinity");
	}
	
	@Test(priority = 9, description = "Invalid operation results in NaN")
	void invalidOperationNaN() {
		calPage.performMathOperation(0, 0, "/");
		Assert.assertEquals(calPage.getRawResult(), "NaN");
	}
	
	@Test(priority = 10, description = "Operator precedence")
	void complexOperation() {
		calPage.processComplexMathOperation("24+4*2-8/4");
		Assert.assertEquals(calPage.getPolishedResult(), 30);
	}

}
