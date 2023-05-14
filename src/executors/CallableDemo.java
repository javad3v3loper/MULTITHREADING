package executors;

import java.util.concurrent.*;

/**
 *  Main difference between Callable and Runnable:
 *  Callable - returns Type, and may throw an Exception
 *  while Runnable - returns nothing and doesn't throw exception
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService es = Executors.newCachedThreadPool();
        Future<Integer> future = es.submit(() -> 1 + 1);

        System.out.println(future.get(10, TimeUnit.SECONDS));

    }

}
