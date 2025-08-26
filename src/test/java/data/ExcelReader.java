package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	static FileInputStream fis = null;

	public FileInputStream getFileInputStream()
	{
		String filePath = System.getProperty("user.dir") + "/src/test/java/testFiles/UserData.xlsx";
		File srcFile = new File(filePath);

		try {
			fis = new FileInputStream(srcFile);
		} catch (FileNotFoundException e)
		{
			System.out.println("Test Data file not found. terminating Process!!: Check file path of TestData");
			System.exit(0);
		}
		return fis;
	}

	public Object[][] getExcelData() throws IOException
	{
		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// get first sheet in the excel using index
		XSSFSheet sheet = wb.getSheetAt(0);
		
		int TotalNumberOfRows = (sheet.getLastRowNum() + 1);
		int TotalNumberOfCols = 4;
		
		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
		for(int i = 0; i < TotalNumberOfRows; i++)
		{
			for(int j = 0; j < TotalNumberOfCols; j++)
			{
				XSSFRow row = sheet.getRow(i);
				// "toString" convert the extraction data from excel file to string
				arrayExcelData[i][j] = row.getCell(j).toString();
			}
		}
		wb.close();
		return arrayExcelData;
	}
}
