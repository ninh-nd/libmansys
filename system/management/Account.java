package system.management;

import java.util.*;
import java.sql.*;

import system.info.Person;
import menu.register.*;

public class Account extends Person{
	public Account(String name, String email, String phoneNumber, String address, String username, String password) {
		super(name, email, phoneNumber, address);
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}
	
	Scanner sc = new Scanner(System.in);

	private String username;

	private String password;

	
	public void createNewAccount() {
		
		Register acc = new Register();
		
		System.out.println("Create new account");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Email: ");
		String email = sc.nextLine();
		
		System.out.print("Phonenumber: ");
		String phonenumber = sc.nextLine();
		
		System.out.print("Address: ");
		String address = sc.nextLine();
		
		System.out.print("Create Username ");
		String username = sc.nextLine();
		
		System.out.print("Create Password: ");
		String password = sc.nextLine();
		
		acc.register(username, password, username, email, phonenumber, address, password);
				
		System.out.println("Success ");
	}

}