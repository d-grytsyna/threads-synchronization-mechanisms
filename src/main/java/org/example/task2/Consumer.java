package org.example.task2;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;

    private static int array_size = 5;
    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (Integer number = drop.take();
           number!=array_size;
             number = drop.take()) {
            System.out.format("NUMBER RECEIVED: %s%n", number);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {}
        }
        System.out.println("CONSUMER STOPPED");
    }
}