package recommandation;

import java.util.List;

// Interface for fetching user action history
interface UserActionHistoryFetcher {
    List<Action> fetchUserActions(String email);
}
