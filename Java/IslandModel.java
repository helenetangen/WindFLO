

public class IslandModel extends GeneticAlgorithm{
	
	
	private int demeCount;
	private int demeSize;
	private int migrationRate;
	private int migrationInterval;
	

	
	public IslandModel(WindFarmLayoutEvaluator evaluator, int populationSize, int tournamentSize, int generations, double mutationRate,double crossoverRate, int demeCount, int demeSize, int migrationRate, int migrationInterval) {
		super(evaluator, populationSize, tournamentSize, generations, mutationRate,crossoverRate);
		this.demeCount = demeCount;
		this.demeSize = demeSize;
		this.migrationRate = migrationRate;
		this.migrationInterval = migrationInterval;
	}
	
	
	

	
}
