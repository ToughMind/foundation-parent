package lq.design_pattern.facade;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static Object getInstance() {
		Object obj = null;
		try {
			Properties p = new Properties();
			InputStream in = null;
			try {
				in = Client.class.getResourceAsStream("class.properties");
				p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			obj = (Object)Class.forName(p.getProperty("class")).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
