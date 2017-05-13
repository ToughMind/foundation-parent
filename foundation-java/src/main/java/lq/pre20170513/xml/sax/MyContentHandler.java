package lq.pre20170513.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * @description SAX学习---ContentHandler接口 
 * 
 * @author liuquan
 * @date  2015年12月11日
 */
public class MyContentHandler implements ContentHandler{
	StringBuffer jsonStringBuffer ;  
	//为了让输出有层次感  定义每行输出前方的空格数
    int frontBlankCount = 0;  
    
    public MyContentHandler(){  
        jsonStringBuffer = new StringBuffer();  
    }  

	/**
	 * @description 接收用来查找 SAX 文档事件起源的对象。 
	 * @param locator  可以返回任何 SAX 文档事件位置的对象 
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		System.out.println(this.toBlankString(this.frontBlankCount) + "setDocumentLocator()---(lineNumber = "+locator.getLineNumber() +",columnNumber = "+locator.getColumnNumber() +",systemId = "+locator.getSystemId()+",publicId = "+locator.getPublicId()+")");  		
	}

	/**
	 * @description 接收文档的开始的通知。 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void startDocument() throws SAXException {
		 System.out.println(this.toBlankString(this.frontBlankCount++) + "startDocument()---");  
	}
	
	/**
	 * @description 开始前缀 URI 名称空间范围映射。 此事件的信息对于常规的命名空间处理并非必需：
	 * 				当 http://xml.org/sax/features/namespaces 功能为 true（默认）时，SAX XML 读取器将自动替换元素和属性名称的前缀。   
	 * @param prefix 前缀
	 * @param uri 命名空间 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		System.out.println(this.toBlankString(this.frontBlankCount++)+"startPrefixMapping()--- xmlns:"+prefix+" = "  +"\""+uri+"\"");  
	}
	
	/**
	 * @description 接收文档的开始的通知。 
	 * @param uri 元素的命名空间 
	 * @param localName 元素的本地名称（不带前缀） 
	 * @param qName 元素的限定名（带前缀） 
	 * @param atts 元素的属性集合 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		 System.out.println(this.toBlankString(this.frontBlankCount++) + "startElement()---"+qName+"      uri="+uri);   
	}

	/**
	 * @description 接收字符数据的通知。 
	 * 				在DOM中 ch[start:end] 相当于Text节点的节点值（nodeValue） 
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
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
        System.out.println(this.toBlankString(this.frontBlankCount) + "characters()---length:"+ length +",str:"+buffer.toString());  		
	}
	
	/**
	 * @description 接收文档的结尾的通知。 
	 * @param uri 元素的命名空间 
	 * @param localName ：元素的本地名称（不带前缀） 
	 * @param qName 元素的限定名（带前缀） 
	 * @param atts
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		 System.out.println(this.toBlankString(--this.frontBlankCount)+"endElement()---"+qName+"      uri="+uri);  				
	}
	
	/**
	 * @description 结束前缀 URI 范围的映射。  
	 * @param prefix
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		 System.out.println(this.toBlankString(--this.frontBlankCount) + "endPrefixMapping()---"+prefix);  		
	}
	
	/**
	 * @description 接收文档的结尾的通知。
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void endDocument() throws SAXException {
		System.out.println(this.toBlankString(--this.frontBlankCount) + "endDocument()---");  		
	}

	/**
	 * @description 接收元素内容中可忽略的空白的通知。
	 * @param ch 来自 XML 文档的字符 
	 * @param start  数组中的开始位置 
	 * @param length 从数组中读取的字符的个数 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
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
        System.out.println("ignorableWhitespace()---" + this.toBlankString(this.frontBlankCount)+">>> ignorable whitespace("+length+"): "+buffer.toString());  	
	}

	/**
	 * @description  接收处理指令的通知。 
	 * @param target 处理指令目标 
	 * @param data 处理指令数据，如果未提供，则为 null。
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		System.out.println("processingInstruction()---" + this.toBlankString(this.frontBlankCount)+">>> process instruction : (target = \"" +target+"\",data = \""+data+"\")"); 		
	}

	/**
	 * @description 接收跳过的实体的通知。 
	 * @param name  所跳过的实体的名称。如果它是参数实体，则名称将以 '%' 开头， 如果它是外部 DTD 子集，则将是字符串 "[dtd]" 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		 System.out.println(this.toBlankString(this.frontBlankCount) + ">>> skipped_entity : "+name);  
	}

	/**
	 * @description 自写辅助工具 让输出有层次感觉 
	 * @param count
	 * @return
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	private String toBlankString(int count){  
        StringBuffer buffer = new StringBuffer();  
        for(int i = 0;i<count;i++)  
            buffer.append("    ");  
        return buffer.toString();  
    }
}
