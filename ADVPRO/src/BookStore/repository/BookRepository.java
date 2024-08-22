package BookStore.repository;

import BookStore.model.Book;

import java.util.*;

public class BookRepository {
    private Map<String, Book> books = new HashMap<>();

    public void saveBook(Book book) {
        if (book != null) {
            books.put(book.getISBN(), book);
        }
    }

    public Book findBookByISBN(String isbn) {
        return books.get(isbn);
    }

    public List<Book> getAllBooks() {
        return  new ArrayList<>(books.values());
    }
}
