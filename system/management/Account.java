package management;
import java.util.*;

import system.info.Person;


public class Account extends Person{
	public Account(String name, String email, String phoneNumber, String address, String username, String password) {
		super(name, email, phoneNumber, address);
		this.username = username;
		this.password = password;
		// TODO Auto-generated constructor stub
	}
	List<Account> Account = new ArrayList<Account>();
	Scanner sc = new Scanner(System.in);

	private String username;

	private String password;

	private Person info;
	
	public void logIn() {
	
	}

	public void logOut() {

	}
	
	public void createNewAccount() {
		
		
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
		
		Account account = new Account(name, email, phonenumber, address, username, password);
		Account.add(account);
		
		System.out.println("Success ");
	}

}