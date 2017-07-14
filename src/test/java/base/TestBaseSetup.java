package base;

import static com.jayway.restassured.config.RestAssuredConfig.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.config.SSLConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;


import base.ExtentManager;
import base.ExtentTestManager;


/*
 * This is a base Test class. All testng tests needs to inherited from
 * this class. It contains common reporting infra
 */
public class TestBaseSetup  {
	
	//Initiate Webdriver before each class
	
    /*private WebDriver driver;
	static String driverPath = "/Users/Dhinakaran/Plus65/Automation Projects/metalkRevamp_testNG/src/test/resources/";
	

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath
				+ "chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	


	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}*/

	//End of Initiate Webdriver before each class
	
	//Code for Extent report logging
	
	public static ExtentReports extent;
	
	public static RestAssuredConfig requestConfig;
	public static RestAssuredConfig responseConfig;
	public static RestAssuredConfig config;
	public static String reportName ="";
	public static String logFilesDriectoryName ="";
	public static String logFileName = "";
	//private static String screenShot_Folder_TimeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());

    @BeforeSuite
    public void extentSetup(ITestContext context) {
    	String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
    	reportName = "Report"+timeStamp+".html";
    	logFilesDriectoryName = "Rest_Assured_Log_Files_"+timeStamp;
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance(reportName);
    }

    /*@BeforeMethod
    public void startExtent(Method method)  {
        ExtentTestManager.startTest(method.getName()); // new

    }*/
    
    @BeforeMethod
    public void startExtent(Method method) throws FileNotFoundException  {
        ExtentTestManager.startTest(method.getName()); // new

    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
    
    

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws IOException {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));  // new
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));  // new

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);  // new
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");  // new
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));// new
            /*TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            String filePath = "./test-output/Extent_html_Reports/Screenshots_" + screenShot_Folder_TimeStamp+"/"+result.getName()+".png";
            FileUtils.copyFile(source, new File(filePath));
            String relativeFilePath = "./Screenshots_" + screenShot_Folder_TimeStamp+"/"+result.getName()+".png";
            ExtentTestManager.getTest().log(LogStatus.INFO, "Screen Shot at Failure: " + ExtentTestManager.getTest().addScreenCapture(relativeFilePath));*/
            ;
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");  // new

        }

        ExtentTestManager.endTest();  // new

        extent.flush();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
    
  //End of Code for Extent report logging


}
