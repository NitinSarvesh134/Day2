import java.util.concurrent.*;
import java.util.ArrayList;
public class Day2 {

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {

        // Start measuring execution time
        long startTime = System.nanoTime();

        int numberOfThreads = 1000;
        long numbersPerThread = 1000000;

        // Create an ExecutorService with a pool of 1000 threads
        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);

        // Store the Futures returned by the submitted tasks
        ArrayList<Future<Long>> futures = new ArrayList<>();

        // Submit 1000 Callable tasks
        for (int i = 0; i < numberOfThreads; i++) {

            final int threadIndex = i;

            // Create a Callable task that returns a Long
            Callable<Long> task = new Callable<Long>() {


                public Long call() {

                    // Calculate this task's assigned range
                    long start = (long) threadIndex * numbersPerThread + 1;
                    long end = start + numbersPerThread - 1;

                    long sum = 0;

                    // Add all numbers in the assigned range
                    for (long j = start; j <= end; j++) {
                        sum += j;
                    }

                    // Return this task's result
                    return sum;
                }
            };

            // Submit the Callable and save its Future
            Future<Long> future = executor.submit(task);

            futures.add(future);
        }

        // Stop accepting new tasks
        executor.shutdown();

        // Add all the returned results together
        long total = 0;

        for (int i = 0; i < futures.size(); i++) {

            // Get the result (waits if the task is not finished)
            long result = futures.get(i).get();

            total += result;
        }

        System.out.println("Final total: " + total);

        // Stop measuring execution time
        long endTime = System.nanoTime();

        // Calculate elapsed time
        long elapsedTime = endTime - startTime;

        // Convert nanoseconds to milliseconds
        System.out.println("Time taken: "
                + elapsedTime / 1000000.0 + " milliseconds");
    }
}
