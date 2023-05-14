package deadlock;

import java.util.concurrent.TimeUnit;

public class DeadlockDemo {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Lock1 is captured");
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
                synchronized (lock2) {
                    System.out.println("This block will never be executed");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Lock2 is captured");
                synchronized (lock1) {
                    System.out.println("This block will never be executed");
                }
            }
        }).start();
    }
}

