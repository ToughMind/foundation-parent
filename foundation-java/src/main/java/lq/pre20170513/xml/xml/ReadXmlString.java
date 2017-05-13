package lq.pre20170513.xml.xml;

import org.dom4j.DocumentHelper;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

/**
 * @description 4种 XML格式的字符串读取方式 简单比较
 * 
 * @author liuquan
 * @date 2015年12月31日
 */
public class ReadXmlString {

	public static void main(String[] args) throws Exception {
		String xmlStr = "<message><a>try</a><b>try</b><c>try</c><d>try</d><e>try</e><f>try</f><g>try</g><h>try</h><i>do you konw？</i><世界>你好！</世界></message>";
		long begin = System.currentTimeMillis();
		parse4Dom(xmlStr);
		long after = System.currentTimeMillis();
		System.out.println("DOM用时" + (after - begin) + "毫秒");

		begin = System.currentTimeMillis();
		parse4Sax(xmlStr);
		after = System.currentTimeMillis();
		System.out.println("SAX用时" + (after - begin) + "毫秒");

		begin = System.currentTimeMillis();
		parse4DOM4j(xmlStr);
		after = System.currentTimeMillis();
		System.out.println("DOM4j用时" + (after - begin) + "毫秒");
		
		begin = System.currentTimeMillis();
		parse4JDOM(xmlStr);
		after = System.currentTimeMillis();
		System.out.println("JDOM用时" + (after - begin) + "毫秒");
	}

	/**
	 * @description JDOM解析,需要下载对应的jar包  
	 * @param xmlStr
	 * @throws Exception 
	 * @time 2016年1月1日 上午2:04:03
	 */
	private static void parse4JDOM(String xmlStr) throws Exception { 
		SAXBuilder builder=new SAXBuilder(false);
		org.jdom.Document doc = builder.build(new InputSource(new StringReader(xmlStr)));   
        org.jdom.Element root = doc.getRootElement(); // 得到根元素   
        System.out.println("根节点" + root.getName());   
           
        List<org.jdom.Element> list = root.getChildren(); // 得到元素的集合   
        //List studentList = students.getChildren("student"); // 得到指定元素（节点）的集合   

        if( list!=null){   
            for (int i = 0; i < list.size(); i++) {   
                 org.jdom.Element element = (org.jdom.Element)list.get(i);   
                 System.out.println("节点=" + element.getName() + "\ttext=" + element.getValue());   
             }   
          }   
	}

	/**
	 * @description DOM4J解析,需要下载对应的jar包
	 * @param xmlStr
	 * @throws Exception
	 * @time 2016年1月1日 上午1:52:58
	 */
	private static void parse4DOM4j(String xmlStr) throws Exception { 
		org.dom4j.Document doc=(org.dom4j.Document)DocumentHelper.parseText(xmlStr);   
        org.dom4j.Element message = doc.getRootElement();   
        System.out.println("根节点" + message.getName());   
        Iterator elements = message.elementIterator();   
        while(elements.hasNext()){   
           org.dom4j.Element element = (org.dom4j.Element)elements.next();   
           System.out.println("节点" + element.getName() + "\ttext=" + element.getText());
           System.out.println();  
        }
	}

	/**
	 * @description java自带的SAX解析
	 * @param xmlStr
	 * @author liuquan
	 * @throws Exception
	 * @date 2015年12月31日
	 */
	private static void parse4Sax(String xmlStr) throws Exception {
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		SAXParser saxparser = saxfac.newSAXParser();
		// 为演示方便 写成匿名内部类
		saxparser.parse(new InputSource(new StringReader(xmlStr)),
				new myHandler());
	}

	/**
	 * @description java自带的DOM解析.
	 * @param xmlStr
	 * @author liuquan
	 * @throws Exception
	 * @date 2015年12月31日
	 */
	private static void parse4Dom(String xmlStr) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
		Element message = doc.getDocumentElement();
		NodeList list = message.getChildNodes();
		if (list != null) {
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				System.out.println("节点=" + node.getNodeName() + "\ttext="
						+ node.getFirstChild().getNodeValue());
			}
		}
	}
}

/**
 * @Description SAX解析的处理器 sax解析要多写一个类，但是非常快
 * @author liuquan
 * @time 2016年1月1日 上午1:53:03
 */
class myHandler extends DefaultHandler {

	private StringBuffer buf;
	private String str;

	public myHandler() {
		super();
	}

	public void startDocument() throws SAXException {
		buf = new StringBuffer();
		System.out.println("*******开始解析XML*******");
	}

	public void endDocument() throws SAXException {
		System.out.println("*******XML解析结束*******");
	}

	public void endElement(String namespaceURI, String localName,
			String fullName) throws SAXException {
		str = buf.toString();
		System.out.println("节点=" + fullName + "\tvalue=" + buf + " 长度="
				+ buf.length());
		buf.delete(0, buf.length());
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {
		// 将元素内容累加到StringBuffer中
		buf.append(chars, start, length);
	}
}