package com.qa.opencard.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.qa.opencard.exception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight = null;

	// This code is for browser enums

//	public WebDriver initDriver(Browser browserName) {
//		System.out.println("The browser name is: " + browserName);
//		optionsManager = new OptionsManager(prop);
//		switch (browserName) {
//		case CHROME:
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
//			break;
//
//		case FIREFOX:
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
//			break;
//		case EDGE:
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());
//			break;
//		case SAFARI:
//			driver = new SafariDriver();
//			break;
//
//		default:
//			System.out.println("Please pass the right browser...." + browserName);
//			throw new FrameworkException("INVALID BROWSER...");
//		}
//
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		return driver;
//
//	}

	// new syntax for switch case statement

//	public WebDriver initDriver(Properties prop) {
//		String browserName = prop.getProperty("browser");
//		// String browserName = System.getProperty("browser");
//		System.out.println("The browser name is: " + browserName);
//		highlight = prop.getProperty("highlight");
//		optionsManager = new OptionsManager(prop);
//
//		switch (browserName.toLowerCase().trim()) {
//		case "chrome" -> tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//		// driver = new ChromeDriver(optionsManager.getChromeOptions());
//
//		case "firefox" -> tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//		// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
//
//		case "edge" -> tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
//		// driver = new EdgeDriver(optionsManager.getEdgeOptions());
//
//		case "safari" -> tlDriver.set(new SafariDriver());
//		// driver = new SafariDriver();
//
//		default -> System.out.println("Please pass the right browser...." + browserName);
//		// throw new FrameworkException("INVALID BROWSER...");
//		}

	/*
	 * This code is for again for enum practice for that used enum Browser2
	 */

//	public WebDriver initDriver1(Properties prop) {
//	    String browserName = prop.getProperty("browser");
//	    System.out.println("The browser name is: " + browserName);
//	    highlight = prop.getProperty("highlight");
//	    optionsManager = new OptionsManager(prop);
//
//	    Browser2 selectedBrowser = Browser2.valueOf(browserName.toUpperCase());
//	    WebDriver driver = selectedBrowser.initDriver(optionsManager);
//
//	    driver.manage().deleteAllCookies();
//	    driver.manage().window().maximize();
//	    
//	    return driver;
//	}

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// String browserName = System.getProperty("browser");
		System.out.println("The browser name is: " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run the testcases on remote grid
				initRemoteDriver(browserName);
			} else {
				// run it on local machine
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;

		case "firefox":
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run the testcases on remote grid
				initRemoteDriver(browserName);
			} else {
				// run it on local machine
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}

			break;
		case "edge":
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run the testcases on remote grid
				initRemoteDriver(browserName);
			} else {
				// run it on local machine
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}

			break;
			
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser...." + browserName);
			throw new FrameworkException("INVALID BROWSER...");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
//		driver.manage().window().maximize();
		// return driver;
		return getDriver();

	}

	/**
	 * this method is for intializing the browser on remote machine run testcases on
	 * grid
	 * 
	 * @param browserName
	 */

	private void initRemoteDriver(String browserName) {
		System.out.println("Running test on grid with browserName : " + browserName);
		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome": {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			}
			case "firefox": {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			}
			case "edge": {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;
			}
			default:
				System.out.println("Please pass the right browser...." + browserName);
				throw new IllegalArgumentException("Unexpected value: " + browserName);

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * this method is used to launch the url.
	 * 
	 * @param url
	 */
	public void launchURL(Properties prop) {
		String url = prop.getProperty("url");
		if (url == null) {
			System.out.println("URL IS NULL");
			throw new FrameworkException("NULL URL");
		}
		if (url.indexOf("http") == 0 || url.indexOf("https://") == 0) {
			try {
				getDriver().get(url);
				// driver.get(url);
			} catch (Exception e) {
				System.out.println("http is missing");
				throw new FrameworkException("HTTP is missing");

			}
		} else {
			throw new FrameworkException("HTTP is missing");
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initprop() {

		// mvn clean install -Denv = "qa"

		FileInputStream fs = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("env name is : " + envName);

		try {
			if (envName == null) {

				fs = new FileInputStream("./src/test/resources/config/config.qa.properties");

			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fs = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					fs = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					fs = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("Wrong env name : " + envName);

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fs);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

		// old code only to use config prop without env base

//		try {
//			fs = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(fs);
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return prop;

	}

	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * take screenshot with element highlighted with border
	 */

//	public static String getScreenshotWithHighlight(String methodName, WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//
//		// Highlight the element with a red border using JavaScript
//		js.executeScript("arguments[0].style.border='2px solid red'", element);
//
//		// Take the screenshot
//		String path = getScreenshot(methodName);
//
//		// Remove the border after taking the screenshot (optional)
//		js.executeScript("arguments[0].style.border=''", element);
//
//		return path;
//	}
}
