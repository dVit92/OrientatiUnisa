package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import stack.Node;

public class LinkedIterator<E> implements Iterator<E> {

	private Node<E> head;
	private int size;
	
	public LinkedIterator(E[] A){
		head = null;
		size = A.length;
		for(int i = 0; i<size; i++){
			Node<E> tmp = new Node<E>(A[i],head);
			head = tmp;
		}
	}
	
	public boolean hasNext() {
		return (size > 0);
	}

	public E next() throws NoSuchElementException{
		if(!hasNext())
			throw new NoSuchElementException("Non vi sono altri elementi.");
		E toRet = head.getElement();
		head = head.getNext();
		size--;
		return toRet;
	}

	public void remove()throws UnsupportedOperationException {
		 throw new UnsupportedOperationException("remove");
	} 

}
