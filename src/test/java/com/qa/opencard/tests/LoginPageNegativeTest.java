package com.qa.opencard.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencard.base.BaseTest;
import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.listeners.TestAllureListener;
import com.qa.opencard.utils.ExcelUtils;
import com.qa.opencard.utils.Log;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(TestAllureListener.class)

public class LoginPageNegativeTest extends BaseTest {

	public String getRandomUserID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
	}

	@DataProvider
	public Object[][] incorrectLoginTestData() {

		return new Object[][] {

				{ getRandomUserID(), "pwd123" }, { getRandomUserID(), "pwd1234" }, { getRandomUserID(), "pwd12345" },

		};
	}

	@DataProvider
	public Object[][] getincorrectLoginExcelTestData() {
		return ExcelUtils.getTestData(AppConstants.WRONG_LOGIN_SHEET_NAME);
	}

	@Description("Do Login with Wrong Credentials Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "incorrectLoginTestData")
	public void doLoginwithWrongCredentialsTest(String username, String password) {
		Log.startTestCase("-----Start do Login with Wrong Credentials Test------");
		AssertTrue(doWronglogin.doLoginwithWrongCredentials(username, password),
				"Do Login with Wrong Credentials Test is failed");

	}

}
