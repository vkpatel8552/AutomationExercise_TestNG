package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWishListPage extends StartUpPage{


	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;

	@FindBy(css="div.page-title-wrapper span")
	public WebElement myWishListPageHeader;

	@FindBy(css="div[role='alert']>div.message>div")
	public WebElement addToWishListMsg;
	
	@FindBy(xpath="//div[@class='block-title']//strong[contains(text(),'My Wish List')]")
	public WebElement myWishListHeader;

	String xpathRemoveProductIcon = "//div[@class='product-item-details']//span[text()='{PARAM1}']//ancestor::strong/following-sibling::div[@class='product-item-actions']//a[@title='Remove This Item']";
	
	public MyWishListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public MyWishListPage waitForMyWishListPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("My Wish List", maxWaitTime)
				.waitTillElementIsInvisible(defaultWelMsg, maxWaitTime);
		return PageFactory.initElements(driver, MyWishListPage.class);
	}

	public String getMyWishListPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(myWishListPageHeader, maxWaitTime)
					   .getInnerText(myWishListPageHeader).trim();
	}
	
	public String getAddToWishListSuccessMsg() throws Exception {
		return ngHelper.waitTillElementIsVisible(addToWishListMsg, maxWaitTime)
					   .getInnerText(addToWishListMsg);
	}
	
	public MyWishListPage clickOnRemoveProductFromWishList(String productName) throws Exception {
		ngHelper.jScriptScroll(myWishListHeader);
		
		WebElement removeProductIcon = ngHelper
				.findTheElement(By.xpath(CommonPage.generateDynamicXPath(xpathRemoveProductIcon, productName)));

		ngHelper.waitTillElementIsVisible(removeProductIcon, maxWaitTime)
				.jScriptScrollAndClick(removeProductIcon);
		return PageFactory.initElements(driver, MyWishListPage.class);
	}

}
