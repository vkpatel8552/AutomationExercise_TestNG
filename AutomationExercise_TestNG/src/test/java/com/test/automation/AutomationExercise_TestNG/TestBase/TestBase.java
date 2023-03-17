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

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "emailConfiguration"})
	public void intializeEmailConfiguration(String emailConfiguration) throws IOException {
		emailProperty = new Efficacies().loadPropertyFile(emailConfiguration);
	}

	@AfterSuite
	public void emailSentLogic() throws InterruptedException {
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
