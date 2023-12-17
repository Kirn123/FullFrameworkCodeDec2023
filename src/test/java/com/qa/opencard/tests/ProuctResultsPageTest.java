package com.qa.opencard.tests;

import java.util.Map;
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

@Epic("Epic 400 OpenCart Products Page")
@Story("Story 401 Register page")
@Feature("Products page")
@Listeners(TestAllureListener.class)
public class ProuctResultsPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

//	@DataProvider
//	public Object[][] getSearchData() {
//		return new Object[][] {
//			{"MacBook", "MacBook Pro", 4},
//			{"MacBook", "MacBook Air", 4},
//			{"iMac", "iMac", 3},
//			{"Samsung", "Samsung SyncMaster 941BW", 1}
//
//		};
//	}

	@DataProvider
	public Object[][] getSearchExcelTestData() {
		return ExcelUtils.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
	}

	@Description("Product Images Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey, String productName, String imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		AssertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount, "Images count is wrong");
	}

	@Description("Product Info Test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();

		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");

		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"), "$2,000.00");

		softAssert.assertAll();

	}

}