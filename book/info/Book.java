package book.info;

public class Book {
	private int bookID;
	private String title;
	private String author;
	private String category;
	private String publisher;
	static int idCounter = -1;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Book(int bookID, String title, String author, String category, String publisher) {
		this.bookID = idCounter++;
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
	}
	public Book(int bookID, String title, String author, String category) {
		this.bookID = idCounter++;
		this.title = title;
		this.author = author;
		this.category = category;
	}
	public Book(int bookID, String title, String author) {
		this.bookID = idCounter++;
		this.title = title;
		this.author = author;
	}
	
}
