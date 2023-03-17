================= Required Configuration Changes ===============

1) Update Config.properties file

>>> Go to src/main folder under your project
>>> Open config.properties file
>>> Update required properties based on your project need like
browser, baseURL, username, password etc

2) Update EmailConfig.properties file

>>> Go to src/main folder under your project
>>> Open emailConfig.properties file
>>> Update required properties based on your project need like
senderEmail, senderPassword, fromEmail, toEmail, emailSubject, projectName, executionReport

PS: senderPassword should be 16 digit encrypted password which you can get from GMail >> Settings
executionReport: must contains .html extension

3) Update extent-config.xml file
>>> Go to src/main folder under your project
>>> Open extent-config.properties file
>>> Update required properties based on your project need like
documentTitle, reportName

=================== Newly added testng.xml file ==============

Make sure ExtentReportListener and RetryListener added before test suite starts

=================== How to run the Project? ===================

First Way:
1) Select the testng.xml file under the project
2) Right click and select option Run As >> TestNG

Second Way:
1) Under POM.xml make sure testng.xml file added under maven-surefire-plugin
2) Right click on project
3) Run As >> Run Configuration >> Maven Run >> Create new run 
4) Give name as AutomationExercise_TestNG 
5) Select directory as AutomationExercise_TestNG workspace 
6) Add goal as clean verify >> run

================= Default Java Verison ==================

To avoid compilation error for ITestListner class with latest testng dependencies, 
kept Java version as 11 in maven-sure-fire-plugin. Make sure in your machine JRE11 or later 
installed and set as Default under Windows >> Preferences >> Java >> Installed JREs. If not 
you can add and select as Default version to work project smoothly.
