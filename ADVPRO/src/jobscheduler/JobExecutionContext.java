package jobscheduler;

public class JobExecutionContext {
    private final String jobId;
    private final long startTime;
    private final long endTime;
    private final boolean success;
    private final Exception error;

    public JobExecutionContext(String jobId, long startTime, long endTime, boolean success, Exception error) {
        this.jobId = jobId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.success = success;
        this.error = error;
    }

    // Getters for logging
    public String getJobId() { return jobId; }
    public long getStartTime() { return startTime; }
    public long getEndTime() { return endTime; }
    public boolean isSuccess() { return success; }
    public Exception getError() { return error; }
}
