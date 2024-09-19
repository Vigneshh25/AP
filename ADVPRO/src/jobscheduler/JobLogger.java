package jobscheduler;

import java.util.logging.Logger;

public class JobLogger {
    private final Logger logger = Logger.getLogger(JobLogger.class.getName());

    public void log(JobExecutionContext context) {
        String logMessage = String.format(
                "Job ID: %s, Start Time: %d, End Time: %d, Success: %b, Error: %s",
                context.getJobId(),
                context.getStartTime(),
                context.getEndTime(),
                context.isSuccess(),
                context.getError() != null ? context.getError().getMessage() : "None"
        );
        logger.info(logMessage);
    }
}
