package methods;

import org.openqa.selenium.WebDriver;
import driver.DriverScript;
import locators.ObjectLocators;

public class AppDependentMethods extends DriverScript implements ObjectLocators{
	/***********************************************
	 * Method Name	: navigateURL()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean navigateURL(WebDriver oDriver, String strURL)
	{
		try {
			oDriver.navigate().to(strURL);
			Thread.sleep(2000);
			
			if(appInd.compareValues(oDriver, oDriver.getTitle(), "actiTIME - Login")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'navigateURL()' method. "
					+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name	: loginToApp()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean loginToApp(WebDriver oDriver, String UN, String PWD, boolean isNewUser)
	{
		String strStatus = null;
		try {
			test = extent.startTest("loginToApp");
			strStatus+= appInd.setObject(oDriver, obj_UserName_Edit, UN);
			Thread.sleep(1000);
			strStatus+= appInd.setObject(oDriver, obj_Password_Edit, PWD);
			Thread.sleep(2000);
			strStatus+= appInd.clickObject(oDriver, obj_Login_Btn);
			Thread.sleep(4000);
			
			//Validate If user is login for the first time
			if(isNewUser) {
				if(appInd.isOptionalElementPresent(oDriver, obj_StartExplore_Link)) {
					strStatus+= appInd.clickObject(oDriver, obj_StartExplore_Link);
				}else {
					reports.writeResult(oDriver, "Fail", "Start exploring link doesnot displayed for the new user", test, true);
				}
			}
			
			//Handle shortcut window
			if(appInd.isOptionalElementPresent(oDriver, obj_ShortCut_Wnd)) {
				strStatus+= appInd.clickObject(oDriver, obj_ShortCut_CloseBtn);
			}
			
			//verify login happen successful
			strStatus+= appInd.verifyText(oDriver, obj_Page_Title, "Text", "Enter Time-Track");
			
			if(strStatus.contains("false"))
			{
				reports.writeResult(oDriver, "Fail", "Failed to login to actiTime Application", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "The login to actiTime was successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'loginToApp()' method. "
					+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: logoutFromApp()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean logoutFromApp(WebDriver oDriver)
	{
		String strStatus = null;
		try {
			test = extent.startTest("logoutFromApp");
			strStatus+= appInd.clickObject(oDriver, obj_Logout_Link);
			Thread.sleep(2000);
			
			strStatus+= appInd.verifyText(oDriver, obj_Header_Label, "Text", "Please identify yourself");
			
			if(strStatus.contains("false"))
			{
				reports.writeResult(oDriver, "Fail", "Failed to logout from the application", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "Logout is successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'logoutFromApp()' method. "
					+e.getMessage(), test, true);
			return false;
		}
	}
}
