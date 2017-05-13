package lq.pre20170513.xml.sax;

import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;

/**
 * @description SAX学习---DTDHandler接口,接收与 DTD相关的事件的通知的处理器接口
 * 
 * @author liuquan
 * @date  2015年12月11日
 */
public class MyDTDHandler implements DTDHandler{

	/**
	 * @description 接收注释声明事件的通知。 
	 * @param name 注释名称
	 * @param publicId 注释的公共标识符，如果未提供，则为 null
	 * @param systemId 注释的系统标识符，如果未提供，则为 null
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void notationDecl(String name, String publicId, String systemId)
			throws SAXException {
		System.out.println("notationDecl()---(name = "+name  +",systemId = "+publicId  +",publicId = "+systemId+")"); 		
	}

	/**
	 * @description 接收未解析的实体声明事件的通知。 
	 * @param name 未解析的实体的名称。 
	 * @param publicId 实体的公共标识符，如果未提供，则为 null。
	 * @param systemId 实体的系统标识符。
	 * @param notationName 相关注释的名称。 
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void unparsedEntityDecl(String name, String publicId,
			String systemId, String notationName) throws SAXException {
		System.out.println("unparsedEntityDecl---(name = "+name +",systemId = "+publicId  +",publicId = "+systemId  +",notationName = "+notationName+")");  		
	}
	
}
