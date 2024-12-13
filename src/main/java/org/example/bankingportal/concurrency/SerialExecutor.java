package org.example.bankingportal.concurrency;

import io.micrometer.common.lang.NonNullApi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

@NonNullApi
public class SerialExecutor implements Executor {
    private final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(16);

    private Executor executor;

    private Runnable activeRunnable;
    @Override
    public synchronized void execute(Runnable command) {
        try {
            workQueue.add(() -> {
                command.run();
            });
        } finally {
            scheduleNextTask();
        }
        if(activeRunnable == null) {
            scheduleNextTask();
        }
    }

    private synchronized void scheduleNextTask() {
        if((activeRunnable = workQueue.poll()) !=null) {
            executor.execute(activeRunnable);
        }
    }
}
