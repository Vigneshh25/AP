package designpatterns.behavioral.Iterator;

import java.util.NoSuchElementException;

class BookIterator implements Iterator<Book> {
    private BookCollection bookCollection;
    private int index;

    public BookIterator(BookCollection bookCollection) {
        this.bookCollection = bookCollection;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookCollection.getSize();
    }

    @Override
    public Book next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements to iterate.");
        }
        return bookCollection.getBookAt(index++);
    }
}
