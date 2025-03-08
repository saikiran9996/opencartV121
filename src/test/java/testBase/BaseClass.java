package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeTest(groups = { "Regression", "Master", "Sanity" })
	@Parameters({ "os", "browser" })

	public void setUp(String os, String br) throws IOException {

		// Loading config.properties

		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());// log4j2 //this.getClass()

		// for remote Grid execution

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			// os

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);

			} else if (os.equalsIgnoreCase("Linux")) {
				cap.setPlatform(Platform.LINUX);
			} else {
				System.out.println(" invalid OS....!");
				return;
			}

			// browser

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "fire":
				cap.setBrowserName("FireFox");
				break;
			default:
				System.out.println(" Invalid Browser...!");
				return;

			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "fire":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL"));// reading url from conf.properties
		driver.manage().window().maximize();

	}

	@AfterTest(groups = { "Regression", "Master", "Sanity" })

	public void tearDown() {
		driver.quit();

	}

	// for random String / dynamic values

	public String randomString() {
		String random = RandomStringUtils.randomAlphabetic(5);
		return random;
	}

	public String randomNumber() {
		String randomNum = RandomStringUtils.randomNumeric(10);
		return randomNum;
	}

	public String randomAlphaNum() {
		String randomStr = RandomStringUtils.randomAlphabetic(2);
		String randomNum = RandomStringUtils.randomNumeric(10);
		return (randomNum + "@" + randomStr);
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss").format(new Date());

		TakesScreenshot takesceenshot = (TakesScreenshot) driver;

		File sourceFile = takesceenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\ScreenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
