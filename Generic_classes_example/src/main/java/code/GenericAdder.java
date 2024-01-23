package code;

// This is a small class to test the addition of two different data types using generic class.

public class GenericAdder<T extends Number, U extends Number> {
    public double add(T num1, U num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static void main(String[] args) {
        GenericAdder<Integer, Double> adder = new GenericAdder<>();
        System.out.println(adder.add(1, 2.0));
    }
}