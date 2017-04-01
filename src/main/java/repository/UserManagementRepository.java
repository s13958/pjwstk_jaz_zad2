package repository;

import java.util.List;

import users.User;
import users.UserRole;

public interface UserManagementRepository {
	public List<User> getUsers();

	public boolean login(User user);

	public UserRole getRole(User user);

	public String getEmail(User user);

	public boolean registrate(User user);

	public boolean grantPremium(String username);
}
