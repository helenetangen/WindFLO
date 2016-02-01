import java.util.ArrayList;
import java.util.concurrent.Callable;


public class MasterSlave extends GeneticAlgorithm{

	
	public MasterSlave(WindFarmLayoutEvaluator evaluator, int populationSize, int tournamentSize, int generations, double mutationRate, double crossoverRate){
		super(evaluator, populationSize, tournamentSize, generations, mutationRate, crossoverRate);
	}

	
	public void evaluate(){
		ArrayList<double[][]> layouts = new ArrayList<double[][]>();
		
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
			layouts.add(layout);
		}
		
		Callable slaveOne = new Slave(evaluator, layouts, fitness, 0, 5);
		Callable slaveTwo = new Slave(evaluator, layouts, fitness, 5, 10);
		Thread threadOne = new Thread((Runnable) slaveOne);
		Thread threadTwo = new Thread((Runnable) slaveTwo);
	}
	
	
}
