package designpatterns.behavioral.Iterator;

import java.util.ArrayList;
import java.util.List;

class BookCollection implements Aggregate<Book> {
    private List<Book> books;

    public BookCollection() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public int getSize() {
        return books.size();
    }

    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(this);
    }
}
