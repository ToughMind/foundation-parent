package lq.record.simple.mybatis;

import org.apache.ibatis.session.SqlSession;

/**
 * 创建SqlSession的核心代码
 * 
 * @author 刘泉
 * @date 2016年8月3日 下午3:48:48
 */
public class GetSqlSession {
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    public static SqlSession getSqlsession() {
        SqlSession sqlSession = tl.get();
        if (sqlSession == null) {
            sqlSession = GetSqlSessionFactory.getSqlSessionFactory()
                .openSession();
        } else {}
        System.out.println("获得的sqlSession对象的hashCode=" + sqlSession.hashCode());
        return sqlSession;
    }

    public static void commit() {
        if (tl.get() != null) {
            tl.get().commit();
            tl.get().close();
            tl.set(null);
            System.out.println("提交了");
        }
    }

    public static void rollback() {
        if (tl.get() != null) {
            tl.get().rollback();
            tl.get().close();
            tl.set(null);
            System.out.println("回滚了");
        }
    }
}
