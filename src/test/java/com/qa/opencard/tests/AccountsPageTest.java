package com.qa.opencard.tests;

import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencard.base.BaseTest;
import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.listeners.TestAllureListener;

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
		accPage = loginPage.doLogin("augbatch2023@opencart.com", "Selenium@12345");
	}

	@Description("Account page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		AssertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE, "Title is not correct");
	}

	@Description("Account page URL test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageURLTest() {
		AssertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION), "URL is not correct");
	}

	@Description("Logout Link exist test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isLogoutLinkExistTest() {
		AssertTrue(accPage.isLogutLinkExist(), "Logout link doesnot exist");
	}

	@Description("Search field exist test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isSearchFieldExistTest() {
		AssertTrue(accPage.isSearchFieldExist(), "Serach field is not displayed");
	}

	@Description("Account Page Headers Count Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		AssertEquals(actAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT,
				"Page header count is wrong");
	}

	@Description("Account Page Headers Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void accPageHeadersTest() {
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
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		AssertEquals(actProductHeader, "MacBook Pro", "Search Test Failed");
	}

}