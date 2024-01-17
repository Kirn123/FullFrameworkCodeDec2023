package com.qa.opencard.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.utils.ElementUtils;
import com.qa.opencard.utils.Log;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content > h2");

	// page const...
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);

	}

	// page actions:
	@Step("Getting Accounts page title")
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		//TSystem.out.println("Acc page title:" + title);
		Log.info("Acc page title:"+ title);
		return title;
	}

	@Step("Getting Accounts page Url")
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		//System.out.println("acc page url:" + url);
		Log.info("acc page url:" + url);
		return url;
	}

	@Step("Logout Link Exist")
	public boolean isLogutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("Doing Logout")
	public void logout() {
		if (isLogutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}

	@Step("Findig that the search field exist or not")
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("Getting account Headers")
	public List<String> getAccountsHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders,
				AppConstants.MEDIUM_DEFAUTT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}

	@Step("Finding Search Results Page")
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doClearAndSendKeysWithWait(search, searchKey, AppConstants.MEDIUM_DEFAUTT_WAIT);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);// TDD
	}
}
