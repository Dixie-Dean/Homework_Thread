import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static int stringsNumber = 25;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(stringsNumber);
        List<Future<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < stringsNumber; i++) {
            Callable<Integer> callable = new TextCallable(generateText("aab", 30_000));
            Future<Integer> future = threadPool.submit(callable);
            tasks.add(future);
        }

        int max = 0;
        for (Future<Integer> future : tasks) {
            if (future.get() > max) {
                max = future.get();
            }
        }

        System.out.println("Max Interval: " + max);
        threadPool.shutdown();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}