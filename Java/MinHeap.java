public class MinHeap extends Heap{
	
	
	public MinHeap(){
		heap = new double[0];
		this.buildMinHeap();
	}
	
	
	public MinHeap(double[] table){
		heap = table;
		this.buildMinHeap();
	}
	
	
	public void insertElement(int key){
		double[] newHeap = new double[heap.length + 1];
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
			double temporary = heap[index];
			heap[index] = heap[this.parent(index)];
			heap[this.parent(index)] = temporary;
			index = this.parent(index);
		}
	}
	
	
	public double extractMinimum(){
		if (heap.length < 1){
			System.out.println("No elements in heap to extract");
			return -1;
		}
		double minimum = heap[0];

		double[] newHeap = new double[heap.length - 1];
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
	
	
	public double getMinimum(){
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
			double temporary = heap[index];
			heap[index] = heap[smallest];
			heap[smallest] = temporary; 
			this.minHeapify(smallest);
		}
	}


}
