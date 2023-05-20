package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.CommentBean;
import util.ConnectionPool;
import util.DaoUtil;

public class CommentDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	private static final String SELECT_ALL = "SELECT * FROM public.comment;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.comment where id=?;";
	private static final String ADD_COMMENT = "INSERT INTO public.comment(parent_id, text, \"timestamp\", user_id, product_id) VALUES (?,?,?,?,?);";
	private static final String UPDATE_COMMENT = "UPDATE public.comment SET parent_id, text=?, \"timestamp\"=?, user_id=?, product_id=? WHERE id=?;";
	private static final String DELETE_COMMENT = "DELETE FROM public.comment WHERE id=?";
	private static final String DELETE_COMMENT_BY_USER_ID = "DELETE FROM public.comment WHERE user_id=?";

	public static List<CommentBean> getAll() throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<CommentBean> comments = new ArrayList<>();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				comments.add(new CommentBean(resultSet.getLong("id"),
						resultSet.getLong("parent_id"),
						resultSet.getTimestamp("\\\"timestamp\\\"").toInstant(),
						resultSet.getString("text"),
						resultSet.getLong("user_id"),
						resultSet.getLong("product_id")));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return comments;
	}

	public static CommentBean getById(Long Id) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		Object values[] = { Id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_BY_ID, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return new CommentBean(resultSet.getLong("id"),
						resultSet.getLong("parent_id"),
						resultSet.getTimestamp("\\\"timestamp\\\"").toInstant(),
						resultSet.getString("text"),
						resultSet.getLong("user_id"),
						resultSet.getLong("product_id"));
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}

	public static boolean add(CommentBean comment) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		boolean result = false;
		Object values[] = { comment.getParent_id(),comment.getText(),comment.getTimestamp(),comment.getUser_id(),comment.getProduct_id() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, ADD_COMMENT, true, values);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			if (preparedStatement.getUpdateCount() > 0)
				result = true;
			if (resultSet.next())/**/
				comment.setId(Long.parseLong(resultSet.getString(1)));
			preparedStatement.close();

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean update(CommentBean comment) throws SQLException {
		Connection connection = null;
		Object values[] = { comment.getParent_id(),comment.getText(),comment.getTimestamp(),comment.getUser_id(),comment.getProduct_id(), comment.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, UPDATE_COMMENT, false, values);

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

	public static boolean deleteById(Long id) throws SQLException {
		Connection connection = null;
		Object values[] = { id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_COMMENT, false, values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static boolean deleteByUserId(Long id) throws SQLException {
		Connection connection = null;
		Object values[] = { id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_COMMENT_BY_USER_ID, false, values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
