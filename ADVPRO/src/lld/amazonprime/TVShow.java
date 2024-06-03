package lld.amazonprime;

class TVShow implements Video {
    private String title

;

    public TVShow(String title) {
        this.title = title;
    }

    public String getId() {
        return title.replaceAll("\\s", "_");
    }

    public void play() {
        System.out.println("Playing TV show: " + title);
    }

    public void addToWatchlist() {
        System.out.println("Added TV show " + title + " to watchlist.");
    }

    public void removeFromWatchlist() {
        System.out.println("Removed TV show " + title + " from watchlist.");
    }
}
