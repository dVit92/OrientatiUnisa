package dictionary;
import java.util.Map.Entry;

import eccezioni.InvalidEntryException;
import eccezioni.InvalidKeyException;
import nodeList.NodePositionList;
import nodeList.Position;
import nodeList.PositionList;


//Un logfile è un dizionario implementato tramite una lista non ordinata
public class LogFile<K, V> implements Dictionary<K, V>
{
	protected PositionList<Entry<K, V>> logfile;

	public LogFile() {
		logfile = new NodePositionList<Entry<K, V>>();
	}
	
	
	
	
	public int size() {
		return logfile.size();
	}

	public boolean isEmpty() {
		return logfile.isEmpty();
	}

	private void checkKey(K key) throws InvalidKeyException
	{
		if (key == null)
			throw new InvalidKeyException("Key non valida");
	}

	public Entry<K, V> find(K key) throws InvalidKeyException
	{
		checkKey(key);
		for (Position<Entry<K, V>> p : logfile.positions())
		{
			if (p.element().getKey().equals(key))
				return p.element();
		}
		return null;
	}

	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException
	{
		checkKey(key);
		PositionList<Entry<K, V>> toReturn = new NodePositionList<Entry<K, V>>();
		for (Position<Entry<K, V>> p : logfile.positions())
		{
			if (p.element().getKey().equals(key))
				toReturn.addLast(p.element());
		}
		return toReturn;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException
	{
		checkKey(key);
		Entry<K, V> e = new MyEntry<K, V>(key, value);
		logfile.addLast(e);
		return e;
	}

	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException
	{
		checkEntry(e);
		if (isEmpty())
			return null;
		for (Position<Entry<K, V>> p : logfile.positions())
		{
			if (p.element().equals(e))
				return logfile.remove(p);
		}
		return null;
	}

	private MyEntry<K, V> checkEntry(Entry<K, V> e)
	{
		if (e == null || !(e instanceof MyEntry))
			throw new InvalidEntryException("Entry non valida");

		return (MyEntry<K, V>) e;
	}

	public Iterable<Entry<K, V>> entries()
	{
		PositionList<Entry<K, V>> toReturn = new NodePositionList<Entry<K, V>>();
		if (isEmpty())
			return toReturn;
		for (Entry<K, V> e : logfile) {
			toReturn.addLast(e);
		}
		return toReturn;
	}

	protected void insertEntry(Entry<K, V> e)
	{
		checkEntry(e);
		logfile.addLast(e);
	}
	

	

	
}
