package Problems;

import java.util.*;

class Tweet {
    private int tweetId;
    private String content;
    private long timestamp;

    public Tweet(int tweetId, String content, long timestamp) {
        this.tweetId = tweetId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getTweetId() {
        return tweetId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

class User {
    private int userId;
    private Set<Integer> followedUsers;
    private List<Tweet> tweets;

    public User(int userId) {
        this.userId = userId;
        this.followedUsers = new HashSet<>();
        this.tweets = new ArrayList<>();
        // Initially, every user follows themselves
        this.follow(userId);
    }

    public int getUserId() {
        return userId;
    }

    public void postTweet(int tweetId, String content, long timestamp) {
        Tweet tweet = new Tweet(tweetId, content, timestamp);
        tweets.add(tweet);
    }

    public void follow(int userId) {
        followedUsers.add(userId);
    }

    public void unfollow(int userId) {
        // Users cannot unfollow themselves
        if (userId != this.userId) {
            followedUsers.remove(userId);
        }
    }

    public List<Tweet> getNewsFeed() {
        List<Tweet> newsFeed = new ArrayList<>();
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> Long.compare(b.getTimestamp(), a.getTimestamp()));

        // Add user's own tweets to the priority queue
        for (Tweet tweet : tweets) {
            pq.offer(tweet);
        }

        // Add followed users' tweets to the priority queue
        for (int followedUserId : followedUsers) {
            User followedUser = Twitter.getUser(followedUserId);
            if (followedUser != null) {
                for (Tweet tweet : followedUser.tweets) {
                    pq.offer(tweet);
                }
            }
        }

        // Retrieve the top 10 tweets from the priority queue
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            newsFeed.add(pq.poll());
            count++;
        }

        return newsFeed;
    }
}

public class Twitter {
    private static Map<Integer, User> userMap;
    private static int tweetId;

    public Twitter() {
        userMap = new HashMap<>();
        tweetId = 0;
    }

    public static User getUser(int userId) {
        return userMap.get(userId);
    }

    public void postTweet(int userId, String content) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        user.postTweet(tweetId++, content, System.currentTimeMillis());
    }

    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        User follower = userMap.get(followerId);
        follower.follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User follower = userMap.get(followerId);
            follower.unfollow(followeeId);
        }
    }

    public List<Tweet> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)) {
            return new ArrayList<>();
        }
        User user = userMap.get(userId);
        return user.getNewsFeed();
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        // User 1 posts tweets
        twitter.postTweet(1, "Hello, Twitter!");
        twitter.postTweet(1, "This is my first tweet.");

        // User 2 posts tweets
        twitter.postTweet(2, "Just joined Twitter!");
        twitter.postTweet(2, "Excited to tweet.");

        // User 1 follows User 2
        twitter.follow(1, 2);

        // User 2 posts more tweets
        twitter.postTweet(2, "My third tweet.");

        // User 1's news feed
        System.out.println("User 1's news feed:");
        List<Tweet> newsFeed1 = twitter.getNewsFeed(1);
        for (Tweet tweet : newsFeed1) {
            System.out.println(tweet.getContent());
        }

        // User 2's news feed
        System.out.println("\nUser 2's news feed:");
        List<Tweet> newsFeed2 = twitter.getNewsFeed(2);
        for (Tweet tweet : newsFeed2) {
            System.out.println(tweet.getContent());
        }

        // User 1 unfollows User 2
        twitter.unfollow(1, 2);

        // User 1's news feed after unfollowing
        System.out.println("\nUser 1's news feed after unfollowing:");
        List<Tweet> newsFeed1AfterUnfollow = twitter.getNewsFeed(1);
        for (Tweet tweet : newsFeed1AfterUnfollow) {
            System.out.println(tweet.getContent());
        }
    }
}
