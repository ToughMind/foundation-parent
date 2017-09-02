package lq.design_pattern.singleton;

/**
 * @description 将创建实例和获取实例分开，优化后的懒汉单例模式 
 * 
 * @author liuquan
 * @date  2015年12月17日
 */
public class Singleton4 {  
  
    private static Singleton4 instance = null;  
  
    private Singleton4() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new Singleton4();  
        }  
    }  
  
    public static Singleton4 getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }  
    
}  