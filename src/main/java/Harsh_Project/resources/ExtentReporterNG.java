package Harsh_Project.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() 
	
	{
		String path = System.getProperty("user.dir") + "//Reports//report.html";     //setting address for report
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);      //ExtentSparkReporter class helps for providing basic details (meta data).
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");     
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);                             // attaching ExtentSparkReporter object with ExtentReports class.
		extent.setSystemInfo("Tester", "Harsh Vibhor Singh");
		return extent;
	}

}
