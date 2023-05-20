package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.MessageBean;
import util.ConnectionPool;
import util.DaoUtil;

public class MessageDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	private static final String SELECT_ALL = "SELECT * FROM public.support_message;";
	private static final String SELECT_ALL_WITH_PAGINATION="SELECT * FROM public.support_message WHERE user_mail!='support@webshop.ip' AND text LIKE ? ORDER BY sent_time DESC OFFSET ? LIMIT ? ";
	private static final String SELECT_READ_WITH_PAGINATION="SELECT * FROM public.support_message WHERE read='1' AND user_mail!='support@webshop.ip' AND text LIKE ? ORDER BY sent_time DESC OFFSET ? LIMIT ? ";
	private static final String SELECT_UNREAD_WITH_PAGINATION="SELECT * FROM public.support_message WHERE read='0' AND user_mail!='support@webshop.ip' AND text LIKE ? ORDER BY sent_time DESC OFFSET ? LIMIT ? ";
	private static final String SELECT_SUPPORT_WITH_PAGINATION="SELECT * FROM public.support_message WHERE user_mail='support@webshop.ip' AND text LIKE ? ORDER BY sent_time DESC OFFSET ? LIMIT ? ";
	private static final String SELECT_BY_ID= "SELECT * FROM public.support_message WHERE id=?;";
	private static final String ADD_MESSAGE= "INSERT INTO public.support_message(read, sent_time, text,user_id,username,user_mail) VALUES (?,NOW(),?,?,?,?);";
	private static final String UPDATE_READ = "UPDATE public.support_message SET read=? WHERE id=?;";
	private static final String COUNT_ALL="SELECT COUNT(*) FROM public.support_message WHERE user_mail!='support@webshop.ip' AND text LIKE ?;";
	private static final String COUNT_ALL_READ="SELECT COUNT(*) FROM public.support_message WHERE read='1' AND user_mail!='support@webshop.ip' AND text LIKE ?;";
	private static final String COUNT_ALL_UNREAD="SELECT COUNT(*) FROM public.support_message WHERE read='0' AND user_mail!='support@webshop.ip' AND text LIKE ?;";
	private static final String COUNT_ALL_SUPPORT="SELECT COUNT(*) FROM public.support_message WHERE user_mail='support@webshop.ip' AND text LIKE ? ;";

	public static List<MessageBean> getAll() throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<MessageBean> messages = new ArrayList<>();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				messages.add(new MessageBean(resultSet.getLong("id"), resultSet.getString("text"),
						resultSet.getBoolean("read"), resultSet.getTimestamp("sent_time").toInstant(),
						resultSet.getLong("user_id"), resultSet.getString("username"),
						resultSet.getString("user_mail")));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return messages;
	}
	public static List<MessageBean> getAll(String search,Integer offset,Integer limit,int type) throws SQLException {
		search="%"+search+"%";
		String query="";
		switch(type) {
		case 0:
			query=SELECT_ALL_WITH_PAGINATION;
			break;
		case 1:
			query=SELECT_READ_WITH_PAGINATION;
			break;
		case 2:
			query=SELECT_UNREAD_WITH_PAGINATION;
			break;
		case 3:
			query=SELECT_SUPPORT_WITH_PAGINATION;
			break;
		default:
			query=SELECT_ALL;
			break;
		}
		Connection connection = null;
		ResultSet resultSet = null;
		List<MessageBean> messages = new ArrayList<>();
		Object values[] = { search,offset, limit };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, query, false,values);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				messages.add(new MessageBean(resultSet.getLong("id"), resultSet.getString("text"),
						resultSet.getBoolean("read"), resultSet.getTimestamp("sent_time").toInstant(),
						resultSet.getLong("user_id"), resultSet.getString("username"),
						resultSet.getString("user_mail")));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return messages;
	}
	public static MessageBean getById(Long Id) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		Object values[] = { Id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_BY_ID, false, values);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return new MessageBean(resultSet.getLong("id"), resultSet.getString("text"),
						resultSet.getBoolean("read"), resultSet.getTimestamp("sent_time").toInstant(),
						resultSet.getLong("user_id"), resultSet.getString("username"),
						resultSet.getString("user_mail"));
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	public static boolean add(MessageBean message) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		boolean result = false;
		Object values[] = { message.getRead(),message.getText(),message.getUserId(),message.getUsername(),message.getUserMail() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, ADD_MESSAGE, true, values);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			if (preparedStatement.getUpdateCount() > 0)
				result = true;
			if (resultSet.next())/**/
				message.setId(Long.parseLong(resultSet.getString(1)));
			preparedStatement.close();

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static boolean updateRead(MessageBean message, boolean read) throws SQLException {
		Connection connection = null;
		Object values[] = { read, message.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, UPDATE_READ, false, values);

			int res = preparedStatement.executeUpdate();
			if (res == 1) {
				return true;
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
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
	public static Integer countAll(String search,int type) throws SQLException {
		String query="";
		search="%"+search+"%";
		switch(type) {
		case 0:
			query=COUNT_ALL;
			break;
		case 1:
			query=COUNT_ALL_READ;
			break;
		case 2:
			query=COUNT_ALL_UNREAD;
			break;
		case 3:
			query=COUNT_ALL_SUPPORT;
			break;
		default:
			query=SELECT_ALL;
			break;
		}
		Connection connection = null;
		ResultSet resultSet = null;
		Object values[] = { search };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, query,false,values);
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
