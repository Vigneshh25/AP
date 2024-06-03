package lld.amazonprime;

interface Video {
    String getId();

    void play();

}

class StreamingVideo implements Video {
    private String videoId;

    public StreamingVideo(String videoId) {
        this.videoId = videoId;
    }

    public String getId() {
        return videoId;
    }

    public void play() {
        System.out.println("Playing streaming video " + videoId + "...");
    }

}

class VideoProxy implements Video {
    private Video video;
    private User user;

    public VideoProxy(Video video, User user) {
        this.video = video;
        this.user = user;
    }

    public String getId() {
        return video.getId();
    }

    public void play() {
        if (userHasSubscription()) {
            video.play();
        } else {
            System.out.println("User " + user.getId() + " does not have a subscription.");
        }
    }

    private boolean userHasSubscription() {
        // Check user subscription status...
        return true;
    }
}
