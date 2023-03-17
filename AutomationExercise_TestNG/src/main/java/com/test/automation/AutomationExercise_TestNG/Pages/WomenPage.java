package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WomenPage extends StartUpPage {

	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.content-heading>h2.title")
	public WebElement contentHeader;

	@FindBy(css="div.page-title-wrapper span")
	public WebElement womenPageHeader;

	public WomenPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public WomenPage waitForWomenPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("Women", maxWaitTime)
				.waitTillElementIsVisible(womenPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, WomenPage.class);
	}	
	
	public WomenPage scrollToHotSellerSection() throws Exception {
		ngHelper.jScriptScroll(contentHeader)
				.waitTillElementIsVisible(contentHeader, maxWaitTime);
		return PageFactory.initElements(driver, WomenPage.class);
	}

	public String getWomenPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(womenPageHeader, maxWaitTime)
					   .getInnerText(womenPageHeader).trim();
	}
	
}
