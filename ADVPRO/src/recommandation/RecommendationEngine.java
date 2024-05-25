package recommandation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Recommendation Engine
class RecommendationEngine {
    private UserActionHistoryFetcher actionHistoryFetcher;

    public RecommendationEngine(UserActionHistoryFetcher actionHistoryFetcher) {
        this.actionHistoryFetcher = actionHistoryFetcher;
    }

    public List<String> generateRecommendations(String email) {
        List<Action> actions = actionHistoryFetcher.fetchUserActions(email);
        Map<String, Long> interestCount = actions.stream()
                .collect(Collectors.groupingBy(Action::getContent, Collectors.counting()));

        return interestCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
