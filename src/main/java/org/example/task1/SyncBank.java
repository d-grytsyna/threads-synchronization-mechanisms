package org.example.task1;


class SyncBank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    public SyncBank(int n, int initialBalance){
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }


    public void transfer(int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test();
    }
    public synchronized void syncTransfer(int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test();
    }
    public synchronized void syncWaitTransfer(int from, int to, int amount) {
        while (accounts[from]<amount){
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        notifyAll();
        if (ntransacts % NTEST == 0)
            test();
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
class TransferThreadSync extends Thread {
    private SyncBank bank;
    private int fromAccount;
    private int maxAmount;
    private static final int REPS = 1000;
    public TransferThreadSync(SyncBank b, int from, int max){
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
                    bank.syncWaitTransfer(fromAccount, toAccount, amount);
                }catch (Exception e){}



            }
        }


    }
}