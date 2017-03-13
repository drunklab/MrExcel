package unit.excel.mode.controller;

import unit.excel.model.CellVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jxl.Sheet;


public class CellContorller {   
   //获取非结构化的数据
   public  List<CellVo>  getUnstructList(List<CellVo> Cells,Sheet BookSheet){
	   if(Cells == null || Cells.size()==0)
		   return null;
	   String cellData;
	   for(int i=0 ;i< Cells.size(); i++){
		   //con.excel.*.xml中 cell x,y坐标从1起始,实际坐标-1
		   int x = Cells.get(i).getX()-1;
		   int y = Cells.get(i).getY()-1;
		   cellData = BookSheet.getCell(y,x).getContents();
		   if(cellData == null)
			  Cells.get(i).setValue("");
		   else
		      Cells.get(i).setValue(cellData); 
	   }
	   return Cells;
   }
   
   //获取非结化的数据，map类型
   public Map<String, CellVo> getUnstructMap(List<CellVo> Cells,Sheet BookSheet){
	   Map<String, CellVo> unstructMap = new HashMap<String, CellVo>();
	   
	   if(Cells == null || Cells.size()==0)
		   return null;
	   String cellData;
	   for(int i=0 ;i< Cells.size(); i++){
		   //con.excel.*.xml中 cell x,y坐标从1起始,实际坐标-1
		   int x = Cells.get(i).getX()-1;
		   int y = Cells.get(i).getY()-1;
		   cellData = BookSheet.getCell(y,x).getContents();
		   Cells.get(i).setValue(cellData);
		   unstructMap.put(Cells.get(i).getKey(),Cells.get(i));
	   }
	   return unstructMap;
   }
   

//获取结构化的数据
@SuppressWarnings({ "rawtypes", "unchecked" })
public ArrayList getTableData(List<CellVo> Cells,Sheet BookSheet){
	   
	   if(Cells == null || Cells.size()==0)
	   {
		   System.out.println("cells is null");
		   return null;
	   }
		   
	   ArrayList dataList = new ArrayList();//表格数据集

	   CellVo cellVo = new CellVo();
	
	   //获取TABLE 的起始坐标XY(通过0,0坐标定位)
	   cellVo = Cells.get(0);
	   int tagblePointX = cellVo.getX()-1;
	   int tagblePointY = cellVo.getY()-1; 
       	   
	   try{
		    System.out.println("Cells.size()"+Cells.size());
		    String cellTxt;
		    
			for (int rowi=tagblePointX; rowi<BookSheet.getRows(); rowi++)
			{
				String[] dataRow = new String[Cells.size()] ;
				int x=0;
				for (int colj=tagblePointY; colj<BookSheet.getColumns(); colj++)
				{
					cellTxt = BookSheet.getCell(colj, rowi).getContents();
					dataRow[x] = cellTxt;
					x++;
				}
				dataList.add(dataRow);
			}
			
			return 	dataList;	
		}catch(Exception ex){
		   System.out.println(this.getClass().getName()+" Exception\n"+ex.getStackTrace());
		   return null;
		}
   }
}
