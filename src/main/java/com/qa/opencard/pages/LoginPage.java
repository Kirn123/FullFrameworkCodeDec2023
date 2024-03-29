package com.qa.opencard.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.utils.ElementUtils;
//import com.qa.opencard.utils.JavaScriptUtil;
import com.qa.opencard.utils.Log;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	// private JavaScriptUtil jsutil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By footerlinkList = By.xpath("//div[contains(@class,'col-sm-3')]//li/a");
	private By registerLink = By.linkText("Register");

	// page const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
		// jsutil = new JavaScriptUtil(this.driver);

	}

	@Step("Getting login page title")

	// page actions/methods:
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		// System.out.println("login page title:" + title);
		Log.info("login page title:" + title);
		return title;
	}

	@Step("Getting login page url")

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		// System.out.println("login page url:" + url);
		Log.info("login page url:" + url);
		return url;
	}

	@Step("is Forgot Pwd Link Exist")

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("is Logo Exist")

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();

	}

	@Step("Validate footer links")

	public List<String> getloginfooterlinks() {
		List<WebElement> footersList = eleUtil.waitForVisibilityOfElements(footerlinkList,
				AppConstants.MEDIUM_DEFAUTT_WAIT);
		List<String> footersValList = new ArrayList<String>();
		for (WebElement e : footersList) {
			String text = e.getText();
			footersValList.add(text);
		}
		return footersValList;
	}

	@Step("Login to the application")

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAUTT_WAIT);
		eleUtil.doSendKeys(userName, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

}
