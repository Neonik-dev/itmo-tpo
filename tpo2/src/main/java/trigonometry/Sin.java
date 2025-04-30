package trigonometry;

public class Sin {

    private static final double PI = Math.PI;

    public double compute(double x, double eps) {
        if (!validX(x)) {
            throw new IllegalArgumentException("Неверный аргумент x");
        }

        x %= 2 * PI;

        if (x == 0 || x == PI) {
            return 0d;
        }

        double res = x;
        int k = 3;
        double pow = Math.pow(x, 3);
        int sign = -1;
        long fact = 6;

        while (true) {
            double curTerm = (sign * pow) / fact;
            if (Math.abs(curTerm) < eps) {
                break;
            }

            res += curTerm;
            sign = -sign;
            fact *= (long) (k + 1) * (k + 2);
            pow *= Math.pow(x, 2);
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
