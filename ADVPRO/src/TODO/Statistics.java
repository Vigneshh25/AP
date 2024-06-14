package TODO;

public class Statistics {
    private final long addedTasks;
    private final long completedTasks;
    private final long missedDeadlines;

    public Statistics(long addedTasks, long completedTasks, long missedDeadlines) {
        this.addedTasks = addedTasks;
        this.completedTasks = completedTasks;
        this.missedDeadlines = missedDeadlines;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "addedTasks=" + addedTasks +
                ", completedTasks=" + completedTasks +
                ", missedDeadlines=" + missedDeadlines +
                '}';
    }
}
