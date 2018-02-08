package pica.nd.com.kotlintest.rx;

/**
 * Created by Administrator on 2018/2/8.
 */

public interface Action<T> {
    void call(T t);
}
