package meeting;

import java.util.concurrent.DelayQueue;

public class AuditLogManager {
    private final DelayQueue<AuditLog> auditLogs;
    private final int retentionDays;

    private static AuditLogManager instance;

    private AuditLogManager(int retentionDays) {
        this.auditLogs = new DelayQueue<>();
        this.retentionDays = retentionDays;
        new Thread(this::cleanUpOldLogs).start();
    }

    public static synchronized AuditLogManager getInstance(int retentionDays) {
        if (instance == null) {
            instance = new AuditLogManager(retentionDays);
        }
        return instance;
    }

    public void addLog(AuditLog log) {
        auditLogs.offer(log);
    }

    private void cleanUpOldLogs() {
        while (true) {
            try {
                AuditLog log = auditLogs.take();
                System.out.println("Removed old log: " + log.getMeetingId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getRetentionDays() {
        return retentionDays;
    }
}
