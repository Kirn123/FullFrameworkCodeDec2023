package com.qa.opencard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.utils.ElementUtils;
import com.qa.opencard.utils.JavaScriptUtil;
import com.qa.opencard.utils.Log;

import io.qameta.allure.Step;

public class LoginPageNegative {

	private WebDriver driver;
	private ElementUtils eleUtil;
	private JavaScriptUtil jsutil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logErrorMessage = By
			.xpath("//div[contains(text(),' Warning: No match for E-Mail Address and/or Password')]");

	// page const...
	public LoginPageNegative(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
		jsutil = new JavaScriptUtil(this.driver);

	}

	// page actions/method
	@Step("Findig the login validations")
	public boolean doLoginwithWrongCredentials(String username, String pwd) {
		// System.out.println("Wrong credentials are " + username + " : " + pwd);
		Log.info("Wrong credentials are " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAUTT_WAIT);
		eleUtil.doClearAndSendKeys(userName, username);
		eleUtil.doClearAndSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMsg = eleUtil.doElementGetText(logErrorMessage);
		// System.out.println(errorMsg);
		Log.info("This is an error message: " + errorMsg);
		if (errorMsg.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
			return true;
		}

		return false;

	}

}
