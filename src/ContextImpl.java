import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by Vlad on 11.12.2016.
 */
public class ContextImpl implements Context {

    final int taskCount;
    final Future<?>[] futures;



    boolean isFinished = false;
    boolean isInterrupted = false;


    ContextImpl(Future<?>[]futures ,final Runnable callback){

        taskCount=futures.length;
        this.futures=futures;

        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean completed;
                do {
                     completed=true;
                     for(Future f: futures){
                         if(!f.isDone()){
                             completed=false;
                         }
                     }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(!completed);
                setFinished(true);
                callback.run();
            }
        }).start();
    }


    @Override
    public synchronized int getCompletedTaskCount() {
        int cnt = 0;
        for(Future future:futures){
            if(future.isDone()&&!future.isCancelled()){
                try{
                    future.get();
                    cnt++;
                } catch (InterruptedException|ExecutionException e) {

                } catch(CancellationException e1){

                }
            }
        }
        return cnt;
    }

    @Override
    public synchronized int getFailedTaskCount() {
        int cnt = 0;
        for(Future future:futures){
            if(future.isDone()){
                try{
                    future.get();
                } catch (InterruptedException|ExecutionException e) {
                    cnt++;
                } catch(CancellationException e1){

                }
            }
        }
        return cnt;
    }


    @Override
    public synchronized int getInterruptedTaskCount() {
        int cnt=0;
        for(Future future:futures){
            if(future.isCancelled()){
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public synchronized void interrupt() {
        for(Future<?> future:futures){
            future.cancel(true);
        }
        isInterrupted=true;
    }


    private synchronized void setFinished(boolean finished) {
        isFinished=finished;
    }

    @Override
    public synchronized boolean isFinished() {
        return isFinished;
    }

    @Override
    public synchronized boolean isInterrupted() {
        return isInterrupted;
    }
}
