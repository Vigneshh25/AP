package recommandation;

import java.util.*;
import java.util.stream.Collectors;

// WebCrawlerWithSameHostnameMain class to demonstrate the functionality
public class RecommendationSystem {
    public static void main(String[] args) {
        UserActionHistoryFetcher fetcher = new FacebookActionHistoryFetcher();
        RecommendationEngine engine = new RecommendationEngine(fetcher);
        RecommendationAPI api = new RecommendationAPI(engine);

        String userEmail = "user@example.com";
        List<String> recommendations = api.getRecommendations(userEmail);

        System.out.println("Recommendations for " + userEmail + ": " + recommendations);
    }
}
