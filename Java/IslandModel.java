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
		
		System.out.println("Start making demes.");
		demes = new GeneticAlgorithm[demeCount];
		for (int i = 0; i < demeCount; i++){
			demes[i] = new GeneticAlgorithm(evaluator, demeSize, tournamentSize, migrationInterval, mutationRate, crossoverRate);
			demes[i].initialize();
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
			
			for (int k = 0; k < demes.length; k++){
				GeneticAlgorithm deme = demes[k];
				System.out.println("Deme: " + k);
				for (int j = 0; j < deme.individuals.length; j++){
					System.out.println("Fitness " + j + ": " + deme.fitness[j]);
				}
			}
		}
	}
	
	
	public void migration(){
		MaxHeap[] winnersHeaps = new MaxHeap[demes.length];
		MinHeap[] losersHeaps  = new MinHeap[demes.length];
		
		//Find winners and losers in each deme (migrants)
		for (int i = 0; i < demes.length; i++){
			MaxHeap winners = new MaxHeap();
			MinHeap losers  = new MinHeap();
			GeneticAlgorithm demeWinners = demes[i];
			GeneticAlgorithm demeLosers  = demes[(i + 1) % demes.length];
			
			for (int j = 0; j < demeWinners.populationSize; j++){
				double fitnessWinner = demeWinners.fitness[j];
				double fitnessLoser  = demeLosers.fitness[j];
				if (winners.size() < migrationRate){
					winners.insertElement(fitnessWinner, j);
					losers.insertElement(demeLosers.fitness[j], j);
				}
				else{
					if (winners.getMaximumFitness() > fitnessWinner){
						winners.extractMaximum();
						winners.insertElement(fitnessWinner, j);
					}
					if (losers.getMinimumFitness() < fitnessLoser){
						losers.extractMinimum();
						losers.insertElement(fitnessLoser, j);
					}
				}
			}
			winnersHeaps[i] = winners;
			losersHeaps[i]  = losers;
			
		}
		//Raplace winners from one deme with losers from the next (ring-topology).
		for (int i = 0; i < demes.length; i++){
			MaxHeap winners = winnersHeaps[i];
			MinHeap losers  = losersHeaps[i];
			GeneticAlgorithm demeWinners = demes[i];
			GeneticAlgorithm demeLosers  = demes[(i + 1) % demes.length];
			for (int j = 0; j < migrationRate; j++){
				demeLosers.individuals[losers.getMinimumIndex()] = demeWinners.individuals[winners.getMaximumIndex()];
				demeLosers.fitness[losers.getMinimumIndex()] = winners.getMaximumFitness();
				winners.extractMaximum();
				losers.extractMinimum();
			}
			
		}	
	}
	
	
}
