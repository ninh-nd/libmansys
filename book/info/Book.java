package book.info;
public class Book {

	public Book(String title, int author, String category, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
	}

	public Book(String title, int author) {
		super();
		this.title = title;
		this.author = author;
	}

	public Book(String title, int author, String category) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
	}
	private String title;

	private int author;

	private String category;

	private String publisher;
	
}
