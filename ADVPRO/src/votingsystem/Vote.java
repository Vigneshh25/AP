package votingsystem;

public class Vote {
    private User user;
    private Candidate candidate;

    public Vote(User user, Candidate candidate) {
        this.user = user;
        this.candidate = candidate;
    }

    public User getUser() {
        return user;
    }

    public Candidate getCandidate() {
        return candidate;
    }
}
