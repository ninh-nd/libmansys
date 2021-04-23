package book.info;

public class Book {
	private String title;
	
	private String author;
	
	private String category;
	
	private String publisher;
	
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
	public Book(String title, String author, String category, String publisher) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
	}
	public Book(String title, String author, String category) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
	}
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
}
