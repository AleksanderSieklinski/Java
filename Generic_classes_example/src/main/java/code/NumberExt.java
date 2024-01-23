package code;
import java.util.Arrays;
import java.util.List;
public class NumberExt {
    public static double sum(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum of integers: " + NumberExt.sum(integers));

        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        System.out.println("Sum of doubles: " + NumberExt.sum(doubles));

        List<Float> floats = Arrays.asList(1.1f, 2.2f, 3.3f, 4.4f, 5.5f);
        System.out.println("Sum of floats: " + NumberExt.sum(floats));
    }
}