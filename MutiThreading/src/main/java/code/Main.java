package code;

import java.util.Random;

class PartialSum implements Runnable {
    private final double[] array;
    private final int start;
    private final int end;
    private double sum = 0;

    public PartialSum(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public double getSum() {
        return sum;
    }
}

class ThreadSum extends Thread {
    private final double[] array;
    private final int start;
    private final int end;
    private double sum = 0;

    public ThreadSum(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
    }

    public double getSum() {
        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int N = 10000;
        final int K = 10;
        double[] array = new double[N];
        Random random = new Random();

        // Wypełniamy tablicę liczbami pseudolosowymi
        for (int i = 0; i < N; i++) {
            array[i] = random.nextDouble();
        }

        // Obliczamy sumę tablicy w konwencjonalny sposób
        long startTime = System.nanoTime();
        double conventionalSum = 0;
        for (double v : array) {
            conventionalSum += v;
        }
        long endTime = System.nanoTime();
        long conventionalTime = endTime - startTime;

        // Obliczamy sumę tablicy za pomocą wielowątkowości implementując interfejs Runnable
        startTime = System.nanoTime();
        PartialSum[] partialSums = new PartialSum[K];
        Thread[] threads = new Thread[K];
        for (int i = 0; i < K; i++) {
            partialSums[i] = new PartialSum(array, i * N / K, (i + 1) * N / K);
            threads[i] = new Thread(partialSums[i]);
            threads[i].start();
        }
        double multiThreadedSumInterface = 0;
        for (int i = 0; i < K; i++) {
            threads[i].join();
            multiThreadedSumInterface += partialSums[i].getSum();
        }
        endTime = System.nanoTime();
        long multiThreadedTimeInterface = endTime - startTime;

        // Obliczamy sumę tablicy za pomocą wielowątkowości rozszerzając klasę Thread
        startTime = System.nanoTime();
        ThreadSum[] threadSums = new ThreadSum[K];
        for (int i = 0; i < K; i++) {
            threadSums[i] = new ThreadSum(array, i * N / K, (i + 1) * N / K);
            threadSums[i].start();
        }
        double multiThreadedSumClass = 0;
        for (int i = 0; i < K; i++) {
            threadSums[i].join();
            multiThreadedSumClass += threadSums[i].getSum();
        }
        endTime = System.nanoTime();
        long multiThreadedTimeClass = endTime - startTime;

        System.out.println("Suma konwencjonalna: " + conventionalSum);
        System.out.println("Czas obliczeń konwencjonalnych: " + conventionalTime + " nanosekund");
        System.out.println("Suma wielowątkowa (interfejs Runnable): " + multiThreadedSumInterface);
        System.out.println("Czas obliczeń wielowątkowych (interfejs Runnable): " + multiThreadedTimeInterface + " nanosekund");
        System.out.println("Suma wielowątkowa (klasa Thread): " + multiThreadedSumClass);
        System.out.println("Czas obliczeń wielowątkowych (klasa Thread): " + multiThreadedTimeClass + " nanosekund");
    }
}