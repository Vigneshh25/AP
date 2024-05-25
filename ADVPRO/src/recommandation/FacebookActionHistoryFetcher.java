package recommandation;

import java.util.Arrays;
import java.util.List;

// Mock implementation of UserActionHistoryFetcher for Facebook
class FacebookActionHistoryFetcher implements UserActionHistoryFetcher {
    @Override
    public List<Action> fetchUserActions(String email) {
        // Mock data - replace with actual API calls
        return Arrays.asList(
                new Action("like", "sports"),
                new Action("comment", "electronics"),
                new Action("save", "books")
        );
    }
}
