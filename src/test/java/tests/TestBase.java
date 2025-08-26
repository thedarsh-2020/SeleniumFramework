package tests;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.Helper;


public class TestBase extends AbstractTestNGCucumberTests
{
	
	public static WebDriver driver;
	
	public static String downloadPath = System.getProperty("user.dir") + "/Downloads";
	
	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}
	
	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		//options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
	
	@BeforeSuite
	@Parameters({"browser"})
	// "@Optional("chrome")" de 3mla zy el default parameter, y3ne en ana msh mede ay value fya5od el default mnha
	public void startDriver(@Optional("chrome") String browserName)
	{
		
		// "ignorecase" y3ne msh far2a eza kan el parameter elly gay small OR capital msh far2a
		if (browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver(chromeOption());
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver(firefoxOption());
		}
		else if (browserName.equalsIgnoreCase("ie"))
		{
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.navigate().to("https://demo.nopcommerce.com/");
	}
	
	@AfterSuite
	public void stopDriver()
	{
		driver.quit();
	}
	
	// take screenshots whentest caeses fails and add it in screenshos folder
	// "ITestResult" de el value elly btb2a shyla el result tb3 kol method en kant pass or fail
	@AfterMethod
	public void screenshotOnFailure(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
	
}
