package unit.excel.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)   //控制字段或属性的序列化。
										//FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标注）字段到XML。
										//XmlAccessType.PROPERTY和XmlAccessType.NONE。
@XmlType(name = "", propOrder = {}) //@XmlType，将Java类或枚举类型映射到XML模式类型
@XmlRootElement(name = "cell") //将Java类或枚举类型映射到XML元素。
public class CellVo {
	
	@XmlElement//将Java类的一个属性映射到与属性同名的一个XML元素。
	int x;
	
	@XmlElement
	int y;
	
	@XmlElement
	String key;//标签索引key
	
	@XmlElement
	String value;//标签值value
	
//	String fontName;
//	String fontSize;
//	Boolean fontWeight = false;
//	String fontAlign;
//	String fontVAlign;

	
	public CellVo(){}
	
	public void setX(int x){
		this.x=x;
	}
	
	public int getX(){
		return this.x;
	}
	
	
	public void setY(int y){
		this.y= y;
	}
	
	
	public int getY(){
		return this.y;
	}
	
	
	
	public void setKey(String key){
		this.key= key;
	}
	
	
	public String getKey(){
		return this.key;
	}
	
	
	public void setValue(String value){
   	   this.value = value;
	}
		
	public String getValue(){
		return this.value;
	}
}
