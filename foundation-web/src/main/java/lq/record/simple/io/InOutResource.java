package lq.record.simple.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 从资源文件中读取数据，并刷新内存数据
 * 
 * @author 刘泉
 * @date 2016年7月16日 下午3:42:10
 */
public class InOutResource {
    public static void main(String[] args) {
        fun1();
    }

    /**
     * 从资源文件中读取数据(json数据)
     * 
     * @author 刘泉
     * @date 2016年7月16日 下午3:43:51
     */
    private static void fun1() {
        // 加载资源文件
        Resource res = new ClassPathResource("json/dev/user.json");
        if (!res.exists()) {
            System.out.println("未找到json资源文件！");
            return;
        }
        File file = null;
        BufferedReader in = null;
        try {
            file = res.getFile();
            in = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));
            StringBuilder json = new StringBuilder(1024);
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line.trim());
            }
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
