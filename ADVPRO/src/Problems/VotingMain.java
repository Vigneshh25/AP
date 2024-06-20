package Problems;

import java.util.*;

class Vote {
    private int voterId;
    private int candidateId;
    private long timestamp; // Assuming timestamp in milliseconds

    public Vote(int voterId, int candidateId, long timestamp) {
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.timestamp = timestamp;
    }

    public int getVoterId() {
        return voterId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

class VotingSystem {
    private Map<Integer, PriorityQueue<Vote>> candidateVotes;
    private TreeMap<Long, Integer> currentLeader;

    public VotingSystem() {
        candidateVotes = new HashMap<>();
        currentLeader = new TreeMap<>(Collections.reverseOrder());
    }

    /**
     * Casts a vote for a candidate at a given time.
     * @param voterId The ID of the voter.
     * @param candidateId The ID of the candidate.
     * @param timestamp The timestamp when the vote was cast.
     */
    public void vote(int voterId, int candidateId, long timestamp) {
        Vote vote = new Vote(voterId, candidateId, timestamp);
        
        // Update candidateVotes map
        if (!candidateVotes.containsKey(candidateId)) {
            candidateVotes.put(candidateId, new PriorityQueue<>((a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp())));
        }
        candidateVotes.get(candidateId).offer(vote);
        
        // Update currentLeader map
        if (currentLeader.containsKey(timestamp)) {
            currentLeader.put(timestamp, candidateId);
        } else {
            currentLeader.put(timestamp, candidateId);
        }
    }

    /**
     * Finds the leading candidate at a given time.
     * @param timestamp The timestamp for which to find the leading candidate.
     * @return The ID of the leading candidate at the given timestamp.
     */
    public int getLeadingCandidate(long timestamp) {
        Long closestTime = currentLeader.floorKey(timestamp);
        if (closestTime == null) {
            return -1; // No votes cast before the given timestamp
        }
        return currentLeader.get(closestTime);
    }
}

public class VotingMain {
    public static void main(String[] args) {
        VotingSystem votingSystem = new VotingSystem();

        // Example usage
        votingSystem.vote(1, 101, System.currentTimeMillis()); // Voter 1 votes for Candidate 101
        votingSystem.vote(2, 102, System.currentTimeMillis() + 1000); // Voter 2 votes for Candidate 102 after 1 second
        votingSystem.vote(3, 101, System.currentTimeMillis() + 2000); // Voter 3 votes for Candidate 101 after 2 seconds

        // Get leading candidate at current time
        int leadingCandidate = votingSystem.getLeadingCandidate(System.currentTimeMillis());
        System.out.println("Leading candidate now: " + leadingCandidate);

        // Wait for 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Get leading candidate after 3 seconds
        leadingCandidate = votingSystem.getLeadingCandidate(System.currentTimeMillis());
        System.out.println("Leading candidate after 3 seconds: " + leadingCandidate);
    }
}
