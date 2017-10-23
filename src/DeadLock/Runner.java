package DeadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhenboliu on 2017/10/21.
 */
public class Runner {
    Account acc1=new Account();
    Account acc2=new Account();
    private Lock lock1= new ReentrantLock();
    private Lock lock2= new ReentrantLock();

    private void acquireLocks(Lock l1, Lock l2){
        while(true){
            boolean getL1=l1.tryLock();
            boolean getL2=l2.tryLock();

            if(getL1&&getL2){
                return;
            }
            if(getL1){
                l1.unlock();
            }if(getL2){
                l2.unlock();
            }

        }
    }
    public void firstThread() throws InterruptedException{
        Random randow=new Random();
        acquireLocks(lock1,lock2);
        for (int i = 0; i <10000 ; i++) {
            Account.transfer(acc1,acc2,randow.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void secondThread()throws InterruptedException{
        Random randow=new Random();
        acquireLocks(lock2,lock1);
        for (int i = 0; i <10000 ; i++) {
            Account.transfer(acc2,acc1,randow.nextInt(100));
        }
        lock1.unlock();
        lock2.unlock();
    }
    public void finished(){
        System.out.println("The balance of Accoun1: "+acc1.getBalance());
        System.out.println("The balance of Accoun1: "+acc2.getBalance());
        System.out.println("The total balance: "+(acc1.getBalance()+acc2.getBalance()));
    }
}
