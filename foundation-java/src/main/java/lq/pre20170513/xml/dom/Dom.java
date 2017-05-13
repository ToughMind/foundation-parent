package lq.pre20170513.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description DOM解析
 * 
 * @author liuquan
 * @date  2015年12月14日
 */
public class Dom {  
	public List<Map<String, Object>> getBooks(String uri) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(uri);
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName("book"); 
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//books结点的book字段
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				NodeList childList = node.getChildNodes();		
				//book结点中的name和price字段
				for (int j = 0; j < childList.getLength(); j++){ 
					Map<String, Object> tmpMap = new HashMap<String, Object>();
					Node tmpNode = childList.item(j); 
					if(!"#text".equals(tmpNode.getNodeName())){
						String key = tmpNode.getNodeName();
						String value = tmpNode.getFirstChild().getNodeValue(); 
						tmpMap.put(key, value);		
						list.add(tmpMap);
					}					
				}				
			} 
			return list;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {	
		Dom dom = new Dom(); 
		List<Map<String, Object>> domResult = dom.getBooks("book.xml");
		if (domResult != null && domResult.size() > 0) {
			System.out.println("DOM:" + domResult.toString()); 
		} else {
			System.out.println("DOM:" + "domResult is null!");
		}
	}
}
