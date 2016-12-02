
public class Parameters {

	public static final boolean DEBUG_FILEPARSER = true;
	
	public static final String FILENAME = "china.arff";
	//public static final String FILENAME = "kemerer.arff";
	//public static final String FILENAME = "maxwell.arff";
	//public static final String FILENAME = "miyazaki94.arff";
	
	
	public static final int MAX_INIT_DEPTH = 4; // Originally 4

	public static final int POPULATION_SIZE = 1000; // Originally 1000

	public static final int MAX_CROSSOVER_DEPTH = 8; // Originally 8

	public static final boolean STRICT_PROGRAM_CREATION = true; // Originally true
	
	public static final float CROSSOVER_PROBABILITY = 0.3f; // Default 0.9

	public static final int EVOLVE = 30; // Originally 30
	
	// Mean Absolute Error
	// Uses formula sum(abs(actual-estimated))/#ofValues
	public static final boolean USE_MAE = false;
	
	// Mean Magniture of Relative Error
	// Uses formula abs(actual-estimated)/actual
	public static final boolean USE_MMRE = false;
	
	//Proportion of Estimates within n%
	// Uses formula sum(abs(actual-estimated))/#ofValues
	public static final boolean USE_PREDN = true;
	// Percentage +/- actual value the result should be in. 
	public static final double PREDN_PERCENTAGE = 0.10;
	
	// The GP will run using + - / * as default, set this to true to also include log and exp
	public static final boolean INCLUDE_OTHER_OPS = false;


}
