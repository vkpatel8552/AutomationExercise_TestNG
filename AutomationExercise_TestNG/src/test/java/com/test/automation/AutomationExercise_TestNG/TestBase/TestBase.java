package com.test.automation.AutomationExercise_TestNG.TestBase;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import test.automationframework.Notification.EmailTestExecutionReports;
import test.automationframework.Utils.Efficacies;

public class TestBase {

	Properties emailProperty;
	Properties configProperty;

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "environment"})
	public void intializeEmailConfiguration(String environment) throws IOException {
		configProperty = new Efficacies().loadPropertyFile(environment);
		if (configProperty.getProperty("sendEmail").equalsIgnoreCase("yes"))
			emailProperty = new Efficacies().loadPropertyFile("emailConfig.properties");
	}

	@AfterSuite
	public void emailSentLogic() throws InterruptedException, IOException {
		if (configProperty.getProperty("sendEmail").equalsIgnoreCase("yes")) {
			EmailTestExecutionReports email = new EmailTestExecutionReports(emailProperty);
			Session session = email.setBasicEmailConfiguration().createNewEmailSession();
			Message msg;
			try {
				msg = email.setEmailMsgContent(session);
				email.sendEmail(msg, emailProperty.getProperty("executionReport"));
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}
