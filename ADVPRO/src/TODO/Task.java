package TODO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String description;
    private LocalDateTime deadline;
    private List<String> tags;
    private boolean isCompleted;

    public Task(String description, LocalDateTime deadline, List<String> tags) {
        this.id = idCounter.incrementAndGet();
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task{id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", tags=" + tags +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
