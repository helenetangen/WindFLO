	
	
public class IslandModel{
	
	
	private int demeCount;         //Number of demes.
	private int demeSize;          //Number of individuals in each deme.
	private int migrationRate;     //Number of individuals that migrate.
	private int migrationInterval; //Number of generations between migrations.
	
	
	//Each deme is a GeneticAlgorithm object.
	private GeneticAlgorithm[] demes;
	private final static int GENERATIONS = 5;
	
	
	public IslandModel(WindFarmLayoutEvaluator evaluator, int populationSize, int tournamentSize, int generations, double mutationRate,double crossoverRate, int demeCount, int migrationRate, int migrationInterval) {
		this.demeCount         = demeCount;
		this.demeSize          = populationSize / demeCount;
		this.migrationInterval = migrationInterval;
		this.migrationRate     = migrationRate;
		
		demes = new GeneticAlgorithm[demeCount];
		for (int i = 0; i < demeCount; i++){
			demes[i] = new GeneticAlgorithm(evaluator, demeSize, tournamentSize, migrationInterval, mutationRate, crossoverRate);
		}
	}
	
	
	public void run(){
		for (int i = 0; i < GENERATIONS; i++){
			for (int j = 0; j < demeCount; j++){
				demes[j].run();
			}
			this.migration();
		}
	}
	
	
	//Use min- and maxheaps to find immigrates.
	public void migration(){
		
		for (int i = 0; i < demes.length; i++){
			GeneticAlgorithm deme = demes[i];
			//Implement min- and max heaps to do this.
		}
	}
	
	
}
