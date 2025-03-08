package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentsReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;// for UI of the report
	public ExtentReports extent;// common info on the report like name and title and theme
	public ExtentTest test;// creating test cases entries in the report and update the status of the test
							// methods
	String repName;

	public void onStart(ITestContext context) {

		/*
		 * SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=
		 * new Date(); String currentdatetimestamp= df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp

		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// location of
																			// the
																			// file(report)
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");// title of the report
		sparkReporter.config().setReportName("Opencart Functional Tester");// name of the user who automate
		sparkReporter.config().setTheme(Theme.DARK);// theme color

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // attach the extent report to spark report
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Envinorment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operation System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry of the report
		test.assignCategory(result.getMethod().getGroups());// to display groups in reports
		test.log(Status.PASS, result.getName() + " Got ScuccessFully Exceuted");// update status p/f/s
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry of the report
		test.assignCategory(result.getMethod().getGroups());// to display groups in reports

		test.log(Status.FAIL, result.getName() + "Got Failed ");// update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry of the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case Skipped is: " + result.getName());// update status p/f/s
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {

		extent.flush();// write all test updated in report and it is mandatory

		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	/*	try {

			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);

			// create the email msg
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googleemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("saikiranshanigaram99@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("saikiranshanigaram99@gmail.com");// sender
			email.setSubject("Test Result");
			email.setMsg("please find the attached report....!");
			email.addTo("saikiranshanigaram@gmail.com");// receiver
			email.attach(url, "extent report", " please check the report....!");
			email.send();// send email

		} catch (MalformedURLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */

	}

}
