
public class GeneticAlgorithmMinHeap extends GeneticAlgorithmHeap{

	
	public GeneticAlgorithmMinHeap(){
		super();
	}
	
	
	public void insertElement(double key, int index){
		//Increase heap sizes:
		double[] newFitnessHeap = new double[fitnessHeap.length + 1];
		int[] newIndexHeap = new int[fitnessHeap.length + 1];
		
		for (int i = 0; i < fitnessHeap.length; i++){
			newFitnessHeap[i] = fitnessHeap[i];
			newIndexHeap[i] = indexHeap[i];
		}
		newFitnessHeap[fitnessHeap.length] = Integer.MIN_VALUE;
		fitnessHeap = newFitnessHeap;
		indexHeap = newIndexHeap;
		this.increaseKey(key, index, fitnessHeap.length - 1);
	}
	
	
	public void increaseKey(double key, int index, int position){
		if (key < fitnessHeap[position]){
			System.out.println("New key is smaller than current key.");
		}
		fitnessHeap[position] = key;
		indexHeap[position]   = index;
		while(position > 0 && fitnessHeap[this.parentIndex(position)] > fitnessHeap[position]){
			double temporaryFitness = fitnessHeap[position];
			fitnessHeap[position] = fitnessHeap[this.parentIndex(position)];
			fitnessHeap[this.parentIndex(position)] = temporaryFitness;
			
			int temporaryIndex = indexHeap[position];
			indexHeap[position] = indexHeap[this.parentIndex(position)];
			indexHeap[this.parentIndex(position)] = temporaryIndex;
			
			position = this.parentIndex(position);
		}
	}
	
	
}
