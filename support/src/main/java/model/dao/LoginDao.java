package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionPool;
import util.DaoUtil;
import util.MD5;

public class LoginDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String LOGIN = "SELECT id FROM public.users WHERE username = ? AND password = ? AND type='SUPPORT';";
	
	public static Long loginUser(String username,String password) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		if(username.isBlank()||password.isBlank())
			return -1L;

		Object values[] = { username,MD5.getMd5(password) };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, LOGIN, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getLong(1);
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return -1L;
	}
	public static void main(String...args) throws SQLException {
		loginUser("s","p");
	}
}
