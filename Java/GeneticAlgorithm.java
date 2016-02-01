import java.util.ArrayList;
import java.util.Random;


public class GeneticAlgorithm {

	
	// Genetic Algorithm Variables
	protected WindFarmLayoutEvaluator evaluator;
	protected boolean[][] individuals;
	protected double[] fitness;
	protected int populationSize;
	protected int tournamentSize;
	protected double mutationRate;
	protected double crossoverRate;
	protected ArrayList<double[]> grid;
	
	
	// Other variables
	protected Random random = new Random();
	
	
}
