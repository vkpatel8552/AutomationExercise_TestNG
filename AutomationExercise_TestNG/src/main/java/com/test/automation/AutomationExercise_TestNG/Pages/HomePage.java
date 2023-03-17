package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends StartUpPage{

	@FindBy(partialLinkText="Sign In")
	public WebElement loginlnk;
	
	@FindBy(partialLinkText="Create an Account")
	public WebElement createAcclnk;
	
	@FindBy(css="li.greet>span.logged-in")
	public WebElement loggedInUserName;
	
	@FindBy(css="li.customer-welcome button.switch")
	public WebElement userProfileOptBtn;
	
	@FindBy(css="div.customer-menu>ul.links")
	public WebElement userProfileOptMenu;
	
	@FindBy(partialLinkText="Sign Out")
	public WebElement logoutLnk;
	
	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;
	
	@FindBy(css="li.greet>span.not-logged-in")
	public WebElement defaultUserName;
	
	@FindBy(css="div.content-heading>h2.title")
	public WebElement contentHeader;
	
	@FindBy(css="div.products-grid li.product-item:nth-of-type(1) a.product-item-link")
	public WebElement productItemlnk;
	
	@FindBy(partialLinkText="What's New")
	public WebElement newArrivallnk;

	@FindBy(partialLinkText="Women")
	public WebElement womenlnk;
	
	@FindBy(partialLinkText="Men")
	public WebElement menlnk;
	
	String xpathProductlnk="//div[@class='product-item-details']//a[contains(text(),'{PARAM1}')]";
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageTitle() throws Exception {
		return ngHelper.getTitle();
	}
	
	public HomePage navigateToHome(String url) {
		ngHelper.navigateTo(url);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public HomePage waitForHomePageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("Home Page", maxWaitTime)
				.waitTillElementIsVisible(defaultWelMsg, maxWaitTime);
		return PageFactory.initElements(driver, HomePage.class);
	}	

	public HomePage waitForHomePageWithLoggedInUserToLoad() throws Exception {
		ngHelper.waitTillElementIsInvisible(defaultWelMsg, maxWaitTime);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public LoginPage clickOnLoginlnk() throws Exception {
		ngHelper.waitTillElementIsClickable(loginlnk, maxWaitTime)
				.click(loginlnk);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public RegisterUserPage clickOnCreateAcclnk() throws Exception {
		ngHelper.waitTillElementIsClickable(createAcclnk, maxWaitTime)
				.click(createAcclnk);
		return PageFactory.initElements(driver, RegisterUserPage.class);
	}
	
	public String getLoggedInUser() throws Exception {
		return ngHelper.waitTillElementIsVisible(loggedInUserName, maxWaitTime)
					   .getInnerText(loggedInUserName);
	}
	
	public String getDefaultLoggedInUser() throws Exception {
		return ngHelper.waitTillElementIsVisible(defaultUserName, highWaitTime)
					   .getInnerText(defaultUserName);
	}
	
	public HomePage clickOnLogoutlnk() throws Exception{
		ngHelper.waitTillElementIsClickable(userProfileOptBtn, maxWaitTime)
				.click(userProfileOptBtn)
				.waitTillElementIsVisible(userProfileOptMenu, maxWaitTime)
				.waitTillElementIsClickable(logoutLnk, maxWaitTime)
				.click(logoutLnk);
		return PageFactory.initElements(driver, HomePage.class);
	}

	public HomePage scrollToContentHeader() throws Exception {
		ngHelper.jScriptScroll(contentHeader)
				.waitTillElementIsVisible(contentHeader, maxWaitTime);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public String getHotSellerPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(contentHeader, maxWaitTime)
					   .getInnerText(contentHeader);
	}
	
	public ProductDetailsPage clickOnFirstProduct() throws Exception{
		ngHelper.waitTillElementIsClickable(productItemlnk, maxWaitTime)
				.click(productItemlnk);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public ProductDetailsPage clickOnSpecificProduct(String productName) throws Exception{
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathProductlnk, productName));
		ngHelper.waitTillElementIsClickable(ele, maxWaitTime)
				.click(ele);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
	
	public NewArrivalPage clickOnWhatsNewlnk() throws Exception{
		ngHelper.waitTillElementIsClickable(newArrivallnk, maxWaitTime)
				.click(newArrivallnk);
		return PageFactory.initElements(driver, NewArrivalPage.class);
	}
	
	public WomenPage clickOnWomenlnk() throws Exception{
		ngHelper.waitTillElementIsClickable(womenlnk, maxWaitTime)
				.click(womenlnk);
		return PageFactory.initElements(driver, WomenPage.class);
	}
	
	public MenPage clickOnMenlnk() throws Exception{
		ngHelper.waitTillElementIsClickable(menlnk, maxWaitTime)
				.click(menlnk);
		return PageFactory.initElements(driver, MenPage.class);
	}
}
