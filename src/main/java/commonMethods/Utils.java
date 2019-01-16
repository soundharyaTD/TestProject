package commonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

public class Utils {
	public static String testDataFile=getDataFromTestConfig("Test Data Path");
	
	public static String getDataFromTestData(String autoTestCaseNameVal, String label) {
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/data/TestData.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");
			int rowNum = ws.getLastRowNum() + 1;

			Iterator rowIterator = ws.rowIterator();
			int numberOfCells = 0;
			if (rowIterator.hasNext()) {
				Row headerRow = (Row) rowIterator.next();
				// get the number of cells in the header row
				numberOfCells = headerRow.getLastCellNum();
			}
			for (int index = 0; index < rowNum; index++) {

				XSSFRow row = ws.getRow(index);
				XSSFCell cell = row.getCell(0);
				String cellVal = cellToString(cell);
				//System.out.println(cellVal);
				if (cellVal.equals(autoTestCaseNameVal)) {
					//System.out.println("Entered"+cellVal+"Row Index:"+index);
					for (int cellIndex = 1; cellIndex < numberOfCells; cellIndex++) {
						XSSFCell findLable = row.getCell(cellIndex);
						String labelString = cellToString(findLable);
						if (labelString.equals(label)) {
							//System.out.println("Cell Index: "+cellIndex);
							XSSFRow nextRow = ws.getRow(index + 1);
							XSSFCell adjacentRowCell = nextRow
									.getCell(cellIndex);
							String adjacentRowCellVal = cellToString(adjacentRowCell);
							//System.out.println("Cell Val: "+adjacentRowCellVal);
							requiredCellVal = adjacentRowCellVal;
							break;
						}
					}

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredCellVal.trim();
	}
	
	public static ArrayList<ArrayList<String>> getDataFromTestData(String autoTestCaseNameVal) {
		ArrayList<ArrayList<String>> twodlist = new ArrayList<ArrayList<String>>();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/data/TestData.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");
			int rowNum = ws.getLastRowNum() + 1;
			Iterator rowIterator = ws.rowIterator();
			int numberOfCells = 0;
			if (rowIterator.hasNext()) {
				Row headerRow = (Row) rowIterator.next();
				// get the number of cells in the header row
				numberOfCells = headerRow.getLastCellNum();
			}
			
			for (int index = 0; index < rowNum; index++) {

				XSSFRow row = ws.getRow(index);
				XSSFCell cell = row.getCell(0);
				String cellVal = cellToString(cell);
				//System.out.println(cellVal);
				
				if (cellVal.equals(autoTestCaseNameVal)) {
					int teindex =  index+1;
					//System.out.println("Entered"+cellVal+"Row Index:"+index);
					
					for (int cellIndex = 1; cellIndex < numberOfCells; cellIndex++) {
						
						//System.out.println("Cell Index: "+cellIndex);
						XSSFRow nextRow = ws.getRow(teindex);
						//System.out.println("Blank cell type value "+nextRow.getCell(0).CELL_TYPE_BLANK);
						if(cellToString(nextRow.getCell(0))==""){
							ArrayList<String> iList = new ArrayList<String>();
							for(int inCellIndex=1; inCellIndex<nextRow.getLastCellNum(); inCellIndex++){
								XSSFCell unamecell = nextRow
										.getCell(inCellIndex);
								String uname = cellToString(unamecell);
								iList.add(uname);
								//System.out.println("Cell Val uname: "+uname+"\t");
							}
							//System.out.println("after looop"+iList.toString());
							teindex++;
							twodlist.add(iList);
						}else{
							break;
						}
							
					}
					
					break;
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("two d list size" + twodlist.size());
		System.out.println("two d list first index" + twodlist.toString());
//		System.out.println("two d list second index" + twodlist.toString());
		return twodlist;
	}
	
	
	public static String getDataFromTestConfig(String component) {
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/data/TestConfiguration.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rowNum = ws.getLastRowNum() + 1;

			java.util.Iterator<Row> rowIterator = ws.rowIterator();
            int numberOfCells = 0;
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getLastCellNum();
            }
            
			for (int index = 0; index < rowNum; index++) 
			{
				XSSFRow row = ws.getRow(index);
				
				for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
				{
					XSSFCell cell = row.getCell(cellIndex);
					String cellVal = cellToString(cell);
					if (cellVal.equals(component)) 
					{
						XSSFCell adjacentCell = row.getCell(cellIndex+1);
						String adjacentCellVal = cellToString(adjacentCell);
						requiredCellVal = adjacentCellVal;
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return requiredCellVal;
	}
	
	public static String cellToString(XSSFCell cell) {
			int type;
			String result;
			type = cell.getCellType();

			switch (type) {

			case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
				result = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
				result = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING: // String Value in Excel
				result = "" + cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				result = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN: // boolean value
				result = "" + cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				result = "Error";
			default:
				throw new RuntimeException(
						"There is no support for this type of cell");
			}

			return result.toString();
	}
	
	@Test
	public static String multipleDataExecution(String Path,int ColumnNum,int RowNum)
	{
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(Path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rowNum = ws.getLastRowNum() + 1;

			java.util.Iterator<Row> rowIterator = ws.rowIterator();
            int numberOfCells = 0;
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getLastCellNum();
            }
			for (int index = 0; index < rowNum; index++) 
			{
				XSSFRow row = ws.getRow(index);	
				for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
				{
					XSSFCell cell = row.getCell(cellIndex);
					try{
					String cellVal = cellToString(cell);
					if ((cellIndex==ColumnNum)&&(index==RowNum))
					{
						XSSFCell adjacentCell = row.getCell(cellIndex);
						String adjacentCellVal = cellToString(adjacentCell);
						
						requiredCellVal = adjacentCellVal;
  					  break;
					}
					}
					catch(NullPointerException w)
					{
						requiredCellVal="";
					}				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return requiredCellVal;
	}
	public static int getNoOfRows(String Path)
	{
		int rowNum =0;
		try {
			FileInputStream fis = new FileInputStream(Path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");

			rowNum = ws.getLastRowNum() + 1;

			java.util.Iterator<Row> rowIterator = ws.rowIterator();
            int numberOfCells = 0;
            /*if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getLastCellNum();
            }
			for (int index = 0; index < rowNum; index++) 
			{
				XSSFRow row = ws.getRow(index);	
				for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
				{
					XSSFCell cell = row.getCell(cellIndex);
					
						XSSFCell adjacentCell = row.getCell(cellIndex);
						String adjacentCellVal = cellToString(adjacentCell);
						requiredCellVal = adjacentCellVal;
						System.out.println(requiredCellVal+"requiredCellVal");
					
				}

			}*/

		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return rowNum;
	}
	
}