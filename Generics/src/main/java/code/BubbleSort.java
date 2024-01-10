package code;
public class BubbleSort<T extends Comparable<T>> {
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (compare(array[j], array[j+1]) > 0) {
                    // swap array[j+1] and array[j]
                    T temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
    }
    public int compare(T o1, T o2) {
        if (o1 instanceof Integer && o2 instanceof Integer) {
            return Integer.compare(countNonZeroDigits((Integer) o1), countNonZeroDigits((Integer) o2));
        } else if (o1 instanceof Double && o2 instanceof Double) {
            return Double.compare(getMantissa((Double) o1), getMantissa((Double) o2));
        } else if (o1 instanceof String && o2 instanceof String) {
            return Integer.compare(((String) o1).length(), ((String) o2).length());
        }
        return 0;
    }
    private int countNonZeroDigits(Integer value) {
        return (int) String.valueOf(Math.abs(value)).chars().filter(c -> c != '0').count();
    }
    private double getMantissa(Double value) {
        while (value >= 1) {
            value /= 10;
        }
        return value;
    }
    public static void main(String[] args) {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        Integer[] array = { 11, 60, 333, 3123, 24123 };
        bubbleSort.sort(array);
        System.out.println();
        for (Integer i : array) {
            System.out.println(i);
        }
        BubbleSort<Double> bubbleSort2 = new BubbleSort<>();
        Double[] array2 = { 1.0, 5.0, 3.0, 4.0, 2.0 };
        bubbleSort2.sort(array2);
        System.out.println();
        for (Double i : array2) {
            System.out.println(i);
        }
        BubbleSort<String> bubbleSort3 = new BubbleSort<>();
        String[] array3 = { "gd", "bbb", "dcaw", "asdxxd", "uad2eaas" };
        bubbleSort3.sort(array3);
        System.out.println();
        for (String i : array3) {
            System.out.println(i);
        }
    }
}