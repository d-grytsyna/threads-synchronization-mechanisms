package org.example.task1;

public class BankTest {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;
    public static void main(String[] args) {
//        BankLock b = new BankLock(NACCOUNTS, INITIAL_BALANCE);
        SyncBank b = new SyncBank(NACCOUNTS, INITIAL_BALANCE);

        int i;
        for (i = 0; i < NACCOUNTS; i++){
//            TransferThreadLock t = new TransferThreadLock(b, i,
//                    INITIAL_BALANCE);
            TransferThreadSync t = new TransferThreadSync(b, i, INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start () ;
        }
    }

}

