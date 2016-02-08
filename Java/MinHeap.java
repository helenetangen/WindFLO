public class MinHeap extends Heap{
	
	
	public static void main(String[] args){
		//Three-element heap
		int[] table = {7, 0, 18, -3};
		MinHeap heap = new MinHeap(table);
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.extractMinimum();
		heap.printHeap();
		heap.insertElement(5);
		heap.printHeap();
		heap.insertElement(6);
		heap.printHeap();
		heap.insertElement(18);
		heap.printHeap();
		heap.insertElement(3);
		heap.printHeap();
		heap.insertElement(-3);
		heap.printHeap();
		heap.insertElement(100);
		heap.printHeap();
	}
	
	
	public MinHeap(int[] table){
		heap = table;
		this.buildMinHeap();
	}
	
	
	public void insertElement(int key){
		int[] newHeap = new int[heap.length + 1];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		newHeap[heap.length] = Integer.MAX_VALUE;
		heap = newHeap;
		this.decreaseKey(key, heap.length - 1);
	}
	
	
	public void decreaseKey(int key, int index){
		if (key > heap[index]){
			System.out.println("New key is greater than current key.");
		}
		heap[index] = key;
		while (index > 0 && heap[this.parent(index)] > heap[index]){
			int temporary = heap[index];
			heap[index] = heap[this.parent(index)];
			heap[this.parent(index)] = temporary;
			index = this.parent(index);
		}
	}
	
	
	public int extractMinimum(){
		if (heap.length < 1){
			System.out.println("No elements in heap to extract");
			return -1;
		}
		int minimum = heap[0];

		int[] newHeap = new int[heap.length - 1];
		if (heap.length > 1){
			newHeap[0] = heap[heap.length - 1];
		}
		for (int i = 1; i < (heap.length - 1); i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
		this.minHeapify(0);
		return minimum;
	}
	
	
	public int getMinimum(){
		return heap[0];
	}
	
	
	public void buildMinHeap(){
		int count = heap.length/2 - 1;
		while (count >= 0){
			this.minHeapify(count);
			count = count - 1;
		}
	}
	
	
	public void minHeapify(int index){
		int left  = left(index);
		int right = right(index);
		int smallest;
		if (left < heap.length && heap[left] < heap[index]){
			smallest = left;
		}
		else{
			smallest = index;
		}
		if (right < heap.length && heap[right] < heap[smallest]){
			smallest = right;
		}
		if (smallest != index){
			int temporary = heap[index];
			heap[index] = heap[smallest];
			heap[smallest] = temporary; 
			this.minHeapify(smallest);
		}
	}


}
