package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	static String path;

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String xlsheet) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();

		return rowcount;

	}

	public  int getcellCount(String xlsheet, int rownum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();

		return cellcount;

	}

	public  String getcellData(String xlsheet, int rownum, int colnum) throws IOException {

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		String data;

		try {
			// data.toString();
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);// returns the formatted value of cell as a string regardless of the
													// cell
			// type.
		} catch (Exception e) {
			data = "";
		}

		wb.close();
		fi.close();

		return data;
	}

	public  void setcellData(String xlsheet, int rownum, int colnum, String data) throws IOException {

		// reading the data
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);

		// writing the data
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public  void fillGreenColor(String xlsheet, int rownum, int colnum) throws IOException {

		// reading the data
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);

		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public  void fillRedColor(String xlsheet, int rownum, int colnum) throws IOException {

		// reading the data
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		fo = new FileOutputStream(path);

		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

}
