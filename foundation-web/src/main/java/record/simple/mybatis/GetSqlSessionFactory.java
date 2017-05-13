package record.simple.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 获取SqlSessionFactory对象
 * 
 * @author 刘泉
 * @date 2016年8月3日 下午3:31:05
 */
public class GetSqlSessionFactory {
    private static SqlSessionFactory sqlSesssionFactory;

    private GetSqlSessionFactory() {}

    public static void main(String[] args) {
        getSqlSessionFactory();
    }

    synchronized public static SqlSessionFactory getSqlSessionFactory() {
        try {
            if (sqlSesssionFactory == null) {
                String resource = "record/simple/mybatis/mybatis-config.xml";
                InputStream inputStream = Resources
                    .getResourceAsStream(resource);
                sqlSesssionFactory = new SqlSessionFactoryBuilder()
                    .build(inputStream);
            } else {}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSesssionFactory;
    }
}
