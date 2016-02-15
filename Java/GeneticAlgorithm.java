import java.util.ArrayList;
import java.util.Random;


public class GeneticAlgorithm{

	
	// Genetic Algorithm Variables
	protected WindFarmLayoutEvaluator evaluator;
	protected boolean[][] individuals;
	protected double[] fitness;
	protected int populationSize;
	protected int tournamentSize;
	protected int generations;
	protected double mutationRate;
	protected double crossoverRate;
	protected ArrayList<double[]> grid;
	
	
	// Other variables
	protected Random random = new Random();
	protected static final double MINIMUM_DISTANCE = 8.001;
	
	
	public GeneticAlgorithm(WindFarmLayoutEvaluator evaluator, int populationSize, int tournamentSize, int generations, double mutationRate, double crossoverRate){
		this.evaluator = evaluator;
		this.populationSize = populationSize;
		this.tournamentSize = tournamentSize;
		this.generations    = generations;
		this.mutationRate   = mutationRate;
		this.crossoverRate  = crossoverRate;
		this.grid = new ArrayList<double[]>();	
	}
	
	
	public void initialize(){
		//Set up the grid
		this.setUpGrid();
		
		//Initialize individuals
		this.generatePopulation();
		
		//Evaluate initial population
		this.evaluate();
	}
	
	
	public void run(){
		
		//Genetic Algorithm
		for (int i = 0; i < generations; i++){
			
			//Tournament selection
			int[] winners = this.tournamentSelection();
			
			//Crossover
			boolean[][] children = this.crossover(winners);
			
			//Mutation
			children = this.mutation(children, winners);
			
			//Elitism
			children = this.elitism(children, winners);
			
			individuals = children;
			
			//Evaluate new population
			evaluate();
		}
	}
	
	
	public void evaluate(){
		//Just for visualization
		double minimumFitness = Double.MAX_VALUE;
		
		//Create-, and evaluate layout for each individual.
		for (int p = 0; p < populationSize; p++){
			int turbineCount = 0;
			for (int i = 0; i < grid.size(); i++){
				if (individuals[p][i]){
					turbineCount++;
				}
			}
			double[][] layout = new double[turbineCount][2];
			int j = 0; 
			for (int i = 0; i < grid.size(); i++){
				if (individuals[p][i]){
					layout[j][0] = grid.get(i)[0];
					layout[j][1] = grid.get(i)[1];
					j++;
				}
			}
			double energyCost;
			if (evaluator.checkConstraint(layout)){
				evaluator.evaluate(layout);
				energyCost = evaluator.getEnergyCost();
			}
			else{
				energyCost = Double.MAX_VALUE;
			}
			fitness[p] = energyCost;
			
			//Just for visualization
			if (fitness[p] < minimumFitness){
				minimumFitness = fitness[p];
			}
			
		}
		System.out.println("Minimum fitness: " + minimumFitness);
	}
	
	
	public boolean[][] elitism(boolean[][] children, int[] winners){
		for (int c = 0; c < winners.length; c++){
			children[populationSize - winners.length + c] = individuals[winners[c]];
		}
		return children;
	}
	
	
	public boolean[][] mutation(boolean[][] children, int[] winners){
		for (int c = 0; c < (populationSize - winners.length); c++){
			for (int j = 0; j < children[c].length; j++){
				if (random.nextDouble() < mutationRate){
					children[c][j] = !children[c][j];
				}
			}
		}
		return children;
	}
	
	
	public boolean[][] crossover(int[] winners){
		boolean[][] children = new boolean[populationSize][grid.size()];
		for (int c = 0; c < (populationSize - winners.length); c++){
			int positionOne = random.nextInt(winners.length);
			int positionTwo = random.nextInt(winners.length - 1);
			if (positionTwo >= positionOne){
				positionTwo++;
			}
			int pOne = winners[positionOne];
			int pTwo = winners[positionTwo];

			boolean[] parentOne = individuals[pOne];
			boolean[] parentTwo = individuals[pTwo];
			boolean[] child = new boolean[grid.size()];
			for (int j = 0; j < child.length; j++){
				if (random.nextDouble() < crossoverRate){
					child[j] = parentTwo[j];
				}
				else{
					child[j] = parentOne[j];
				}
			}
			children[c] = child;
		}
		return children;
	}
	
	
	public int[] tournamentSelection(){
		int[] winners     = new int[(int) populationSize/tournamentSize];
		int[] competitors = new int[populationSize];
		for (int c = 0; c < competitors.length; c++){
			competitors[c] = c;
		}
		for (int c = 0; c < competitors.length; c++){
			int index     = random.nextInt(c + 1);
			int temporary = competitors[index];
			competitors[index] = competitors[c];
			competitors[c] = temporary;
		}
		for (int t = 0; t < winners.length; t++){
			int winner = -1;
			double winnerFitness = Double.MAX_VALUE;
			for (int c = 0; c < tournamentSize; c++){
				int competitor = competitors[tournamentSize * t + c];
				if (fitness[competitor] < winnerFitness){
					winner = competitor;
					winnerFitness = fitness[winner];
				}
			}
			winners[t] = winner;
		}
		return winners;
	}
	
	
	public void setUpGrid(){
		double interval = MINIMUM_DISTANCE * evaluator.getTurbineRadius();
		for (double x = 0.0; x < evaluator.getFarmWidth(); x += interval){
			for (double y = 0.0; y < evaluator.getFarmHeight(); y += interval){
				boolean validPosition = true;
				for (int o = 0; o < evaluator.getObstacles().length; o++){
					double[] obstacle = evaluator.getObstacles()[o];
					if (x > obstacle[0] && y > obstacle[1] && x < obstacle[2] && y < obstacle[3]){
						validPosition = false;
					}
				}
				if (validPosition){
					double[] point = {x, y};
					grid.add(point);
				}
			}
		}
	}
	
	
	public void generatePopulation(){
		individuals = new boolean[populationSize][grid.size()];
		fitness     = new double[populationSize];
		
		for (int p = 0; p < populationSize; p++){
			for (int i = 0; i < grid.size(); i++){
				individuals[p][i] = random.nextBoolean();
			}
		}
	}

	
}
