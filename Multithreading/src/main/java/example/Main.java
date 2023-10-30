package example;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  private final static Random random = new Random();

  public static void main(String[] args) {
    int workersNumber = random.nextInt(5) + 2;
    System.out.println(workersNumber);

    ExecutorService executorService = Executors.newFixedThreadPool(workersNumber);

    AtomicInteger numberOfP = new AtomicInteger(1);

    long workTime = 30000;
    Thread threadProd = new Thread(() -> {
      try {
        Producer.startWork(workTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });

    threadProd.start();
    while (Producer.workFlag) {
      executorService.submit(() -> {
        if (Producer.contentTest.size() > 0 && Producer.workFlag) {
          try {
            Object lock = new Object();
            synchronized (lock){
              Worker.takeProblem(Thread.currentThread().getName(), Producer.contentTest.poll(), numberOfP.getAndIncrement());
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }
    threadProd.interrupt();
    executorService.shutdown();
  }
}
