package equation;

public class Equation {
    private final LogarithmSolver logarithmSolver;
    private final TrigonometricSolver trigonometricSolver;

    public Equation(LogarithmSolver logarithmSolver, TrigonometricSolver trigonometricSolver) {
        this.logarithmSolver = logarithmSolver;
        this.trigonometricSolver = trigonometricSolver;
    }

    public Double compute(Double x, Double eps) {
        return x > 0 ? logarithmSolver.compute(x, eps) : trigonometricSolver.compute(x, eps);
    }
}
