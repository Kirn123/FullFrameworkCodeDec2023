package com.qa.opencard.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
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

@Epic("Epic 300 OpenCart Accounts Page")
@Story("Story 301 Accounts page")
@Feature("Accounts page")
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		Log.info("This is Account page test setup method");
		accPage = loginPage.doLogin("augbatch2023@opencart.com", "Selenium@12345");
	}

	@Description("Account page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		Log.startTestCase("-----Start acc Page Title Test------");
		AssertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, "Title is not correct");
	}

	@Description("Account page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageURLTest() {
		Log.startTestCase("-----Start acc Page URL Test------");
		AssertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION), "URL is not correct");
	}

	@Description("Logout Link exist test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isLogoutLinkExistTest() {
		Log.startTestCase("-----Start is Logou tLink Exist Test------");
		AssertTrue(accPage.isLogutLinkExist(), "Logout link doesnot exist");
	}

	@Description("Search field exist test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isSearchFieldExistTest() {
		Log.startTestCase("-----Start is Search Field Exist Test------");
		AssertTrue(accPage.isSearchFieldExist(), "Serach field is not displayed");
	}

	@Description("Account Page Headers Count Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageHeadersCountTest() {
		Log.startTestCase("-----Start acc Page Headers Count Test------");
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		AssertEquals(actAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT,
				"Page header count is wrong");
	}

	@Description("Account Page Headers Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageHeadersTest() {
		Log.startTestCase("-----Start acc Page Headers Test------");
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		// sort the actual list
		// sort the expected list
		// compare
		AssertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST, "Wrong Header List");
	}

	@Description("Account Page search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchTest() {
		Log.startTestCase("-----Start search Test------");
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		AssertEquals(actProductHeader, "MacBook Pro", "Search Test Failed");

	}

}