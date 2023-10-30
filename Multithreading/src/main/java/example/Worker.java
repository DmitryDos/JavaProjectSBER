package example;

public class Worker {
  public static void takeProblem(String name, long problem, int numberOfWork) throws InterruptedException {

    System.out.println("Worker " + name + " take problem " + numberOfWork + " on " + problem + " millis");
    Thread.sleep(problem);
  }
}
