package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceTerminationDemo {
    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        IntStream.range(0, 10).forEach((i) -> {
            es.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(i);
                    System.out.println("Task #" + i + " is completed");
                } catch (InterruptedException e) {
                    System.out.println("Task #" + i + " is interrupted");
                }
            });
        });

        // Stop to accept new tasks, and shutdown es after already submitted
        // tasks are executed;
        System.out.println("Shutting down");
        es.shutdown();

        try {
            es.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            es.shutdownNow();
        }
    }
}
