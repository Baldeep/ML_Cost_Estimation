import java.util.List;

import org.apache.log4j.Logger;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class CostEstimationPREDNFitness extends GPFitnessFunction {

	private static final long serialVersionUID = -7681378576167093531L;
	
	private List<List<Double>> inputs;
	private List<Double> outputs;
	private List<Variable> variables;
	
	private int dataLen;
	
	private static Object[] NO_ARGS = new Object[0];
	
	Logger log = Logger.getLogger(CostEstimationPREDNFitness.class);
	
	public CostEstimationPREDNFitness(List<List<Double>> inputs, List<Double> outputs, List<Variable> variables) {

		this.inputs = inputs;
		this.outputs = outputs; // Efforts are in the last column
		this.variables = variables;
		this.dataLen = inputs.get(0).size();
		
		log.info("Inputs size: " + inputs.size() + "; variables: " + variables.size() + "; dataLen: " + dataLen + "; outputs size: " + outputs.size());
		
	}
	
	@Override
	protected double evaluate(IGPProgram program) {
	        double result = 0.0;
	        

	        for (int i = 0; i < dataLen; i++) {
	            // Set the input values
	        	for(int j = 0; j < variables.size(); j++){ 
	        		variables.get(j).set(inputs.get(j).get(i));
	        	}
	        	double value = program.execute_double(0, NO_ARGS);
	        	
	        	double min = outputs.get(i)*(1.0-Parameters.PREDN_PERCENTAGE);
	        	double max = outputs.get(i)*(1.0+Parameters.PREDN_PERCENTAGE);
	        	
	        	if(value >= min && value <= max){
	        		result++;
	        	}
	        }
	        
	        return result;
	    }

}
