package votingsystem;

import java.util.*;
import java.util.concurrent.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class VotingSystem {
    private static final Logger LOGGER = Logger.getLogger(VotingSystem.class.getName());
    private final VotingService votingService;
    private final AuthenticationService authenticationService;
    private final Election election;
    private final ExecutorService executorService;
    private final ReentrantLock lock = new ReentrantLock();

    public VotingSystem(int numberOfUsers) {
        this.authenticationService = new AuthenticationService();
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate("1", "Alice"));
        candidates.add(new Candidate("2", "Bob"));
        this.election = new Election(candidates);
        this.votingService = new VotingService(election, authenticationService);
        this.executorService = Executors.newFixedThreadPool(numberOfUsers);
    }

    public boolean registerUser(String id, String username, String password) {
        User user = new User(id, username, password);
        lock.lock();
        try {
            if (authenticationService.register(user)) {
                LOGGER.info("User registered successfully: " + username);
                return true;
            } else {
                LOGGER.warning("User registration failed: " + username);
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean vote(String username, String password, String candidateId) {
        if (votingService.vote(username, password, candidateId)) {
            LOGGER.info("Vote cast successfully by " + username);
            return true;
        } else {
            LOGGER.warning("Vote casting failed for " + username);
            return false;
        }
    }

    public void printResults() {
        Map<String, Integer> results = votingService.getResults();
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            LOGGER.info("Candidate ID: " + entry.getKey() + ", Votes: " + entry.getValue());
        }
    }

    public void startVotingSimulation(int numberOfUsers) {
        List<String> usernames = new ArrayList<>();
        for (int i = 1; i <= numberOfUsers; i++) {
            usernames.add("user" + i);
            registerUser(String.valueOf(i), "user" + i, "pass" + i);
        }

        for (String username : usernames) {
            executorService.submit(() -> {
                Random random = new Random();
                String[] candidates = {"1", "2"};
                int candidateIndex = random.nextInt(candidates.length);
                vote(username, "pass" + username.substring(4), candidates[candidateIndex]);
                try {
                    Thread.sleep(random.nextInt(60000)); // Simulate user voting at random intervals up to 1 minute
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    public void displayResultsRealTime() {
        Runnable displayTask = () -> {
            LOGGER.info("Real-time vote count:");
            printResults();
        };

        ScheduledExecutorService displayExecutorService = Executors.newScheduledThreadPool(1);
        displayExecutorService.scheduleAtFixedRate(displayTask, 0, 5, TimeUnit.SECONDS);
    }

    public void stopVotingSimulation() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) {
        int numberOfUsers = 1000;
        VotingSystem votingSystem = new VotingSystem(numberOfUsers);

        // Start the voting simulation
        votingSystem.startVotingSimulation(numberOfUsers);

        // Display results in real-time
        votingSystem.displayResultsRealTime();

        // Stop the simulation after some time for demonstration purposes
        Runtime.getRuntime().addShutdownHook(new Thread(votingSystem::stopVotingSimulation));


    }
}

