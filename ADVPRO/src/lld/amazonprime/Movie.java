package lld.amazonprime;

class Movie implements Video {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getId() {
        return title.replaceAll("\\s", "_");
    }

    public void play() {
        System.out.println("Playing movie: " + title);
    }

    public void addToWatchlist() {
        System.out.println("Added movie " + title + " to watchlist.");
    }

    public void removeFromWatchlist() {
        System.out.println("Removed movie " + title + " from watchlist.");
    }
}
