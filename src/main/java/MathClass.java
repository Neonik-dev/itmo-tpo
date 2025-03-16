public class MathClass {
    public static double arccos(double x, int terms) {
        if (x < -1 || x > 1) throw new IllegalArgumentException("x должно быть в диапазоне [-1, 1]");

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

    private static double factorial(int num) {
        if (num == 0 || num == 1) return 1;
        double result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        double x = -0.7;  // Примерное значение
        int terms = 10;  // Количество членов ряда

        double result = arccos(x, terms);
        System.out.println("arccos(" + x + ") ≈ " + result);
        System.out.println(Math.acos(x));
    }
}
