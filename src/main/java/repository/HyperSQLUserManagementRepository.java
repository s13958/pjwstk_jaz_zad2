package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import users.User;
import users.UserRole;

public enum HyperSQLUserManagementRepository implements UserManagementRepository {

	INSTANCE;

	private static Connection connection;

	private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users(username VARCHAR(40), password VARCHAR(40), email VARCHAR(40), role VARCHAR(40))";
	private static final String CLEAN_TABLE_QUERY = "DELETE FROM users";
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM users";
	private static final String ADD_USER_QUERY = "INSERT INTO users(username, password, email, role) VALUES(?,?,?,?)";
	private static final String LOGIN_VERIFICATION_QUERY = "SELECT * FROM users WHERE username=? AND password=?";
	private static final String GET_USER_DETAILS_QUERY = "SELECT * FROM users WHERE username=?";
	private static final String UPDATE_ROLE_QUERY = "UPDATE users SET role=? WHERE username=?";

	private static PreparedStatement addUserStatement;
	private static PreparedStatement loginVerificationStatement;
	private static PreparedStatement getUserDetailsStatement;
	private static PreparedStatement updateRoleStatement;
	private static PreparedStatement getAllUsersStatement;

	private static Statement statement;

	private static final String PATH = "jdbc:hsqldb:hsql://localhost/workdb";

	static {
		try {
			connection = DriverManager.getConnection(PATH);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			statement.executeUpdate(CREATE_TABLE_QUERY);
			statement.executeUpdate(CLEAN_TABLE_QUERY);

			addUserStatement = connection.prepareStatement(ADD_USER_QUERY);
			loginVerificationStatement = connection.prepareStatement(LOGIN_VERIFICATION_QUERY);
			getUserDetailsStatement = connection.prepareStatement(GET_USER_DETAILS_QUERY);
			updateRoleStatement = connection.prepareStatement(UPDATE_ROLE_QUERY);
			getAllUsersStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
			
			User administrator = new User();
			administrator.setUsername("admin");
			administrator.setPassword("1234");
			administrator.setEmail("admin@homepage.com");
			administrator.setUserRole(UserRole.ADMINISTRATOR);

			User user1 = new User();
			user1.setUsername("user1");
			user1.setPassword("1234");
			user1.setEmail("user1@homepage.com");
			user1.setUserRole(UserRole.PREMIUM);

			User user2 = new User();
			user2.setUsername("user2");
			user2.setPassword("1234");
			user2.setEmail("user2@homepage.com");
			user2.setUserRole(UserRole.REGULAR);

			User user3 = new User();
			user3.setUsername("user3");
			user3.setPassword("1234");
			user3.setEmail("user3@homepage.com");
			user3.setUserRole(UserRole.REGULAR);

			User user4 = new User();
			user4.setUsername("user4");
			user4.setPassword("1234");
			user4.setEmail("user4@homepage.com");
			user4.setUserRole(UserRole.REGULAR);

			User user5 = new User();
			user5.setUsername("user5");
			user5.setPassword("1234");
			user5.setEmail("user5@homepage.com");
			user5.setUserRole(UserRole.REGULAR);

			initUser(administrator);
			initUser(user1);
			initUser(user2);
			initUser(user3);
			initUser(user4);
			initUser(user5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HyperSQLUserManagementRepository getInstance() {
		return HyperSQLUserManagementRepository.INSTANCE;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		try {
			ResultSet rs = getAllUsersStatement.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setUserRole(UserRole.convertStringToUserRole(rs.getString("role")));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}

	@Override
	public boolean login(User user) {
		try {
			loginVerificationStatement.setString(1, user.getUsername());
			loginVerificationStatement.setString(2, user.getPassword());
			ResultSet rs = loginVerificationStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UserRole getRole(User user) {
		try {
			getUserDetailsStatement.setString(1, user.getUsername());
			ResultSet rs = getUserDetailsStatement.executeQuery();
			rs.next();
			return UserRole.convertStringToUserRole(rs.getString("role"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEmail(User user) {
		try {
			getUserDetailsStatement.setString(1, user.getUsername());
			ResultSet rs = getUserDetailsStatement.executeQuery();
			rs.next();
			return rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean registrate(User user) {
		int count = 0;
		try {
			addUserStatement.setString(1, user.getUsername());
			addUserStatement.setString(2, user.getPassword());
			addUserStatement.setString(3, user.getEmail());
			addUserStatement.setString(4, user.getUserRole().toString());

			count = addUserStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count > 0;
	}
	
	private static void initUser(User user) {
		int count = 0;
		try {
			addUserStatement.setString(1, user.getUsername());
			addUserStatement.setString(2, user.getPassword());
			addUserStatement.setString(3, user.getEmail());
			addUserStatement.setString(4, user.getUserRole().toString());

			count = addUserStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean grantPremium(String username) {
		try {
			getUserDetailsStatement.setString(1, username);
			ResultSet rs = getUserDetailsStatement.executeQuery();
			if (!rs.next())
				return false;

			String userRole = rs.getString("role");

			int result = 0;

			switch (userRole.toUpperCase()) {
			case "ADMINISTRATOR":
				return false;
			case "REGULAR":
				updateRoleStatement.setString(1, "PREMIUM");
				updateRoleStatement.setString(2, username);
				result = updateRoleStatement.executeUpdate();
				return result > 0;
			case "PREMIUM":
				updateRoleStatement.setString(1, "REGULAR");
				updateRoleStatement.setString(2, username);
				result = updateRoleStatement.executeUpdate();
				return result > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
