public class MaxHeap extends Heap{
		
	
	public MaxHeap(){
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
		while(position > 0 && fitnessHeap[this.parentIndex(position)] < fitnessHeap[position]){
			double temporaryFitness = fitnessHeap[position];
			fitnessHeap[position] = fitnessHeap[this.parentIndex(position)];
			fitnessHeap[this.parentIndex(position)] = temporaryFitness;
			
			int temporaryIndex = indexHeap[position];
			indexHeap[position] = indexHeap[this.parentIndex(position)];
			indexHeap[this.parentIndex(position)] = temporaryIndex;
			
			position = this.parentIndex(position);
		}
	}
	
	
	public double extractMaximum(){
		if (fitnessHeap.length < 1){
			System.out.println("No elements in heap to extract");
			return -1;
		}
		double max = fitnessHeap[0];

		double[] newFitnessHeap = new double[fitnessHeap.length - 1];
		int[] newIndexHeap = new int[fitnessHeap.length - 1];
		
		if (fitnessHeap.length > 1){
			newFitnessHeap[0] = fitnessHeap[fitnessHeap.length - 1];
			newIndexHeap[0] = indexHeap[fitnessHeap.length - 1];
		}
		for (int i = 1; i < (fitnessHeap.length - 1); i++){
			newFitnessHeap[i] = fitnessHeap[i];
			newIndexHeap[i] = indexHeap[i];
		}
		fitnessHeap = newFitnessHeap;
		indexHeap = newIndexHeap;
		this.maxHeapify(0);
		return max;
	}
	
	
	public void maxHeapify(int index){
		int left  = leftIndex(index);
		int right = rightIndex(index);
		int smallest;
		if (left < fitnessHeap.length && fitnessHeap[left] > fitnessHeap[index]){
			smallest = left;
		}
		else{
			smallest = index;
		}
		if (right < fitnessHeap.length && fitnessHeap[right] > fitnessHeap[smallest]){
			smallest = right;
		}
		if (smallest != index){
			double temporaryFitness = fitnessHeap[index];
			fitnessHeap[index] = fitnessHeap[smallest];
			fitnessHeap[smallest] = temporaryFitness; 
			
			int temporaryIndex = indexHeap[index];
			indexHeap[index] = indexHeap[smallest];
			indexHeap[smallest] = temporaryIndex; 
			
			maxHeapify(smallest);
		}
	}
	
	
	public double getMaximumFitness(){
		return fitnessHeap[0];
	}
	
	
	public int getMaximumIndex(){
		return indexHeap[0];
	}

}
