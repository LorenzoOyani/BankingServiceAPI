package org.example.bankingportal.concurrency;

import java.util.Map;
import java.util.concurrent.*;

public class Memoirs3<A, V> implements Computable<A, V>{
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private Runnable activeRunnable;
    private final Computable<A, V> computable;

    public Memoirs3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A a) throws InterruptedException {
        // process the future task.
        //in a way it can be run with a FutureTask concrete implementation
        Future<V> future = cache.get(a);
        if (future == null) {
            Callable<V> callable = ()-> computable.compute(a);
            FutureTask<V> futureTasks = new FutureTask<>(callable);

            future = cache.putIfAbsent(a, futureTasks);
            if(future == null) {
                future = futureTasks;
                futureTasks.run();
            }
        }
        try{
           return  future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
