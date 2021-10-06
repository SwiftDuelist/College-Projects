// Part of Quiz 2 due on 04/07/20
// ObjectStack will borrow some methods from the LinkedList class
public class ObjectStack extends linkedList implements Stack {

	// How many elements in the Stack
	private int size = 0;
	
	public ObjectStack() {
		// Call constructor
		super();
	}
	
	
	// Push: use insertFront to implement push
	public void push(Object myData) {
		
		// Fill in body
		insertFront(myData);
		size++;
	}
	
	// Pop: use removeFront to implement pop
	public Object pop() throws EmptyStackException{
		
		size--;
		return removeFront();
		// Fill in body
	}
	
	// top: use getHead to implement top
	public Object top() throws EmptyStackException{
		
		if(isEmpty()) {
			return null;
		}else
		// Fill in body
		return getHead().getData();
	}
	
	public boolean isEmpty() {
		
		// Fill in body
		return size<=0;
	}
	
	public int size() {
		// Fill in body
		return size;
	}
	
}
