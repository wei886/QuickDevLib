package EventBus.inner;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import EventBus.ann.ReceiveEvents;
import EventBus.inner.bus.ReceiveEventsAnn;

public final class AnnBuilder {

    static ReceiveEventsAnn getReceiveEventsAnn(Method m) {
        for (Annotation a : m.getDeclaredAnnotations()) {
            Class<?> at = a.annotationType();
            if (ReceiveEvents.class == at) {
                return new ReceiveEventsAnn((ReceiveEvents) a);
            }
        }
        return null;
    }
}