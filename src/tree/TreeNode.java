package tree;

import nodeList.Position;
import nodeList.PositionList;

public class TreeNode<E> implements TreePosition<E> {

	private E element;
	private TreePosition<E> parent;
	private PositionList<Position<E>> children;
	
	public TreeNode(){}
	
	public TreeNode(E e, TreePosition<E> p, PositionList<Position<E>> c){
		setElement(e);
		setParent(p);
		setChildren(c);
	}
	
	public E element() {
		return element;
	}

	public void setElement(E e) {
		element = e;
	}

	public PositionList<Position<E>> getChildren() {
		return children;
	}

	public void setChildren(PositionList<Position<E>> c) {
		children = c;
	}

	public TreePosition<E> getParent() {
		return parent;
	}

	public void setParent(TreePosition<E> v) {
		parent = v;
	}

}
