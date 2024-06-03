package lld.amazonprime;

abstract class VideoDecorator implements Video {
    protected Video decoratedVideo;

    public VideoDecorator(Video decoratedVideo) {
        this.decoratedVideo = decoratedVideo;
    }

    public void play() {
        decoratedVideo.play();
    }
}

class SubtitledVideo extends VideoDecorator {
    public SubtitledVideo(Video decoratedVideo) {
        super(decoratedVideo);
    }

    @Override
    public String getId() {
        return "";
    }

    public void play() {
        // Add subtitle logic...
        System.out.println("Playing subtitled video...");
        super.play();
    }

}

class HDVideo extends VideoDecorator {
    public HDVideo(Video decoratedVideo) {
        super(decoratedVideo);
    }

    @Override
    public String getId() {
        return "";
    }

    public void play() {
        // Add HD quality logic...
        System.out.println("Playing HD video...");
        super.play();
    }
}
