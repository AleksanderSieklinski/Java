package library;

// This is a generic class that implements the bubble sort algorithm. It can sort any array of objects that implement the Comparable interface.

public class BubbleSort<T extends Comparable<T>> {
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (array[j].compareTo(array[j+1]) > 0) {
                    // swap array[j+1] and array[j]
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
    }

    public static void main(String[] args) {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        Integer[] array = { 1, 6, 3, 3, 2 };
        bubbleSort.sort(array);
        System.out.println();
        for (Integer i : array) {
            System.out.println(i);
        }
        BubbleSort<Double> bubbleSort2 = new BubbleSort<>();
        Double[] array2 = { 1.0, 3.0, 3.0, 4.0, 2.0 };
        bubbleSort2.sort(array2);
        System.out.println();
        for (Double i : array2) {
            System.out.println(i);
        }
        BubbleSort<String> bubbleSort3 = new BubbleSort<>();
        String[] array3 = { "g", "b", "f", "d", "u" };
        bubbleSort3.sort(array3);
        System.out.println();
        for (String i : array3) {
            System.out.println(i);
        }
    }
}