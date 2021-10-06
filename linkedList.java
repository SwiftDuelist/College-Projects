
public class linkedList {
	
	private LinkedListNode head;
	
	public linkedList() {
		
		head = null;
	}
	
	//getHead
	public LinkedListNode getHead() {
		
		return head;
	}
	
	//setHead
	public void setHead(LinkedListNode H) {
		
		head = H;
	}
	
	// add a node at the end of the list
	// after insertBack(30): 10 -> 50 -> 30
	public void insertBack(Object myData) {
		
		LinkedListNode place = new LinkedListNode(myData);
		
		if(head == null) {
			head = place;
		}else {
			
			LinkedListNode placeTwo = new LinkedListNode(head);
			placeTwo.setNext(head);
			
			while(placeTwo.getNext() != null) {
				
				placeTwo = placeTwo.getNext();
			}
			placeTwo.setNext(place);
		}
		
	}
	
	// remove a node at the end of the list
	// Current list 10 -> 50 -> 30
	// after removeBack: 10 -> 50
	public Object removeBack(){
		
		LinkedListNode current = new LinkedListNode(head);
		current.setNext(head);

		while (current.getNext().getNext() != null) {
            current = current.getNext();
            }
		Object end = current.getNext();
		current.setNext(null);
		current = null;
		
		return end;
	}	
	//Add node in front of list
	public void insertFront(Object myData) {
		
		LinkedListNode A = new LinkedListNode(myData);
		if (head == null)
			head = A;		//Inserts new node into list if list is empty
		else
		{					
			A.setNext(head);	//Sets next to the head of the list and replaces head with new node
			head = A;
		}
	}
	
	//Remove the first node from the list
	public Object removeFront() {
		
		if (head == null)
		{
			System.out.println("Error: List is empty.");
			return null;
		}
		else
		{
			Object myData = head.getData();
			LinkedListNode t = head;
			head = head.getNext();	//Change head to next Node
			t.setNext(null);		//Detach Node t from list
			return myData;
		}

		
	}
	
}
