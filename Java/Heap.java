public class Heap {

	
	protected int[] heap;
	
	
	public int parent(int index){
		return ((index + 1) / 2) - 1;
	}
	
	
	public int left(int index){
		return 2 * index + 1;
	}
	
	
	public int right(int index){
		return (2 * (index + 1));
	}
	
	
	public void printHeap(){
		System.out.println("Heap: ");
		for (int i = 0; i < heap.length; i++){
			System.out.println(heap[i]);
		}
		System.out.println();
	}
	
	
}
