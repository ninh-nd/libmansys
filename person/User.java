package person;

import java.sql.ResultSet;

public abstract class User {
	private String username;

	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	private String name;

	private String email;

	private String phoneNumber;

	private String address;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public User(String username, String password, String name, String email, String address, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public abstract ResultSet viewRentedBooks(User user);

	public abstract ResultSet viewHistory(User user);
}
