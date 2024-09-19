package votingsystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Election {
    private final ConcurrentMap<String, AtomicInteger> voteCounts;
    private final Set<String> voters;

    public Election(List<Candidate> candidates) {
        this.voteCounts = new ConcurrentHashMap<>();
        this.voters = Collections.newSetFromMap(new ConcurrentHashMap<>());

        for (Candidate candidate : candidates) {
            voteCounts.put(candidate.getId(), new AtomicInteger(0));
        }
    }

    public synchronized boolean castVote(User user, String candidateId) {
        if (voters.contains(user.getId())) {
            return false; // User has already voted
        }
        if (voteCounts.containsKey(candidateId)) {
            voteCounts.get(candidateId).incrementAndGet();
            voters.add(user.getId());
            return true;
        }
        return false; // Invalid candidate
    }

    public Map<String, Integer> getResults() {
        Map<String, Integer> results = new HashMap<>();
        for (Map.Entry<String, AtomicInteger> entry : voteCounts.entrySet()) {
            results.put(entry.getKey(), entry.getValue().get());
        }
        return results;
    }
}
