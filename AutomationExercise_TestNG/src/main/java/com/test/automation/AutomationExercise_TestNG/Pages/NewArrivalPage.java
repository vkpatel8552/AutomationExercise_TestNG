package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewArrivalPage extends StartUpPage{

	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.content-heading>h2.title")
	public WebElement contentHeader;

	@FindBy(css="div.page-title-wrapper span")
	public WebElement newArrivalPageHeader;
	
	public NewArrivalPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public NewArrivalPage waitForNewArrivalPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("What's New", maxWaitTime)
				.waitTillElementIsVisible(newArrivalPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, NewArrivalPage.class);
	}	
	
	public NewArrivalPage scrollToLatestSection() throws Exception {
		ngHelper.jScriptScroll(contentHeader)
				.waitTillElementIsVisible(contentHeader, maxWaitTime);
		return PageFactory.initElements(driver, NewArrivalPage.class);
	}
	
	public String getNewArrivalPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(newArrivalPageHeader, maxWaitTime)
					   .getInnerText(newArrivalPageHeader).trim();
	}

	public String getLatestCollectionPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(contentHeader, maxWaitTime)
					   .getInnerText(contentHeader);
	}
}
