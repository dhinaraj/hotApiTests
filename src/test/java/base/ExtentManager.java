package base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;
    private static ITestContext context;
    

    
    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
           String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
           File outputDirectory = new File(context.getOutputDirectory());
           File resultDirectory = new File(outputDirectory.getParentFile(), "Extent_html_Reports");
           extent = new ExtentReports(resultDirectory + File.separator +  "Report"+timeStamp+".html", true);
           Reporter.log("Extent Report directory: " + resultDirectory, true);
        }

        return extent;
    }
    
    public synchronized static ExtentReports getInstance(String reportFileName) {
        if (extent == null) {
           
           File outputDirectory = new File(context.getOutputDirectory());
           File resultDirectory = new File(outputDirectory.getParentFile(), "Extent_html_Reports");
          
           extent = new ExtentReports(resultDirectory + File.separator +  reportFileName , true);
           Reporter.log("Extent Report directory: " + resultDirectory, true);
           
        }

        return extent;
    }

    public static void setOutputDirectory(ITestContext context) {
        ExtentManager.context = context;
    }
    
}