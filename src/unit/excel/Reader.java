package unit.excel;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;


public class Reader {

	public Reader(){
		
	}
	
	public Sheet[] getSheets(String FilePath)
	{
		
		try {
			File excelFile = new File(FilePath);
			Workbook workbook= Workbook.getWorkbook(excelFile);
			Sheet[] sheets = workbook.getSheets();
			return sheets;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void print(String ImportExcelFPath)
	{
		try{
			System.out.println(ImportExcelFPath);
			File excelFile = new File(ImportExcelFPath);
			Workbook workbook = Workbook.getWorkbook(excelFile);
			Sheet []sheets = workbook.getSheets();

			Sheet sheet = sheets[0];
			System.out.println("className:"+this.getClass().getName()
					+"{sheets count:"
					+sheets.length+",sheet name:"+sheet.getName()+"}");
			for (int i=0; i<sheet.getRows(); i++)
			{
				//read columns
				for (int j=0; j<sheet.getColumns(); j++)
				{
					Cell cell = sheet.getCell(j, i);
					if(cell.getContents()!=""){
					   System.out.print("\t"+cell.getContents().trim());// 打印出该列的值
					}
				}
				System.out.println();
			}
			workbook.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
