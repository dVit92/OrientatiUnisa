package stack;

import eccezioni.EmptyStackException;

public class NodeStack<E> implements Stack<E>{

	protected Node<E> top;
	protected int size;
	
	public NodeStack(){
		top = null;
		size = 0;
	}

	public E pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("Stack vuoto.");
		E temp = top.getElement();
		top = top.getNext();
		size--;
		return temp;
	}
	
	public void push(E elem) {
		Node<E> v = new Node<E>(elem,top);
		top = v;
		size++;
	}
	
	public E top() throws EmptyStackException {
		if (isEmpty()) throw new EmptyStackException("Stack vuoto");
		
		return top.getElement();
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return(size()==0);
	}

	@Override
	public String toString() {
		return "NodeStack [top=" + top + ", size=" + size + ", pop()=" + pop()
				+ ", top()=" + top() + ", size()=" + size() + ", isEmpty()="
				+ isEmpty() + "]";
	}
	
	

}
