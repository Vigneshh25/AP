package recommandation;

import java.util.List;

// API Endpoint for recommendations
class RecommendationAPI {
    private RecommendationEngine recommendationEngine;

    public RecommendationAPI(RecommendationEngine recommendationEngine) {
        this.recommendationEngine = recommendationEngine;
    }

    public List<String> getRecommendations(String email) {
        return recommendationEngine.generateRecommendations(email);
    }
}
