package com.test.automation.AutomationExercise_TestNG.Men;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.automation.AutomationExercise_TestNG.Pages.HomePage;
import com.test.automation.AutomationExercise_TestNG.Pages.LoginPage;
import com.test.automation.AutomationExercise_TestNG.Pages.MenPage;
import com.test.automation.AutomationExercise_TestNG.Pages.ProductDetailsPage;
import com.test.automation.AutomationExercise_TestNG.Pages.StartUpPage;
import com.test.automation.AutomationExercise_TestNG.TestBase.TestBase;

import test.automationframework.Exception.CustomException;
import test.automationframework.Utils.Efficacies;
import test.automationframework.Utils.WebDriverUtils;

public class MenTest extends TestBase {

	public WebDriver driver;
	public Properties property;
	public StartUpPage startUpPage;
	public LoginPage loginPage;
	public HomePage homePage;
	public ProductDetailsPage productDetailsPage;
	public MenPage menPage;
	public String instanceURL;
	SoftAssert softAssert;
	String homeUrl;
	public ObjectMapper mapper;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "environment", "browser" })
	public void LoginToAdminApp(String environment, String browser) throws Exception {
		WebDriverUtils utils = new WebDriverUtils();
		utils.initializeDriver(browser);
		property = new Efficacies().loadPropertyFile(environment);
		driver = WebDriverUtils.getDriver();
		startUpPage = new StartUpPage(driver);
		homePage = startUpPage.navigateToPage(property.getProperty("baseURL"));
		homeUrl = property.getProperty("baseURL");
		mapper = new ObjectMapper();
	}

	@Test(description = "Luma_Men_TC_1: Add Product to Product Compare List from Men's Page", groups = {
			"Smoke Test" })
	public void addProductToCompareList() throws Exception {
		String jsonFilePath = "/Men/Men_TC1.json";

		try {
			softAssert = new SoftAssert();

			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");
			
			// Navigate to Home Page"
			homePage = homePage.navigateToHome(homeUrl)
							   .waitForHomePageToLoad();
			
			softAssert.assertEquals(homePage.getHomePageTitle(), testData.get("homePageTitle"),
					"Home Page Loaded successfully");

			menPage = homePage.clickOnMenlnk()
							   .waitForMenPageToLoad()
							   .scrollToHotSellerSection()
							   .waitForMenPageToLoad();
			
			softAssert.assertEquals(homePage.getHotSellerPageHeader(), testData.get("hotSellerHeader"),
					"Hot Seller section visible after scroll down");

			productDetailsPage = homePage.clickOnSpecificProduct(testData.get("productName"))
										 .waitForProductDetailsPageToLoad();
			

			productDetailsPage = productDetailsPage.selectProductSize(testData.get("productSize"))
												   .selectProductColor(testData.get("productColor"))
												   .enterProductQty(testData.get("productQty"))
												   .clickOnAddToCompareListlnk();
					
			softAssert.assertEquals(productDetailsPage.getProductAddtoCompareListMsg(),testData.get("addToCompareMsg"),
					"Verifying Product Successfully added to cart message");
			
			softAssert.assertAll();
		} catch (Exception exception) {
			throw new CustomException(exception, driver);
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		startUpPage.killDriver();
	}

}