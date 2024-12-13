package org.example.bankingportal.concurrency;

import java.util.Map;
import java.util.concurrent.*;

// not scalable!

public class Memoirs2<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>(); // a map object implementation

    private final Computable<A, V> computable;

    public Memoirs2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override

    public V compute(A a) throws InterruptedException {
        Future<V> future = cache.get(a);
        if (future == null) {  // a check then act sequence.
         Callable<V> callable = new Callable<V>() {
             @Override
             public V call() throws Exception {
                 return computable.compute(a);
             }
         };
            FutureTask<V> futureTask = new FutureTask<>(callable); // a future runnable task
            future = futureTask;
            futureTask.run();

        }
        try{
           return future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


}
