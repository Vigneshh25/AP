package lld.amazonprime;

import java.util.ArrayList;
import java.util.List;

interface Playlist extends Video {
    void addVideo(Video video);

    void removeVideo(Video video);
}

class BasicPlaylist implements Playlist {
    private List<Video> videos;

    public BasicPlaylist() {
        videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void removeVideo(Video video) {
        videos.remove(video);
    }

    @Override
    public String getId() {
        return "";
    }

    public void play() {
        System.out.println("Playing all videos in the playlist...");
        for (Video video : videos) {
            video.play();
        }
    }
}
