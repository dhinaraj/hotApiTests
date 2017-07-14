package base;

import static com.jayway.restassured.config.RestAssuredConfig.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.Reporter;

import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.config.SSLConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.ExtentTestManager;





public class TestLogger extends TestBaseSetup {
	
	private static ITestContext context;
	
	public static void logStep(final String message) {
          //Reporter.log(message + "<br>", true);
          ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");  // new
	}
    
    public static void attachFile(final String fileName) {
        Reporter.log(fileName + "<br>", true);
        ExtentTestManager.getTest().log(LogStatus.INFO, "<a href='/Users/Dhinakaran/Plus65/Automation Projects/arrowMiiApiTests/"+fileName+"'>ResultFile</a>" + "<br>");  // new
  }
    

    public static RestAssuredConfig requestFileConfig(Method method) throws FileNotFoundException  {
   	 String methodName = method.getName();
  	  //final StringWriter writer = new StringWriter();
      //final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
  	  final PrintStream captor = new PrintStream(new FileOutputStream(methodName+"_Request.txt", true));
  	  requestConfig = config().logConfig(LogConfig.logConfig().defaultStream(captor).and().enablePrettyPrinting(false)).sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
  	  return requestConfig;

   }
   
   public static RestAssuredConfig responseFileConfig(Method method) throws FileNotFoundException  {
  	 String methodName = method.getName();
 	  //final StringWriter writer = new StringWriter();
     //final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
 	  final PrintStream captor = new PrintStream(new FileOutputStream(methodName+"_Response.txt", true));
 	  responseConfig = config().logConfig(LogConfig.logConfig().defaultStream(captor).and().enablePrettyPrinting(false)).sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
 	return responseConfig;

  }
   
   public static RestAssuredConfig logConfig() throws FileNotFoundException  {
	      logFileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date())+".txt";
	      String logFilePathName = "/Users/Dhinakaran/Plus65/Automation Projects/arrowMiiApiTests/test-output/Extent_html_Reports/"+logFilesDriectoryName+"/"+logFileName;
	 	 // final StringWriter writer = new StringWriter();
	      //final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
	      boolean success = new File("/Users/Dhinakaran/Plus65/Automation Projects/arrowMiiApiTests/test-output/Extent_html_Reports/"+logFilesDriectoryName).mkdir();
	      if (success) {
	          System.out.println("Directory: " + "/Users/Dhinakaran/Plus65/Automation Projects/arrowMiiApiTests/test-output/Extent_html_Reports/"+logFilesDriectoryName + " created");
	        }
	      final File file = new File(logFilePathName);
	 	  final PrintStream captor = new PrintStream(new FileOutputStream(file, true));
	 	 config = config().logConfig(LogConfig.logConfig().defaultStream(captor).and().enablePrettyPrinting(false)).sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
	 	Reporter.log(logFileName + "<br>", true);
        ExtentTestManager.getTest().log(LogStatus.INFO, "<a href='"+logFilePathName+"'>Request or Response log file</a>" + "<br>");
	 	return config;

	  }
   
 

    
    

}
