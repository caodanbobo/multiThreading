package countDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhenboliu on 2017/10/19.
 */
class Processor implements Runnable{
    private CountDownLatch latch;

    public  Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
public class App {
    public static void main(String[] args) {
        CountDownLatch latch=new CountDownLatch(5);
        ExecutorService executor= Executors.newFixedThreadPool(2);
        for (int i = 0; i <6 ; i++) {
            executor.submit(new Processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed.");
    }
}
