package lq.design_pattern.flyweight;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbConnectionPool {

	// 设定连接池中存放连接的数目
	public void setMaxConns(int numConnections);

	// 设定打开或者关闭连接池
	public void setConnWitch(String onOrOff) throws Exception;

	// 产生连接池
	public void initConnPool() throws SQLException;

	// 从连接池中获取连接
	public Connection getConnection() throws SQLException;

	// 将连接返回给连接池
	public void returnConnection() throws SQLException;

	// 销毁连接池
	public void destroyConnPool() throws SQLException; 
}
