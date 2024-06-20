package Problems;

import java.util.ArrayList;
import java.util.List;

public class BrowserHistory {
    private List<String> history;
    private int currentIndex;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
    }

    public void visit(String url) {
        // Clear forward history
        history = history.subList(0, currentIndex + 1);
        history.add(url);
        currentIndex++;
    }

    public String back(int steps) {
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }

    public String forward(int steps) {
        currentIndex = Math.min(history.size() - 1, currentIndex + steps);
        return history.get(currentIndex);
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("example.com");
        browserHistory.visit("google.com"); // You are in "example.com". Visit "google.com"
        browserHistory.visit("facebook.com"); // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com"); // You are in "facebook.com". Visit "youtube.com"

        System.out.println(browserHistory.back(1)); // Move back to "facebook.com". Return "facebook.com"
        System.out.println(browserHistory.back(1)); // Move back to "google.com". Return "google.com"
        System.out.println(browserHistory.forward(1)); // Move forward to "facebook.com". Return "facebook.com"

        browserHistory.visit("linkedin.com"); // Visit "linkedin.com" from "facebook.com"
        System.out.println(browserHistory.forward(2)); // Can't move forward any steps. Return "linkedin.com"
        System.out.println(browserHistory.back(2)); // Move back two steps to "google.com". Return "google.com"
        System.out.println(browserHistory.back(7)); // Move back to "example.com". Return "example.com"
    }
}
