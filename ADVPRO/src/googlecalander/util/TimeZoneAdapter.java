package googlecalander.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

// Adapter Pattern for Time Zone Support
public class TimeZoneAdapter {
    private ZoneId timeZone;

    public TimeZoneAdapter(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    public ZonedDateTime convertToTimeZone(LocalDateTime dateTime) {
        return dateTime.atZone(timeZone);
    }
}
