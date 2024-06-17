package BookStore;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBookRepository {
    private Map<String, Book> books = new HashMap<>();

    public void saveBook(Book book) {
        if (book != null) {
            books.put(book.getISBN(), book);
        }
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }
}
