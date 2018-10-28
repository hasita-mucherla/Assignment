package com.qa.jive.JiveAssignment;

import java.util.StringTokenizer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage {
	
	protected WebDriver driver = null;
	
	public CalculatorPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = "button[value='1']")
	WebElement one;
	    
	@FindBy(css = "button[value='2']")
	WebElement two;
	
	@FindBy(css = "button[value='3']")
	WebElement three;
	
	@FindBy(css = "button[value='4']")
	WebElement four;
	
	@FindBy(css = "button[value='5']")
	WebElement five;
	
	@FindBy(css = "button[value='6']")
	WebElement six;
	
	@FindBy(css = "button[value='7']")
	WebElement seven;
	
	@FindBy(css = "button[value='8']")
	WebElement eight;
	
	@FindBy(css = "button[value='9']")
	WebElement nine;
	
	@FindBy(css = "button[value='0']")
	WebElement zero;
	
	@FindBy(css = "button[value='DEL']")
	WebElement delete;
	
	@FindBy(css = "button[value='=']")
	WebElement equals;
	
	@FindBy(css = "button[value='-']")
	WebElement minus;
	
	@FindBy(css = "button[value='+']")
	WebElement plus;
	
	@FindBy(css = "button[value='/']")
	WebElement divide;
	
	@FindBy(css = "button[value='×']")
	WebElement multiply;
	
	@FindBy(id = "expression")
	WebElement inputDisplay;
	
	@FindBy(id = "output")
	WebElement outputDisplay;
	
	protected WebElement getNumericButton(int arg) {
		switch(arg) {
		case 0:
			return zero;
		case 1:
			return one;
		case 2:
			return two;
		case 3:
			return three;
		case 4:
			return four;
		case 5:
			return five;
		case 6:
			return six;
		case 7:
			return seven;
		case 8:
			return eight;
		case 9:
			return nine;
		}
		return zero; // We should never reach here
	}
	
	protected WebElement getMathOperatorButton(String arg) {
		if(arg.equalsIgnoreCase("=")) {
			return equals;
		} else if(arg.equalsIgnoreCase("+")) {
			return plus;
		} else if(arg.equalsIgnoreCase("-")) {
			return minus;
		} else if(arg.equalsIgnoreCase("*")) {
			return multiply;
		} else if(arg.equalsIgnoreCase("/")) {
			return divide;
		} else if(arg.equalsIgnoreCase("BS")) {
			return delete;
		}
		return plus; // We should never reach here
	}
	
	public void performMathOperation(int a, int b, String operation){
		processArgument(a);
		getMathOperatorButton(operation).click();
		processArgument(b);
		equals.click();
	}
	
	public int getPolishedResult() {
		return Integer.parseInt(outputDisplay.getText());
	}
	
	public String getRawResult() {
		return outputDisplay.getText();
	}
	
	private void processArgument(int a) {
		// -ve numbers are considered, yet
		// Thread.sleep is not encouraged but Still I've added so that we can see it on the screen when we ran the test suite.
		try {
				Thread.sleep(500);
				if ( a >= 0 && a <= 9) {
					getNumericButton(a).click();
					return;
				} else if (a > 9 ) {
					int dividend = a/10;
					int reminder = a%10;
					processArgument(dividend);
					processArgument(reminder);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
	public String performInvalidOperation(String operation1, String operation2) {
		getMathOperatorButton(operation1).click();
		getMathOperatorButton(operation2).click();
		equals.click();
		return outputDisplay.getText();
	}
	
	public void processSimpleMathOpration(String mathExpression) {
		
        StringTokenizer tokenizedByOperation;
    	int operandOne = 0;
    	int operandTwo = 0;
    	String operation = "";
    	
        tokenizedByOperation = new StringTokenizer(mathExpression, "+");
        if(tokenizedByOperation.countTokens() == 2) {
        	operation = "+";
        }

        if(operation.isEmpty()) {
            tokenizedByOperation = new StringTokenizer(mathExpression, "-");
            if(tokenizedByOperation.countTokens() == 2)
            	operation = "-";
        }
        
        if(operation.isEmpty()) {
            tokenizedByOperation = new StringTokenizer(mathExpression, "*");
            if(tokenizedByOperation.countTokens() == 2)
            	operation = "*";
        }
        
        if(operation.isEmpty()) {
            tokenizedByOperation = new StringTokenizer(mathExpression, "/");
            if(tokenizedByOperation.countTokens() == 2)
            	operation = "/";
        }
        
    	operandOne = Integer.parseInt(tokenizedByOperation.nextToken());
    	operandTwo = Integer.parseInt(tokenizedByOperation.nextToken());
    	performMathOperation(operandOne, operandTwo, operation);
	}
	
	public void processComplexMathOperation(String complexExpression) {
		// Process each character
		char[] listOfStrokes = complexExpression.toCharArray();
		for(char c: listOfStrokes) {
			if(Character.isWhitespace(c)) {
				// do nothing
			} else if(Character.isDigit(c)){ // It is a numeric digit
				processArgument(Character.getNumericValue(c));
				
			} else { // It is a math operator
				getMathOperatorButton(Character.toString(c)).click();
			}
		}
		equals.click();
	}
}
