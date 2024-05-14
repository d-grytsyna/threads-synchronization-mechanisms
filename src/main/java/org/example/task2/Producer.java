package org.example.task2;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    private static int array_size = 5;
    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Integer importantInfo[] = new Integer[array_size];
        Random random = new Random();
        for(int i=0; i<importantInfo.length; i++){
            importantInfo[i] = i;
        }

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {}
        }
        drop.put(array_size);

        System.out.println("PRODUCER STOPPED");

    }
}