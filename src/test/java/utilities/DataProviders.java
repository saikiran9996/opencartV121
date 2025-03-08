package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//dataprovider1

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {

		String path = ".\\testData\\Opencart_Login_Data.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcol = xlutil.getcellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcol];

		for (int i = 1; i < totalrows; i++)// 1 // read data from xl in the form of 2 dimensional array
		{
			for (int j = 0; j < totalcol; j++) // 0 i is rows j is col
			{
				logindata[i - 1][j] = xlutil.getcellData("Sheet1", i, j); // 1,0
			}
		}

		return logindata;// return 2 dimensional array

	}
	
	//dataprovider2
	
	//dataprovider3
	
	//dataprovider4
}
