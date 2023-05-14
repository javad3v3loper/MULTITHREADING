package executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>(Arrays.asList(
                () -> "task #1",
                () -> "task #2",
                () -> "task #3"
        ));

        List<Future<String>> futures = es.invokeAll(tasks);

        futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "";
                })
                .forEach(System.out::println);
    }
}
