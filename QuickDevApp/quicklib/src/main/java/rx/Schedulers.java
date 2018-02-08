package pica.nd.com.kotlintest.rx;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Schedulers {
    public static Scheduler io() {
        return Scheduler.IO;
    }

    public static Scheduler mainThread() {
        return Scheduler.MAIN;
    }

}
