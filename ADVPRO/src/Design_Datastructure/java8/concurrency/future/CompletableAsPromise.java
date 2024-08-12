package Design_Datastructure.java8.concurrency.future;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableAsPromise {

    public static void main(String[] args) throws Exception {

        Resource resource = new Resource();
        usingIsDone(resource);
        usingNodeJSPromise(resource);
        usingJava8Stream(resource);
    }

    private static void usingIsDone(Resource resource) throws Exception {
        System.out.println("************USING isDONE*****************");

        CompletableFuture<String> promise1 = CompletableFuture.supplyAsync(resource::task1);
        CompletableFuture<String> promise2 = CompletableFuture.supplyAsync(resource::task2);

        CompletableFuture<Void> stringCompletableFuture = CompletableFuture.allOf(promise1, promise2);

        while (!stringCompletableFuture.isDone()) {
        }
        System.out.println(promise1.get());
        System.out.println(promise2.get());
        System.out.println("***************************************");
    }

    private static void usingJava8Stream(Resource resource) {
        System.out.println("***************** Using Stream **********************");

        CompletableFuture<String> promise1 = CompletableFuture.supplyAsync(resource::task1);
        CompletableFuture<String> promise2 = CompletableFuture.supplyAsync(resource::task2);


        String finalResultWithStream = Stream.of(promise1, promise2)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(finalResultWithStream);
        System.out.println("***************************************");
    }

    private static void usingNodeJSPromise(Resource resource) throws Exception {
        System.out.println("*******Using All of which is look like Node.js promise*****");

        CompletableFuture<String> promise1 = CompletableFuture.supplyAsync(resource::task1);
        CompletableFuture<String> promise2 = CompletableFuture.supplyAsync(resource::task2);

        String finalResultPromiseAll = CompletableFuture.allOf(promise1, promise2).thenApply(it -> {
            String firstResult = promise1.join();
            String secondResult = promise2.join();
            return firstResult + "   " + secondResult;
        }).get();
        System.out.println(finalResultPromiseAll);
        System.out.println("***************************************");
    }


    static class Resource {

        public String task1() {
            System.out.println("Resource.Task1-Started");
            System.out.println("Resource.Task1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Resource.Task1-Ended");
            return "Task1 Completed";
        }

        public String task2() {
            System.out.println("Resource.Task2-Started");
            System.out.println("Resource.Task2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Resource.Task2-Ended");
            return "Task2 Completed";
        }
    }
}