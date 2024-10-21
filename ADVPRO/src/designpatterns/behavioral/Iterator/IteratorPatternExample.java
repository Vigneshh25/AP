package designpatterns.behavioral.Iterator;

public class IteratorPatternExample {
    public static void main(String[] args) {
        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("1984", "George Orwell"));
        bookCollection.addBook(new Book("Brave New World", "Aldous Huxley"));
        bookCollection.addBook(new Book("Fahrenheit 451", "Ray Bradbury"));

        Iterator<Book> bookIterator = bookCollection.createIterator();
        
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            System.out.println(book);
        }
    }
}
