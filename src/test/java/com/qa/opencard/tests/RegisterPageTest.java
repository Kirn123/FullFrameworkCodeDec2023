package com.qa.opencard.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencard.base.BaseTest;
import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.listeners.TestAllureListener;
import com.qa.opencard.utils.ExcelUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200 OpenCart Register test")
@Story("Story 201 Register page")
@Feature("Register page")
@Listeners(TestAllureListener.class)
public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmailID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
		// this is the example:- testautomation1212121@opencart.com

		// this is new option as per UUID :- return "testautomation" +
		// UUID.randomUUID()+"@gmail.com";

	}

//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//
//				{ "Rahul", "yadav", "9876543233", "test@123", "yes" },
//				{ "Karishma", "automation", "9876544434", "test@123", "no" },
//				{ "Kiran", "auto","9876522234", "test@123", "yes" }
//
//		};
//	}

	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtils.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}

	// run = total rows
	// params = total cols
	@Description("User Register Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String mobileNumber, String password, String subscribe) {

		boolean isRegDone = registerPage.userRegisteration(firstName, lastName, getRandomEmailID(), mobileNumber,
				password, subscribe);

		AssertTrue(isRegDone, "Registration failed");

	}

}