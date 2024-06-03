package lld.amazonprime;

import java.util.*;

class Watchlist {
    private final List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
        System.out.println("Added to watchlist: " + video.getId());
    }

    public List<Video> getVideos() {
        return new ArrayList<>(videos);
    }

    public void removeVideo(Video video) {
        videos.remove(video);
        System.out.println("Removed from watchlist: " + video.getId());
    }
}