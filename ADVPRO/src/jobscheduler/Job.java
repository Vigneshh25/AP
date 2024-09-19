package jobscheduler;

public interface Job {
    String getJobId();
    void execute() throws Exception;  // The job logic that can throw an exception.
}
