package equation;

import logarithm.Log3;

public class LogarithmSolver implements Solver {

    private final Log3 log3;

    public LogarithmSolver(Log3 log3) {
        this.log3 = log3;
    }

    @Override
    public double compute(double x, double eps) {
        return Math.pow(Math.pow(Math.pow(Math.pow(Math.pow(log3.compute(x, eps), 2), 3), 2), 3), 3);
    }
}
