package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterUserPage extends StartUpPage{

	@FindBy(xpath="//li[contains(@class,'welcome')]//span[contains(text(),'Default welcome msg')]")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.page-title-wrapper span")
	public WebElement regPageHeader;
	
	@FindBy(css="button[title='Create an Account']")
	public WebElement createAccBtn;
	
	String xpathUserInfoField = "//div[contains(@class,'required')]//span[text()='{PARAM1}']/parent::label//following-sibling::div//input";
	
	public RegisterUserPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public RegisterUserPage waitForRegisterUserPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleMatches("Create New Customer", maxWaitTime)
				.waitTillElementIsVisible(defaultWelMsg, maxWaitTime);
		return PageFactory.initElements(driver, RegisterUserPage.class);
	}	
	
	public String getRegisterPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(regPageHeader, maxWaitTime)
					   .getInnerText(regPageHeader).trim();
	}
	
	public RegisterUserPage enterUserPersonalDetails(String fieldName, String fieldVal) throws Exception {
		By ele = By.xpath(CommonPage.generateDynamicXPath(xpathUserInfoField, fieldName));
		WebElement elem = ngHelper.findTheElement(ele);
		
		ngHelper.waitTillElementIsVisible(elem, maxWaitTime)
				.clickAndClear(elem)
				.sendKeysTo(elem, fieldVal);
		return PageFactory.initElements(driver, RegisterUserPage.class);
	}
	
	public MyAccountPage clickOnCreateAccountBtn() throws Exception {
		ngHelper.waitTillElementIsClickable(createAccBtn, maxWaitTime)
				.click(createAccBtn);
		return PageFactory.initElements(driver, MyAccountPage.class);
	}

}
