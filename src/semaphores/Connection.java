package semaphores;

import java.util.concurrent.Semaphore;

/**
 * Created by zhenboliu on 2017/10/22.
 */
public class Connection {
    private static Connection connection=null;
    private int connections=0;
    private Semaphore sem=new Semaphore(10);
    private Connection() {
    }
    public static synchronized Connection getInstance(){
        if(connection==null){
            connection=new Connection();
        }
        return connection;
    }
    public void connect(){
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{//incase there are exceptions preventing sem being released.
            doConnect();
        }finally {
            sem.release();
        }
    }

    private void doConnect(){

        synchronized (this){
            connections++;
            System.out.println("Current connections: "+connections);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            connections--;
        }
    }
}
