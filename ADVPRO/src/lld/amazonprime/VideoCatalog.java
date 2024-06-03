package lld.amazonprime;

import java.util.ArrayList;
import java.util.List;

class VideoCatalog {
    private final List<Video> videos = new ArrayList<>();
    private final VideoNotifier videoNotifier = new VideoNotifier();

    // Private constructor
    private VideoCatalog() {}

    // Static inner class for Singleton instance
    private static class SingletonHelper {
        private static final VideoCatalog INSTANCE = new VideoCatalog();
    }

    public static VideoCatalog getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void addVideo(Video video) {
        videos.add(video);
        videoNotifier.notifyObservers("New video available: " + video.getId());
    }

    public Video getVideo(String videoId) {
        for (Video video : videos) {
            if (video.getId().equals(videoId)) {
                return video;
            }
        }
        return null;
    }

    public VideoNotifier getVideoNotifier() {
        return videoNotifier;
    }
}

