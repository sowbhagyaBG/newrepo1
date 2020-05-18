package driver;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import reports.ReportUtils;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static UserModuleMethods userMethods = null;
	public static TaskModuleMethods taskMethods = null;
	public static Datatable datatable = null;
	public static ReportUtils reports = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String strController = null;
	public static String strResLocation = null;
	public static String strScrnShotLocation = null;
	public static String strModuleName = null;
	
	@BeforeSuite
	public void loadClass() {
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			userMethods = new UserModuleMethods();
			taskMethods = new TaskModuleMethods();
			datatable = new Datatable();
			reports = new ReportUtils();
			strController = System.getProperty("user.dir")+"\\ExecutionController\\Controller.xlsx";
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'loadClass()' method. "+e.getMessage());
		}
	}
	
	
	@Test
	public void executeTest()
	{
		String execute = null;
		Class cls = null;
		Object obj = null;
		Method meth = null;
		try {
			int pRows = datatable.getRowCount(strController, "Projects");
			for(int i=0; i<pRows; i++)
			{
				execute = datatable.getCellData(strController, "Projects", "ExecuteProject", i+1);
				if(execute.equalsIgnoreCase("Yes"))
				{
					String projectName = datatable.getCellData(strController, "Projects", "ProjectName", i+1);
					int mRows = datatable.getRowCount(strController, projectName);
					for(int j=0; j<mRows; j++)
					{
						execute = datatable.getCellData(strController, projectName, "ExecuteModule", j+1);
						if(execute.equalsIgnoreCase("Yes")) {
							strModuleName = datatable.getCellData(strController, projectName, "ModuleName", j+1);
							int tcRows = datatable.getRowCount(strController, strModuleName);
							for(int k=0; k<tcRows; k++)
							{
								execute = datatable.getCellData(strController, strModuleName, "ExecuteTest", k+1);
								if(execute.equalsIgnoreCase("Yes")) {
									String scriptName = datatable.getCellData(strController, strModuleName, "ScriptName", k+1);
									String className = datatable.getCellData(strController, strModuleName, "ClassName", k+1);
									cls = Class.forName(className);
									obj = cls.newInstance();
									meth = obj.getClass().getMethod(scriptName);
									String status = String.valueOf(meth.invoke(obj));
									if(status.equals("true")) {
										datatable.setCellData(strController, strModuleName, "Status", k+1, "PASSED");
									}else {
										datatable.setCellData(strController, strModuleName, "Status", k+1, "FAILED");
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'executeTest()' method. "+e.getMessage());
		}
		finally {
			cls = null;
			obj = null;
			meth = null;
		}
	}
}
