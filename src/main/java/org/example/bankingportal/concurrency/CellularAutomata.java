package org.example.bankingportal.concurrency;

import reactor.core.scheduler.Scheduler;

import java.util.concurrent.CyclicBarrier;

public class CellularAutomata {
    private final Board  board;

    private final CyclicBarrier barrier;

    private final Scheduler.Worker[] workers;

    public CellularAutomata(Board board, CyclicBarrier barrier, Scheduler.Worker[] workers) {
        this.board = board;
        this.barrier = barrier;
        this.workers = workers;
    }
}
