package org.practice.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SharedCounter {

  private static Integer val = 0;
  private static List<Integer> valList =
//      new ArrayList<>();  gives inconsistent data
//      Collections.synchronizedList(new ArrayList<>());
      new CopyOnWriteArrayList <>();


  public static void main(String... Args) {

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    List<Future> objects = new LinkedList<>();

    for (int i = 0; i < 8; i++) {
      objects.add(executorService.submit(SharedCounter::addElement));
    }

    executorService.shutdown();

    if (objects.stream().allMatch(Future::isDone)) {System.out.println(val);}

    boolean check = true;
    while (check) {
      if (objects.stream().allMatch(Future::isDone)) {
        valList.stream().forEach(e ->System.out.print(e+" "));
        check = false;
      }
    }
  }

  public synchronized static void increment() {
    val++;
  }

  public static void addElement() {
    valList.add(4);
  }

}
