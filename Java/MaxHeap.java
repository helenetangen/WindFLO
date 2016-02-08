public class MaxHeap extends Heap{
	
	
	public static void main(String[] args){
		//Full size heap
		int[] table1 = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
		MaxHeap heap = new MaxHeap(table1);
//		heap.printHeap();
//		System.out.println("Max: " + heap.extractMaximum());
//		heap.printHeap();
//		
//		//Empty heap
//		int[] table2 = {};
//		heap = new MaxHeap(table2);
//		heap.printHeap();
//		
//		//One-element heap
//		int[] table3 = {1};
//		heap = new MaxHeap(table3);
//		heap.printHeap();
//		
//		//Two-element heap
//		int[] table4 = {1, 7};
//		heap = new MaxHeap(table4);
//		heap.printHeap();
//		
		//Three-element heap
		int[] table5 = {7, 1, 4};
		heap = new MaxHeap(table5);
		heap.printHeap();
		heap.insertElement(5);
		heap.printHeap();
		heap.insertElement(6);
		heap.printHeap();
		heap.insertElement(18);
		heap.printHeap();
		heap.insertElement(3);
		heap.printHeap();
//		
//		//Three-element heap
//		int[] table6 = {14, 8, 9, 2, 1, 4, 3, 7, 10, 16};
//		heap = new MaxHeap(table6);
//		heap.printHeap();
	}
		
	
	public MaxHeap(int[] table){
		heap = table;
		this.buildMaxHeap();
	}
	
	
	public void insertElement(int key){
		int[] newHeap = new int[heap.length + 1];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		newHeap[heap.length] = Integer.MIN_VALUE;
		heap = newHeap;
		this.increaseKey(key, heap.length - 1);
	}
	
	
	public void increaseKey(int key, int index){
		if (key < heap[index]){
			System.out.println("New key is smaller than current key.");
		}
		heap[index] = key;
		while (index > 0 && heap[this.parent(index)] < heap[index]){
			int temporary = heap[index];
			heap[index] = heap[this.parent(index)];
			heap[this.parent(index)] = temporary;
			index = this.parent(index);
		}
	}
	

	public int extractMaximum(){
		if (heap.length < 1){
			System.out.println("No elements in heap to extract");
			return -1;
		}
		int max = heap[0];

		int[] newHeap = new int[heap.length - 1];
		if (heap.length > 1){
			newHeap[0] = heap[heap.length - 1];
		}
		for (int i = 1; i < (heap.length - 1); i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
		this.maxHeapify(0);
		return max;
	}
	
	
	public int getMaximum(){
		return heap[0];
	}
	
	
	public void buildMaxHeap(){
		int count = heap.length/2 - 1;
		while (count >= 0){
			maxHeapify(count);
			count = count - 1;
		}
	}
	
	
	public void maxHeapify(int index){
		int left  = left(index);
		int right = right(index);
		int largest;
		if (left < heap.length && heap[left] > heap[index]){
			largest = left;
		}
		else{
			largest = index;
		}
		if (right < heap.length && heap[right] > heap[largest]){
			largest = right;
		}
		if (largest != index){
			int temporary = heap[index];
			heap[index] = heap[largest];
			heap[largest] = temporary; 
			maxHeapify(largest);
		}
	}
	

}
