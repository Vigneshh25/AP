package designpatterns.creational.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Thread-Safe with Double-Checked Locking
public class LazyInitialization {
    private static volatile LazyInitialization instance;

    private LazyInitialization() {
    }

    public static synchronized LazyInitialization getInstance() {
        if (instance == null) {
            synchronized (instance) {
                instance = new LazyInitialization();
            }
        }
        return instance;
    }

    public static LazyInitialization getInstance1() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(LazyInitialization::getInstance);
        service.submit(LazyInitialization::getInstance);
        service.shutdown();
    }

    static class Holder {
        //Bill Pugh Method
        static final LazyInitialization INSTANCE = new LazyInitialization();
    }
}
