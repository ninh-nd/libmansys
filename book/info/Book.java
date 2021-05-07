package book.info;

import java.util.ArrayList;

public class Book {
	private int book_id;
	private String title;
	private ArrayList<String> author;
	private String category;
	private String publisher;
	private BookStatus bookstatus;
	private int quantity;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Book(String title,  ArrayList<String> author, String category, String publisher) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
	}
	public Book(String title,  ArrayList<String> author, String category) {
		this.title = title;
		this.author = author;
		this.category = category;
	}
	public Book(String title,  ArrayList<String> author) {
		this.title = title;
		this.author = author;
	}
	
}
