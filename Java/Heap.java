public class Heap {

	
	public static void main(String[] args){
		
	}
	
	
	protected double[] heap;
	protected int[] indices;
	
	
	public int parent(int index){
		return ((index + 1) / 2) - 1;
	}
	
	
	public int left(int index){
		return 2 * index + 1;
	}
	
	
	public int right(int index){
		return (2 * (index + 1));
	}
	
	
	public int size(){
		return heap.length;
	}
	
	
	public void printHeap(){
		System.out.println("Heap: ");
		for (int i = 0; i < heap.length; i++){
			System.out.println(heap[i]);
		}
		System.out.println();
	}
	
	
}
