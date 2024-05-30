package Design_Datastructure.ratelimter.versions;

public class Main {
    public static void main(String[] args) {
        AsynchronousRateLimiter rateLimiter = new AsynchronousRateLimiter(1, 1, 2000);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            rateLimiter.submit(() -> {
                System.out.println("Executing task " + finalI + " at " + System.currentTimeMillis());
                return "Task " + finalI + " result";
            }).thenAccept(result -> System.out.println("Result: " + result))
              .exceptionally(ex -> {
                  System.err.println("Task failed: " + ex.getMessage());
                  return null;
              });
        }
    }
}
