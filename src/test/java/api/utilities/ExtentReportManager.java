package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spRep;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		//timeStamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		
		//specify location of the report
		spRep = new ExtentSparkReporter(".\\reports\\"+repName);
	
		//Title of the report
		spRep.config().setDocumentTitle("RestassuredAutomationProject");
	
		//name of the report
		spRep.config().setReportName("Pet Store Users API Report");
		spRep.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(spRep);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "naru");
	}
	
	public void onTestSuccess(ITestResult res)
	{
		test = extent.createTest(res.getName());
		test.assignCategory(res.getMethod().getGroups());
		test.createNode(res.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult res)
	{
		test = extent.createTest(res.getName());
		test.createNode(res.getName());
		test.assignCategory(res.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, res.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); 
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
