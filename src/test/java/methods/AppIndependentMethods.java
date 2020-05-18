package methods;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	/***********************************************
	 * Method Name	: getDateTime()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public String getDateTime(String strFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try {
			dt = new Date();
			sdf = new SimpleDateFormat(strFormat);
			return sdf.format(dt);
		}catch(Exception e) {
			System.out.println("Exception in 'getDateTime()' method. "+e.getMessage());
			return null;
		}
		finally {
			dt = null;
			sdf = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: captureScreenShot()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public String captureScreenShot(WebDriver oDriver)
	{
		File objFile = null;
		String strDestination = null;
		try {
			strDestination = strScrnShotLocation + "\\screenShot_"
					+appInd.getDateTime("ddMMYY_hhmmss")+".png";
			objFile = ((TakesScreenshot)oDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(objFile, new File(strDestination));
			return strDestination;
		}catch(Exception e)
		{
			System.out.println("Exception in 'captureScreenShot()' method. "+e.getMessage());
			return null;
		}
		finally {
			objFile = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: clickObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean clickObject(WebDriver oDriver, By objBy)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' was clicked successful", test, false);
				oEle.click();
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: clickObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean clickObject(WebDriver oDriver, String objLocator)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' was clicked successful", test, false);
				oEle.click();
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clickObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: setObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean setObject(WebDriver oDriver, By objBy, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful", test, false);
				oEle.sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: setObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean setObject(WebDriver oDriver, String objLocator, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The data '"+strData+"' was entered in the element '"+objLocator+"' successful", test, false);
				oEle.sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'setObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: clearAndSetObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean clearAndSetObject(WebDriver oDriver, By objBy, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The data '"+strData+"' was entered in the element '"+String.valueOf(objBy)+"' successful", test, false);
				oEle.clear();
				Thread.sleep(1000);
				oEle.sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clearAndSetObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: clearAndSetObject()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean clearAndSetObject(WebDriver oDriver, String objLocator, String strData)
	{
		WebElement oEle = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed()) {
				reports.writeResult(oDriver, "Pass", "The data '"+strData+"' was entered in the element '"+objLocator+"' successful", test, false);
				oEle.clear();
				Thread.sleep(1000);
				oEle.sendKeys(strData);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'clearAndSetObject()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally {
			oEle = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: compareValues()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean compareValues(WebDriver oDriver, String strActual, String strExpected)
	{
		try {
			if(strActual.equals(strExpected)) {
				reports.writeResult(oDriver, "Pass", "The actual '"+strActual+"' & expected '"+strExpected+"' are matching", test, false);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The mis-match in both actual '"+strActual+"' & expected '"+strExpected+"' values", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'compareValues()' method. "+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: verifyText()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean verifyText(WebDriver oDriver, By objBy, String objType, String expectedVal)
	{
		WebElement oEle = null;
		String actualVal = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(objBy);
			if(oEle.isDisplayed())
			{
				switch(objType.toLowerCase()) {
					case "text":
						actualVal = oEle.getText();
						break;
					case "value":
						actualVal = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						actualVal = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeResult(oDriver, "Fail", "Invalid object Type '"+objType+"' was specified", test, false);
						return false;
				}
				
				if(appInd.compareValues(oDriver, actualVal, expectedVal)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: verifyText()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean verifyText(WebDriver oDriver, String objLocator, String objType, String expectedVal)
	{
		WebElement oEle = null;
		String actualVal = null;
		Select oSel = null;
		try {
			oEle = oDriver.findElement(By.xpath(objLocator));
			if(oEle.isDisplayed())
			{
				switch(objType.toLowerCase()) {
					case "text":
						actualVal = oEle.getText();
						break;
					case "value":
						actualVal = oEle.getAttribute("value");
						break;
					case "list":
						oSel = new Select(oEle);
						actualVal = oSel.getFirstSelectedOption().getText();
						break;
					default:
						reports.writeResult(oDriver, "Fail", "Invalid object Type '"+objType+"' was specified", test, false);
						return false;
				}
				
				if(appInd.compareValues(oDriver, actualVal, expectedVal)) {
					return true;
				}else {
					return false;
				}
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'verifyText()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEle = null;
			oSel = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: isElementPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isElementPresent(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' exist successful", test, false);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: isElementPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isElementPresent(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' exist successful", test, false);
				return true;
			}else {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' doesnot exist", test, true);
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: isElementNotPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isElementNotPresent(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Fail", "The element '"+String.valueOf(objBy)+"' still exist", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "The element '"+String.valueOf(objBy)+"' doesnot exist successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementNotPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: isElementNotPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isElementNotPresent(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				reports.writeResult(oDriver, "Fail", "The element '"+objLocator+"' still exist", test, true);
				return false;
			}else {
				reports.writeResult(oDriver, "Pass", "The element '"+objLocator+"' doesnot exist successful", test, false);
				return true;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isElementNotPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: isOptionalElementPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isOptionalElementPresent(WebDriver oDriver, By objBy)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(objBy);
			if(oEles.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isOptionalElementPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: isOptionalElementPresent()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean isOptionalElementPresent(WebDriver oDriver, String objLocator)
	{
		List<WebElement> oEles = null;
		try {
			oEles = oDriver.findElements(By.xpath(objLocator));
			if(oEles.size()>0) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'isOptionalElementPresent()' method. "+e.getMessage(), test, true);
			return false;
		}
		finally
		{
			oEles = null;
		}
	}
	
	
	/***********************************************
	 * Method Name	: closeBrowser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean closeBrowser(WebDriver oDriver)
	{
		try {
			oDriver.close();
			oDriver = null;
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'closeBrowser()' method. "+e.getMessage(), test, true);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: launchBrowser()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public WebDriver launchBrowser(String browser)
	{
		WebDriver oDriver = null;
		try {
			switch(browser.toLowerCase())
			{
				case "ch":
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Library\\drivers\\chromedriver.exe");			
					oDriver = new ChromeDriver();
					break;
				case "ff":
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Library\\drivers\\geckodriver.exe");			
					oDriver = new FirefoxDriver();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Library\\drivers\\IEDriverServer.exe");			
					oDriver = new InternetExplorerDriver();
					break;
			}
			
			if(oDriver!=null) {
				reports.writeResult(oDriver, "Pass", "The '"+browser+"' browser has launched successful", test, false);
				oDriver.manage().window().maximize();
				return oDriver;
			}else{
				reports.writeResult(oDriver, "Fail", "Failed to launch the '"+browser+"' browser", test, false);
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception while executing 'launchBrowser()' method. "
					+e.getMessage(), test, true);
			return null;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: getPropValue()
	 * purpose	  	: 
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public String getPropValue(String strKey)
	{
		FileInputStream fin = null;
		Properties prop = null;
		try {
			fin = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\Config.properties");
			prop = new Properties();
			prop.load(fin);
			return prop.getProperty(strKey);
		}catch(Exception e) {
			reports.writeResult(null, "Exception", "Exception while executing 'getPropValue()' method. "
					+e.getMessage(), test, true);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				prop = null;
			}catch(Exception e) {
				reports.writeResult(null, "Exception", "Exception while executing 'getPropValue()' method. "
						+e.getMessage(), test, true);
			}
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: waitFor()
	 * purpose	  	: Webdriver wait for the required wait scenario
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean waitFor(WebDriver oDriver, By objBy, String strWaitFor, String expected, int timeOut)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oDriver, timeOut);
			switch(strWaitFor.toLowerCase())
			{
				case "element":
					oWait.until(ExpectedConditions.presenceOfElementLocated(objBy));
					break;
				case "visible":
					oWait.until(ExpectedConditions.visibilityOfElementLocated(objBy));
					break;
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElementLocated(objBy, expected));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(objBy, expected));
					break;
				case "invisible":
					oWait.until(ExpectedConditions.invisibilityOfElementLocated(objBy));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(objBy));
					break;
				default:
					reports.writeResult(oDriver, "Fail", "Invalid wait condition '"+strWaitFor+"' specified", test, false);
			}
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception in 'waitFor()' method.", test, true);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: waitFor()
	 * purpose	  	: Webdriver wait for the required wait scenario
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public boolean waitFor(WebDriver oDriver, String objLocator, String strWaitFor, String expected, int timeOut)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oDriver, timeOut);
			switch(strWaitFor.toLowerCase())
			{
				case "element":
					oWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objLocator)));
					break;
				case "visible":
					oWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objLocator)));
					break;
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(objLocator), expected));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(objLocator), expected));
					break;
				case "invisible":
					oWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objLocator)));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(By.xpath(objLocator)));
					break;
				case "alert":
					oWait.until(ExpectedConditions.alertIsPresent());
					break;
				default:
					reports.writeResult(oDriver, "Fail", "Invalid wait condition '"+strWaitFor+"' specified", test, false);
			}
			return true;
		}catch(Exception e)
		{
			reports.writeResult(oDriver, "Exception", "Exception in 'waitFor()' method.", test, true);
			return false;
		}
	}
}
