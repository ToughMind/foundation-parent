package record.simple.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 实现java反射的一些基本操作
 * 
 * @author 刘泉
 * @date 2016年8月3日 下午8:49:31
 */
public class demo {
    public static void main(String[] args) {
        try {
            // 3种方式取得User对象
            Class<?> ref1 = Class.forName("Reflect.User");
            Class<?> ref2 = User.class;
            Class<?> ref3 = new User().getClass();
            System.out.println("打印3种方式获取的类名" + ref1.getName() + "----"
                + ref2.getName() + "----" + ref3.getName());

            Class<?> ref = User.class.getClassLoader()
                .loadClass("Reflect.User");
            // 使用Class类的newInstance()方法实例化User对象
            User user1 = (User) ref.newInstance();
            System.out.println("使用Class类的newInstance()方法实例化User对象："
                + user1.getName() + "," + user1.getAge());

            // 使用Constructor类newInstance()方法实例化User对象
            Constructor<?> constructorRef1 = ref.getDeclaredConstructor(null);
            User user2 = (User) constructorRef1.newInstance(null);
            System.out.println("使用Class类的newInstance()方法实例化User对象："
                + user2.getName() + "," + user2.getAge());

            // 使用Constructor类newInstance()方法实例化User对象
            Constructor<?> constructorRef2 = ref
                .getDeclaredConstructor(String.class, int.class);
            User user3 = (User) constructorRef2.newInstance("YES", 3);
            System.out.println("使用Class类的newInstance()方法实例化User对象："
                + user3.getName() + "," + user3.getAge());

            // 无参方法
            Method method = ref.getDeclaredMethod("print");
            System.out.println(method.invoke(user1));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

class User {
    private String name;

    private int age;

    public User() {
        name = "goodBoy";
        age = 3;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String print() {
        System.out.println("调用print()无参方法");
        return "无参返回";
    }

}
