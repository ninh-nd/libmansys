package book.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Book {
	private int book_id;
	private String title;
	private ArrayList<String> author;
	private String category;
	private String publisher;
	private BookStatus book_status;
<<<<<<< HEAD
	private int quantity;
	
	
	public int getBook_id() {
		return book_id;
	}
=======
	public static ArrayList<String> categoryList = new ArrayList<String>(Arrays.asList("Novel", "Action", "Comedy", "Education", "Drama", "Poetry", "Adventure", "Horror"));
>>>>>>> c863c57b5daf32f72a6b2bdfa8596e8a937677dc
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getAuthor() {
		return author;
	}
	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public BookStatus getBook_status() {
		return book_status;
	}
	public void setBook_status(BookStatus book_status) {
		this.book_status = book_status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Book(String title,  ArrayList<String> author) {
		this.title = title;
		this.author = author;
	}
	public Book(String title,  ArrayList<String> author, String category) {
		this(title, author);
		this.category = category;
	}
	public Book(String title,  ArrayList<String> author, String category, String publisher) {
		this(title, author, category);
		this.publisher = publisher;
	}
<<<<<<< HEAD
	public Book(int book_id,String title,  ArrayList<String> author, String category, String publisher) {
		this(title, author, category);
		this.book_id =book_id;
		this.publisher = publisher;
	}
=======
>>>>>>> c863c57b5daf32f72a6b2bdfa8596e8a937677dc
}
