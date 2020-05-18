package methods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import driver.DriverScript;

public class Datatable extends DriverScript{
	/***********************************************
	 * Method Name	: getExcelTestData()
	 * purpose	  	: It will read the excel data based on PK
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public Map<String, String> getExcelTestData(String strSheet, String strLogicalName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		Map<String, String> data = null;
		int rowNum = 0;
		String sKey = null;
		String sValue = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			data = new HashMap<>();
			fin = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+strModuleName+".xlsx");
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(strSheet);
			
			//Validate sheet exist
			if(sh==null) {
				reports.writeResult(null, "Fail", "The sheet '"+strSheet+"' doesnot exist", test, false);
				return null;
			}
			
			//Verify the given logical name present
			int rows = sh.getPhysicalNumberOfRows();
			for(int r=1; r<rows; r++)
			{
				row1 = sh.getRow(r);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().equalsIgnoreCase(strLogicalName)) {
					rowNum = r;
					break;
				}
			}
			
			if(rowNum>0) {
				row1 = sh.getRow(0);
				row2 = sh.getRow(rowNum);
				for(int c=0; c<row1.getPhysicalNumberOfCells(); c++)
				{
					cell1 = row1.getCell(c);
					sKey = cell1.getStringCellValue();
					cell2 = row2.getCell(c);
					
					//format the excel cell data
					if(cell2==null|cell2.getCellType()==CellType.BLANK) {
						sValue = "";
					}
					else if(cell2.getCellType()==CellType.BOOLEAN) {
						sValue = String.valueOf(cell2.getBooleanCellValue());
					}
					else if(cell2.getCellType()==CellType.STRING) {
						sValue = cell2.getStringCellValue();
					}
					else if(cell2.getCellType()==CellType.NUMERIC) {
						if(HSSFDateUtil.isCellDateFormatted(cell2)) {
							double dt = cell2.getNumericCellValue();
							Calendar cal = Calendar.getInstance();
							cal.setTime(HSSFDateUtil.getJavaDate(dt));
							
							//Prefix zero if day is <10
							if(cal.get(Calendar.DAY_OF_MONTH)<10) {
								sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
							}else {
								sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							//Prefix zero if month is <10
							if((cal.get(Calendar.MONTH)+1)<10) {
								sMonth = "0" + (cal.get(Calendar.MONTH)+1);
							}else {
								sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							sYear = String.valueOf(cal.get(Calendar.YEAR));
							sValue = sDay +"/"+sMonth+"/"+sYear;
						}else {
							sValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					data.put(sKey, sValue);
				}
				return data;
			}else {
				reports.writeResult(null, "Fail", "The logical name '"+strLogicalName+"' doesnot exist", test, false);
				return null;
			}
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception while executing 'getExcelTestData()' method. "
					+e.getMessage(), test, false);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception while executing 'getExcelTestData()' method. "
						+e.getMessage(), test, false);
				return null;
			}
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: getRowCount()
	 * purpose	  	: It will find the row numbers from the excel
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public int getRowCount(String filePath, String sheetName)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		try {
			fin = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "The sheet '"+sheetName+"' doesnot exist", test, false);
				return -1;
			}

			return sh.getPhysicalNumberOfRows()-1;
		}catch(Exception e)
		{
			reports.writeResult(null, "Fail", "Exception while executing 'getRowCount()' method. "+e.getMessage(), test, false);
			return -1;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Fail", "Exception while executing 'getRowCount()' method. "+e.getMessage(), test, false);
				return -1;
			}
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: getCellData()
	 * purpose	  	: It is to read the particular cell value
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public String getCellData(String filepath, String sheetName, String colName, int rowNum)
	{
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		String strData = null;
		int colNum = 0;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		try {
			fin = new FileInputStream(filepath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "The sheet '"+sheetName+"' doesnot exist", test, false);
				return null;
			}
			
			//Find the column number using column name
			row = sh.getRow(0);
			for(int c=0; c<row.getPhysicalNumberOfCells(); c++)
			{
				cell = row.getCell(c);
				if(cell.getStringCellValue().equals(colName)) {
					colNum = c;
					break;
				}
			}
			
			//Read the cell value
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			
			//format the cell data
			if(cell==null|cell.getCellType()==CellType.BLANK) {
				strData = "";
			}
			else if(cell.getCellType()==CellType.BOOLEAN) {
				strData = String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType()==CellType.STRING) {
				strData = cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				if(HSSFDateUtil.isCellDateFormatted(cell)) {
					double dt = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(dt));
					
					//Prefix zero if day is <10
					if(cal.get(Calendar.DAY_OF_MONTH)<10) {
						sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
					}else {
						sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					//Prefix zero if month is <10
					if((cal.get(Calendar.MONTH)+1)<10) {
						sMonth = "0" + (cal.get(Calendar.MONTH)+1);
					}else {
						sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
					}
					
					sYear = String.valueOf(cal.get(Calendar.YEAR));
					strData = sDay +"/"+sMonth+"/"+sYear;
				}
			}else {
				strData = String.valueOf(cell.getNumericCellValue());
			}
			return strData;
		}catch(Exception e)
		{
			reports.writeResult(null, "Fail", "Exception while executing 'getCellData()' method. "+e.getMessage(), test, false);
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Fail", "Exception while executing 'getCellData()' method. "+e.getMessage(), test, false);
				return null;
			}
		}
	}
	
	
	
	/***********************************************
	 * Method Name	: setCellData()
	 * purpose	  	: It is to write to the particular cell
	 * Author	  	: TestUser1
	 * Reviewer	  	: tester2
	 * Date created	: 
	 * Date Modified: 
	 * *********************************************
	 */
	public void setCellData(String filePath, String sheetName, String columnName, int rowNum, String strData)
	{
		FileInputStream fin = null;
		FileOutputStream fout = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int colNum = 0;
		try {
			fin = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeResult(null, "Fail", "The sheet '"+sheetName+"' doesnot exist", test, false);
				return;
			}
			
			//Find the column number using column name
			row = sh.getRow(0);
			for(int c=0; c<row.getPhysicalNumberOfCells(); c++)
			{
				cell = row.getCell(c);
				if(cell.getStringCellValue().equals(columnName)) {
					colNum = c;
					break;
				}
			}
			
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			if(row.getCell(colNum)==null) {
				cell = row.createCell(colNum);
			}
			
			cell.setCellValue(strData);
			
			fout = new FileOutputStream(filePath);
			wb.write(fout);
		}catch(Exception e)
		{
			reports.writeResult(null, "Exception", "Exception in 'setCellData()' method.", test, false);
		}
		finally
		{
			try {
				fout.flush();
				fout.close();
				fout = null;
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
			}catch(Exception e)
			{
				reports.writeResult(null, "Exception", "Exception in 'setCellData()' method.", test, false);
			}
		}
	}
}
