package FoodManagement;

public class Review {
    private final int rating;
    private final String comment;

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }
}
