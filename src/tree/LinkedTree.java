package tree;

import java.util.Iterator;

import nodeList.NodePositionList;
import nodeList.Position;
import nodeList.PositionList;
import eccezioni.BoundaryViolationException;
import eccezioni.EmptyTreeException;
import eccezioni.InvalidPositionException;
import eccezioni.NonEmptyTreeException;
import eccezioni.UndeletableNodeException;

public class LinkedTree<E> implements Tree<E> {

	private TreePosition<E> root;
	private int size;
	
	public LinkedTree(){
		root= null;
		size=0;
	}
	
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = new NodePositionList<>();
		for(Position<E> pos: positions)
			elements.addLast(pos.element());
		return elements.iterator();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size() == 0);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = 	new NodePositionList<Position<E>>();
		if(size != 0)
			preorderPositions(root(), positions);
		return positions;
	}

	private void preorderPositions(Position<E> v, PositionList<Position<E>> pos)throws InvalidPositionException {
		pos.addLast(v);
		if(isExternal(v))
			return;
		Iterator<Position<E>> itC = children(v).iterator();
		while (itC.hasNext())
			preorderPositions(itC.next(), pos);
	}

	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> root() throws EmptyTreeException {
		if(isEmpty())
			throw new EmptyTreeException("L'albero è vuoto.");
		return root;
	}

	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<E> vv = checkPosition(v);
		Position<E> parentPos= vv.getParent();
		if(parentPos==null)
			throw new BoundaryViolationException("Nessun genitore,è una radice");
		
		
		return parentPos;
	}

	private TreePosition<E> checkPosition(Position<E> v) throws InvalidPositionException{
		if((v == null)||!(v instanceof TreePosition))
			throw new InvalidPositionException("Error.");
		return (TreePosition<E>)v;
	}

	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TreePosition<E> tv = checkPosition(v);
		if(isExternal(v))
			throw new InvalidPositionException("Il nodo è una foglia, non ha figli.");
		return (Iterable<Position<E>>) tv.getChildren();
	}

	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TreePosition<E> tv = checkPosition(v);
		return (tv.getChildren() == null);
	}

	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return !isExternal(v);
	}

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}

	public E removeRoot() throws EmptyTreeException, UndeletableNodeException{
		if(isEmpty())
			throw new EmptyTreeException("L'albero è vuoto, non vi è alcuna radice.");
		if(size() > 1)
			throw new UndeletableNodeException("La radice ha figli, non posso eliminare.");
		E toRet = root.element();
		root = null;
		size = 0;
		return toRet;
	}
	
	public E removeExternalChild(Position<E> v) throws EmptyTreeException, UndeletableNodeException{
		TreePosition<E> tv = checkPosition(v);
		if(isExternal(tv))
			throw new InvalidPositionException("E' una foglia, non ha figli da eliminare.");
		PositionList<Position<E>> children = tv.getChildren();
		if(!isExternal(children.first().element()))
			throw new UndeletableNodeException("Il figlio del nodo passato, non è una foglia, non si può eliminare.");
		E toRet = children.remove(children.first()).element();
		size--;
		if(children.isEmpty())
			tv.setChildren(null);
		return toRet;
	}
	
	public E remove(Position<E> v)throws InvalidPositionException{
		
		if(isEmpty()||isInternal(v))
			throw new InvalidPositionException("Posizione nodo non valida.");
		
		TreePosition<E> tv = checkPosition(v);
		
		if(isRoot(tv))
			return removeRoot();
		
		E toRet = tv.element();
		TreePosition<E> parent = tv.getParent();
		PositionList<Position<E>> children = parent.getChildren();
		for(Position<Position<E>> c: children.positions())
			if(c.element() == v)
				children.remove(c);
		if(parent.getChildren().isEmpty()) 
			parent.setChildren(null);
		size--;
		return toRet;
	}

	public Position<E> addRoot(E e)throws NonEmptyTreeException{
		if(!isEmpty())
			throw new NonEmptyTreeException("L'albero non è vuoto.");
		root = new TreeNode<E>(e, null, null);
		size++;
		return root;
	}
	
	public Position<E> addChild(E e, Position<E> v)throws InvalidPositionException{
		TreePosition<E> tv = checkPosition(v);
		TreeNode<E> child = new TreeNode<>(e, tv, null);

		if(isExternal(v)){
			PositionList<Position<E>> childrenList = new NodePositionList<Position<E>>();        //se è una foglia
			tv.setChildren(childrenList);
		}
		tv.getChildren().addLast(child);
		
		return child;
		
	}

	
}
