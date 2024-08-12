package Design_Datastructure.java8.concurrency.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureASync {

    public static void main(String[] args) throws Exception {

        Resource resource = new Resource();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(resource::task1)
                .thenApplyAsync(resource::task2)
                .thenApplyAsync(resource::task3)
                .thenApplyAsync(resource::task4)
                .thenApplyAsync(resource::task5);


        stringCompletableFuture.get();

    }

    static class Resource {

        public String task1() {
            System.out.println("Resource.Task1-Started");
            System.out.println("Resource.Task1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Resource.Task1-Ended");
            return "Task1 Completed";
        }


        public String task2(String future) {
            System.out.println("Resource.Task2-Started");

            System.out.println(future + "\nResource.Task2-Ended");
            return "Task2 Completed";
        }

        public String task3(String future) {
            System.out.println("Resource.Task3-Started");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(future + "\nResource.Task3-Ended");
            return "Task3 Completed";
        }


        public String task4(String future) {
            System.out.println("Resource.Task4-Started");

            System.out.println(future + "\nResource.Task4-Ended");
            return "Task4 Completed";
        }

        public String task5(String future) {
            System.out.println("Resource.Task5-Started");

            System.out.println(future + "\nResource.Task5-Ended");
            return "Task5 Completed";
        }
    }
}