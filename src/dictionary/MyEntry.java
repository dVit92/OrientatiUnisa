package dictionary;

import java.util.Map.Entry;


public class MyEntry<K, V> implements Entry<K,V> {

	protected K key;
	protected V value;
	public MyEntry(K k, V e) {
		key = k; value = e;
	}
	public K getKey() { 
		return key; 
	}
	public V getValue() { 
		return value; 
	}
	
	public V setValue(V value) {
		
		return null;
	}



}
