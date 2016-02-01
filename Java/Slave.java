import java.util.ArrayList;
import java.util.concurrent.Callable;


public class Slave implements Callable<double[]>{

	
	private ArrayList<double[][]> layouts;
	private WindFarmLayoutEvaluator evaluator;
	private int start;
	private int end;
	
	
	public Slave(WindFarmLayoutEvaluator evaluator, ArrayList<double[][]> layouts, double[] fitness, int start, int end){
		this.evaluator = evaluator;
		this.layouts   = layouts;
		this.start     = start;
		this.end       = end;
	}


	public double[] call() throws Exception {
		double[] fitness = new double[layouts.size()];
		double minimumFitness = Double.MAX_VALUE;
		
		for (int i = start; i < end; i++){
			double energyCost;
			if (evaluator.checkConstraint(layouts.get(i))){
				evaluator.evaluate(layouts.get(i));
				energyCost = evaluator.getEnergyCost();
			}
			else{
				energyCost = Double.MAX_VALUE;
			}
			fitness[i] = energyCost;
			
			//Just for visualization
			
			if (fitness[i] < minimumFitness){
				minimumFitness = fitness[i];
			}
		}
		System.out.println(minimumFitness);
		
		return fitness;
	}
	

}
