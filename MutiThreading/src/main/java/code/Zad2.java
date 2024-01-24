package code;

import java.util.Arrays;
import java.util.Scanner;

class GetNumbers implements Runnable {
    private final int[] array;

    public GetNumbers(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            synchronized (array) {
                for (int i = 0; i < array.length; i++) {
                    System.out.print("Podaj liczbę: ");
                    array[i] = scanner.nextInt();
                }
                array.notify();
                try {
                    array.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
class GetSum implements Runnable {
    private final int[] array;

    public GetSum(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (array) {
                try {
                    array.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                int sum = 0;
                for (int num : array) {
                    sum += num;
                }
                System.out.println("Suma: " + sum);
                Arrays.fill(array, 0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                array.notify();
            }
        }
    }
}
public class Zad2 {
    public static void main(String[] args) {
        int[] array = new int[5];
        Thread getNumbersThread = new Thread(new GetNumbers(array));
        Thread getSumThread = new Thread(new GetSum(array));

        getNumbersThread.start();
        getSumThread.start();
    }
}