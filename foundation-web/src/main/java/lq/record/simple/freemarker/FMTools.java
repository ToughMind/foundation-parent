package lq.record.simple.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 学习一个新的框架，必然要学习它的SDK和API，但freemarker框架的API非常简单。 将freemarker操作的API封装成一个JAVA类。
 * 
 * @author 刘泉
 * @date 2016年7月22日 下午8:31:07
 */
public class FMTools {

    public static void printIT(Map root, String path)
        throws IOException, TemplateException {
        String ftlPath = FMTools.class.getResource("").getPath();
        System.out.println(ftlPath);
        //取得Configuration配置对象
        Configuration cfg = new Configuration();
        //指定ftl魔板问价内存放位置
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        //设置对象包装类为DefaultObjectWrapper
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        //取得模板
        Template temp = cfg.getTemplate(path);
        //向System.out中打印运行结果
        Writer out = new OutputStreamWriter(System.out);
        //root是打印出来的数据
        temp.process(root, out);
        out.flush();
        out.close();
    }
}
