package trigonometry;

public class Sin {

    private static final double PI = Math.PI;

    public double compute(double x, double eps) {
        if (!validX(x)) {
            throw new IllegalArgumentException("Неверный аргумент x");
        }

        x %= 2 * PI;

        if (Math.abs(x) == 0 || Math.abs(x) == PI) {
            return 0d;
        }

        double res = x;
        double term = x;
        int k = 3;
        int sign = -1;

        while (Math.abs(term) >= eps) {
            term *= x * x / (k * (k - 1)); // корректное обновление текущего слагаемого
            res += sign * term;
            sign *= -1;
            k += 2;
        }

        return res;
    }

    public boolean validX(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return false;
        }
        return x <= 0;
    }
}
