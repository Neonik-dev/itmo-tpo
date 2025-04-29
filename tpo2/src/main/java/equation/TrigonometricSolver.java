package equation;

import trigonometry.Cos;

public class TrigonometricSolver implements Solver {

    private final Cos cos;

    public TrigonometricSolver(Cos cos) {
        this.cos = cos;
    }

    @Override
    public double compute(double x, double eps) {
        return cos.compute(x, eps) + cos.compute(x, eps);
    }
}
