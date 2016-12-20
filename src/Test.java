import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Vlad on 11.12.2016.
 */
public class Test {


    static final Random random=new Random(System.currentTimeMillis());

    static Runnable makeTask(int N, int time) {
        return () -> {
            System.out.println("Start: " + N);

            try {
                Thread.sleep(time);
                int rnd = random.nextInt(5);
                if(rnd==4){
                    throw new RuntimeException("Oops, I did it again");
                }
            } catch (InterruptedException e) {
                Thread.interrupted();
            }

            System.out.println("End: " + N);
        };
    }



    public static void printConextState(Context context) {
        StringBuilder builder = new StringBuilder("Completed: ")
                               .append(context.getCompletedTaskCount())
                .append(", Failed: ")
						.append(context.getFailedTaskCount())
                .append(", Interrupted: ")
					.append(context.getInterruptedTaskCount());
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {

        Runnable[] tasks = new Runnable[10];
        Random random = new Random();
        for (int i = 0; i < tasks.length; ++i) {
            tasks[i] = makeTask(i, 1000 + random.nextInt(4000));
        }


        ExecutorManagerImpl executorManager = new ExecutorManagerImpl();
        Context context = executorManager.execute(()->{System.out.println("All tasks finished");}, tasks);

        long start = System.currentTimeMillis();
        long current = start;

        while (current - start < 5000) {
            printConextState(context);

            try {
                Thread.sleep(250);
            } catch(InterruptedException e) {
                Thread.interrupted();
            }
            current = System.currentTimeMillis();
        }
        context.interrupt();
        printConextState(context);

    }
}

