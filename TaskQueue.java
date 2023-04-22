

/**
 * A queue class, implemented as a linked list.
 * The nodes of the linked list are implemented as the TaskElement class.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
		/*
           Assignment number : 8
           File Name : TaskQueue.java
           Name: Ran Zaaroor
           Student ID : 209374040
           Email : Ran.zaaroor@gmail.com
       	*/
public class TaskQueue {

	TaskElement first;
	TaskElement last;
	
	/**
	 * Constructs an empty queue
	 */
	public TaskQueue(){
		first = null;
		last = null;
	}
	
	/**
	 * Removes and returns the first element in the queue
	 * 
	 * @return the first element in the queue
	 */
	public TaskElement dequeue(){
		if (this.first == null){
			throw new IllegalStateException("empty queue");
		}
		TaskElement ans = this.first;
		this.first = this.first.prev;
		if(this.first != null){
			this.first.next = null;
		}
		return ans;
	}
	
	/**
	 * Returns and does not remove the first element in the queue
	 * 
	 * @return the first element in the queue
	 */
	public TaskElement peek(){
		return this.first;
	}
	
	/**
	 * Adds a new element to the back of the queue
	 * 
	 * @param node
	 */
	public void enqueue(TaskElement node){
		if (first == null){
			first = node;
			last = node;
			return;
		}
		node.next = this.last;
		this.last.prev = node;
		this.last = node;
	}
	
}

