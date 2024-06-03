package lld.amazonprime;

import java.util.List;

class AmazonPrimeVideo {
    public static void main(String[] args) {
        // Implementation details...
        VideoCatalog videoCatalog = VideoCatalog.getInstance();
        VideoNotifier videoNotifier = videoCatalog.getVideoNotifier();

        User user1 = new User("user1", videoNotifier);
        User user2 = new User("user2", videoNotifier);


        Video movie = new Movie("Inception");
        Video tvShow = new TVShow("Breaking Bad");

        videoCatalog.addVideo(movie);
        videoCatalog.addVideo(tvShow);


        RecommendationStrategy strategy = new PopularVideosStrategy();
        List<Video> recommendations = strategy.recommendVideos(user1);
        System.out.println("Recommendations for user1: " + recommendations);

        Watchlist watchlist = new Watchlist();
        VideoCommand addToWatchlistCommand = new AddToWatchlistCommand(watchlist,movie);
        addToWatchlistCommand.execute();

        VideoCommand removeFromWatchlistCommand = new RemoveFromWatchlistCommand(watchlist,movie);
        removeFromWatchlistCommand.execute();

        VideoDecorator subtitledMovie = new SubtitledVideo(movie);
        subtitledMovie.play();

        VideoDecorator hdTvShow = new HDVideo(tvShow);
        hdTvShow.play();

        Playlist playlist = new BasicPlaylist();
        playlist.addVideo(movie);
        playlist.addVideo(tvShow);
        playlist.play();

        VideoState playingState = new PlayingState();
        playingState.play();
        playingState.pause();
        playingState.stop();

        Video video = new StreamingVideo("xyz123");
        VideoProxy videoProxy = new VideoProxy(video, user1);
        videoProxy.play();
    }
}
