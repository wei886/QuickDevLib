package pica.nd.com.kotlintest.rx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/2/8.
 */

public class ExecutorUtils {

    private ExecutorUtils() {

    }

    private static ExecutorUtils instance;

    public static ExecutorUtils INSTANCE() {
        if (instance == null) {
            instance = new ExecutorUtils();
        }
        return instance;
    }

    ExecutorService executorService;

    private ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }
        return executorService;
    }

    public void execute(final RunnableEx runnableEx) {
        getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnableEx.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface RunnableEx {
        void run() throws Exception;
    }
}
