package test.report;

import unit.excel.model.*;
import unit.xml2class.XMLConverter;

public class TestXML2ClassConvert {

	public static void main(String[] args) {
		
		BlockVo blockCell = new BlockVo();
		XMLConverter xmlConverter = new XMLConverter();

		//非结构化数据
		blockCell = (BlockVo)xmlConverter.XML2Class(System.getProperty("user.dir")+"\\conf\\excel.01.unstruct.xml",blockCell);
        System.out.println("BlockType:"+blockCell.getBlockType()
        		+"cells size:"+blockCell.getCells().size());
        //表格
        blockCell = (BlockVo)xmlConverter.XML2Class(System.getProperty("user.dir")+"\\conf\\excel.01.table.xml",blockCell);
        System.out.println("BlockType:"+blockCell.getBlockType()
        		+"cells size:"+blockCell.getCells().size());
        
	}

}
