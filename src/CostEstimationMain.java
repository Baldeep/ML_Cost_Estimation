import org.apache.log4j.Logger;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

public class CostEstimationMain {
	public static void main(String[] args) throws Exception{
		Logger log = Logger.getLogger(CostEstimationMain.class);
		
		GPProblem problem = new CostEstimationProblem(Parameters.FILENAME);

        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(Parameters.EVOLVE);
        System.out.println("\n");
        log.info("Evaulation of dataset: " + Parameters.FILENAME);
        gp.outputSolution(gp.getAllTimeBest());
	}
}
