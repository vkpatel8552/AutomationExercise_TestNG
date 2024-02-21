package com.test.automation.AutomationExercise_TestNG.NewArrival;

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
import com.test.automation.AutomationExercise_TestNG.Pages.NewArrivalPage;
import com.test.automation.AutomationExercise_TestNG.Pages.ProductDetailsPage;
import com.test.automation.AutomationExercise_TestNG.Pages.StartUpPage;
import com.test.automation.AutomationExercise_TestNG.TestBase.TestBase;

import test.automationframework.Exception.CustomException;
import test.automationframework.Utils.Efficacies;
import test.automationframework.Utils.WebDriverUtils;

public class NewArrivalTest extends TestBase {

	public WebDriver driver;
	public Properties property;
	public StartUpPage startUpPage;
	public LoginPage loginPage;
	public HomePage homePage;
	public ProductDetailsPage productDetailsPage;
	public NewArrivalPage newArrivalPage;
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

	@Test(description = "Luma_NewArrival_TC_1: Verify First Product Details from New Arrival Page", groups = {
			"Regression Test" })
	public void NewArrival_TC1_FirstProductNewArrival() throws Exception {
		String jsonFilePath = "/NewArrival/NewArrival_TC1.json";

		try {
			softAssert = new SoftAssert();

			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");
			Map<String, String> verifyProductDetails = new Efficacies().readJsonElementInOrder(jsonFilePath,
					"verifyProductDetails");
			Map<String, String> verifyProductAddDetails = new Efficacies().readJsonElementInOrder(jsonFilePath,
					"verifyProductAdditionalDetails");

			// Navigate to Home Page"
			homePage = homePage.navigateToHome(homeUrl)
							   .waitForHomePageToLoad();

			softAssert.assertEquals(homePage.getHomePageTitle(), testData.get("homePageTitle"),
					"Home Page Loaded successfully");

			newArrivalPage = homePage.clickOnWhatsNewlnk()
							   .waitForNewArrivalPageToLoad();
			
			softAssert.assertEquals(newArrivalPage.getNewArrivalPageHeader(), testData.get("newArrivalPageHeader"),
					"New Arrival Page Loaded Successfully");

			newArrivalPage = newArrivalPage.scrollToLatestSection()
										   .waitForNewArrivalPageToLoad();
		
			softAssert.assertEquals(newArrivalPage.getLatestCollectionPageHeader(), testData.get("hotSellerHeader"),
					"Luma's Laetst Collection header visible after scroll");
			
			productDetailsPage = homePage.clickOnFirstProduct()
										 .waitForProductDetailsPageToLoad();
			
			for (String column: verifyProductDetails.keySet()) {
				String actualVal = productDetailsPage.getProductBasicDetails(column);
			
				softAssert.assertEquals(actualVal, verifyProductDetails.get(column),
						"Verifying Product Details for Column: "+ column);
			}
			
			productDetailsPage = productDetailsPage.clickOnProductDetailsTab()
												   .waitForProductDetailsPageToLoad();
			
			softAssert.assertEquals(productDetailsPage.getProductDetails(),testData.get("productDescription"),
					"Verifying Product Description form Product Details Tab");
			
			productDetailsPage = productDetailsPage.clickOnProductMoreInfoTab()
					   							   .waitForProductDetailsPageToLoad();

			for (String column: verifyProductAddDetails.keySet()) {
				String actualVal = productDetailsPage.getProductInfo(column);
			
				softAssert.assertEquals(actualVal, verifyProductAddDetails.get(column),
						"Verifying Additional Product Details for Column: "+ column);
			}
			
			softAssert.assertAll();
		} catch (Exception exception) {
			throw new CustomException(exception, driver);
		}
	}

	@Test(description = "Luma_NewArrival_TC_2: Add Product into cart from Luma's Latest Collection Section", groups = {
			"Regression Test" })
	public void NewArrival_TC2_AddToCartFromLatestCollection() throws Exception {
		String jsonFilePath = "/NewArrival/NewArrival_TC2.json";

		try {
			softAssert = new SoftAssert();

			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");

			// Navigate to Home Page"
			homePage = homePage.navigateToHome(homeUrl)
							   .waitForHomePageToLoad();

			softAssert.assertEquals(homePage.getHomePageTitle(), testData.get("homePageTitle"),
					"Home Page Loaded successfully");

			newArrivalPage = homePage.clickOnWhatsNewlnk()
							   .waitForNewArrivalPageToLoad();
			
			softAssert.assertEquals(newArrivalPage.getNewArrivalPageHeader(), testData.get("newArrivalPageHeader"),
					"New Arrival Page Loaded Successfully");

			newArrivalPage = newArrivalPage.scrollToLatestSection()
										   .waitForNewArrivalPageToLoad();
		
			softAssert.assertEquals(newArrivalPage.getLatestCollectionPageHeader(), testData.get("hotSellerHeader"),
					"Luma's Laetst Collection header visible after scroll");
			
			productDetailsPage = homePage.clickOnSpecificProduct(testData.get("productName"))
										 .waitForProductDetailsPageToLoad();
			
			productDetailsPage = productDetailsPage.selectProductSize(testData.get("productSize"))
												   .selectProductColor(testData.get("productColor"))
												   .enterProductQty(testData.get("productQty"))
												   .clickOnAddToCart();
					
			softAssert.assertEquals(productDetailsPage.getProductAddtoCartMsg(),testData.get("addToCartMsg"),
					"Verifying Product Successfully added to cart message");
			
			softAssert.assertEquals(productDetailsPage.getShoppingCartItemsCount(),testData.get("productQty"),
					"Verifying Qty of Product added in Cart");
			
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