package unit.xml2class;

import unit.excel.model.CellVo;
import java.io.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XMLConverter {
 
 
   
   public static Object XML2Class(String XMLFilePath,Object T){
	   try {
			   File file = new File(XMLFilePath);
			   
			   JAXBContext jaxbContext = JAXBContext.newInstance(T.getClass());//JAXBContext类，是应用的入口，用于管理XML/Java绑定信息。其操作包括解组、编组和验证
			   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();//Marshaller接口，将Java对象序列化为XML数据。
			   Object objInstance = jaxbUnmarshaller.unmarshal(file);//Unmarshaller接口，将XML数据反序列化为Java对象树。
			   return objInstance;
		  } catch (JAXBException e) {
			  e.printStackTrace();
			  return null;
		  }
   }   
   
   
   public static void Class2XML(String OutXMLPath,Object T){

		  try {
			  File file = new File(OutXMLPath);
			  JAXBContext jaxbContext = JAXBContext.newInstance(CellVo.class);
			  Marshaller jaxbMarshaller = jaxbContext.createMarshaller();//方法允许将模式中声明的任何全局 XML 元素解组为实例文档的根

			  // output pretty printed
			  jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			  jaxbMarshaller.marshal(T, file);//将Java对象转换成XML
			  //jaxbMarshaller.marshal(T, System.out);
	 
		  } catch (JAXBException e) {
			 e.printStackTrace();
		  }
	 
   }
   
}
