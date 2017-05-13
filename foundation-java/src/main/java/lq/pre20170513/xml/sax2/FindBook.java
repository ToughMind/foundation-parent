package lq.pre20170513.xml.sax2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * @description 对应book.xml 
 * 				目的输入id=2 把相对应的book结点的name和price取出来
 * 
 * @author liuquan
 * @date  2015年12月11日
 */
public class FindBook extends DefaultHandler{
	//可以把下面字段看作状态变量，就是匹配成功时赋值进来，然后输出
	private String name;
	private String price;
	
	//手动输入的要匹配的"id"字段和值   <book id = "2">
	private String attrName;
	private String attrValue;
	
	//在startElement时，(入栈操作，主要这里看一下，入栈顺序是book,name,price;正常过程name和price的赋值会发生在这里)，之后就是endElement时出栈，若匹配到book，则输出已赋值的name和price
	private Stack tagsStack = new Stack();
		
	@Override
	public void endDocument() throws SAXException {
		 System.out.println("没有找到匹配的书本");
	}

	@Override
	public void startElement(String uri, String localName, String qName,Attributes attrs) throws SAXException { 
		if(tagsStack.empty()){     
			if("book".equals(qName)){
				int len=attrs.getLength();
				for(int i=0;i<len;i++){	
					//比如<book id="2">  attrs.getQName(i))对应id  attrs.getValue(i))对应2
					if(attrName.equals(attrs.getQName(i)) && attrValue.equals(attrs.getValue(i))){ 
						tagsStack.push(qName);
						break;
					}
				}
			}
		}
		else{
			tagsStack.push(qName);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(!tagsStack.empty()){
			String tag=(String) tagsStack.peek();
			if("name".equals(tag))
				name=new String(ch,start,length);
			if("price".equals(tag))
				price=new String(ch,start,length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(!tagsStack.empty()){
			String tag=(String)tagsStack.pop();
			if(tagsStack.empty() && "book".equals(tag)){
				System.out.println("name: "+name);
				System.out.println("price: "+price);
				//下面的异常如果不抛出 程序就会到endDocument()方法里
				throw new SAXException("找到了匹配的书本");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String strId =  sc.next(); 
		System.out.println("id=" + strId);
		
		FindBook f = new FindBook();
		f.setAttrName("id"); 
		f.setAttrValue(strId);    	
		//String realpath=System.getProperty("user.dir")+File.separator+"src"+File.separator+"sax"+File.separator+"student.xml"; 
		SAXParserFactory spf=SAXParserFactory.newInstance();
		try {
			SAXParser parser=spf.newSAXParser();
			parser.parse(new File("book.xml"), f);
		} catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
        	//如果不想打出堆栈信息 就把下一句注释掉
        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public Stack getTagsStack() {
		return tagsStack;
	}

	public void setTagsStack(Stack tagsStack) {
		this.tagsStack = tagsStack;
	}
}
