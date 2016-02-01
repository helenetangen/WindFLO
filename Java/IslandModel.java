	
	
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
	
	
	public int[] tournamentSelection(){
		int[] winners     = new int[populationSize/tournamentSize];
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
	
	
}
