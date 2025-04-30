import equation.Equation;
import equation.LogarithmSolver;
import equation.TrigonometricSolver;
import logarithm.Ln;
import logarithm.Log3;
import trigonometry.Cos;
import trigonometry.Sin;

public class Main {
    public static void main(String[] args) {
        Ln ln = new Ln();
        Log3 log3 = new Log3(ln);
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        LogarithmSolver logarithmSolver = new LogarithmSolver(log3);
        TrigonometricSolver trigonometricSolver = new TrigonometricSolver(cos);

        Equation equation = new Equation(logarithmSolver, trigonometricSolver);
        double answer = equation.compute(1.0, 0.01);
        System.out.println(answer);
    }
}