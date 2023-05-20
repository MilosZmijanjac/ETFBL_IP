package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConnectionPool;
import util.DaoUtil;

public class ProductDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	private static final String DELETE_PRODUCT = "DELETE FROM public.product WHERE id=?";
	private static final String DELETE_PRODUCT_BY_USER_ID = "DELETE FROM public.product WHERE user_id=?";
	private static final String DELETE_PRODUCT_BY_CATEGORY_ID = "DELETE FROM public.product WHERE category_id=?";

	public static boolean deleteById(Long id) throws SQLException {
		Connection connection = null;
		Object values[] = { id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_PRODUCT, false, values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean deleteByUserId(Long user_id) throws SQLException {
		Connection connection = null;
		Object values[] = { user_id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_PRODUCT_BY_USER_ID, false,
					values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static boolean deleteByCategoryId(Long user_id) throws SQLException {
		Connection connection = null;
		Object values[] = { user_id };
		boolean result = false;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_PRODUCT_BY_CATEGORY_ID, false,
					values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
}
