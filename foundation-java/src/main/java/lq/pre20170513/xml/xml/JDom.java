package lq.pre20170513.xml.xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @description JDOM创建解析xml
 * 
 * @author liuquan
 * @date  2015年12月14日
 */
public class JDom implements XmlInterface {

	@Override
	public void createXml(String fileName) {
		Document document;
		Element root = new Element("employees");
		Element employee = new Element("employee");
		Element name = new Element("name");
		name.setText("sunshine");
		Element sex = new Element("sex");
		sex.setText("M");
		Element age = new Element("age");
		age.setText("23");

		document = new Document(root);
		root.addContent(employee);
		employee.addContent(name);
		employee.addContent(sex);
		employee.addContent(age); 
		XMLOutputter XMLOut = new XMLOutputter();
		try {
			XMLOut.output(document, new FileOutputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parserXml(String fileName) {
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document document = builder.build(fileName);
			Element employees = document.getRootElement();
			List employeeList = employees.getChildren("employee");
			for (int i = 0; i < employeeList.size() ; i++) {
				Element employee = (Element) employeeList.get(i);
				List employeeInfo = employee.getChildren();
				for (int j = 0 ; j < employeeInfo.size() ; j++) {
					System.out.println(((Element) employeeInfo.get(j)).getName()+ ":"+ ((Element) employeeInfo.get(j)).getValue());
				}
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		JDom jdom = new JDom();
		jdom.createXml("jdom.xml");
		jdom.parserXml("jdom.xml");
	}
}