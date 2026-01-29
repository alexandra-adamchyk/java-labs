import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySumForkJoin {
    private static final int ARRAY_SIZE = 1_000_000;
    private static final int MAX_VALUE = 100;
    private static final int THRESHOLD = 20;

    public static void main(String[] args) {
        // Initialize array with random values
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(MAX_VALUE + 1); // 0 to 100 inclusive
        }

        // Start timing
        long startTime = System.currentTimeMillis();

        // Create ForkJoinPool and execute task
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask sumTask = new SumTask(array, 0, array.length);
        long sum = forkJoinPool.invoke(sumTask);

        // End timing
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Output results
        System.out.println("Сума всіх елементів масиву: " + sum);
        System.out.println("Час виконання програми: " + executionTime + " мс");
        System.out.println("Розмір масиву: " + ARRAY_SIZE + " елементів");
        System.out.println("Поріг рекурсії: " + THRESHOLD + " елементів");
    }

    // RecursiveTask for array sum calculation
    static class SumTask extends RecursiveTask<Long> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            
            // Base case: if subarray has less than 20 elements, calculate sum directly
            if (length < THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            }
            
            // Recursive case: split the array in half
            int mid = start + length / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            
            // Fork the left task and compute the right task
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();
            
            return leftResult + rightResult;
        }
    }
}
