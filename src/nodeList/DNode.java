package nodeList;

import eccezioni.InvalidPositionException;

public class DNode<E> implements Position<E>{

	private E element;
	private DNode<E> prev, next;
	
	public DNode(DNode<E> p, DNode<E> n, E e){
		prev = p;
		next = n;
		element = e;
	}
	
	public E element() throws InvalidPositionException{
		if((prev == null)||(next == null))
			throw new InvalidPositionException("Posizione non valida.");
		
		return element;
	}
	
	public DNode <E> getNext(){ 
		return next; 
	}
	
	public DNode <E> getPrev(){ 
		return prev; 
	}
	
	public void setElement(E newEl){
		element = newEl;
	}
	
	public void setNext(DNode<E> newN){
		next = newN;
	}
	
	public void setPrev(DNode<E> newP){
		prev = newP;
	}
	

}
