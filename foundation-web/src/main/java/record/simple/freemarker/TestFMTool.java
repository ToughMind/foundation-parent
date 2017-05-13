package record.simple.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import freemarker.template.TemplateException;

/**
 * 使用FMTools类在控制台输入运行结果
 * 
 * @author 刘泉
 * @date 2016年7月22日 下午9:34:01
 */
public class TestFMTool {

    public static void main(String[] args) {
        // fun1();
        //fun2();
        fun3();
    }

    /**
     * 输出嵌套类型
     * 
     * @author 刘泉
     * @date 2016年7月23日 上午9:56:06
     */
    private static void fun3() {
        try {
            Map map = new HashMap();

            // List中有Map
            List mapList = new ArrayList();
            Map userMap1 = new HashMap();
            Map userMap2 = new HashMap();
            mapList.add(userMap1);
            mapList.add(userMap2);
            map.put("mapListKey", mapList);
            userMap1.put("user0Key", new User("0", "user0"));
            userMap1.put("user1Key", new User("1", "user1"));
            userMap2.put("user0Key", new User("0", "user0"));
            userMap2.put("user1Key", new User("1", "user1"));

            // Map中有List
            Map listMap = new HashMap();
            List userList0 = new ArrayList();
            List userList1 = new ArrayList();
            listMap.put("userListKey0", userList0);
            listMap.put("userListKey1", userList1);
            map.put("listMapKey", listMap);
            userList0.add(new User("0", "user0"));
            userList0.add(new User("1", "user1"));
            userList1.add(new User("0", "user0"));
            userList1.add(new User("1", "user1"));

            FMTools.printIT(map, "test3.ftl");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出复杂类型
     * 
     * @author 刘泉
     * @date 2016年7月22日 下午10:06:13
     */
    private static void fun2() {
        try {
            Map map = new HashMap();

            // 简单数据类型的计算
            int intValue = 2000000;
            map.put("intKey", intValue);

            // 数组数据类型的计算
            String[] array = new String[] { "a", "b", "c", "d" };
            map.put("arrayKey", array);

            // 复杂数组类型
            User[] userArray = new User[2];
            userArray[0] = new User("0", "user0");
            userArray[1] = new User("1", "user1");
            map.put("userArrayKey", userArray);

            // 输出集合对象List
            List userList = new ArrayList();
            userList.add(new User("0", "user0"));
            userList.add(new User("1", "user1"));
            map.put("userListKey", userList);

            // 输出集合对象Set
            Set userSet = new HashSet();
            userSet.add(new User("0", "user0"));
            userSet.add(new User("1", "user1"));
            map.put("userSetKey", userSet);

            // 输出集合对象Map
            Map userMap = new LinkedHashMap();
            userMap.put("user0Key", new User("0", "user0"));
            userMap.put("user1Key", new User("1", "user1"));
            map.put("userMapKey", userMap);

            FMTools.printIT(map, "test2.ftl");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将8种简单数据类型用FreeMarker框架进行输出
     * 
     * @author 刘泉
     * @date 2016年7月22日 下午9:35:48
     */
    private static void fun1() {
        try {
            byte byteValue = -128;
            short shortValue = 10000;
            int intValue = 2000000;
            long longValue = 20000L;
            float floatValue = 100000F;
            double doubleValue = 20000D;
            char charValue = 'A';
            boolean booleanValue = false;

            Map map = new HashMap();
            map.put("byteKey", byteValue);
            map.put("shortKey", shortValue);
            map.put("intKey", intValue);
            map.put("longKey", longValue);
            map.put("floatKey", floatValue);
            map.put("doubleKey", doubleValue);
            map.put("charKey", charValue);
            map.put("booleanKey", booleanValue);

            FMTools.printIT(map, "test.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static class User {
        private String id;

        private String name;

        public User(String id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
