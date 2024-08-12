package Design_Datastructure.java8.concurrency.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class AdvancedFutureDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit a simple Callable task
        Future<Integer> future = executor.submit(() -> {
            // Simulating a long-running task
            Thread.sleep(2000);
            return 42;
        });

        // Polling Future for completion
        try {
            while (!future.isDone()) {
                System.out.println("Task is still running...");
                Thread.sleep(500); // Check every 500ms
            }
            Integer result = future.get(); // Retrieve the result
            System.out.println("Task completed with result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Handling timeouts
        try {
            Integer timeoutResult = future.get(1, TimeUnit.SECONDS);
            System.out.println("Task completed within timeout with result: " + timeoutResult);
        } catch (TimeoutException e) {
            System.out.println("Task did not complete within the timeout period.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Canceling the task
        Future<?> cancellableFuture = executor.submit(() -> {
            try {
                while (true) {
                    System.out.println("Running cancellable task...");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted.");
            }
        });

        try {
            Thread.sleep(2000); // Let the task run for a while
            cancellableFuture.cancel(true); // Attempt to cancel the task
            if (cancellableFuture.isCancelled()) {
                System.out.println("Task was successfully canceled.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // CompletableFuture with chaining and exception handling
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Starting a long-running task...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task interrupted", e);
            }
            return 42;
        }, executor).thenApply(result -> {
            System.out.println("First stage completed with result: " + result);
            return result * 2;
        }).thenApplyAsync(result -> {
            System.out.println("Second stage completed with result: " + result);
            return result + 10;
        }).exceptionally(ex -> {
            System.err.println("An error occurred: " + ex.getMessage());
            return 0;
        });

        try {
            Integer finalResult = completableFuture.get();
            System.out.println("Final result: " + finalResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Combining multiple CompletableFutures
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10, executor);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20, executor);

        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, Integer::sum);
        try {
            Integer combinedResult = combinedFuture.get();
            System.out.println("Combined result: " + combinedResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Run multiple tasks and wait for all of them to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2);
        allOf.thenRun(() -> System.out.println("All tasks completed"));

        // Run multiple tasks and wait for any one of them to complete
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2);
        anyOf.thenAccept(result -> System.out.println("One of the tasks completed with result: " + result));

        // Properly shutdown the executor service
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
