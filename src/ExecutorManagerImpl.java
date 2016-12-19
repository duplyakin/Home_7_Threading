/**
 * Created by Vlad on 18.12.2016.
 */
public class ExecutorManagerImpl {

    public ExecutorManagerImpl() {
    }

    Context execute(final Runnable callback, Runnable... tasks) {
        final ContextImpl context = new ContextImpl();

        int index = 0;
        for (final Runnable task : tasks) {
            ++index;
            if (context.isInterrupted()) {
                context.setInterruptedTaskCount(tasks.length - index + 1);
                if (index == 1) {
                    context.finish(); // все задачи отменены, ни одна не началась
                }
                break;   // leave for-loop
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        int failedTaskCount = context.getFailedTaskCount();
                        context.setFailedTaskCount(failedTaskCount);
                        return;
                    }
                    int completedTaskCount = context.getCompletedTaskCount();
                    context.setFailedTaskCount(completedTaskCount);
                    if (tasks.length == context.getCompletedTaskCount()) {
                        context.finish(); // все задачи выполнены
                    }
                    if (tasks.length - context.getInterruptedTaskCount() == context.getCompletedTaskCount()) {
                        callback.run();
                    }
                }
            });
            thread.start();
        }
        return context;
    }
}
