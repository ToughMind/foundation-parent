package lq.pre20170513.xml.xml;

/**
 * @description XML文档建立与解析的接口 
 * 
 * @author liuquan
 * @date  2015年12月14日
 */
public interface XmlInterface {   
	/**
	 * @description 建立XML文档 
	 * @param fileName 文件全路径名称 
	 * @author liuquan
	 * @date  2015年12月14日
	 */	
	public void createXml(String fileName);  
	/**
	 * @description 
	 * @param fileName 解析XML文档 
	 * @author liuquan
	 * @date  2015年12月14日
	 */
	public void parserXml(String fileName);  
}
