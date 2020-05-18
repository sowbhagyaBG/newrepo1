package testScripts;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import driver.DriverScript;

public class UserModuleScripts extends DriverScript{
	//Launch browser->URL->login->logout
	/***********************************************
	 * Script Name	: TC_LoginLogout()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean TC_LoginLogout() {
		WebDriver oBrowser = null;
		Map<String, String> objData = null;
		String strStatus = null;
		try {
			extent = reports.startReport("TC_LoginLogout", "User_101");
			test = extent.startTest("Open Browser & navigate URL");
			objData = datatable.getExcelTestData("Users", "User_101");
			oBrowser = appInd.launchBrowser(objData.get("BrowserName"));
			strStatus+= appDep.navigateURL(oBrowser, appInd.getPropValue("URL"));
			strStatus+= appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password"), false);
			strStatus+= appDep.logoutFromApp(oBrowser);
			strStatus+= appInd.closeBrowser(oBrowser);
			
			if(strStatus.contains("false")) {
				reports.writeResult(null, "Fail", "The test script 'TC_LoginLogout()' failed", test, false);
				return false;
			}else {
				reports.writeResult(null, "Pass", "The test script 'TC_LoginLogout()' Passed", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'TC_LoginLogout()' script. "+e.getMessage(), test, false);
			return false;
		}
		finally {
			reports.endTest(test);
		}
	}
	
	
	//Launch browser->URL->login->Create User->Delete USer->logout
	/***********************************************
	 * Script Name	: TC_CreateDelteUser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean TC_CreateDeleteUser()
	{
		WebDriver oBrowser = null;
		Map<String, String> objData = null;
		String strStatus = null;
		try {
			extent = reports.startReport("TC_LoginLogout", "User_102");
			test = extent.startTest("Open Browser & navigate URL");
			objData = datatable.getExcelTestData("Users", "User_102");
			oBrowser = appInd.launchBrowser(objData.get("BrowserName"));
			strStatus+= appDep.navigateURL(oBrowser, appInd.getPropValue("URL"));
			strStatus+= appDep.loginToApp(oBrowser, objData.get("UserName"), objData.get("Password"), false);
			String strUN = userMethods.createUser(oBrowser, objData);
			strStatus+= userMethods.deleteUser(oBrowser, strUN);
			strStatus+= appDep.logoutFromApp(oBrowser);
			strStatus+= appInd.closeBrowser(oBrowser);
			
			if(strStatus.contains("false")) {
				reports.writeResult(null, "Fail", "The test script 'TC_CreateDeleteUser()' failed", test, false);
				return false;
			}else {
				reports.writeResult(null, "Pass", "The test script 'TC_CreateDeleteUser()' Passed", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'TC_CreateDeleteUser()' script. "+e.getMessage(), test, false);
			return false;
		}
		finally {
			reports.endTest(test);
		}
	}
	
	
	
	//login->CreateUser->loginWithNewUser->Logout->ModifyUser->LoginWithModified->logout->DeleteUser
	/***********************************************
	 * Script Name	: TC_CreateModifyDeleteUser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean TC_CreateModifyDeleteUser()
	{
		WebDriver oBrowser1 = null;
		WebDriver oBrowser2 = null;
		Map<String, String> objData1 = null;
		Map<String, String> objData2 = null;
		String strStatus = null;
		boolean blnRes = false;
		try {
			extent = reports.startReport("TC_LoginLogout", "User_103");
			test = extent.startTest("Open Browser & navigate URL");
			objData1 = datatable.getExcelTestData("Users", "User_103");
			objData2 = datatable.getExcelTestData("Users", "User_103_1");
			oBrowser1 = appInd.launchBrowser(objData1.get("BrowserName"));
			strStatus+= appDep.navigateURL(oBrowser1, appInd.getPropValue("URL"));
			strStatus+= appDep.loginToApp(oBrowser1, objData1.get("UserName"), objData1.get("Password"), false);
			String strUN = userMethods.createUser(oBrowser1, objData1);
			
			//Login with newly created user
			test = extent.startTest("NewUser: Open Browser & navigate URL");
			oBrowser2 = appInd.launchBrowser(objData2.get("BrowserName"));
			strStatus+= appDep.navigateURL(oBrowser2, appInd.getPropValue("URL"));
			blnRes = appDep.loginToApp(oBrowser2, objData1.get("user_UN"), objData1.get("user_PWD"), true);
			if(blnRes) {
				strStatus+= true;
				oBrowser2.close();
			}
			else {
				oBrowser1.close();
				oBrowser2.close();
				reports.writeResult(oBrowser2, "Fail", "Failed to login with new user", test, true);
				return false;
			}
			
			//Modify the user
			strStatus+= userMethods.modifyUser(oBrowser1, strUN, objData2);
			
			//Login with newly modified user
			test = extent.startTest("ModifiedUser: Open Browser & navigate URL");
			oBrowser2 = appInd.launchBrowser(objData2.get("BrowserName"));
			strStatus+= appDep.navigateURL(oBrowser2, appInd.getPropValue("URL"));
			blnRes = appDep.loginToApp(oBrowser2, objData2.get("UserName"), objData2.get("Password"), false);
			if(blnRes) {
				strStatus+= true;
				oBrowser2.close();
			}
			else {
				oBrowser1.close();
				oBrowser2.close();
				reports.writeResult(oBrowser2, "Fail", "Failed to login with newlhy modified user", test, true);
				return false;
			}
			
			strStatus+= userMethods.deleteUser(oBrowser1, strUN);
			strStatus+= appDep.logoutFromApp(oBrowser1);
			strStatus+= appInd.closeBrowser(oBrowser1);
			
			if(strStatus.contains("false")) {
				reports.writeResult(null, "Fail", "The test script 'TC_CreateModifyDeleteUser()' failed", test, false);
				return false;
			}else {
				reports.writeResult(null, "Pass", "The test script 'TC_CreateModifyDeleteUser()' Passed", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'TC_CreateModifyDeleteUser()' script. "+e.getMessage(), test, false);
			return false;
		}
		finally {
			reports.endTest(test);
		}
	}
}
