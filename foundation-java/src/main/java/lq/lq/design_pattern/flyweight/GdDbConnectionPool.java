package lq.lq.design_pattern.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GdDbConnectionPool implements DbConnectionPool {
	private String DB_DRIVER = "com.mysql.jdbc.Driver";
	private String DB_User = "root";
	private String DB_PASSWD = "root";
	private String DATASRC_URL = "jdbc:mysql://localhost/myApp";
	static final int defaultMaxConnections = 10;// 默认的连接池的大小
	private List freeConnections;// 存放目前空闲的连接,空闲池
	private Map busyConnections;// 存放目前正在使用的连接,繁忙池
	private int maxConnections;// 设定连接池的大小

	public GdDbConnectionPool(int numConnections) {
		maxConnections = numConnections;
		freeConnections = null;
		busyConnections = null;
	}

	public GdDbConnectionPool() {
		maxConnections = defaultMaxConnections;
		freeConnections = null;
		busyConnections = null;
	}

	// 销毁数据库连接池
	public void destroyConnPool() throws SQLException {
		// 假如还有正在使用的连接
		if (busyConnections != null) {
			Set set = busyConnections.entrySet();
			Iterator iterator = set.iterator();
			// 销毁正在使用的连接
			while (iterator.hasNext()) {
				Map.Entry map = (Map.Entry) iterator.next();
				Connection conn = (Connection) map.getValue();
				conn.close();
			}
			busyConnections = null;
		}
		// 假如还有空闲的连接
		if (freeConnections != null) {
			// 销毁空闲的连接
			for (int i = 0; freeConnections.size() > i; i++) {
				Connection conn = (Connection) freeConnections.get(i);
				conn.close();
			}
			freeConnections = null;
		}
	}

	// 获取数据库连接的操作
	public synchronized Connection getConnection() throws SQLException {
		if (freeConnections == null)
			throw new SQLException("连接池还没有创建");
		try {
			// 表示暂时没有空闲的连接
			if (freeConnections.size() == 0)
				wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 获取空闲池的连接
		Connection conn = (Connection) freeConnections.get(0);

		freeConnections.remove(0);// 在空闲池里销毁已经获取的连接
		busyConnections.put(Thread.currentThread(), conn);// 将已经获取的连接放在繁忙池里
		return conn;
	}

	// 初始化连接池
	public void initConnPool() throws SQLException {
		try {
			freeConnections = new ArrayList(maxConnections);
			busyConnections = new HashMap(maxConnections);
			// 创建连接,并放在连接池中
			Class.forName(DB_DRIVER);
			// conn = DriverManager.getConnection(DATASRC_URL, DB_User,
			// DB_PASSWD);
			// GdDbConnection con = new GdDbConnection(this);
			for (int i = 0; i < maxConnections; i++) {
				freeConnections.add(DriverManager.getConnection(DATASRC_URL,
						DB_User, DB_PASSWD));
			}
		} catch (Exception e) {
			freeConnections = null;
			busyConnections = null;
			throw new SQLException(e.toString());
		}
	}

	public synchronized void returnConnection() throws SQLException {
		// 从繁忙池中销毁已经返回的连接
		Connection conn = (Connection) busyConnections.get(Thread
				.currentThread());
		if (conn == null)
			throw new SQLException("没有发现繁忙池中有连接");
		busyConnections.remove(Thread.currentThread());
		// 将已经返回的连接重新放回空闲池中
		freeConnections.add(conn);
		notify();
	}

	// 设置数据库的开关
	public void setConnWitch(String onOrOff) throws Exception {
		try {
			// 假如为ON,则表示初始化连接池
			if ("ON".equalsIgnoreCase(onOrOff)) {
				initConnPool();
				// 假如为OFF,则表示销毁连接池
			} else if ("OFF".equalsIgnoreCase(onOrOff))
				destroyConnPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMaxConns(int numConnections) {
		maxConnections = numConnections;
	}
}
