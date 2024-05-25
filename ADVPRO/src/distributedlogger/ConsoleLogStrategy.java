package distributedlogger;

public class ConsoleLogStrategy implements LogStrategy {
    @Override
    public void log(LogEvent event) {
        System.out.println(event);
    }
}
