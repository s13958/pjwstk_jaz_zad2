package repository;

import java.util.ArrayList;
import java.util.List;

import users.User;
import users.UserRole;

public enum DummyUserManagementRepository implements UserManagementRepository {

	INSTANCE;

	public static DummyUserManagementRepository getInstance() {
		return DummyUserManagementRepository.INSTANCE;
	}

	private static List<User> db;

	static {
		db = new ArrayList<>();
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

		db.add(administrator);
		db.add(user1);
		db.add(user2);
		db.add(user3);
		db.add(user4);
		db.add(user5);
	}

	public List<User> getUsers() {
		return db;
	}

	public boolean login(User user) {
		boolean isUserInDatabase = db.stream().anyMatch(item -> {
			return item.getUsername().equals(user.getUsername()) && item.getPassword().equals(user.getPassword());
		});

		if (isUserInDatabase) {
			return true;
		} else {
			return false;
		}
	}

	public UserRole getRole(User user) {
		for (User dbuser : db) {
			if (dbuser.getUsername().equals(user.getUsername()))
				return dbuser.getUserRole();
		}
		return null;
	}

	public String getEmail(User user) {
		for (User dbuser : db) {
			if (dbuser.getUsername().equals(user.getUsername()) && dbuser.getPassword().equals(user.getPassword()))
				return dbuser.getEmail();
		}
		return null;
	}

	public boolean registrate(User user) {
		boolean isUserNew = db.stream().noneMatch(item -> {
			return item.getUsername().equals(user.getUsername()) && item.getEmail().equals(user.getEmail());
		});

		if (isUserNew) {
			db.add(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean grantPremium(String username) {
		User user = null;
		for (int i = 0; i < db.size(); i++) {
			if (db.get(i).getUsername().equals(username)) {
				user = db.get(i);
				break;
			}
		}
		if (user == null) {
			return false;
		} else {
			if (user.getUserRole().equals(UserRole.ADMINISTRATOR)) {
				return false;
			} else if (user.getUserRole().equals(UserRole.REGULAR)) {
				user.setUserRole(UserRole.PREMIUM);
				return true;
			} else if (user.getUserRole().equals(UserRole.PREMIUM)) {
				user.setUserRole(UserRole.REGULAR);
				return true;
			} else {
				return false;
			}
		}
	}
}