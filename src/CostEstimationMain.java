import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

public class CostEstimationMain {
	public static void main(String[] args) throws Exception{
		GPProblem problem = new CostEstimationProblem(Parameters.FILENAME);

        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(30);

        System.out.println("Formulaiscover: x^2 + 2y + 3x + 5");
        gp.outputSolution(gp.getAllTimeBest());
	}
}
