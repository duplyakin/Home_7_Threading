

/**
 * Created by Vlad on 11.12.2016.
 */
public class ContextImpl implements Context {
    @Override
    public int getCompletedTaskCount() {
        return 0;
    }

    @Override
    public int getFailedTaskCount() {
        return 0;
    }

    @Override
    public int getInterruptedTaskCount() {
        return 0;
    }

    @Override
    public void interrupt() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
