package lq.pre20170513.xml.sax3;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class MyContentHandler extends DefaultHandler{
	private String preName = null; //当前结点元素的名字
	private final String NAME = "name";
	private final String PRICE = "price";
	private final String BOOK = "book";
	
	 @Override  
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	        if (qName.equals(BOOK)) {
	        	// doto 具体对名字的业务处理，这里我只是简单的进行输出  
	            System.out.println( BOOK+ ":" + attributes.getQName(0)+" value:"+attributes.getValue(0));   
	        }  
	        preName = qName;//标记当前的标签名字  
	    }  
	  
	    @Override  
	    public void endElement(String uri, String localName, String qName) throws SAXException {  
	        preName = null;// 一次处理完后重置标签名  
	    }  
	  
	    @Override  
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	        if (null != preName) {  
	            String temp = new String(ch, start, length);  
	            if (preName.equals(NAME)) {  
	                // doto 具体对名字的业务处理，这里我只是简单的进行输出  
	                System.out.println(NAME + ":" + temp);  
	            } else if (preName.equals(PRICE)) {  
	                System.out.println(PRICE + ":" + temp);  
	            }  
	        }  
	    }  
	    
	    public static void main(String[] args) throws IOException, SAXException {
	    	MyContentHandler myHandler = new MyContentHandler(); 
	        XMLReader reader = XMLReaderFactory.createXMLReader();  
	        reader.setContentHandler(myHandler);  
	        //reader.setErrorHandler(myHandler);  //错误的处理器  
	        reader.parse("book.xml");  
		}
}  
	

