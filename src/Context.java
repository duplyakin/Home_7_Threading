/**
 * Created by Vlad on 11.12.2016.
 */
public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    void setCompletedTaskCount(int completedTaskCount);

    void setFailedTaskCount(int failedTaskCount);

    void setInterruptedTaskCount(int interruptedTaskCount);


    boolean isFinished();

    void finish();

    boolean isInterrupted();

    void interrupt();
}
