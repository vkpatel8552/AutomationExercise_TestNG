package com.test.automation.AutomationExercise_TestNG.Login;

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
import com.test.automation.AutomationExercise_TestNG.Pages.StartUpPage;
import com.test.automation.AutomationExercise_TestNG.TestBase.TestBase;

import test.automationframework.Exception.CustomException;
import test.automationframework.Utils.Efficacies;
import test.automationframework.Utils.WebDriverUtils;

public class LoginTest extends TestBase {

	public WebDriver driver;
	public Properties property;
	public StartUpPage startUpPage;
	public LoginPage loginPage;
	public HomePage homePage;
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

	@Test(description = "Luma_Login_TC_1: Verify Login Functionality with Valid Credentials", groups = { "Smoke Test" }, priority=0)
	public void loginWithValidCredential() throws Exception {
		String jsonFilePath = "/Login/Login_TC1.json";

		try {
			softAssert = new SoftAssert();

			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");

			// Navigate to Home Page"
			homePage.navigateToHome(homeUrl)
					.waitForHomePageToLoad();

			loginPage = homePage.clickOnLoginlnk()
								.waitForLoginPageToLoad();

			softAssert.assertEquals(loginPage.getLoginPageHeader(), testData.get("loginPageHeader"),
					"Login Page Loaded successfully");

			homePage = loginPage.enterUserEmail(testData.get("loggedInUserName"))
					.enterPassword(testData.get("loggedInUserPassword"))
					.clickOnLoginBtn()
					.waitForHomePageWithLoggedInUserToLoad();

			softAssert.assertEquals(homePage.getLoggedInUser(), testData.get("userWelcomeMsg"),
					"Login Page Loaded successfully");

			homePage.clickOnLogoutlnk()
					.waitForHomePageToLoad();

			softAssert.assertAll();
		} catch (Exception exception) {
			throw new CustomException(exception, driver);
		}
	}

	@Test(description = "Luma_Login_TC_2: Verify Login Functionality with Invalid Credentials.", groups = {
			"Regression Test" }, priority=2)
	public void loginWithInValidCredential() throws Exception {
		String jsonFilePath = "/Login/Login_TC2.json";

		try {
			softAssert = new SoftAssert();
			
			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");

			// Navigate to Home Page"
			homePage.navigateToHome(homeUrl)
					.waitForHomePageToLoad();

			loginPage = homePage.clickOnLoginlnk()
					 		    .waitForLoginPageToLoad();

			softAssert.assertEquals(loginPage.getLoginPageHeader(), testData.get("loginPageHeader"),
					"Login Page Loaded successfully");

			loginPage = loginPage.enterUserEmail(testData.get("loggedInUserName"))
					.enterPassword(testData.get("loggedInUserPassword")).clickOnLoginBtnInvalidCred()
					.waitForLoginPageToLoad();

			softAssert.assertEquals(loginPage.getLoginErrMsg(), testData.get("errMsg"),
					"Error message shown successfully after trying to login with invalid credentials");
			softAssert.assertAll();

		} catch (Exception exception) {
			throw new CustomException(exception, driver);
		}
	}

	@Test(description = "Luma_Login_TC_3: Verify Login-LogOut Functionality with Valid Credentials", groups = {
			"Regression Test" }, priority=1)
	public void loginLogoutWithValidCredential() throws Exception {
		String jsonFilePath = "/Login/Login_TC3.json";

		try {
			softAssert = new SoftAssert();

			// Read data from Json File
			Map<String, String> testData = new Efficacies().readJsonElementInOrder(jsonFilePath, "Data");

			// Navigate to Home Page"
			homePage.navigateToHome(homeUrl)
					.waitForHomePageToLoad();

			loginPage = homePage.clickOnLoginlnk()
							    .waitForLoginPageToLoad();

			softAssert.assertEquals(loginPage.getLoginPageHeader(), testData.get("loginPageHeader"),
					"Login Page Loaded successfully");

			homePage = loginPage.enterUserEmail(testData.get("loggedInUserName"))
					.enterPassword(testData.get("loggedInUserPassword")).clickOnLoginBtn()
					.waitForHomePageWithLoggedInUserToLoad();

			softAssert.assertEquals(homePage.getLoggedInUser(), testData.get("userWelcomeMsg"),
					"Login Page Loaded successfully");

			homePage.clickOnLogoutlnk()
					.waitForHomePageToLoad();

			softAssert.assertEquals(homePage.getDefaultLoggedInUser(), testData.get("defaultWelcomeMsg"),
					"User Logged out successfully & Default Welcome message shown");
			
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