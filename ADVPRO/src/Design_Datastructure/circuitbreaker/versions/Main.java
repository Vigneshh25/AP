package Design_Datastructure.circuitbreaker.versions;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = new CircuitBreaker(2, 5000, 3);

        for (int i = 0; i < 10; i++) {
            try {
                if (circuitBreaker.allowRequest()) {
                    String result = executeRemoteService();
                    circuitBreaker.recordSuccess();
                    System.out.println("Service call successful: " + result);
                } else {
                    System.out.println("Service call blocked by circuit breaker.");
                }
            } catch (Exception e) {
                circuitBreaker.recordFailure();
                System.out.println("Service call failed.");
            }
        }
    }

    private static String executeRemoteService() throws Exception {
        // Simulate a remote service call
        if (new Random().nextInt(4) == 0) {
            throw new Exception("Service failure");
        }
        return "Service response";
    }
}
