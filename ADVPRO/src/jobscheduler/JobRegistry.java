package jobscheduler;

import java.util.HashMap;
import java.util.Map;

public class JobRegistry {
    private final Map<String, Class<? extends Job>> jobTypes = new HashMap<>();

    public void registerJobType(String jobTypeName, Class<? extends Job> jobClass) {
        jobTypes.put(jobTypeName, jobClass);
    }

    public Job createJob(String jobTypeName) throws Exception {
        if (!jobTypes.containsKey(jobTypeName)) {
            throw new IllegalArgumentException("No such job type registered: " + jobTypeName);
        }
        return jobTypes.get(jobTypeName).getDeclaredConstructor().newInstance();
    }
}
