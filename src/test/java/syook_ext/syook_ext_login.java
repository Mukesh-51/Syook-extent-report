package syook_ext;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class syook_ext_login {

	static WebDriver D;
	public static ExtentTest test;
	static ExtentReports report;
	StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
		report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report-config.xml"));

	}

	@BeforeMethod
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Mukesh\\Softwares\\Drivers\\chromedriver_win32\\chromedriver.exe");
		D = new ChromeDriver();
		D.manage().window().maximize();
	}

	@Test
	public void invalid_Login() {
		D.get("https://test.syookinsite.com/");
		D.findElement(By.id("userEmail")).sendKeys("admin@syook.co");
		D.findElement(By.id("userPassword")).sendKeys("180@Domlur");
		D.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/form/div[4]/button")).click();
		try {
			D.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 assertEquals(D.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/form/div[1]/span")).getText(),
			 "Log in to your Syook account");
			//assertEquals(D.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/a[3]/span")).getText(), "Metrics");
			test.log(LogStatus.FAIL, "Login with invalid cred passed!!!!!!!!");
			// test.log(Status.PASS, "Login Passed..!!");

		} catch (Error e) {
			// verificationErrors.append(e.toString());
			test.log(LogStatus.PASS, "Logged in with invalid cred!!!!!!!!");
			//Aman asked for change
			// test.log(Status.FAIL, "Login Failed..!!");
		}

	}

	@Test
	public void val_Login() {
		D.get("https://test.syookinsite.com/");
		D.findElement(By.id("userEmail")).sendKeys("admin@syook.com");
		D.findElement(By.id("userPassword")).sendKeys("180@Domlur");
		D.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/form/div[4]/button")).click();
		try {
			D.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			assertEquals(D.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/a[3]/span")).getText(), "Metrics");
			test.log(LogStatus.PASS, "Login passed!!!!!!!!");
			// test.log(Status.PASS, "Login Passed..!!");

		} catch (Error e) {
			// verificationErrors.append(e.toString());
			test.log(LogStatus.FAIL, "Login failed!!!!!!!!");
			// test.log(Status.FAIL, "Login Failed..!!");
		}

	}

	@AfterMethod
	public void endTest() {
		D.close();
	}

	@AfterTest
	public void extEnd() {
		report.flush();

	}
}
