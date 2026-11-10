public class Day2 {

    public static void main(String[] args) throws InterruptedException {

        // Start measuring execution time
        long startTime = System.nanoTime();

        int numberOfThreads = 1000;
        long numbersPerThread = 1000000;

        // Array to store all 1000 threads
        Thread[] threads = new Thread[numberOfThreads];

        // Array to store each thread's result
        long[] results = new long[numberOfThreads];

        // Create and start all 1000 threads
        for (int i = 0; i < numberOfThreads; i++) {

            final int threadIndex = i;

            // Create a Runnable task
            Runnable task = new Runnable() {


                public void run() {

                    // Calculate this thread's assigned range
                    long start = (long) threadIndex * numbersPerThread + 1;
                    long end = start + numbersPerThread - 1;

                    long sum = 0;

                    // Add all numbers in the assigned range
                    for (long j = start; j <= end; j++) {
                        sum += j;
                    }

                    // Store this thread's result
                    results[threadIndex] = sum;
                }
            };

            // Create a thread using the Runnable task
            threads[i] = new Thread(task);

            // Start the thread
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        // Add all thread results together
        long total = 0;

        for (int i = 0; i < numberOfThreads; i++) {
            total += results[i];
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
