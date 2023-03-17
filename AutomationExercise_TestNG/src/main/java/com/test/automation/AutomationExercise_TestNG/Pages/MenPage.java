package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenPage extends StartUpPage {

	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.content-heading>h2.title")
	public WebElement contentHeader;

	@FindBy(css="div.page-title-wrapper span")
	public WebElement menPageHeader;

	String xpathProductlnk="//div[@class='product-item-details']//a[contains(text(),'{PARAM1}')]";
	
	public MenPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public MenPage waitForMenPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("Men", maxWaitTime)
				.waitTillElementIsVisible(menPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, MenPage.class);
	}	
	
	public MenPage scrollToHotSellerSection() throws Exception {
		ngHelper.jScriptScroll(contentHeader)
				.waitTillElementIsVisible(contentHeader, maxWaitTime);
		return PageFactory.initElements(driver, MenPage.class);
	}

	public String getMenPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(menPageHeader, maxWaitTime)
					   .getInnerText(menPageHeader).trim();
	}
	
	public ProductDetailsPage clickOnSpecificProduct(String productName) throws Exception{
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathProductlnk, productName));
		ngHelper.waitTillElementIsClickable(ele, maxWaitTime)
				.click(ele);
		return PageFactory.initElements(driver, ProductDetailsPage.class);
	}
}
