package test.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import unit.excel.Reader;
import unit.excel.mode.controller.*;
import unit.excel.model.BlockVo;
import unit.excel.model.CellVo;
import unit.xml2class.XMLConverter;

import unit.db.DbHelper;

public  class TestImport{

	
	public static void main(String[] args) {
		
		//TestImport test = new TestImport();
		try {
			
			String filePath=System.getProperty("user.dir")+"\\conf\\import\\esbservice_data.xls";
			//new Reader().print(filePath);//打印EXCEL表格中的数据
			//System.out.println("Excel data End--------------");
			
			TestImport.importExcel();//导入非结构化与表格中的数据
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  static void importExcel(){
		String importFilePath=System.getProperty("user.dir")+"\\conf\\import\\esbservice_data.xls";
		String unstructConfig = System.getProperty("user.dir")+"\\conf\\excel.01.unstruct.xml";//非结构化配置
		String tableConfig = System.getProperty("user.dir")+"\\conf\\excel.01.table.xml";//table配置
		Sheet[] sheets = new Reader().getSheets(importFilePath);
		
		if(sheets.length==0){
	        System.out.println("sheet is null");
        	return;
        }

		XMLConverter xmlConverter = new XMLConverter();
		
		//JAXB（Java Architecture for XML Binding)http://baike.baidu.com/view/725509.htm?fr=aladdin
		BlockVo unstructBlockCell = (BlockVo)xmlConverter.XML2Class(unstructConfig, new BlockVo());//非结构化数据,xml 2 class
        List<CellVo> unstructCells = new CellContorller().getUnstructList(unstructBlockCell.getCells(),sheets[0]);
	    
        System.out.println("\n -unstructCells-----------");
        if(unstructCells != null){
	       for(CellVo cell:unstructCells)
	    	   System.out.println("\t"+cell.getKey()   +":"+cell.getValue() +"{"+cell.getX()+","+cell.getY()+"}");
	    }else{
	       System.out.println("unstructCells is null");
	    }
        
	    System.out.println("\n -dataHeader-----------");
		//表格数据
		BlockVo tableBlockCell = (BlockVo)xmlConverter.XML2Class(tableConfig,new BlockVo());
        List<CellVo> tableHDCells =tableBlockCell.getCells();//table列标题

        
        //打印table列名
        if(tableHDCells != null){
 	       for(CellVo cell:tableHDCells)
 	    	   System.out.println("\t"+cell.getKey()   +":"+cell.getValue() +"{"+cell.getX()+","+cell.getY()+"}");
 	    }else{
 	       System.out.println("tableCells is null");
 	    }
        System.out.println("\n -tabledata----------");

        //打印table列数据
        @SuppressWarnings("unchecked")
		ArrayList<String[]> tableData = new CellContorller().getTableData(tableBlockCell.getCells(),sheets[0]);//table列数据
        
		Iterator<String[]> iterator = tableData.iterator();
        
        while (iterator.hasNext())
        {
        	String []dataRow = (String[]) iterator.next();
        	
        	InsertSeviceData(dataRow,tableHDCells);
//        	for(String str:dataRow){
//        		System.out.print("\t"+str+",");
//        	}
        	System.out.println();
        }
	}

    static void InsertSeviceData(String[] CellData,List<CellVo> Cells){
	
		DbHelper dbHelper = new DbHelper();
		String sqlStr ="";
		String colName="";
		String colValue="";
		
		for(CellVo cell:Cells){
		   if(colName==""){
			   colName=cell.getKey();
		   }
		   else{
			   colName=colName+","+cell.getKey();
		   }
		}
		
		for(String col:CellData){
			   if(colValue==""){
				   colValue="'"+col+"'";
			   }
			   else{
				   colValue=colValue+",'"+col+"'";
			   }
		}
		
		
		
//		
//		for(int i=0; i<CellData.length; i++){
//		   CellData;
//		}
		
		   sqlStr="insert into esb_service("+colName+") values("+colValue+")";
		   
		   System.out.println(sqlStr);
		//dbHelper.executeInsert(sqlStr);
		
				
	}
}
