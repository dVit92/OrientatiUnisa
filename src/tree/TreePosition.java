package tree;

import nodeList.Position;
import nodeList.PositionList;

public interface TreePosition<E> extends Position<E> {

	public void setElement(E e);
	public PositionList<Position<E>> getChildren();
	public void setChildren(PositionList<Position<E>> c);
	public TreePosition<E> getParent();
	public void setParent(TreePosition<E> v);
}
