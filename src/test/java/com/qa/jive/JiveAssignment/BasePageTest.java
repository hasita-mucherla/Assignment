package com.qa.jive.JiveAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReadUtility;

public class BasePageTest {
	
	static WebDriver driver = null;
	
	static public CalculatorPage initialization() {
		
		ConfigReadUtility.initConfigFiles();
		
		String browser = ConfigReadUtility.getBrowserType();
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
			// Default browser would be used.
		}
		
	    driver.get(ConfigReadUtility.getLandingURL());
		         
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return PageFactory.initElements(driver, CalculatorPage.class);
	
	}

}
