package lq.pre20170513.xml.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @description SAX学习---ErrorHandler接口,是错误处理程序的基本接口。
 * 
 * @author liuquan
 * @date  2015年12月11日
 */
public class MyErrorHandler implements ErrorHandler{

	/**
	 * @description 接收不可恢复的错误的通知。 
	 * @param exception
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void warning(SAXParseException e) throws SAXException {
		System.err.println("Warning ("+e.getLineNumber()+","+e.getColumnNumber()+") : "+e.getMessage());
	}

	/**
	 * @description 接收可恢复的错误的通知 
	 * @param exception
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void error(SAXParseException e) throws SAXException {
		 System.err.println("Error ("+e.getLineNumber()+","+e.getColumnNumber()+") : "+e.getMessage());  
	}

	/**
	 * @description 接收不可恢复的错误的通知。  
	 * @param exception
	 * @throws SAXException
	 * @author liuquan
	 * @date  2015年12月11日
	 */
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("FatalError ("+e.getLineNumber()+","+e.getColumnNumber()+") : "+e.getMessage());  
	}
		
}
