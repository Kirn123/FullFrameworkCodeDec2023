package com.qa.opencard.tests;

import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencard.base.BaseTest;
import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.listeners.TestAllureListener;
import com.qa.opencard.utils.Log;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 OpenCart login test")
@Story("Story 101 login page")
@Feature("Login page")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		Log.startTestCase("-----Start Login Page Title Test------");
		Log.info("----Verifying the Title-------");
		String actualTitle = loginPage.getLoginPageTitle();
		AssertEquals(actualTitle, "Account Login", "Title is wrong");
		Log.endTestCase("----End Login Page Title Test------");
	}

	@Description("Login page URL test")
	@Severity(SeverityLevel.NORMAL)

	@Test(priority = 2)
	public void loginURLTest() {
		Log.startTestCase("-----Start Login Page URL Test------");
		Log.info("----Verifying the URL-------");
		String URL = loginPage.getLoginPageURL();
		AssertTrue(URL.contains("account/login"), "URL is wrong");
		Log.endTestCase("----End Login Page URL Test------");
	}

	@Description("Login page forgot password link exist test")
	@Severity(SeverityLevel.NORMAL)

	@Test(priority = 3)
	public void ForgotPwdLinkExistTest() {
		AssertTrue(loginPage.isForgotPwdLinkExist(), "Fo");
	}

	@Description("Login page logo exist test")
	@Severity(SeverityLevel.NORMAL)

	@Test(priority = 4)
	public void isLogoExistTest() {
		AssertTrue(loginPage.isLogoExist(), "Logo is missing ");
	}

	@Description("Login page footer link count test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void loginPagefootersCountTest() {
		List<String> footersList = loginPage.getloginfooterlinks();
		System.out.println(footersList);
		AssertEquals(footersList.size(), AppConstants.LOGIN_PAGE_FOOTERS_COUNT, "Page header count is wrong");
	}

	@Description("Login page user Login test")
	@Severity(SeverityLevel.CRITICAL)

	@Test(priority = 6)
	public void doLoginTest() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		AssertTrue(accPage.isLogutLinkExist(), "User login failed ");

	}

}
