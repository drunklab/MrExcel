package test.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class TestSimpleWrite {
		
	//简易的E
	public static void main(String[] args) {
		try {
			TestSimpleWrite.createExcelTest(System.getProperty("user.dir")+"\\conf\\export\\createExcelTest.xls","demo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	 public static void createExcelTest(String filename,String SheetName) throws Exception{
		WritableWorkbook book = Workbook.createWorkbook(new File(filename));
		WritableSheet sheet  = book.createSheet(SheetName, 0);
		
		//header and datarow style
		WritableFont fontHeader = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false);
		WritableFont fontData = new WritableFont(WritableFont.createFont("宋体"), 9, WritableFont.NO_BOLD, false);
		
		WritableCellFormat cellHeaderFormat = new WritableCellFormat(fontHeader);
		WritableCellFormat cellDataFormat = new WritableCellFormat(fontData);
		
		cellHeaderFormat.setAlignment(Alignment.CENTRE);
		cellHeaderFormat.setVerticalAlignment(VerticalAlignment.TOP);
		
		cellDataFormat.setAlignment(Alignment.RIGHT);
		cellDataFormat.setVerticalAlignment(VerticalAlignment.TOP);

		cellDataFormat.setWrap(false);// 自动换行为true
		cellDataFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 把垂直对齐方式指定为居中
	   
		//sheet Style  创建行数，设置行的宽度
	    sheet.setColumnView(0, 10);
	    sheet.setColumnView(1, 30);
	    sheet.setColumnView(2, 20);
		try
		{
			   //设置行值
			   sheet.addCell(new Label(0, 0, "Card Id",cellHeaderFormat));
			   sheet.addCell(new Label(1, 0, "NAME",cellHeaderFormat));
			   sheet.addCell(new Label(2, 0, "Boy/Girl/other",cellHeaderFormat));
			   sheet.addCell(new Label(3, 0, "Height",cellHeaderFormat));
			   sheet.addCell(new Label(4, 0, "Weight",cellHeaderFormat));
			   
			   sheet.addCell(new Label(0, 1, "1010000000000001",cellDataFormat));
			   sheet.addCell(new Label(1, 1, "Jim",cellDataFormat));
			   sheet.addCell(new Label(2, 1, "boy",cellDataFormat));
			   sheet.addCell(new Label(3, 1, "170cm",cellDataFormat));
			   sheet.addCell(new Label(4, 1, "200kg",cellDataFormat));

			   book.write();
			   book.close();
			   
		 }
		 catch (Exception e)
		 {
		    e.printStackTrace();
		 }

		 System.out.println("Test Simple Write end :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	 }

}





