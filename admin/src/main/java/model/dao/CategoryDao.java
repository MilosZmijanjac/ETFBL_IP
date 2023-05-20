package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PGobject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.bean.AttributeBean;
import model.bean.CategoryBean;
import util.ConnectionPool;
import util.DaoUtil;

public class CategoryDao {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	private static final String SELECT_ALL = "SELECT * FROM public.category;";
	private static final String SELECT_BY_ID = "SELECT * FROM public.category where id=?;";
	private static final String SELECT_WITH_PAGINATION="SELECT * FROM public.category OFFSET ? LIMIT ? ";
	private static final String SELECT_ATTRIBUTES_BY_ID = "SELECT special_attributes FROM public.category where id=?;";
	private static final String ADD_CATEGORY = "INSERT INTO public.category(icon_path, name, special_attributes) VALUES (?,?,?);";
	private static final String UPDATE_CATEGORY = "UPDATE public.category SET icon_path=?, name=?, special_attributes=? WHERE id=?;";
	private static final String DELETE_CATEGORY = "DELETE FROM public.category WHERE id=?";
	private static final String COUNT_ALL="SELECT COUNT(*) FROM public.category;";

	public static List<CategoryBean> getAll() throws SQLException, IOException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<CategoryBean> categories = new ArrayList<>();

		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ArrayList<AttributeBean> l = new Gson().fromJson(resultSet.getString("special_attributes"),
						new TypeToken<ArrayList<AttributeBean>>() {
						}.getType());
				categories.add(new CategoryBean(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("icon_path"), l));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return categories;
	}
	public static List<CategoryBean> getAll(Integer offset,Integer limit) throws SQLException, IOException {
		Connection connection = null;
		ResultSet resultSet = null;
		List<CategoryBean> categories = new ArrayList<>();
		Object values[] = { offset,limit };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_WITH_PAGINATION, false,values);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ArrayList<AttributeBean> l = new Gson().fromJson(resultSet.getString("special_attributes"),
						new TypeToken<ArrayList<AttributeBean>>() {
						}.getType());
				categories.add(new CategoryBean(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("icon_path"), l));
			}
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return categories;
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
	public static CategoryBean getById(Long Id) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		Object values[] = { Id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_BY_ID, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ArrayList<AttributeBean> l = new Gson().fromJson(resultSet.getString("special_attributes"),
						new TypeToken<ArrayList<AttributeBean>>() {
						}.getType());
				return new CategoryBean(resultSet.getLong("id"), resultSet.getString("name"),
						resultSet.getString("icon_path"), l);
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	public static ArrayList<AttributeBean> getAtributtesByCategoryId(Long Id) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;

		Object values[] = { Id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, SELECT_ATTRIBUTES_BY_ID, false, values);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return new Gson().fromJson(resultSet.getString("special_attributes"),
						new TypeToken<ArrayList<AttributeBean>>() {
						}.getType());
			}
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	public static boolean add(CategoryBean category) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		boolean result = false;
		PGobject jsonObject = new PGobject();
		jsonObject.setType("json");
		jsonObject.setValue(gson.toJson(category.getSpecialAttributes()));
		Object values[] = { category.getIconPath(), category.getName(), jsonObject };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, ADD_CATEGORY, true, values);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();

			if (preparedStatement.getUpdateCount() > 0)
				result = true;
			if (resultSet.next())/**/
				category.setId(Long.parseLong(resultSet.getString(1)));
			preparedStatement.close();

		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static boolean update(CategoryBean category) throws SQLException {
		Connection connection = null;
		PGobject jsonObject = new PGobject();
		jsonObject.setType("json");
		jsonObject.setValue(gson.toJson(category.getSpecialAttributes()));
		Object values[] = { category.getIconPath(),category.getName(),jsonObject, category.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, UPDATE_CATEGORY, false, values);

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
			PreparedStatement preparedStatement = DaoUtil.prepareStatement(connection, DELETE_CATEGORY, false, values);
			result = preparedStatement.executeUpdate() == 1;
			preparedStatement.close();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static void main(String... args) throws SQLException, IOException, ClassNotFoundException {
		AttributeBean ab = new AttributeBean();
		ab.setName("ime");
		ab.setUnit("metar");
		CategoryBean cb = new CategoryBean();
		ArrayList<AttributeBean> bb = new ArrayList<AttributeBean>();
		bb.add(ab);
		cb.setName("meso");
		cb.setSpecialAttributes(bb);
		CategoryDao.add(cb);
		// System.out.println(CategoryDao.getAll().get(0).getSpecialAttributes().get(0).getUnit());
	}
}
