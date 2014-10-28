package sagar.databasedesign.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sagar.databasedesign.librarymanagementsystem.User;

public final class DatabaseManager {
	private static DatabaseManager databaseManager;
	private static Connection connection = null;

	private static final String URI = "jdbc:mysql://localhost:3306/";
	private static final String SQLUSER = "root";
	private static final String SCHEMA = "library";
	private static final String USE_STATEMENT = "use " + SCHEMA + ";";

	private static final String USERINFO = "userinfo";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String SELECT_USER = "SELECT " + USERNAME + ", "
			+ PASSWORD + " FROM " + SCHEMA + "." + USERINFO + " WHERE "
			+ USERNAME + "=? AND " + PASSWORD + "=SHA1(?);";

	private DatabaseManager() {
		try {
			connection = DriverManager.getConnection(URI, SQLUSER, "");

			Statement stmt = connection.createStatement();

			stmt.execute(USE_STATEMENT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseManager getInstane() {
		if (databaseManager == null)
			databaseManager = new DatabaseManager();
		return databaseManager;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public User getUserInfo(User user) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_USER);
			statement.setString(1, user.getAuthentication().getUserName());
			statement.setString(2, new String(user.getAuthentication()
					.getPassword()));
			rs = statement.executeQuery();

			if (rs != null && rs.next())
				return new User(rs.getString(USERNAME), rs.getString(PASSWORD)
						.toCharArray());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void close() {
		if (databaseManager != null)
			if (connection != null)
				try {
					connection.close();
					databaseManager = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
}
