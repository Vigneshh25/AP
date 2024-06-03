package lld.awslambda;

import java.util.concurrent.TimeUnit;

// Observer Pattern
interface TimeoutObserver {
    void onTimeout();
}

class TimeoutFunction implements Functio, Runnable {
    private final long timeout;
    private final TimeUnit unit;
    private TimeoutObserver observer;

    public TimeoutFunction(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
    }

    public void setObserver(TimeoutObserver observer) {
        this.observer = observer;
    }

    @Override
    public void execute() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(unit.toMillis(timeout));
            observer.onTimeout();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Example usage
public class MainTimeoutObserver {
    public static void main(String[] args) {
        TimeoutFunction function = new TimeoutFunction(1, TimeUnit.SECONDS);
        function.setObserver(() -> System.out.println("Function execution timed out"));
        function.execute();
    }
}
