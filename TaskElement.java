
/**
 * A wrapper class for Task.
 * This class is intended to both be used as a node in a doubly-linked list,
 * and as an element in a heap.
 * 
 *
 */
		/*
           Assignment number : 8
           File Name : TaskElement.java
           Name: Ran Zaaroor
           Student ID : 209374040
           Email : Ran.zaaroor@gmail.com
       	*/
public class TaskElement {

	TaskElement next; //the task element which comes after this in the linked list, null if this element is first.
	TaskElement prev; //the task element which comes before this in the linked list, null if this element is last.
	int heapIndex;        //the index of this element in the heap (implemented as an array).
	Task t;
	
	/**
	 * A standard constructor for the class.
	 * Is intended for use before the element is inserted into the list/heap.
	 * 
	 * @param c
	 */
	public TaskElement(Task t){
		this.heapIndex = -1;
		this.t = t;
		this.next = null;
		this.prev = null;
	}
	
	public String toString(){
		return t.toString();
	}

	public void disconnect() {
		if (this.prev != null){
			this.prev.next = this.next;
		}
		if (this.next != null){
			this.next.prev = this.prev;
		}
		
	}

}
