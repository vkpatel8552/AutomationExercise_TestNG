package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends StartUpPage {

	@FindBy(css="div.page-title-wrapper span")
	public WebElement loginPageHeader;
	
	@FindBy(css="input#email")
	public WebElement inputEmail;
	
	@FindBy(css="input#pass")
	public WebElement inputPassword;
	
	@FindBy(css="button.primary#send2")
	public WebElement loginBtn;
	
	@FindBy(css="div[role='alert']>div.message>div")
	public WebElement loginErrMsg;
	
	@FindBy(linkText="Create an Account")
	public WebElement createAccBtn;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public LoginPage waitForLoginPageToLoad() throws Exception {
		ngHelper.waitTillPageTitleMatches("Customer Login", maxWaitTime)
				.waitTillElementIsVisible(loginPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public RegisterUserPage clickOnCreateAccBtn() throws Exception {
		ngHelper.waitTillElementIsVisible(createAccBtn, maxWaitTime)
				.click(createAccBtn);
		return PageFactory.initElements(driver, RegisterUserPage.class);
	}
	
	public String getLoginPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(loginPageHeader, maxWaitTime)
					   .getInnerText(loginPageHeader);
	}
	
	public LoginPage enterUserEmail(String userName) throws Exception {
		ngHelper.waitTillElementIsVisible(inputEmail, maxWaitTime)
				.sendKeysTo(inputEmail, userName);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public LoginPage enterPassword(String userName) throws Exception {
		ngHelper.waitTillElementIsVisible(inputPassword, maxWaitTime)
				.sendKeysTo(inputPassword, userName);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public HomePage clickOnLoginBtn() throws Exception {
		ngHelper.waitTillElementIsClickable(loginBtn, maxWaitTime)
				.click(loginBtn);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public LoginPage clickOnLoginBtnInvalidCred() throws Exception {
		ngHelper.waitTillElementIsClickable(loginBtn, maxWaitTime)
				.click(loginBtn);
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public String getLoginErrMsg() throws Exception {
		return ngHelper.waitTillElementIsVisible(loginErrMsg, maxWaitTime)
					   .getInnerText(loginErrMsg);
	}
}
