package votingsystem;

import java.util.Map;

public class VotingService {
    private final Election election;
    private final AuthenticationService authenticationService;

    public VotingService(Election election, AuthenticationService authenticationService) {
        this.election = election;
        this.authenticationService = authenticationService;
    }

    public boolean vote(String username, String password, String candidateId) {
        User user = authenticationService.authenticate(username, password);
        if (user != null) {
            return election.castVote(user, candidateId);
        }
        return false; // Authentication failed
    }

    public Map<String, Integer> getResults() {
        return election.getResults();
    }
}
