package semaphores;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhenboliu on 2017/10/22.
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor= Executors.newCachedThreadPool();
        for (int i = 0; i <200 ; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
