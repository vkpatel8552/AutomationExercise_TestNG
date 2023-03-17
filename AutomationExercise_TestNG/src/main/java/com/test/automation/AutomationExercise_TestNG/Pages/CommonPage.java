package com.test.automation.AutomationExercise_TestNG.Pages;

import org.openqa.selenium.WebDriver;

public class CommonPage extends StartUpPage {

	public CommonPage(WebDriver driver) {
		super(driver);
	}
	
	public static String generateDynamicXPath(String xpath, String dynamicValues) {
		Integer count = 1;
		for (String value : dynamicValues.split(";")) {
			xpath = xpath.replace("{PARAM" + count.toString() + "}", value);
			count++;
		}
		return xpath;
	}

	 public static String generateDynamicCSS(String css, String dynamicValues) {
			Integer count = 1;
			for (String value : dynamicValues.split(";")) {
				css = css.replace("{PARAM" + count.toString() + "}", value);
				count++;
			}
			return css;
		}

}
