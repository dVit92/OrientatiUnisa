package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import nodeList.Position;
import nodeList.PositionList;

public class ElementIterator<E> implements Iterator<E>{
	
	protected PositionList<E> list;
	protected Position<E> cur = null;
	
	public ElementIterator(PositionList<E> L) {
		 list = L;
		 if (!list.isEmpty())
			 cur = list.first();
	}
	
	public boolean hasNext() {
		return (cur != null);
	}

	public E next() throws NoSuchElementException{
		if(!hasNext())
			throw new NoSuchElementException("Non vi sono altri elementi.");
		E toRet = cur.element();
		if(cur == list.last())//ultimo elemento della lista
			cur = null;
		else
			cur = list.next(cur);
		return toRet;
	}

	public void remove()throws UnsupportedOperationException {
		 throw new UnsupportedOperationException("remove");
	} 

}
