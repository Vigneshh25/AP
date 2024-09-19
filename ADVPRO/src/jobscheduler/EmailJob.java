package jobscheduler;

public class EmailJob implements Job {
    private final String jobId;

    public EmailJob() {
        this.jobId = "EmailJob_" + System.currentTimeMillis();
    }

    @Override
    public String getJobId() {
        return jobId;
    }

    @Override
    public void execute() {
        // Simulate sending an email
        System.out.println("Sending email...");
    }
}
