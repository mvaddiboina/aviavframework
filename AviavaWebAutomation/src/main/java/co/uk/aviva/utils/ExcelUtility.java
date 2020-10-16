package co.uk.aviva.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtility {

	public static Map<String, String> getTestData(String fileName) throws BiffException, IOException {
		Map<String, String> testData = new HashMap<>();
		String basepath = System.getProperty("user.dir");
		Workbook excel = Workbook.getWorkbook(new File(basepath + "\\TestData\\" + fileName+".xls"));
		Sheet sheet1 = excel.getSheet("testData");
		 int columns = sheet1.getColumns();

		for (int col = 0; col < columns; col++) {
			testData.put(sheet1.getCell(col, 0).getContents(), sheet1.getCell(col, 1).getContents());
		}

		return testData;
	}

}
