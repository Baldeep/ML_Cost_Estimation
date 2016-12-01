
public class Parameters {

	public static final boolean DEBUG_FILEPARSER = true;
	
	public static final String FILENAME = "kemerer.arff";

	public static final int MAX_INIT_DEPTH = 4; // Originally 4

	public static final int POPULATION_SIZE = 1000; // Originally 1000

	public static final int MAX_CROSSOVER_DEPTH = 8; // Originally 8

	public static final boolean STRICT_PROGRAM_CREATION = true; // Originally true

	public static final int EVOLVE = 30; // Originally 30
	
	// The GP will run using + - / * as default, set this to true to also include log and exp
	public static final boolean INCLUDE_OTHER_OPS = false;

}
