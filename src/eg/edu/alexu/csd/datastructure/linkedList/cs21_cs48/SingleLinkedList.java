package eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48;



import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

public class SingleLinkedList implements ILinkedList {
	ListNode head = null;
	ListNode tail = head;
	int size=0;

	public void print() {
		ListNode i = head;
		while (i != null) {
			System.out.println(i.value);
			i = i.next;
		}
	}

	/**
	 * add to linked list
	 */
	public void add(final int index,final Object element) {
		if(index>size || index<0){
			 throw new RuntimeException();
		}
		ListNode newNode = new ListNode(element);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
		} else if (index==size){
			add(element);
			return;
		}else {
			ListNode i = head;
			for (int count = 0; count < index - 1; count++) {
				i = i.next;
			}
			newNode.next = i.next;
			i.next = newNode;
		}
		if(newNode.next==null){
			tail = newNode ;
		}
		size++;
		// TODO Auto-generated method stub

	}

	/**
	* @param index
	* @return the element at the specified position in this list.
	*/
	public void add(final Object element) {
		ListNode newNode = new ListNode(element);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next=tail.next;
			tail.next = newNode ;
			tail = newNode ;
			/*ListNode i = head;
			while (i.next != null) {
				i = i.next;
			}
			newNode.next = i.next;
			i.next = newNode;*/
		}
		size++;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public Object get(final int index) {
		if (index == 0) {
			return head.value;
		} else if( index<size && index >= 0) {
			ListNode i = head;
			for (int count = 0; count < index; count++) {
				i = i.next;
			}

			// TODO Auto-generated method stub
			return i.value;
		}else {
			 throw new RuntimeException();
		}
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 
	 * @param index
	 * @param element
	 */
	public void set(final int index,final Object element) {
		
		if(index>=size || index<0){
			 throw new RuntimeException();
		}
		ListNode newNode = new ListNode(element);
		if (index == 0) {
			newNode.next = head.next;
			head = newNode;

		} else {
			ListNode i = head;
			for (int count = 0; count < index - 1; count++) {
				i = i.next;
			}
			newNode.next = i.next.next;
			i.next = newNode;
			
		}
		if(newNode.next==null)
		{
			tail= newNode ;
		}
		// TODO Auto-generated method stub

	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
	/*	ListNode i = head;
		for (int count = 0; count < size; count++) {
			i = i.next;
		}*/
		head = null;
		tail =head ;
		size = 0;
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}

		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	public void remove(final int index) {
		// TODO Auto-generated method stub
		
		if(index>= size || index<0){
			 throw new RuntimeException();
		}
		if (index == 0) {
			head = head.next;
		} else {
			ListNode i = head;
			for (int count = 0; count < index - 1; count++) {
				i = i.next;
			}
			ListNode j = i.next;
			i.next = j.next;

		}
		size--;
	}

	/**
	 * 
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * @param fromIndex
	 * @param toIndex
	 * @return a view of the portion of this list between the specified fromIndex
	 *         and toIndex, inclusively.
	 */
	public ILinkedList sublist(final int fromIndex, final int toIndex) {
		
		if(fromIndex<0 || toIndex>=size ||fromIndex>toIndex ){
			 throw new RuntimeException();
		}
		ListNode i = head;
		SingleLinkedList newList = new SingleLinkedList();

		for (int count = 0; count <= toIndex; count++) {
			
			if (count <= toIndex && count >= fromIndex) {
				newList.add(i.value);
			}
			i = i.next;
		}
		// TODO Auto-generated method stub
		return newList;
	}

	/**
	 * 
	 */
	public boolean contains(final Object o) {
		ListNode i = head;
		if(o == null)	
		{
			return false ;
		}
			for (int count = 0; count < size; count++) {

				if (i.value == o) {
					return true ;
				}
				i = i.next;
			}
		
		
	return false;
		}
	}
	// TODO Auto-generated method stub

