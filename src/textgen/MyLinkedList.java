package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	private static final int LONG_LIST_LENGTH =10;

	static MyLinkedList<String> shortList;
	static MyLinkedList<Integer> emptyList;
	static MyLinkedList<Integer> longerList;
	static MyLinkedList<Integer> list1;

	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element)
	{
		// TODO: Implement this method
		size++;
		LLNode<E> node = new LLNode<>(element);
		node.next = tail;
		node.prev = tail.prev;
		tail.prev.next = node;
		tail.prev = node;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		// TODO: Implement this method.
		try{
			LLNode<E> e = head.next;
			for(int i = 0; i < index; i++){
				e = e.next;
			}
			return e.data;
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Error! Incorrect index!");
			return null;
		}
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		try {
			size++;
			LLNode<E> node1 = head.next;
			for (int i = 0; i < index; i++) {
				node1 = node1.next;
			}
			LLNode<E> node2 = new LLNode<>(element);

			node2.prev = node1.prev;
			node1.prev.next = node2;
			node1.prev = node2;
			node2.next = node1;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Error! Incorrect index!");
		}

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index)
	{
		// TODO: Implement this method
		try{
			size--;
			LLNode<E> node = head.next;
			for(int i = 0; i < index; i++){
				node = node.next;
			}

			node.prev.next = node.next;
			node.next.prev = node.prev;

			return node.data;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Error! Incorrect index!");
			return null;
		}
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		try{
			LLNode<E> node = head.next;
			for(int i = 0; i < index; i++){
				node = node.next;
			}

			node.data = element;

			return element;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("Error! Incorrect index!");
			return null;
		}
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
