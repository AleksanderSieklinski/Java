package Position_physics_calculation;
public class Lab1zad2 {
    // static method calculating the position of an object in free fall
    public static double calculatePosition(double x0, double v0, double t) {
        double A = -9.81;
        double v = v0 * 1 / 3.6; // convert km/h to m/s
        double x = x0 + v * t + 0.5 * A * t * t;
        return x;
    }
    public static void main(String[] args) {
        double x0 = 257;
        double v0 = 63;
        double t = 2.2;
        System.out.println("The position of the stone at t = 2.2 s is " + calculatePosition(x0, v0, t) + " m.");
    }
}