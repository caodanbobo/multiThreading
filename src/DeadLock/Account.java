package DeadLock;

/**
 * Created by zhenboliu on 2017/10/21.
 */
public class Account {
    private int balance=10000;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amt) {
        balance+=amt;
    }

    public void withdraw(int amt) {
        balance-=amt;
    }

    public static void transfer(Account a1, Account a2, int amt){
        a1.withdraw(amt);
        a2.deposit(amt);
    }
}
