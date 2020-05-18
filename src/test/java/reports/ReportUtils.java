package reports;

import java.io.File;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import driver.DriverScript;

public class ReportUtils extends DriverScript{
	/*****************************************
	 * Method Name	: startReport()
	 * Purpose		: to create Extent report html file
	 * Author		: 
	 * 
	 ****************************************/
	public ExtentReports startReport(String fileName, String testCaseName)
	{
		File resultDir = null;
		File screenshotDir = null;
		String strResDir = null;
		try {
			strResDir = System.getProperty("user.dir")+"\\Results";
			strResLocation = strResDir + "\\" + testCaseName;
			strScrnShotLocation = strResLocation + "\\ScreenShots";
			
			resultDir = new File(strResLocation);
			if(!resultDir.exists()) {
				resultDir.mkdir();
			}
			
			screenshotDir = new File(strScrnShotLocation);
			if(!screenshotDir.exists()) {
				screenshotDir.mkdir();
			}
			
			extent = new ExtentReports(strResLocation+"\\"+fileName+"_"+
						appInd.getDateTime("ddMMYY_hhmmss")+".html", true);
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			extent.addSystemInfo("Environment", System.getProperty("os.version"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;			
		}catch(Exception e)
		{
			System.out.println("Exception in 'startReport()' method. "+e.getMessage());
			return null;
		}
		finally {
			resultDir = null;
			screenshotDir = null;
			strResDir = null;
		}
	}
	
	
	
	/*****************************************
	 * Method Name	: endTest()
	 * Purpose		: to end the extend test
	 * Author		: 
	 * 
	 ****************************************/
	public void endTest(ExtentTest test)
	{
		try {
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			System.out.println("Exception in 'endTest()' method. "+e.getMessage());
		}
	}
	
	
	
	/*****************************************
	 * Method Name	: writeResult()
	 * Purpose		: to write the reports to the extent file
	 * Author		: 
	 * 
	 ****************************************/
	public void writeResult(WebDriver oDriver, String status, String description, ExtentTest test, boolean isScnShotReqd)
	{
		try {
			if(isScnShotReqd) {
				switch(status.toLowerCase()) {
					case "pass":
						test.log(LogStatus.PASS, description +": "+
								test.addScreenCapture(appInd.captureScreenShot(oDriver)));
						break;
					case "fail":
						test.log(LogStatus.FAIL, description +": "+
								test.addScreenCapture(appInd.captureScreenShot(oDriver)));
						break;
					case "warning":
						test.log(LogStatus.WARNING, description +": "+
								test.addScreenCapture(appInd.captureScreenShot(oDriver)));
						break;
					case "exception":
						test.log(LogStatus.FATAL, description +": "+
								test.addScreenCapture(appInd.captureScreenShot(oDriver)));
						break;
					default:
						System.out.println("Invalid result status '"+status+"'");
						
						
				}
			}else {
				switch(status.toLowerCase()) {
				case "pass":
					test.log(LogStatus.PASS, description);
					break;
				case "fail":
					test.log(LogStatus.FAIL, description);
					break;
				case "warning":
					test.log(LogStatus.WARNING, description);
					break;
				case "exception":
					test.log(LogStatus.FATAL, description);
					break;
				default:
					System.out.println("Invalid result status '"+status+"'");
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception in 'writeResult()' method. "+e.getMessage());
		}
	}
}
