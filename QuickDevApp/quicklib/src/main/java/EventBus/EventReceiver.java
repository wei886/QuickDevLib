package EventBus;

public interface EventReceiver<T> {

	void onEvent(String name, T data);

}
