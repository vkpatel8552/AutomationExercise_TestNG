package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import test.automationframework.UIFundamentals.Acolyte;
import test.automationframework.Utils.NGHelper;

public class StartUpPage {

	public WebDriver driver;
	public NGHelper ngHelper;
	public Acolyte acolyte;

	public static int unitTime = 1;
	public static int sleepWait = 3;
	public static int minWaitTime = 30;
	public static int maxWaitTime = 60;
	public static int highWaitTime = 180;

	public StartUpPage(WebDriver driver) {
		this.driver = driver;
		ngHelper = new NGHelper(driver);
		acolyte = new Acolyte(driver);
	}

	public StartUpPage() {
		
	}

	public HomePage navigateToPage(String url) throws Exception {
		ngHelper.maximizeWindow().navigateTo(url);
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void killDriver() {
		if (driver != null)
			driver.quit();
	}
	
}
