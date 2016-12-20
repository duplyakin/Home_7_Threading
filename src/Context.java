/**
 * Created by Vlad on 11.12.2016.
 */
public interface Context {
    int getCompletedTaskCount();

    int getFailedTaskCount();

    int getInterruptedTaskCount();

    boolean isFinished();

    boolean isInterrupted();

    void interrupt();
}
