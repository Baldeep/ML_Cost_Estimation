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
        System.out.println("");
        if(Parameters.USE_MAE){
        	log.info("Using Mean Absolue Error Fitness Function");
        } else if(Parameters.USE_MMRE){
        	log.info("Using Mean Meagnitude of Relative Error Fitness Function");
        } else if(Parameters.USE_PREDN){
        	log.info("Using Pred(n) Fitness Function");
        } else {
        	log.error("No fitness function selected, using MAE as default");
        }
        
        log.info("Evaulation of dataset: " + Parameters.FILENAME);
        gp.outputSolution(gp.getAllTimeBest());
	}
}
