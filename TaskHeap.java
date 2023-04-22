
/**
 * A heap, implemented as an array.
 * The elements in the heap are instances of the class TaskElement,
 * and the heap is ordered according to the Task instances wrapped by those objects.
 * 
 * IMPORTANT: Except the percolation (private) functions, no single function may loop/recurse through all elements in the heap.
 * 			  You may only use loops which look at a small fraction of the heap.
 * 
 *
 */
		/*
           Assignment number : 8
           File Name : TaskHeap.java
           Name: Ran Zaaroor
           Student ID : 209374040
           Email : Ran.zaaroor@gmail.com
       	*/
public class TaskHeap{

	public static int capacity=200; // the maximum number of elements in the heap
	/*
	 * The array in which the elements are kept according to the heap order.
	 * The following must always hold true:
	 * 			if i < size then heap[i].heapIndex == i
	 */
	TaskElement[] heap;
	int size; // the number of elements in the heap, it is required that size <= heap.length
	
	/**
	 * Creates an empty heap which can contain 200 elements.
	 */
	public TaskHeap(){
		this.heap = new TaskElement[capacity+1];
		this.size = 0;

	}
	
	/**
	 * Constructs a heap from a given arbitrary array of TaskElements of size at most 'capacity'. 
	 * This should be done according to the "buildheap" function studied in class.
	 * NOTE: the heapIndex field of each TaskElement might be -1 (or incorrect).
	 * You may NOT use the insert function of heap.
	 * NOTE: for this function you may use loops.
	 * 
	 */
	public TaskHeap(TaskElement[] arr) {
		
		this.heap = new TaskElement[capacity+1];
		this.size = arr.length;
		
		//copy the given array into the heap, shifting the elements by 1
		
		for(int i=0; i<arr.length;i++) {
			heap[i+1]=arr[i];
			heap[i+1].heapIndex=i+1;
		}

		//building the heap
		for (int i = size/2+1; i >= 1; i--) {
			this.percolateDown(i);
		}
	}
	
    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size(){
    	return size;
    }
    
    /**
     * Inserts a given element into the heap.
     *
     * @param e - the element to be inserted.
     */
    public void insert(TaskElement e){
    	heap[size + 1] = e;
        e.heapIndex = size + 1;
        size++;
        percolateUp(size);
    }
    
    

	/**
	 * Returns and does not remove the element which wraps the task with maximal priority.
	 * 
	 * @return the element which wraps the task with maximal priority.
	 */
    public TaskElement findMax(){
    	return heap[1];
    }
    
	/**
	 * Returns and removes the element which wraps the task with maximal priority.
	 * 
	 * @return the element which wraps the task with maximal priority.
	 */
    public TaskElement extractMax() {
    	TaskElement max = findMax();
        if (size == 1) {
            heap[1] = null;
            return max;
        }
        remove(1);
        return max;
    }
    
    /**
     * Removes the element located at the given index.
     * 
	 * Note: this function is not part of the standard heap API.
	 *		 Make sure you understand how to implement it, and why it is required.
	 *       There are several ways this function could be implemented. 
	 * 		 No matter how you choose to implement it, you need to consider the different possible edge cases.
     * @param index
     */
    public void remove(int index){
    	swap(index, size);
        heap[size] = null;
        size--;
		percolateDown(index);
    }
    
	private void percolateUp(int index) {
    	if(index == 1) return;
    	Task child = heap[index].t;
        Task parent = heap[index/2].t;
        if (child.compareTo(parent) < 0) return;
        swap(index, index/2);
        percolateUp(index/2);
    }

	private void swap(int childIndex, int parentIndex) {
		TaskElement child = heap[childIndex];
		TaskElement parent = heap[parentIndex];
		heap[childIndex] = parent;
		heap[parentIndex] = child;
		child.heapIndex = parentIndex;
		parent.heapIndex = childIndex;
	}
	
    private void percolateDown (int index) {
	    if(2*index > size) return;
	    Task parent = heap[index].t;
	    if(2*index == size) {
	    	Task leftChild = heap[2*index].t;
	        if (parent.compareTo(leftChild) < 0)
	            swap(index, 2*index);
	        return;
	    }
	    Task leftChild = heap[2*index].t;
	    Task rightChild = heap[2*index + 1].t;
	    if(leftChild.compareTo(rightChild) > 0){
	            if(parent.compareTo(leftChild) < 0){
	            	swap(index, 2*index);
	            	percolateDown(2*index);
	            }
	    } else 
	    	if(parent.compareTo(rightChild) < 0){
	    		swap(index, 2*index + 1);
	    		percolateDown(2*index + 1);
	    	}
    }
    
    public static void main (String[] args){
    	/*
    	 * A basic test for the heap.
    	 * You should be able to run this before implementing the queue.
    	 * 
    	 * Expected outcome: 
    	 * 	task: Add a new feature, priority: 10
		 *	task: Solve a problem in production, priority: 100
		 *	task: Solve a problem in production, priority: 100
		 *	task: Develop a new feature, priority: 10
		 *	task: Code Review, priority: 3
		 *	task: Move to the new Kafka server, priority: 2
    	 * 
    	 */
    	
    	Task a = new Task(10, "Add a new feature");
    	Task b = new Task(3, "Code Review");
    	Task c = new Task(2, "Move to the new Kafka server");
    	TaskElement [] arr = {new TaskElement(a), new TaskElement(b), new TaskElement(c)};
    	TaskHeap heap = new TaskHeap(arr);
    	System.out.println(heap.findMax());
    	
    	Task d = new Task(100, "Solve a problem in production");
    	heap.insert(new TaskElement(d));
    	
    	System.out.println(heap.findMax());
     	System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());
    
    }
}
