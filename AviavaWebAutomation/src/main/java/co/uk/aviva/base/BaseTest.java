package co.uk.aviva.base;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import co.uk.aviva.utils.Config;
import co.uk.aviva.utils.WaitTime;

public class BaseTest {

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	DesiredCapabilities capability;

	public static ExtentReports extent;
	// helps to generate the logs in test report.
	public static ExtentTest test;

	@BeforeSuite
	public void startReport() throws IOException {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/testReport.html");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", Config.getConfigData("browser"));

		// configuration items to change the look and feel
		// add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Reorts");
		htmlReporter.config().setReportName("AviavaWebAutomation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@BeforeMethod
	public void setUp() throws IOException {
		String basepath = System.getProperty("user.dir");
		if (Config.getConfigData("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", basepath + "\\drivers\\chromedriver.exe");
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			//driver = new ChromeDriver(); // opens chrome brower
		} else if (Config.getConfigData("browser").equalsIgnoreCase("ie")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("IE");
			capability.setPlatform(Platform.WIN10);
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		driver.get(Config.getConfigData("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(WaitTime.MEDIUM_WAIT, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		driver.quit();
	}

	@AfterSuite
	public void generateReport() {
		extent.flush();
	}
}
