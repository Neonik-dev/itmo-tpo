package logarithm;

public class Log3 {
    private final Ln ln = new Ln();

    public double compute(double x, double eps) {
        return ln.compute(x, eps) / 1.09861228867;
    }
}
