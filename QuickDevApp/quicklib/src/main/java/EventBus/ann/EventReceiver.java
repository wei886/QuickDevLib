package EventBus.ann;

public interface EventReceiver<T> {

	void onEvent(String name, T data);

}
