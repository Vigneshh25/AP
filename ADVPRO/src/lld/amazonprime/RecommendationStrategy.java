package lld.amazonprime;

import java.util.List;

interface RecommendationStrategy {
    List<Video> recommendVideos(User user);
}

class PopularVideosStrategy implements RecommendationStrategy {
    public List<Video> recommendVideos(User user) {
        // Implementation details...
        return null;
    }
}

class PersonalizedVideosStrategy implements RecommendationStrategy {
    public List<Video> recommendVideos(User user) {
        // Implementation details...
        return null;
    }
}
