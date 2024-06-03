package lld.amazonprime;

abstract class VideoCommand {
    protected final Video video;
    protected final Watchlist watchlist;

    public VideoCommand(Watchlist watchlist, Video video) {
        this.watchlist = watchlist;
        this.video = video;
    }

    abstract void execute();
}

class AddToWatchlistCommand extends VideoCommand {

    public AddToWatchlistCommand(Watchlist watchlist, Video video) {
        super(watchlist, video);
    }

    @Override
    public void execute() {
        watchlist.addVideo(video);
    }
}

class RemoveFromWatchlistCommand extends VideoCommand {
    public RemoveFromWatchlistCommand(Watchlist watchlist, Video video) {
        super(watchlist, video);
    }

    @Override
    public void execute() {
        watchlist.removeVideo(video);
    }
}
