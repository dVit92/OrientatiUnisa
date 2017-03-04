package stack;

import java.util.Arrays;

import eccezioni.EmptyStackException;

public class ArrayStack<E> implements Stack<E>{

	private int capacity;
	private E S[];
	private int top=-1;
	
	//capacità di default
	public static int CAPACITY = 1000;
	
	//costruttore di default
	public  ArrayStack(){
		this(CAPACITY);
	}
	//costruttore parametrico
	@SuppressWarnings("unchecked")
	public  ArrayStack(int cap){
		capacity = cap;
		S = (E[])new Object[capacity];
	}
	
	
	public int size() {
		return top + 1;
	}

	
	public boolean isEmpty() {
		return(size()==0) ;
	}

	public E top() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException("Lo stack è vuoto");
		return S[top];
	}
	
	@SuppressWarnings("unchecked")
	public void push(E element){
		if(size()==S.length){//raddoppia la grandezza dello stack
			System.out.println("E' necessario aumentare la grandezza dello stack.\n");
			int	i=0;
			E S2[] = (E[])new Object[capacity];
			while(i!=top+1){
				S2[i] = S[i];
				i++;
			}
			capacity *= 2; 
			this.S = (E[])new Object[capacity];
			int k=0;
			while(k!=i){
				S[k] = S2[k];
				k++;
			}
		} 
		S[++top] = element;	
	}

	public E pop() throws EmptyStackException {
		E element;
		if(isEmpty())
			throw new EmptyStackException("Lo stack è vuoto");
		element = S[top];
		S[top--] = null;
		return element;
	}
	
	public void union(Stack<E> s){
		Stack<E> temp = new ArrayStack<>(capacity);
		while(!isEmpty())
			temp.push(pop());
		while(!s.isEmpty())
			temp.push(s.pop());
		while(!temp.isEmpty())
			push(temp.pop());
	}
	
	public String toString() {
		return "ArrayStack\n\t Il primo elemento è: " + top() + "\n\t Grandezza massima: " + capacity
				+ "\n\t Array: " + Arrays.toString(S);
	}
	
	

	
}
