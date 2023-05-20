package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.LogBean;
import model.enumeration.LogType;
import util.ConnectionPool;
import util.DaoUtil;

public class LogDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	private static final String SELECT_ALL_DESC = "SELECT * FROM public.log ORDER BY timestamp DESC;";
	private static final String SELECT_WITH_PAGINATION="SELECT * FROM public.log ORDER BY timestamp DESC OFFSET ? LIMIT ? ";
	private static final String COUNT_ALL="SELECT COUNT(*) FROM public.log;";
	
	public static List<LogBean> getAll() throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<LogBean> logs = new ArrayList<>();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ALL_DESC, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				logs.add(new LogBean(resultSet.getLong("id"), resultSet.getLong("user_id"),
						resultSet.getString("username"),LogType.valueOf(resultSet.getString("type")),
						resultSet.getString("path"),resultSet.getString("message"),
						resultSet.getTimestamp("timestamp").toInstant()));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return logs;
	}
	public static List<LogBean> getAll(Integer offset,Integer limit) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<LogBean> logs = new ArrayList<>();
		Object values[] = { offset,limit };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_WITH_PAGINATION, false,values);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				logs.add(new LogBean(resultSet.getLong("id"), resultSet.getLong("user_id"),
						resultSet.getString("username"),LogType.valueOf(resultSet.getString("type")),
						resultSet.getString("path"),resultSet.getString("message"),
						resultSet.getTimestamp("timestamp").toInstant()));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return logs;
	}
	public static Integer countAll() throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, COUNT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return 0;
	}

}
