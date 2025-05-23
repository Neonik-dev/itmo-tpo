package trigonometry;

public class Cos {

    private static final double PI = Math.PI;
    private static final double TWO_PI = 2 * PI;
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double compute(double x, double eps) {
        if (!validX(x)) {
            throw new IllegalArgumentException("Неверный аргумент x");
        }

        x %= TWO_PI;

        if (Math.abs(x) == PI / 2 || Math.abs(x) == 3 * PI / 2) {
            return 0d;
        }

        double sinX = sin.compute(x, eps);
        double res = Math.sqrt(1 - Math.pow(sinX, 2));

        boolean isNegative = (x > PI / 2 && x <= PI) || (x < -PI / 2 && x >= -PI) || (x >= PI && x <= 3 * PI / 2) || (x <= -PI && x > -3 * PI / 2);

        return isNegative ? -res : res;
    }

    public boolean validX(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return false;
        }
        return x <= 0;
    }
}
