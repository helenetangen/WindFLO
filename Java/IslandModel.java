public class IslandModel extends GeneticAlgorithm{
	
	
	private int demeCount;         //Number of demes.
	private int demeSize;          //Number of individuals in each deme.
	private int migrationRate;     //Number of individuals that migrate.
	private int migrationInterval; //Number of generations between migrations.
	
	
	//Each deme is a GeneticAlgorithm object.
	private GeneticAlgorithm[] demes;
	
	
	public IslandModel(WindFarmLayoutEvaluator evaluator, int populationSize, int tournamentSize, int generations, double mutationRate,double crossoverRate, int demeCount, int migrationRate, int migrationInterval) {
		super(evaluator, populationSize, tournamentSize, generations, mutationRate, crossoverRate);
		this.demeCount         = demeCount;
		this.demeSize          = populationSize / demeCount;
		this.migrationInterval = migrationInterval;
		this.migrationRate     = migrationRate;
		
		demes = new GeneticAlgorithm[demeCount];
		for (int i = 0; i < demeCount; i++){
			demes[i] = new GeneticAlgorithm(evaluator, demeSize, tournamentSize, migrationInterval, mutationRate, crossoverRate);
		}
	}
	
	
	public void runIslandModel(){
		for (int i = 0; i < this.generations; i++){
			System.out.println("Generation: " + (i + 1));
			for (int j = 0; j < demeCount; j++){
				System.out.println("Running deme: " + (j + 1));
				demes[j].run();
			}
			this.migration();
		}
	}
	
	
	//Use min- and maxheaps to find immigrates.
	public void migration(){
		
		MaxHeap maxHeap = new MaxHeap();

		
		for (int i = 0; i < demes.length; i++){
			GeneticAlgorithm deme = demes[i];
			for (int j = 0; j < deme.individuals.length; j++){
				double fitness = deme.fitness[j];
				if (maxHeap.size() < migrationRate){
					maxHeap.insertElement(fitness, j);
				}
				else if(fitness < maxHeap.getMaximum()){
					maxHeap.extractMaximum();
					maxHeap.insertElement(fitness, j);
				}
			}
			System.out.println("Test: ");
		}
	}
	
	
}
