

/**
 * Created by Vlad on 11.12.2016.
 */
public class ContextImpl implements Context {

    int completedTaskCount = 0;
    int failedTaskCount = 0;
    int interruptedTaskCount = 0;
    boolean isFinished = false;
    boolean isInterrupted = false;


    @Override
    public synchronized int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public synchronized int getFailedTaskCount() {
        return failedTaskCount;
    }


    @Override
    public synchronized int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    public synchronized void setCompletedTaskCount(int completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public synchronized void setFailedTaskCount(int failedTaskCount) {
        this.failedTaskCount = failedTaskCount;
    }

    public synchronized void setInterruptedTaskCount(int interruptedTaskCount) {
        this.interruptedTaskCount = interruptedTaskCount;
    }

    @Override
    public synchronized void finish() {

    }

    @Override
    public synchronized void interrupt() {
        isInterrupted = true;
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
