package Problems;

import java.util.*;

public class FileSharingSystem {
    private int nextUserId;
    private Map<Integer, Set<Integer>> chunkOwnership;

    public FileSharingSystem() {
        nextUserId = 1;
        chunkOwnership = new HashMap<>();
    }

    /**
     * Join the system and get a unique user ID.
     * @return The unique user ID assigned.
     */
    public int join() {
        int userId = nextUserId++;
        return userId;
    }

    /**
     * Leave the system and release the user ID for reuse.
     * @param userId The user ID to be released.
     */
    public void leave(int userId) {
        // Remove the user's ownership of all chunks
        for (Set<Integer> chunks : chunkOwnership.values()) {
            chunks.remove(userId);
        }
    }

    /**
     * Share a chunk of the file with a user.
     * @param userId The user who owns the chunk.
     * @param chunkId The ID of the chunk to share.
     */
    public void share(int userId, int chunkId) {
        chunkOwnership.putIfAbsent(chunkId, new HashSet<>());
        chunkOwnership.get(chunkId).add(userId);
    }

    /**
     * Request a chunk of the file and get the IDs of users who own it.
     * @param chunkId The ID of the chunk to request.
     * @return A list of user IDs who own the requested chunk.
     */
    public List<Integer> request(int chunkId) {
        return new ArrayList<>(chunkOwnership.getOrDefault(chunkId, Collections.emptySet()));
    }

    public static void main(String[] args) {
        FileSharingSystem fileSharingSystem = new FileSharingSystem();
        
        int user1 = fileSharingSystem.join();
        int user2 = fileSharingSystem.join();
        int user3 = fileSharingSystem.join();
        
        fileSharingSystem.share(user1, 1);
        fileSharingSystem.share(user2, 1);
        
        List<Integer> owners = fileSharingSystem.request(1);
        System.out.println("Owners of chunk 1: " + owners); // [1, 2]
        
        fileSharingSystem.leave(user1);
        
        owners = fileSharingSystem.request(1);
        System.out.println("Owners of chunk 1 after user1 leaves: " + owners); // [2]
    }
}
