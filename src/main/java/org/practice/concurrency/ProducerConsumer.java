package org.practice.concurrency;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {

  public static void main(String... Args) {

    ExecutorService producerExecutorService = Executors.newFixedThreadPool(1);

    ExecutorService consumerExecutorService = Executors.newFixedThreadPool(1);

    LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

    LinkedList<Future> futures = new LinkedList<>();
    for (int i = 0; i < 5; i++) {
      int finalI = i;
      futures.add(producerExecutorService.submit(() -> {
        try {
          queue.offer(finalI + "", 10, TimeUnit.SECONDS); // this will wait for 10 sec if queue is full for the next element to dequeue
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("producer: "+finalI);
      }));
    }


    for (int i = 0; i < 5; i++) {
      futures.add(consumerExecutorService.submit(() -> {
        try {
          System.out.println("consumer " + queue.take());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }));
    }

    producerExecutorService.shutdown();
    consumerExecutorService.shutdown();
//
//    futures.forEach(future -> {
//      try {
//        future.get();
//      } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//      } catch (ExecutionException e) {
//        throw new RuntimeException(e);
//      }
//    });
//    queue.forEach(System.out::println);
  }

}
