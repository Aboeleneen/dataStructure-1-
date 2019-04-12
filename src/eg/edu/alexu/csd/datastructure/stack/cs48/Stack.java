package eg.edu.alexu.csd.datastructure.stack.cs48;

import eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48.SingleLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IStack;

public class Stack implements IStack {
	
	private SingleLinkedList stack =new SingleLinkedList();
	
	private int topIndex=-1 ;
	private int stackSize=0;

	@Override
	public  Object pop() {
		// TODO Auto-generated method stub
		if(stack.isEmpty()){
			throw new RuntimeException();
		}else {
		topIndex--;
		stackSize--;
		Object top = stack.get(stackSize);
		stack.remove(stackSize);
		return top;
		
		}
	}

	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		if(stack.isEmpty()){
			throw new RuntimeException();
		}
		return stack.get(topIndex);
	}

	@Override
	public final void push(final Object element) {
		// TODO Auto-generated method stub
		topIndex++;
		stackSize++;
		stack.add(element);
		
	}

	@Override
	public final  boolean isEmpty() {
		// TODO Auto-generated method stub
		if(stackSize==0){
		return true;
		}
		else {
			return false ;
		}
	}

	@Override
	public final  int size() {
		// TODO Auto-generated method stub
		return stackSize;
	}

}
