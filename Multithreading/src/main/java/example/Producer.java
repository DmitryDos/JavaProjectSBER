package example;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer {
  private final static Random random = new Random();
  private static long startTime;
  public static boolean workFlag = true;

  public static BlockingQueue<Integer> contentTest = new LinkedBlockingQueue<>();
  public static AtomicInteger numberOfProblems = new AtomicInteger(1);
  public static void startWork(long workTime) throws InterruptedException {
    startTime = System.currentTimeMillis();
    addWork(workTime);
    workFlag = false;
  }

  public synchronized static void addWork(long workTime) throws InterruptedException {
    while(System.currentTimeMillis() - startTime < workTime){
      long deltaTime = System.currentTimeMillis() - startTime;
      System.out.println("CurrentTime: " + deltaTime);
      int time = random.nextInt(2900) + 100;
      System.out.println("Add work number " + numberOfProblems.get() + " on " + time + " millis");
      contentTest.add(time);
      numberOfProblems.getAndIncrement();

      Thread.sleep(1000);
    }
    System.out.println("TimeOut");
  }
}
