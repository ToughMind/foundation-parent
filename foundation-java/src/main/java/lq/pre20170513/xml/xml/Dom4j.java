package lq.pre20170513.xml.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;


public class Dom4j implements XmlInterface {

	@Override
	public void createXml(String fileName) {
		Document document = DocumentHelper.createDocument();   
		Element employees = document.addElement("employees");   
		Element employee = employees.addElement("employee");   
		Element name = employee.addElement("name");   
		Element sex = employee.addElement("sex");   
		Element age = employee.addElement("age");   		
		name.setText("sunshine");   			 
		sex.setText("M");   		
		age.setText("20");   
		try {   
			Writer fileWriter=new FileWriter(fileName);   
			XMLWriter xmlWriter=new XMLWriter(fileWriter);   
			xmlWriter.write(document);   
			xmlWriter.close();   
		} catch (IOException e) {   		 
			System.out.println(e.getMessage());   
		}  		
	}

	@Override
	public void parserXml(String fileName) {
		File inputXml=new File(fileName);   
		SAXReader saxReader = new SAXReader();   
		try {   
			Document document = saxReader.read(inputXml);  
			Element employees = document.getRootElement();  	
			for(Iterator i = employees.elementIterator(); i.hasNext();){   
				Element employee = (Element) i.next();   
				for(Iterator j = employee.elementIterator(); j.hasNext();){   
					Element node=(Element) j.next();   
					System.out.println(node.getName()+":"+node.getText());   
				}    
			}   
		} 
		catch (DocumentException e) {   
			System.out.println(e.getMessage());   
		}   
	}  
	
	public static void main(String[] args) {
		Dom4j dom4j = new Dom4j();
		dom4j.createXml("dom4j.xml");
		dom4j.parserXml("dom4j.xml"); 
	}
}   
 

