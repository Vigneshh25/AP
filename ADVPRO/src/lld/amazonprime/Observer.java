package lld.amazonprime;

interface Observer {
    void update(String message);
}

class User implements Observer {
    private String userId;

    public User(String userId, VideoNotifier videoNotifier) {
        this.userId = userId;
        videoNotifier.addObserver(this);
    }

    public String getId() {
        return userId;
    }

    public void update(String message) {
        System.out.println("User " + userId + " received update: " + message);
    }
}
