package BookStore;

public class Book {
    private String title;
    private String author;
    private double price;
    private String isbn;

    private Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.price = builder.price;
        this.isbn = builder.isbn;
    }

    public static class BookBuilder {
        private String title;
        private String author;
        private double price;
        private String isbn;

        public BookBuilder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public BookBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public BookBuilder setISBN(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getISBN() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", Price=" + price + ", ISBN=" + isbn + "]";
    }
}
