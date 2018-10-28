package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReadUtility {

	static String landingURL = null;
	static String browser = null;

	public static void initConfigFiles() {

		Properties urlProps = new Properties();
		Properties driverProps = new Properties();

		try {
			urlProps.load(new FileInputStream("configs\\Urls.properties"));
			landingURL = urlProps.getProperty("LandingURL");

			driverProps.load(new FileInputStream("configs\\DriverPath.properties"));
			browser = driverProps.getProperty("Browser");			
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException Occured");
		} catch (IOException ioe) {
			System.out.println("IOException Occured");
		}
	}

	public static String getLandingURL() {
		return landingURL;
	}

	public static String getBrowserType() {
		return browser;
	}

}