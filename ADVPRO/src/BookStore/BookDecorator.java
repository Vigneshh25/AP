package BookStore;

public abstract class BookDecorator{
    protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        this.decoratedBook = decoratedBook;
    }

    public String getDescription() {
        return decoratedBook.getAuthor();
    }

    public double getPrice() {
        return decoratedBook.getPrice();
    }
}
