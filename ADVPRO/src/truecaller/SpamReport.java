package truecaller;

import java.util.Date;

class SpamReport {
    String id;
    String reporterId;
    String contactId;
    Date reportDate;

    public SpamReport(String id, String reporterId, String contactId) {
        this.id = id;
        this.reporterId = reporterId;
        this.contactId = contactId;
        this.reportDate = new Date();
    }
}
