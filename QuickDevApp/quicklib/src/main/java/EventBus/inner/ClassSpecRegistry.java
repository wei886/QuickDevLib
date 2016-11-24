package EventBus.inner;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import EventBus.inner.base.MethodSpec;
import EventBus.inner.base.ReflectionUtils;
import EventBus.inner.bus.ReceiveEventsAnn;

public final class ClassSpecRegistry {


    public static MethodSpec<ReceiveEventsAnn>[] getReceiveEventsSpecs(
            Class<?> cls) {
        MethodSpec<ReceiveEventsAnn>[] specs = RECEIVE_EVENTS_SPECS.get(cls);
        if (specs == null) {
            ArrayList<MethodSpec<ReceiveEventsAnn>> list = new ArrayList<MethodSpec<ReceiveEventsAnn>>();
            for (Class<?> cl : ReflectionUtils.buildClassHierarchy(cls)) {
                for (Method method : cl.getDeclaredMethods()) {
                    ReceiveEventsAnn ann = AnnBuilder.getReceiveEventsAnn(method);
                    if (ann != null) {
                        list.add(new MethodSpec<ReceiveEventsAnn>(method, ann));
                    }
                }
            }
            specs = list.toArray(new MethodSpec[list.size()]);
            RECEIVE_EVENTS_SPECS.put(cls, specs);
        }
        return specs;
    }

    private static final ConcurrentHashMap<Class<?>, MethodSpec<ReceiveEventsAnn>[]> RECEIVE_EVENTS_SPECS = new ConcurrentHashMap<Class<?>, MethodSpec<ReceiveEventsAnn>[]>();

}
