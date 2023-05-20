package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.UserBean;
import model.enumeration.UserStatus;
import model.enumeration.UserType;
import util.ConnectionPool;
import util.DaoUtil;

public class UserDao {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	private static final String SELECT_ALL = "SELECT * FROM public.user_account;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.user_account WHERE id=?;";
	private static final String ADD_USER = "INSERT INTO public.user_account(avatar_path, created, email, first_name, last_name, password,phone, status, type, address,city,country,username) VALUES ( ?, NOW(), ?, ?, ?, ?, ?, ?, ?,?,?,?, ?, ?);";
	private static final String UPDATE_USER = "UPDATE public.user_account	SET avatar_path=?, email=?, first_name=?, last_name=?, password=?, phone=?, status=?, type=?, username=?,address=?,city=?,country=? WHERE id=?;";
	private static final String DELETE_USER = "DELETE FROM public.user_account WHERE id=?";
	private static final String LOGIN = "SELECT EXISTS (SELECT * FROM public.user_account WHERE username = ? AND password = ? AND type='ADMIN' AND status='ACTIVE');";
	private static final String SELECT_WITH_PAGINATION="SELECT * FROM public.user_account ORDER BY created DESC OFFSET ? LIMIT ? ";
	private static final String COUNT_ALL="SELECT COUNT(*) FROM public.user_account;";

	public static List<UserBean> getAll() throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<UserBean> users = new ArrayList<>();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(new UserBean(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"),
						resultSet.getString("avatar_path"), resultSet.getTimestamp("created").toInstant(),
						UserStatus.valueOf(resultSet.getString("status")),
						UserType.valueOf(resultSet.getString("type")),resultSet.getString("address"),
						resultSet.getString("city"),resultSet.getString("country")));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	
	public static List<UserBean> getAll(Integer offset,Integer limit) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<UserBean> users = new ArrayList<>();
		Object values[] = { offset,limit };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_WITH_PAGINATION, false,values);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(new UserBean(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"),
						resultSet.getString("avatar_path"), resultSet.getTimestamp("created").toInstant(),
						UserStatus.valueOf(resultSet.getString("status")),
						UserType.valueOf(resultSet.getString("type")),resultSet.getString("address"),
						resultSet.getString("city"),resultSet.getString("country")));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
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

	public static UserBean getById(Long Id) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		Object values[] = { Id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_BY_ID, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return new UserBean(resultSet.getLong("id"), resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("phone"),
						resultSet.getString("avatar_path"), resultSet.getTimestamp("created").toInstant(),
						UserStatus.valueOf(resultSet.getString("status")),
						UserType.valueOf(resultSet.getString("type")),resultSet.getString("address"),
						resultSet.getString("city"),resultSet.getString("country"));
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}

	public static boolean add(UserBean user) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		boolean result = false;
		Object values[] = { user.getAvatarPath(), user.getEmail(), user.getFirstName(),
				user.getLastName(), user.getPassword(), user.getPhone(), user.getStatus().toString(),
				user.getType().toString(), user.getAddress(),user.getCity(),user.getCountry(), user.getUsername() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, ADD_USER, true, values);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			if (preparedStatement.getUpdateCount() > 0)
				result = true;
			if (resultSet.next())/**/
				user.setId(Long.parseLong(resultSet.getString(1)));
			preparedStatement.close();

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean update(UserBean user) throws SQLException {
		Connection connection = null;
		Object values[] = { user.getAvatarPath(), user.getEmail(), user.getFirstName(),
				user.getLastName(), user.getPassword(), user.getPhone(), user.getStatus().toString(),
				user.getType().toString(), user.getUsername(),user.getAddress(),user.getCity(),user.getCountry(), user.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, UPDATE_USER, false, values);

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
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_USER, false, values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static Boolean loginUser(String username,String password) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		if(username.isBlank()||password.isBlank())
			return false;

		Object values[] = { username,password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, LOGIN, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getBoolean(1);
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
   public static void main(String...args) throws SQLException {
	   System.out.println(UserDao.loginUser("u", "p"));
   }
}