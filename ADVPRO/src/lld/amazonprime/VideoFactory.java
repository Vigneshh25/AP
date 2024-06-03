package lld.amazonprime;

interface VideoFactory {
    Video createVideo(String type, String title);
}

class MovieFactory implements VideoFactory {
    public Video createVideo(String title) {
        return new Movie(title);
    }

    @Override
    public Video createVideo(String type, String title) {
        return null;
    }
}

class TVShowFactory implements VideoFactory {
    public Video createVideo(String title) {
        return new TVShow(title);
    }

    @Override
    public Video createVideo(String type, String title) {
        return null;
    }
}
