package org.example.task1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankLock {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    private Lock bankLock;

    private Condition sufficientFunds;

    public BankLock(int n, int initialBalance){
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }
    public void lockTransfer(int from, int to, int amount) throws InterruptedException{
        bankLock.lock();
        try {
            while (accounts[from]<amount){
                sufficientFunds.await();
            }
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            sufficientFunds.signalAll();
            if (ntransacts % NTEST == 0)
                test();

        } finally {
            bankLock.unlock();
        }
    }
    public void test(){

        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i] ;
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);



    }

    public int size(){

        return accounts.length;

    }

}

class TransferThreadLock extends Thread {
    private BankLock bank;
    private int fromAccount;
    private int maxAmount;
    private static final int REPS = 1000;
    public TransferThreadLock(BankLock b, int from, int max){
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }
    @Override
    public void run(){

        while (true) {
            for (int i = 0; i < REPS; i++) {
                int toAccount = (int) (bank.size() * Math.random());
                int amount = (int) (maxAmount * Math.random()/REPS);
                try {
                    bank.lockTransfer(fromAccount, toAccount, amount);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
