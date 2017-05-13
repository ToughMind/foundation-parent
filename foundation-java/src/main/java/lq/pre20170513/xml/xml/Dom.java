package lq.pre20170513.xml.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description DOM生成和解析XML文档
 * 
 * @author liuquan
 * @date  2015年12月14日
 */
public class Dom implements XmlInterface{
	private Document document; 
	
	public void init(){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
		} catch (ParserConfigurationException e) { 
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void createXml(String fileName) {
		Element root = this.document.createElement("employees"); 
		Element employee = this.document.createElement("employee");
		Element name = this.document.createElement("name");
		Element sex = this.document.createElement("sex");
		Element age = this.document.createElement("age");
		this.document.appendChild(root);
		name.appendChild(this.document.createTextNode("sunshine"));
		sex.appendChild(this.document.createTextNode("M"));
		age.appendChild(this.document.createTextNode("20"));
		employee.appendChild(name);
		employee.appendChild(sex);
		employee.appendChild(age);
		root.appendChild(employee);
		
		TransformerFactory trf = TransformerFactory.newInstance();		 
		try {
			Transformer tr = trf.newTransformer();
			DOMSource source = new DOMSource(document);
			tr.setOutputProperty(OutputKeys.ENCODING, "utf8");
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
			StreamResult result = new StreamResult(pw);
			tr.transform(source, result);
			System.out.println("生成xml文件成功！");
		} 
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) { 
			e.printStackTrace();
		} 
		catch (TransformerException e) { 
			e.printStackTrace();
		}	 
	}

	@Override
	public void parserXml(String fileName) {
		try { 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document document = db.parse(fileName); 
			//找到employees结点
			Element employees = document.getDocumentElement(); 
			//找到employee集
			NodeList employeeList = employees.getChildNodes(); 
			for (int i = 0; i < employeeList.getLength(); i++) { 
					Node node = employeeList.item(i); 
					//细分到属性 比如name
					NodeList employeeMeta = node.getChildNodes();  
					for (int j = 0; j < employeeMeta.getLength(); j++) {   
						if(! "#text".equals(employeeMeta.item(j).getNodeName())){
							System.out.println(employeeMeta.item(j).getNodeName() + ":" + employeeMeta.item(j).getTextContent());
						}
					} 
			} 
			System.out.println("解析完毕"); 
		} 
		catch (FileNotFoundException e) { 
			System.out.println(e.getMessage()); 
		}
		catch (ParserConfigurationException e) { 
			System.out.println(e.getMessage()); 
		} 
		catch (SAXException e) { 
			System.out.println(e.getMessage()); 
		} 
		catch (IOException e) { 
			System.out.println(e.getMessage()); 
		} 
			
	}

	public static void main(String[] args) {
		Dom dom =  new Dom();
		dom.init();
		dom.createXml("dom.xml"); 	
		dom.parserXml("dom.xml");
		
		
		
	}
}
