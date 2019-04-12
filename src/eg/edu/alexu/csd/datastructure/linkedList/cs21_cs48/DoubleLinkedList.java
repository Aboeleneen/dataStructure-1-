package eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48;
import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
public class DoubleLinkedList implements ILinkedList {
ListNode2 head = null;
	ListNode2 tail =null ;
int size;

/*
 * @javadoc
 */

public void print() {
ListNode2 i = head;
		while (i != null) {
			System.out.println(i.value);
			i = i.next;
		}
	}

	/**
	 * Inserts a specified element at the specified position in the list.
	 * 
	 * @param index
	 * @param element
	 */
	@Override
	public void add(final int index,final Object element) {
		
		if(index>size || index<0)
		{
			throw new RuntimeException();
		}
		ListNode2 newNode = new ListNode2(element);
		if (index == 0) {
			newNode.next = head;
			head = newNode;
			newNode.prev = null;
			head.prev = newNode ;
		} else {
			ListNode2 i = head;
			for (int count = 0; count < index - 1; count++) {
				i = i.next;
			}
			newNode.next = i.next;
			i.next = newNode;
			// i.next.prev = newNode;
			newNode.prev = i;
			if (newNode.next != null) {
				newNode.next.prev = newNode;
			}
		}
		if(newNode.next==null){
			tail = newNode; 
		}
		size++;

		// TODO Auto-generated method stub

	}

	/**
	 * Inserts the specified element at the end of the list.
	 * 
	 * @param element
	 */
	@Override
	public void add(final Object element) {
		ListNode2 newNode = new ListNode2(element);
		newNode.next = null;
	//	ListNode2 i = head;
		if (head == null) {
			newNode.prev = null;
			head = newNode;
			tail = newNode ;

		} else {
			newNode.prev=tail;
			tail.next= newNode ;
			tail=newNode;
			
			/*while (i.next != null) {
				i = i.next;
			}
			newNode.prev = i;
			i.next = newNode;*/

		}

		size++;

		// TODO Auto-generated method stub

	}

	/**
	 * @param index
	 * @return the element at the specified position in this list.
	 */
	@Override
	public Object get(int index) {
		if (index == 0) {
			return head.value;
		} else if (index < size && index >= 0) {
			ListNode2 i = head;
			for (int count = 0; count < index; count++) {
				i = i.next;
			}
			return i.value;
		} else {
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
	@Override
	public void set(int index, Object element) {
		ListNode2 newNode = new ListNode2(element);

		if (index == 0) {
			newNode.next = head.next;
			head = newNode;
			newNode.next.prev = newNode;
			newNode.prev = null;
		} else if (index < size && index >= 0) {
			ListNode2 i = head;
			for (int count = 0; count < index - 1; count++) {
				i = i.next;
			}
			newNode.next = i.next.next;
			i.next = newNode;
			newNode.prev = i;
			if (newNode.next != null) {
				newNode.next.prev = newNode;
			}

		} else {
			throw new RuntimeException();
		}
		// TODO Auto-generated method stub

	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		ListNode2 i = head;
		for (int count = 0; count < size; count++) {
			i = i.next;
		}
		head = i;
		tail=null;
		size = 0;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		
		if(size==0)
		{
			return true ;
		}

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(int index) {
		if (index == 0) {
			head = head.next;
			head.prev = null;
			//head.next = null;
		} else if (index==size-1){
			ListNode2 m = tail.prev;
			tail.prev=null;
			tail=m;
			
		}
		else if(index <size && index>=0) {
			ListNode2 i = head;
			for (int count = 0; count < index-1; count++) {
				i = i.next;
			}
			ListNode2 j = i.next;
			i.next = j.next;
			if (j.next != null) {
				j.next.prev = i;
			}
		}else {
			throw new RuntimeException();
		}
		size--;
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		
		if(fromIndex<0 || toIndex>=size || fromIndex>toIndex)
	
		{
			throw new RuntimeException();
		}
		ListNode2 i = head;
		DoubleLinkedList newList = new DoubleLinkedList();

		for (int count = 0; count <= toIndex; count++) {
			
			if (count <= toIndex && count >= fromIndex) {
				newList.add(i.value);
			}
			i = i.next;
		}
		
		return newList;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		ListNode2 i = head;
		if(o == null)
		{
			return true ;
		}
	
			for (int count = 0; count < size; count++) {

				if (i.value == o) {
				return true ;
				}
				i = i.next;
			}
		
			return false;
	
	}}
