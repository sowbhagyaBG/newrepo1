package methods;

import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import driver.DriverScript;
import locators.ObjectLocators;

public class UserModuleMethods extends DriverScript implements ObjectLocators{
	/***********************************************
	 * Method Name	: createUser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public String createUser(WebDriver oDriver, Map<String, String> data)
	{
		String strStatus = null;
		try {
			test = extent.startTest("createUser");
			strStatus+= appInd.clickObject(oDriver, obj_User_Menu);
			appInd.waitFor(oDriver, obj_AddUser_Btn, "Element", "", 20);
			strStatus+= appInd.clickObject(oDriver, obj_AddUser_Btn);
			appInd.waitFor(oDriver, obj_CreateUser_Btn, "Clickable", "", 20);
			
			strStatus+= appInd.setObject(oDriver, obj_FN_Edit, data.get("FN"));
			strStatus+= appInd.setObject(oDriver, obj_LN_Edit, data.get("LN"));
			strStatus+= appInd.setObject(oDriver, obj_eMail_Edit, data.get("email"));
			strStatus+= appInd.setObject(oDriver, obj_User_UserName_Edit, data.get("user_UN"));
			strStatus+= appInd.setObject(oDriver, obj_User_Password_Edit, data.get("user_PWD"));
			strStatus+= appInd.setObject(oDriver, obj_User_Retype_Edit, data.get("user_ReType"));
			
			String strUser = data.get("LN")+", "+data.get("FN");
			strStatus+= appInd.clickObject(oDriver, obj_CreateUser_Btn);
			appInd.waitFor(oDriver, By.xpath("//div[@class='name']/span[text()='"+strUser+"']"), "Text", strUser, 20);
			
			//Validate user is created..
			strStatus+= appInd.isElementPresent(oDriver, By.xpath("//div[@class='name']/span[text()='"+strUser+"']"));
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to create the user", test, true);
				return null;
			}else {
				reports.writeResult(oDriver, "Pass", "The User is created successful", test, false);
				return strUser;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'createUser()' method. "
					+e.getMessage(), test, true);
			return null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: deleteUser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean deleteUser(WebDriver oDriver, String UserName)
	{
		String strStatus = null;
		try {
			test = extent.startTest("deleteUser");
			strStatus+= appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+UserName+"'"+"]"));
			appInd.waitFor(oDriver, obj_SaveChanges_Btn, "Clickable", "", 20);
			strStatus+= appInd.clickObject(oDriver, obj_DeleteUser_Btn);
			appInd.waitFor(oDriver, "", "Alert", "", 20);
			
			oDriver.switchTo().alert().accept();
			appInd.waitFor(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+UserName+"'"+"]"), "InVisible", "", 5);
			
			//validate user is deleted?
			strStatus+= appInd.isElementNotPresent(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+UserName+"'"+"]"));
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to delete the user", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "User is deleted successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'deleteUser()' method. "
					+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: modifyUser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean modifyUser(WebDriver oDriver, String strUserName, Map<String, String> objData)
	{
		String strStatus = null;
		try {
			test = extent.startTest("modifyUser");
			strStatus+= appInd.clickObject(oDriver, By.xpath("//div[@class='name']/span[text()="+"'"+strUserName+"'"+"]"));
			appInd.waitFor(oDriver, obj_SaveChanges_Btn, "Clickable", "", 20);
			strStatus+= appInd.clearAndSetObject(oDriver, obj_eMail_Edit, objData.get("email"));
			strStatus+= appInd.setObject(oDriver, obj_User_Password_Edit, objData.get("user_PWD"));
			strStatus+= appInd.setObject(oDriver, obj_User_Retype_Edit, objData.get("user_ReType"));
			
			strStatus+= appInd.clickObject(oDriver, obj_SaveChanges_Btn);
			
			appInd.waitFor(oDriver, obj_SaveChanges_Btn, "InVisible", "", 20);
			
			if(strStatus.contains("false")) {
				reports.writeResult(oDriver, "Fail", "Failed to modify the user '"+strUserName+"'", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "Failed to modify the user '"+strUserName+"'", test, false);
				return true;
			}
			
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'modifyUser()' method. "
					+e.getMessage(), test, true);
			return false;
		}
	}
}
