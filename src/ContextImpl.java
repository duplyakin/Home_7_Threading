

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
    public int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }


    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount;
    }

    public void setCompletedTaskCount(int completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public void setFailedTaskCount(int failedTaskCount) {
        this.failedTaskCount = failedTaskCount;
    }

    public void setInterruptedTaskCount(int interruptedTaskCount) {
        this.interruptedTaskCount = interruptedTaskCount;
    }

    @Override
    public void finish() {

    }

    @Override
    public void interrupt() {
        isInterrupted = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public boolean isInterrupted() {
        return isInterrupted;
    }
}
