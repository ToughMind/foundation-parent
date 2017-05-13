package lq.lq.design_pattern.singleton;

/**
 * @description 采用内部类优化后的懒汉单例模式 
 * 
 * @author liuquan
 * @date  2015年12月17日
 */
public class Singleton3 {  
  
    /* 私有构造方法，防止被实例化 */  
    private Singleton3() {  
    }  
  
    /* 此处使用一个内部类来维护单例 */  
    private static class SingletonFactory {  
        private static Singleton3 instance = new Singleton3();  
    }  
  
    /* 获取实例 */  
    public static Singleton3 getInstance() {  
        return SingletonFactory.instance;  
    }  
  
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */  
    public Object readResolve() {  
        return getInstance();  
    }  
    
}  