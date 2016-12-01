import java.util.List;

import org.apache.log4j.Logger;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class CostEstimationFitness extends GPFitnessFunction {

	private static final long serialVersionUID = -7681378576167093531L;
	
	private List<List<Double>> inputs;
	private List<Double> outputs;
	private List<Variable> variables;
	
	private int dataLen;
	
	Logger log = Logger.getLogger(CostEstimationFitness.class);
	
	public CostEstimationFitness(List<List<Double>> inputs, List<Double> outputs, List<Variable> variables) {

		this.inputs = inputs;
		this.outputs = outputs; // Efforts are in the last column
		this.variables = variables;
		this.dataLen = inputs.get(0).size();
		
		log.info("Inputs size: " + inputs.size() + "; variables: " + variables.size() + "; dataLen: " + dataLen + "; outputs size: " + outputs.size());
		
	}
	
	@Override
	protected double evaluate(IGPProgram program) {
	        double result = 0.0f;

	        long longResult = 0;
	        for (int i = 0; i < dataLen; i++) {
	            // Set the input values
	        	for(int j = 0; j < variables.size(); j++){ 
	        		variables.get(j).set(inputs.get(j).get(i));
	        		
	        		System.out.println("i: " + i + "; j: " + j);
	        		
	        		System.out.println("Var: " + variables.get(j).getName() + " - " + variables.get(j).getValue());
	        		System.out.println("In: " + inputs.get(j).get(i));
	        		
	        		long value = (long) program.execute_double(0, new Object[0]);
	        		
	        		longResult += Math.abs(value - outputs.get(i));
	        	}
	        }

	        result = longResult;

	        return result;
	    }

}
