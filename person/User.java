package person;

import java.util.*;

public class User {
	private String username;

	private String password;

	private String name;

	private String email;

	private String phoneNumber;

	private String address;

	public String getUsername() {
		return username;
	}

//	public User(String username, String password) {
//		this.username = username;
//		this.password = password;
//	}

	public User(String username, String password, String name, String email, String address, String phoneNumber) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
}
