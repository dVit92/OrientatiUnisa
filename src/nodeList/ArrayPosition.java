package nodeList;

import eccezioni.InvalidPositionException;

public class ArrayPosition<E> implements Position<E> {

	private int index;
	private E element;
	
	public ArrayPosition(int i, E e){
		index=i;
		element=e;
	}
	
	public E element() throws InvalidPositionException {
		
		return element;
	}
	
	public int getIndex(){
		return index;
	}
	
	public E setElement(E e){
		E tmp= element;
		element=e;
		return tmp;
		
	}

	
	public int setIndex(int i){
		
		int tmp=index;
		index=i;
		return tmp;
	}
}
