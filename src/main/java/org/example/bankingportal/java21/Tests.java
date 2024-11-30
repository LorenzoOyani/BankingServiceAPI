package org.example.bankingportal.java21;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class Tests {

    @Test
    public void loom() throws InterruptedException {

        var list = new ConcurrentSkipListSet<String>();
        var virtualThreads = IntStream.range(0, 100)
                .mapToObj(index -> Thread.ofVirtual().unstarted(
                        () -> {
                            boolean first = list.getFirst() != null;
                            if (first) {
                                list.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            if (first) {
                                list.add(Thread.currentThread().toString());
                            }
                            try{
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )).toList();

        for(Thread thread : virtualThreads) {
            thread.start();
        }

        for(Thread thread : virtualThreads) {
            thread.join();
        }
    }

    public static void main(String[] args) throws IOException {
        var executors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try (var socket = new ServerSocket(9000)) {
            while (true) {
                var clientSocket = socket.accept();
                executors.submit(() -> {
                    try {
                        handleClientRequest(clientSocket);
                    } catch (Exception e) {
                        log.error("fail to handle client request", e);
                    }
                });
            }
        }
    }

    private static void handleClientRequest(Socket clientSocket) throws IOException {
        var next = -1;
        try (var outputByteArray = new ByteArrayOutputStream()) {

            try (var inputBytes = clientSocket.getInputStream()) {
                while (next != inputBytes.read()) {
                    outputByteArray.write(next);
                }
                var inputMessage = inputBytes.toString();
                System.out.println(inputMessage);
            }
        }
    }
}
