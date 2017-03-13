package test.report;

import unit.excel.Reader;

public class TestTableRead {
	public static void main(String[] args){
	   TestTableWrite test = new TestTableWrite();
	   try {
	   	String filename=System.getProperty("user.dir")+"\\conf\\export\\TestTableWrite.xls";
	   	//∂¡»°EXCEL
	   	new Reader().print(filename);
	   } catch (Exception e) {
	   	e.printStackTrace();
	   }
	   test.dbHelper.CloseDatabase();
   }    
}

