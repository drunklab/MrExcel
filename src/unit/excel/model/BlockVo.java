package unit.excel.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)  	//指定是否对字段或属性进行系列化。 
@XmlType(name = "", propOrder = {})  
@XmlRootElement(name = "block")  
public class BlockVo {
	
	@XmlAttribute//将Java类的一个属性映射到与属性同名的一个XML属性。
    private String blockType;

    public String getBlockType(){return this.blockType;}
    
    public void setBlockType(String blockType){this.blockType=blockType;}
    
    protected List<CellVo> cell;
    
	public List<CellVo> getCells() {
        if (cell == null) {
        	cell = new ArrayList<CellVo>();
        }
        return this.cell;
    }
}
