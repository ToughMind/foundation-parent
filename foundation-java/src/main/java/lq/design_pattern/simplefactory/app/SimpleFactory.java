package lq.design_pattern.simplefactory.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description 工厂类 
 * 
 * @author liuquan
 * @date  2015年12月18日
 */
public class SimpleFactory {
	
	//通过if else 判断 获取对象
	public Translate getTranslate(int type){
		if(type == 1){
			return new CNTranslate();
		}
		else if(type == 2){
			return new ENTranslate();
		}
		else{
			return null;
		}
	}
	
	//通过反射 参数要传对象完整路径（包名+类名）
	public Translate getTranslate(String type){
		Translate translate = null;
		try {
			translate = (Translate)Class.forName(type).newInstance();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return translate;
	}
	
	//模拟spring
	public Translate getTranslate(){
		Translate translate = null;
		try {
			Properties p = new Properties();
			InputStream in = null;
			try {
				in = SimpleFactory.class.getResourceAsStream("class.properties");
				p.load(in);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					in.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			translate = (Translate)Class.forName(p.getProperty("class")).newInstance();
		} 
		catch (InstantiationException e) {
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return translate;
	}
	
}
