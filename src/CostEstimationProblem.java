import java.util.ArrayList;
import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

public class CostEstimationProblem extends GPProblem{

	List<Variable> variables = new ArrayList<Variable>();
	
	public CostEstimationProblem(String fileName) throws InvalidConfigurationException {
		super(new GPConfiguration());
		
		FileObject fo = FileParser.readFile(fileName);

        GPConfiguration config = getGPConfiguration();

        List<String> labels = fo.getAttributeLabels();
        
        for(int i = 1; i < labels.size()-1; i++){ // Skip ID and Efforts
        	variables.add(Variable.create(config, labels.get(i), CommandGene.DoubleClass));
        }
        
        // The first label is ID, the last is effort, the inputs is everything inbetween
        List<List<Double>> inputs = new ArrayList<List<Double>>();
        for(int i = 1; i < labels.size()-1; i++){ // Again, efforts doesn't include ID of Efforts
			inputs.add(fo.getDataForLabel(labels.get(i)));
		}
        
        // The last column is the efforts, i.e. the outputs
        List<Double> outputs = fo.getDataForLabel(labels.get(labels.size()-1));
        
        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(4);
        config.setPopulationSize(1000);
        config.setMaxCrossoverDepth(8);
        config.setFitnessFunction(new CostEstimationFitness(inputs, outputs, variables)); // Inputs, Efforts, and variables
        config.setStrictProgramCreation(true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public GPGenotype create() throws InvalidConfigurationException {
		GPConfiguration config = getGPConfiguration();

        // The return type of the GP program.
        Class[] types = { CommandGene.DoubleClass };

        // Arguments of result-producing chromosome: none
        Class[][] argTypes = { {} };

        // Next, we define the set of available GP commands and terminals to
        // use.
        List<CommandGene> nodeSet = new ArrayList<CommandGene>();
        nodeSet.addAll(variables);
        nodeSet.add(new Add(config, CommandGene.DoubleClass));
        nodeSet.add(new Multiply(config, CommandGene.DoubleClass));
        nodeSet.add(new Terminal(config, CommandGene.DoubleClass, 0.0, 10.0, true));
        
        CommandGene[][] nodeSets = { nodeSet.toArray(new CommandGene[nodeSet.size()]) };

        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);

        return result;
	}

}
