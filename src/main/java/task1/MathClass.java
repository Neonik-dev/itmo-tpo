package task1;

public class MathClass {
    private final double x;  // Примерное значение
    private final int terms;  // Количество членов ряда

    public MathClass(double x, int terms) {
        this.x = x;
        this.terms = terms;
    }

    public double arccos() {
        if (x < -1 || x > 1) throw new IllegalArgumentException("x должно быть в диапазоне [-1, 1]");
        if (terms <= 0 || terms > 10000) throw new IllegalArgumentException("x должно быть в диапазоне [-1, 1]");

        double sum = Math.PI / 2;
        double term;
        int n = 0;

        while (n < terms) {
            term = factorial(2 * n) / (Math.pow(4, n) * Math.pow(factorial(n), 2) * (2 * n + 1)) * Math.pow(x, 2 * n + 1d);
            sum -= term;
            n++;
        }
        return sum;
    }

    private double factorial(int num) {
        if (num == 0 || num == 1) return 1;
        double result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
//    public void main() {
//        double result = arccos(x, terms);
//        System.out.println("arccos(" + x + ") ≈ " + result);
//        System.out.println(Math.acos(x));
//    }
}
