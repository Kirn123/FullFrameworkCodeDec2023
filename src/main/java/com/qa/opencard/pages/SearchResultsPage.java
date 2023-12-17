package com.qa.opencard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencard.constants.AppConstants;
import com.qa.opencard.utils.ElementUtils;
import com.qa.opencard.utils.JavaScriptUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	private JavaScriptUtil jsutil;

	// page constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
		jsutil = new JavaScriptUtil(this.driver);
	}

	public ProductInfoPage selectProduct(String productName) {
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new ProductInfoPage(driver);
	}

}
