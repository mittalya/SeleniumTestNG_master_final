package BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
public class BaseTest {
protected WebDriver driver;
protected static ExtentTest test;
static ExtentReports report;
 

@BeforeSuite
public void beforesuite(){
	report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
	test = report.startTest("SmokeTest");
}

@BeforeTest
@Parameters ({"browser","url"})
public void beforeTest(String browser, String url) throws Exception{
	if(browser.equalsIgnoreCase("Chrome")) {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
	  driver = new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("Firefox")) {
		  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
		  driver = new FirefoxDriver();
		}
	else if(browser.equalsIgnoreCase("IE")) {
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.enablePersistentHovering();
		options.introduceFlakinessByIgnoringSecurityDomains();
		options.ignoreZoomSettings();
		options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		options.setCapability("allow-blocked-content", true);
		options.setCapability("allowBlockedContent", true);
		
		/*DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("allow-blocked-content", true);
		capabilities.setCapability("allowBlockedContent", true);*/
		 
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
		  
		  driver = new InternetExplorerDriver(options);
		}
	else {
		throw new Exception("Browser is not correct");
	}

	  driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	  driver.get(url);
 	  
}


public static String capture(WebDriver driver) throws IOException {
File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()+ ".png");
String errflpath = Dest.getAbsolutePath();
FileUtils.copyFile(scrFile, Dest);
return errflpath;
}

/*public void TakeScreenshots(WebDriver driver ) throws Exception {
	String FileWithPath = "C:\\Users\\yasmittal\\Documents\\Selenium package\\Selenium package\\SeleniumTestNG-master_final\\test-output\\Screenshots";
	TakesScreenshot scrShot = ((TakesScreenshot)driver);
	File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
	File DestFile = new File(FileWithPath);
	FileUtils.copyFile(ScrFile, DestFile);
}*/

@BeforeClass
public static void startTest()
{
/*	// loading log4j.xml file
    DOMConfigurator.configure("log4j.xml");
    private static Logger Log = Logger.getLogger(Log.class.getName());*/
}

@AfterMethod

public void afterMethod() {
}

@AfterClass
public void endTest()
{

}

@AfterTest
  public void afterTest() {

  }



@AfterSuite(alwaysRun=true)
	  public void afterSuite() {
		try { 
			SendMailSSLWithAttachment email = new SendMailSSLWithAttachment();
			email.sendEmail();
			report.endTest(test);
			report.flush();
			driver.close(); 
			driver.quit();
			} 
		catch (SessionNotCreatedException e) {}
	  }

}
