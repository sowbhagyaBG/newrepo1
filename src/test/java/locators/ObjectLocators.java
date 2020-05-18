package locators;

public interface ObjectLocators {
	String obj_UserName_Edit = "//input[@id='username']";
	String obj_Password_Edit = "//input[@name='pwd']";
	String obj_Login_Btn = "//a[@id='loginButton']";
	String obj_Page_Title = "//td[@class='pagetitle']";
	String obj_ShortCut_Wnd = "//div[@id='gettingStartedShortcutsMenuWrapper']";
	String obj_ShortCut_CloseBtn = "//div[@id='gettingStartedShortcutsMenuCloseId']";
	String obj_User_Menu = "//div[text()='USERS']";
	String obj_AddUser_Btn = "//div[text()='Add User']";
	String obj_FN_Edit = "//input[@name='firstName']";
	String obj_LN_Edit = "//input[@name='lastName']";
	String obj_eMail_Edit = "//input[@name='email']";
	String obj_User_UserName_Edit = "//input[@name='username']";
	String obj_User_Password_Edit = "//input[@name='password']";
	String obj_User_Retype_Edit = "//input[@name='passwordCopy']";
	String obj_CreateUser_Btn = "//span[text()='Create User']";
	String obj_DeleteUser_Btn = "//button[contains(text(),'Delete User')]";
	String obj_Logout_Link = "//a[@id='logoutLink']";
	String obj_Header_Label = "//td[@id='headerContainer']";
	String obj_SaveChanges_Btn = "//span[text()='Save Changes']";
	String obj_StartExplore_Link = "//span[text()='Start exploring actiTIME']";
}
