package pica.nd.com.kotlintest.rx;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by Administrator on 2018/2/8.
 */

public class Observable<T> {


    private Subscriber subscribe;
    private Scheduler schedulerSubscibeOn;
    private Scheduler schedulerObserveOn;
    private Subscriber subscriber;


    protected Observable(OnSubscriber<T> f) {
        this.onSubscriber = f;
    }

    private OnSubscriber onSubscriber;

    public static <T> Observable<T> create(OnSubscriber<T> onSubscriber) {
        return new Observable<T>(onSubscriber);
    }

    public Observable subscribeOn(Scheduler schedulerSubscibeOn) {
        this.schedulerSubscibeOn = schedulerSubscibeOn;
        return this;
    }

    public Observable observeOn(Scheduler schedulerObserveOn) {
        this.schedulerObserveOn = schedulerObserveOn;
        return this;
    }

    public interface OnSubscriber<T> extends Action<Subscriber<? super T>> {
    }

    public OnSubscriber getOnSubscriber() {
        return onSubscriber;
    }

    public final void subscribe(Subscriber subscribe) {
        this.subscribe = subscribe;
        subscribe(subscribe, this);
    }

    Handler mHandlerSubscribe = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (MessageTag.ONNEXT.getWhat() == what) {
                subscriber.onNext(msg.obj);
            } else if (MessageTag.ONCOMPLETE.getWhat() == what) {
                subscriber.onCompleted();
            } else if (MessageTag.ONERROR.getWhat() == what) {
                subscriber.onError((Throwable) msg.obj);
            }
        }
    };

    Handler mHandlerObserver = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if (what == 1) {
                onSubscriber.call(subscriber);
            } else if (what == 2) {
                Subscriber wrapSub = new Subscriber() {
                    @Override
                    public void onCompleted() {
                        ExecutorUtils.INSTANCE().execute(new ExecutorUtils.RunnableEx() {
                            @Override
                            public void run() throws Exception {
                                subscribe.onCompleted();
                            }
                        });
                    }

                    @Override
                    public void onError(final Throwable e) {
                        ExecutorUtils.INSTANCE().execute(new ExecutorUtils.RunnableEx() {
                            @Override
                            public void run() throws Exception {
                                subscribe.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onNext(final Object o) {
                        ExecutorUtils.INSTANCE().execute(new ExecutorUtils.RunnableEx() {
                            @Override
                            public void run() throws Exception {
                                subscribe.onNext(o);
                            }
                        });
                    }
                };
                onSubscriber.call(wrapSub);
                ;

            }
        }
    };

    public void subscribe(final Subscriber subscriber, Observable observable) {
        this.subscriber = subscriber;
        subscriber.onStart();
        final OnSubscriber onSubscriber = observable.getOnSubscriber();

        if (schedulerSubscibeOn == null && schedulerObserveOn == null) {
            onSubscriber.call(subscriber);
        } else if (Scheduler.IO == schedulerObserveOn && (Scheduler.MAIN == schedulerSubscibeOn)) {
            Log.e("wh", "-----------111111--------------");
            ExecutorUtils.INSTANCE().execute(new ExecutorUtils.RunnableEx() {
                @Override
                public void run() throws Exception {
                    Log.e("wh", "-------------------------");
                    getOnSubscriber().call(new Subscriber() {
                        @Override
                        public void onCompleted() {
                            Message message = new Message();
                            message.what = MessageTag.ONCOMPLETE.getWhat();
                            mHandlerSubscribe.sendMessage(message);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Message message = new Message();
                            message.obj = e;
                            message.what = MessageTag.ONERROR.getWhat();
                            mHandlerSubscribe.sendMessage(message);
                        }

                        @Override
                        public void onNext(Object o) {
                            Message message = new Message();
                            message.obj = o;
                            message.what = MessageTag.ONNEXT.getWhat();
                            mHandlerSubscribe.sendMessage(message);
                        }
                    });
                }
            });

        } else if (Scheduler.IO == schedulerObserveOn) {

            ExecutorUtils.INSTANCE().execute(new ExecutorUtils.RunnableEx() {
                @Override
                public void run() throws Exception {
                    onSubscriber.call(subscriber);
                }
            });

        } else if (Scheduler.MAIN == schedulerObserveOn && (Scheduler.IO == schedulerSubscibeOn)) {

            Message message = new Message();
            message.what = 2;
            mHandlerObserver.sendMessage(message);

        } else if (Scheduler.MAIN == schedulerObserveOn) {
            Message message = new Message();
            message.what = 1;
            mHandlerObserver.sendMessage(message);
        } else {
            onSubscriber.call(subscriber);
        }

    }
}
