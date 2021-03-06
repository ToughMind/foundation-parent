package lq.pre20170513.xml.sax;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @description SAX学习---EntityResolver接口 
 * 
 * @author liuquan
 * @date  2015年12月11日
 */
public class MyEntityResolver implements EntityResolver{

	/**
	 * @description 允许应用程序解析外部实体。 解析器将在打开任何外部实体（顶级文档实体除外）前调用此方法 
	 * @param publicId 被引用的外部实体的公共标识符，如果未提供，则为 null。 
	 * @param systemId 被引用的外部实体的系统标识符。 
	 * @return 一个描述新输入源的 InputSource 对象，或者返回 null；以请求解析器打开到系统标识符的常规 URI 连接。 
	 * @throws SAXException
	 * @throws IOException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException { 
		return null;
	}

}
