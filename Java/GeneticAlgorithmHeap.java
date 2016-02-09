public abstract class GeneticAlgorithmHeap {
	
	
	public static void main(String[] args){
		GeneticAlgorithmMinHeap heap = new GeneticAlgorithmMinHeap();
		heap.printHeap();
		heap.insertElement(5.0, 1);
		heap.printHeap();
		heap.insertElement(1.0, 2);
		heap.printHeap();
		heap.insertElement(10.0, 3);
		heap.printHeap();
		heap.insertElement(2.0, 4);
		heap.printHeap();
		heap.insertElement(7.0, 5);
		heap.printHeap();
	}
	
	
	protected double[] fitnessHeap;
	protected int[] indexHeap;
	
	
	public GeneticAlgorithmHeap(){
		this.fitnessHeap = new double[0];
		this.indexHeap   = new int[0];
	}
	
	
	public int parentIndex(int index){
		return ((index + 1) / 2) - 1;
	}
	
	
	public int leftIndex(int index){
		return 2 * index + 1;
	}
	
	
	public int rightIndex(int index){
		return (2 * (index + 1));
	}
	
	
	public int size(){
		return fitnessHeap.length;
	}
	
	
	public void printHeap(){
		System.out.println("Fitness Heap: ");
		for (int i = 0; i < fitnessHeap.length; i++){
			System.out.println(fitnessHeap[i]);
		}		
		System.out.println("Index Heap: ");
		for (int i = 0; i < indexHeap.length; i++){
			System.out.println(indexHeap[i]);
		}
		System.out.println();
	}

}
