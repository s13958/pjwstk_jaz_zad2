package users;

public enum UserRole {
	REGULAR, PREMIUM, ADMINISTRATOR;
	
	public static UserRole convertStringToUserRole(String string) {
		switch (string.toLowerCase()) {
		case "regular":
			return UserRole.REGULAR;
		case "premium":
			return UserRole.PREMIUM;
		case "administrator":
			return UserRole.ADMINISTRATOR;
		default:
			return null;
		}
	}
}
