package com.qa.opencard.base;

import java.net.SocketException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import com.qa.opencard.factory.DriverFactory;
import com.qa.opencard.pages.AccountsPage;
import com.qa.opencard.pages.LoginPage;
import com.qa.opencard.pages.LoginPageNegative;
import com.qa.opencard.pages.ProductInfoPage;
import com.qa.opencard.pages.RegisterPage;
import com.qa.opencard.pages.SearchResultsPage;

public class BaseTest {
	protected WebDriver driver;
	protected DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	protected LoginPageNegative doWronglogin;

	@BeforeSuite
	public void log() {
		DOMConfigurator.configure("log4j.xml");
	}

	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browserName, String browserVersion,String testName) {
		df = new DriverFactory();
		prop = df.initprop();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		driver = df.initDriver(prop);
		df.launchURL(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		accPage = new AccountsPage(driver);
		searchResultsPage = new SearchResultsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
		registerPage = new RegisterPage(driver);
		doWronglogin = new LoginPageNegative(driver);
	}

	@AfterTest
	public void tearDown() throws SocketException {
		driver.quit();
	}

	public void AssertTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}

	public void AssertFalse(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}

	public void AssertEquals(String actual, String expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	public void AssertEquals(int actual, int expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	public void AssertEquals(boolean actual, boolean expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}

	public void AssertEquals(List<String> actual, List<String> expected, String message) {

		Assert.assertEquals(actual, expected, message);

	}

	public void AssertFail(String message) {
		Assert.fail(message);
	}

}
