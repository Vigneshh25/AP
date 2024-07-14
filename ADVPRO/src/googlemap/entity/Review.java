package googlemap.entity;

public class Review {
    private String userId;
    private String content;
    private int rating;

    public Review(String userId, String content, int rating) {
        this.userId = userId;
        this.content = content;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Getters and setters
}
