package lq.lq.design_pattern.flyweight;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DbConnectionManager { 
	static private DbConnectionManager instance; // 单例模式
	private static Map connPool = new HashMap();

	// 返回唯一实例.如果是第一次调用此方法,则创建实例
	static synchronized public DbConnectionManager getInstance() {
		if (instance == null) {
			instance = new DbConnectionManager();
		}
		return instance;
	}

	// 防止其它对象创建本类的实例
	private DbConnectionManager() {
		init();
	}

	// 根据名称从map中获取连接
	public static Connection getConnection(String name) {
		Connection conn = null;
		try {
			// 从连接池中获取连接
			DbConnectionPool pool = (GdDbConnectionPool) connPool.get(name);
			if (pool != null)
				conn = pool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

	// 将连接返回给连接池
	public static void returnConnection(String name, Connection conn) {
		try {
			DbConnectionPool pool = (GdDbConnectionPool) connPool.get(name);
			if (pool != null)
				pool.returnConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭所有连接
	public synchronized void release() {
		try {
			Set set = connPool.entrySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry map = (Map.Entry) iterator.next();
				DbConnectionPool pool = (GdDbConnectionPool) map.getValue();
				// 关闭连接池
				pool.setConnWitch("OFF");
			}
			connPool = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 创建连接池
	private void createPools() {
		DbConnectionPool pool = new GdDbConnectionPool();
		// 设定连接池的大小
		pool.setMaxConns(10);
		try {
			// 表示创建连接池
			pool.setConnWitch("ON");
			// 将创建后的连接池放在Map中,用mysql表示是用于连接mysql数据库的
			connPool.put("mysql", pool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 初始化
	private void init() {
		// 创建连接池
		createPools();
	}
}
