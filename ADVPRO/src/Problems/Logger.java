package Problems;

import java.util.HashMap;
import java.util.Map;

public class Logger {
    private Map<String, Integer> messageTimestampMap;

    public Logger() {
        messageTimestampMap = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     *
     * @param timestamp The current timestamp.
     * @param message The message to be logged.
     * @return boolean indicating whether the message should be printed.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messageTimestampMap.containsKey(message) || timestamp - messageTimestampMap.get(message) >= 10) {
            messageTimestampMap.put(message, timestamp);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Logger logger = new Logger();

        // logging string "foo" at timestamp 1
        System.out.println(logger.shouldPrintMessage(1, "foo")); // returns true

        // logging string "bar" at timestamp 2
        System.out.println(logger.shouldPrintMessage(2, "bar")); // returns true

        // logging string "foo" at timestamp 3
        System.out.println(logger.shouldPrintMessage(3, "foo")); // returns false

        // logging string "bar" at timestamp 8
        System.out.println(logger.shouldPrintMessage(8, "bar")); // returns false

        // logging string "foo" at timestamp 10
        System.out.println(logger.shouldPrintMessage(10, "foo")); // returns false

        // logging string "foo" at timestamp 11
        System.out.println(logger.shouldPrintMessage(11, "foo")); // returns true
    }
}
