public class MaxHeap extends Heap{
		
	
	public MaxHeap(){
		heap = new double[0];
		indices = new int[0];
		this.buildMaxHeap();
	}
	
	
	public MaxHeap(double[] heap, int[] indices){
		this.heap = heap;
		this.indices = indices;
		this.buildMaxHeap();
	}
	
	
	public void insertElement(double key, int index){
		double[] newHeap = new double[heap.length + 1];
		int[] newIndices = new int[heap.length + 1];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
			newIndices[i] = indices[i];
		}
		newHeap[heap.length]    = Integer.MIN_VALUE;
		newIndices[heap.length] = Integer.MIN_VALUE;
		heap    = newHeap;
		indices = newIndices;
		this.increaseKey(key, index, heap.length - 1);
	}
	
	
	public void increaseKey(double key, int index, int position){
		if (key < heap[position]){
			System.out.println("New key is smaller than current key.");
		}
		heap[position] = key;
		while (position > 0 && heap[this.parent(position)] < heap[position]){
			double temporary = heap[position];
			heap[position] = heap[this.parent(position)];
			heap[this.parent(position)] = temporary;
			position = this.parent(position);
			
			temporary = indices[position];
			indices[position] = indices[this.parent(position)];
			indices[this.parent(position)] = (int) temporary;
		}
	}
	

	public double extractMaximum(){
		if (heap.length < 1){
			System.out.println("No elements in heap to extract");
			return -1;
		}
		double max = heap[0];

		double[] newHeap = new double[heap.length - 1];
		int[] newIndices = new int[heap.length - 1];
		if (heap.length > 1){
			newHeap[0] = heap[heap.length - 1];
			newIndices[0] = indices[heap.length - 1];
		}
		for (int i = 1; i < (heap.length - 1); i++){
			newHeap[i] = heap[i];
			newIndices[i] = indices[i];
		}
		heap = newHeap;
		indices = newIndices;
		this.maxHeapify(0);
		return max;
	}
	
	
	public double getMaximum(){
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
			double temporary = heap[index];
			heap[index] = heap[largest];
			heap[largest] = temporary; 
			
			temporary = indices[index];
			indices[index] = indices[largest];
			indices[largest] = (int) temporary; 
			
			maxHeapify(largest);
		}
	}
	

}
