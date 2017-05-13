package lq.pre20170513.xml.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description sax解析xml文档 
 * 
 * @author liuquan
 * @date  2015年12月14日
 */
public class Sax implements XmlInterface{

	@Override
	public void createXml(String fileName) {
		System.out.println("<<"+fileName+">>"); 		
	}

	@Override
	public void parserXml(String fileName) {
		SAXParserFactory saxfac = SAXParserFactory.newInstance(); 
		try { 
			SAXParser saxparser = saxfac.newSAXParser(); 
			InputStream is = new FileInputStream(fileName); 
			saxparser.parse(is, new MySAXHandler()); 
		} 
		catch (ParserConfigurationException e) { 
			e.printStackTrace(); 
		} 
		catch (SAXException e) { 
			e.printStackTrace(); 
		} 
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 	
	public static void main(String[] args) {
		Sax sax = new Sax();
		sax.createXml("sax.xml");
		sax.parserXml("sax.xml");
	}
}

class MySAXHandler extends DefaultHandler { 
	boolean hasAttribute = false; 
	Attributes attributes = null; 
	
	public void startDocument() throws SAXException { 
		System.out.println("文档开始打印了"); 
	} 
	
	public void endDocument() throws SAXException { 
		System.out.println("文档打印结束了"); 
	} 
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { 
		if (qName.equals("employees")) { 
			return; 
		} 
		if (qName.equals("employee")) { 
			System.out.println(qName); 
		} 
		if (attributes.getLength() > 0) { 
			this.attributes = attributes; 
			this.hasAttribute = true; 
		} 
	} 
	
	public void endElement(String uri, String localName, String qName) throws SAXException { 
		if (hasAttribute && (attributes != null)) { 
			for (int i = 0; i < attributes.getLength(); i++) { 
				System.out.println(attributes.getQName(0) + attributes.getValue(0)); 
			} 
		}
	} 
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		StringBuffer buffer = new StringBuffer();  
        for(int i = start ; i < start+length ; i++){  
            switch(ch[i]){  
                case '\\':buffer.append("\\\\");break;  
                case '\r':buffer.append("\\r");break;  
                case '\n':buffer.append("\\n");break;  
                case '\t':buffer.append("\\t");break;  
                case '\"':buffer.append("\\\"");break;  
                default : buffer.append(ch[i]);   
            }  
        }  
        System.out.println(buffer);
	} 
	
}
