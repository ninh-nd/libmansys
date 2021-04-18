package reader;
import book.info.Book;

public interface Search {

	public abstract Book searchTitle();

	public abstract Book searchAuthor();

	public abstract Book searchCategory();

	public abstract Book searchPublisher();

}
