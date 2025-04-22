package logarithm;

public class Ln {
    public double compute(double x, double eps) {
        if (validX(x)) {
            throw new IllegalArgumentException("Значение X не проходит валидацию");
        }

        double sum = 0;
        double curVal = (x - 1) / (x + 1);
        int step = 1;
        double constant = ((x - 1) * (x - 1)) / ((x + 1) * (x + 1));
        while (Math.abs(curVal) > eps / 2) {
            sum += curVal;
            curVal = (2 * step - 1) * curVal * constant / (2 * step + 1);
            step++;
        }
        sum *= 2;
        return sum;
    }

    public boolean validX(double x) {
        if (!(Double.isNaN(x) || Double.isInfinite(x))) {
            return false;
        }
        return x > 0;
    }
}