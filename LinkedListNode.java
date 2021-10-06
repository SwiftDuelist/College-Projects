
public class LinkedListNode {
	
	private Object data;
	private LinkedListNode next;
	
	public LinkedListNode(Object dataTwo) {
		data = dataTwo;
		next = null;
	}
	
	//getNext
	public String toString()
    {
        String node;
        node = data + " ";
        return node;
    }
	public LinkedListNode getNext() {
		
		return next;
	}
	
	//setNext
	public void setNext(LinkedListNode A) {
		
		next = A;
	}
	
	//getData Return object type
	public Object getData() {
		
		return data;
	}
	
	//setData its parameter is object type
	public void setData(Object B) {
		
		data = B;
	}

}
