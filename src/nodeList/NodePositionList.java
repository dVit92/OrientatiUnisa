package nodeList;

import iterator.LinkedIterator;

import java.util.Iterator;

import eccezioni.BoundaryViolationException;
import eccezioni.EmptyListException;
import eccezioni.InvalidPositionException;

public class NodePositionList<E> implements PositionList<E> {

	protected int numElts;
	protected DNode<E> header, trailer;
	
	
	public NodePositionList(){
		numElts = 0;
		header = new DNode<E>(null, null, null);
		trailer = new DNode<E>(header,null, null);
		header.setNext(trailer);
	}
	
	DNode<E> CheckPosition(Position<E> p)throws InvalidPositionException{
		if (p == null)
			throw new InvalidPositionException("Posizione nulla passata a NodeList");
		if (p == header) 
			throw new InvalidPositionException("Header non è una posizione valida");
		if (p == trailer) 
			throw new InvalidPositionException("Trailer non è una posizione valida"); 
		
		try { 
			DNode <E>temp = (DNode<E>) p;
			if ((temp.getPrev() == null) || (temp.getNext() == null))
				throw new InvalidPositionException("Posizione non appartenente ad una valida NodeList");
			return temp;
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("Posizione di tipo sbagliato per questo contenitore"); 
		}
	} 
	
	
	public Position<E> addBefore(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = CheckPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v.getPrev(),v,e);
		v.getPrev().setNext(newNode);
		v.setPrev(newNode);
		return newNode;
	}

	public Position<E> addAfter(Position<E> p, E e)throws InvalidPositionException {
		DNode<E> v = CheckPosition(p);
		numElts++;
		DNode<E> newNode = new DNode<E>(v,v.getNext(),e);
		v.getNext().setPrev(newNode);
		v.setNext(newNode);
		return newNode;
	}

	public void addFirst(E e) {
		DNode<E> v= new DNode<E>(header, header.getNext(), e);
		numElts++;
		header.getNext().setPrev(v);
		header.setNext(v);
	}

	public void addLast(E e) {
		DNode<E> v= new DNode<E>(trailer.getPrev(), trailer, e);
		numElts++;
		trailer.getPrev().setNext(v);
		trailer.setPrev(v);
	}

	public E remove(Position<E> p) throws InvalidPositionException {
		if(p == null)
			throw new InvalidPositionException("errore");
		DNode<E> v = CheckPosition(p);
		numElts--;
		E el =  v.element();
		v.getPrev().setNext(v.getNext());
		v.getNext().setPrev(v.getPrev());
		v.setPrev(null);
		v.setNext(null);
		v.setElement(null);
		return el;
	}

	public E set(Position<E> p, E e) throws InvalidPositionException {
		DNode<E> v = CheckPosition(p);
		E el = v.element();
		v.setElement(e);
		return el;
	}

	public Position<E> first() throws EmptyListException {
		DNode<E> v = CheckPosition( header.getNext());
		if(isEmpty())
			throw new EmptyListException("NodeList vuota");
		return v;
	}

	public Position<E> last() throws EmptyListException {
		DNode<E> v = CheckPosition(trailer.getPrev());
		if(isEmpty())
			throw new EmptyListException("NodeList vuota");
		return v;
	}

	public Position<E> prev(Position<E> p) throws EmptyListException, BoundaryViolationException {
		DNode<E> v = CheckPosition(p);
		if(v == header)                         
			throw new BoundaryViolationException("Errore, non è possibile andare oltre l'inizio della lista");
		return v.getPrev();
	}

	public Position<E> next(Position<E> p) throws EmptyListException, BoundaryViolationException {
		DNode<E> v = CheckPosition(p);
		if(v == trailer) 
			throw new BoundaryViolationException("Errore, non è possibile andare oltre la fine della lista");
		return v.getNext();
	}
	
	public int size() {
		return numElts;
	}

	public boolean isEmpty() {
		return (numElts == 0);
	}
	
	public void reverse(){
		if(isEmpty()) 
			return;
		reverseAux(first());
	}


	private void reverseAux(Position<E> p){

		if(p==last()) 
			return;
		reverseAux(next(p));
		E first=remove(p);
		addLast(first);


	}
	

	
	public String toString() {
		String s = "(";
		DNode<E> curr = header.getNext();

		while (curr != trailer)
		{
			s += curr.element();
			if (curr.getNext() != trailer)
				s += ", ";
			curr = curr.getNext();
		}
		s += ")";

		return "NodePositionList\n\t Numero di elementi: " + numElts + "\n\t Elementi: " + s;
	}


	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		E []toRet= (E[ ])new Object[size()];
		//copia gli elementi nell’array da passare al 
		//costruttore di LinkedIterator 
		if(!isEmpty()){
			Position<E> current=first();
			for(int i=0;i<size()-1;i++){ 
				toRet[i]=current.element();
				current=next(current);
			}
			toRet[size()-1]=last().element();
		} //fine if!
		return (new LinkedIterator<E>(toRet));
	}
	
	public Iterable <Position <E>> positions(){
		PositionList <Position<E>> toReturn = new NodePositionList <Position<E>>();
		if(!isEmpty()){ 
			Position <E> current=first();
			for(int i=0;i<size()-1;i++){
				toReturn.addLast(current);
				current=next(current);
			} 
			toReturn.addLast(last()); } //fine if!
		return (toReturn);
	}
	

	
}
