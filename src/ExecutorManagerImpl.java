import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Vlad on 18.12.2016.
 */
public class ExecutorManagerImpl {

    public ExecutorManagerImpl() {
    }

    ExecutorService taskExecutor = Executors.newFixedThreadPool(3);


    public Context execute(final Runnable callback, Runnable... tasks) {
        ArrayList<Future<?>> futureList = new ArrayList<>(tasks.length);
        for(Runnable r:tasks) {
            futureList.add(taskExecutor.submit(r));
        }
        final ContextImpl context = new ContextImpl(futureList.toArray(new Future<?>[futureList.size()]), callback);
        return context;
    }
}
