/*
 * Name: Christopher Grigsby 
 * Date: 04/26/21
 * Assignment: SemesterProjectPartC
 */

/**
 * Class LinkedList: provides a LinkedList to the user, this class has been modified 
 * to accept any Type other than the 8 primitives, and added an inner class listIterator 
 */

import java.io.Serializable;
import java.util.NoSuchElementException;


public class LinkedList<T> implements Serializable
{

	public Node head; 
	
	/**
	 * Default Constructor
	 */
	public LinkedList()
	{
		head = null; 
	}
	
	/**
	 * Inner class Node - Holds the data of a node, and a pointer to the nextNode 
	 * @param T - (generic type, any type other than the 8 primitives) - the data of the node
	 */
	class Node<T> implements Serializable
	{
		public T data; 
		public Node nextNode;  
		
		public Node(T data) {
			this.data = data; 
			this.nextNode = null; 
		}
	}//class Node 
	
	public ListIterator listIterator()
	{
		return new LinkedListIterator(); 
	}
	/**
	 * Inner class LinkedListIterator - contains a reference to the last visited object 
	 */
	class LinkedListIterator implements ListIterator, Serializable
	{
		private Node position; 
		private Node previous; 
		private boolean isAfterNext; 
		
		public LinkedListIterator()
		{
			this.position = null; 
			this.previous = null; 
			this.isAfterNext = false; 
		}
		/**
		 * Method next - returns the data of the next node
		 * @return - the object stored as data in the node
		 */
		public Object next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException(); 
			}
			this.previous = this.position; 
			isAfterNext = true; 
			
			if (position == null)
			{
				position = head; 
			}
			else
			{
				position = position.nextNode; 
			}
			return position.data; 
		}
		/**
		 * Method hasNext - Confirms the next position is not at the end of the list
		 * @return a boolean condition
		 */
		public boolean hasNext()
		{
			if (position == null)
			{
				return head != null; 
			}
			else 
			{
				return position.nextNode != null; 
			}
		}
	}
	//BEHAVIORS
	/**
	 * Method addFirstNode - adds an item as the first item in Linked List
	 */
	public <T> void addFirstNode(T data) 
	{
		Node newNode = new Node(data); 
		
		if(this.head == null) 
		{
			this.head = newNode; 
		}
		else
		{
			newNode.nextNode = this.head; 
			this.head = newNode; 
		}
	}//addFirstNode
	/**
	 * Method addNodeAsTail - Adds an item to the end of the Linked List
	 * @param data - (generic type) - the data of the node
	 */
	public <T> void addNodeAsTail(T data) 
	{	
		Node newNode = new Node(data); 
		Node traversal = this.head; 
		Node previous = this.head; 
		
		if (this.head == null)
		{
			this.head = newNode; 
		}
		else
		{
			while (traversal != null)
			{
				previous = traversal; 
				traversal = traversal.nextNode; 
			}
			previous.nextNode = newNode; 
		}
	}
	/**
	 * Method addNodeAtPosition - Add a value at a given point in the linked list
	 * @param position - the position in the list, letting the first node equal 1
	 * @param data - the data of the node 
	 */
	public <T> void addNodeAtPosition(int position, T data) {
		
		Node newNode = new Node(data); 
		Node traversal = this.head; 
		Node previous = this.head; 
		int count = 0; 
		
		//first, determine if position is valid in the list
		while (traversal != null)
		{
			traversal = traversal.nextNode; 
			count++; 
		}
		
		if (position > count || position <= 0) //if invalid position, return
		{
			System.out.println("Invalid position. List begins at 1, and contains: " + count + " nodes"); 
			return; 
		}
		else  //otherwise traverse through the nodes and add newNode to appropriate location
		{
			count = 0; 
			traversal = this.head; 
			previous = this.head; 
			while(traversal != null && count < position - 1)
			{
				previous = traversal; 
				traversal = traversal.nextNode; 
				count++; 	
			}
			previous.nextNode = newNode; 
			newNode.nextNode = traversal; 
		}
	}//add node 
	
	/**
	 * Method deleteNode - Deletes the item passed in. Only the first time data is matched. 
	 * @param data - an Object, the data to be deleted (this may not work)
	 */
	public <T> void deleteNode(T data) 
	{
		//check to see if the list is empty
		if (this.head == null) {
			System.out.println("Nothing to search for. List is empty"); 
			return; 
		}
		
		//set the traversal to the previous node and the head node
		Node traversal = this.head;
		Node previous = this.head; 
		
		//if a match at first node, change the reference of that node
		//change reference to the next node and remove. 
		if (this.head.data == data)
		{
			this.head = this.head.nextNode; 
			return; 
		}
		//traverse until tail is reached or data is matched
		while(traversal != null && traversal.data != data) 
		{
			//advance to the next value 
			previous = traversal; 
			traversal = traversal.nextNode; 
		}
		//if match was found
		//move pointer to next
		if (traversal != null) 
		{
			previous.nextNode = traversal.nextNode; 
		}
		else {
			System.out.println(data + "Does not exist in Linked List"); 
		}
	}
	/**
	 * Method deleteFirst - Deletes the first item of the LinkedList. 
	 * @param - none
	 * @return - none
	 */
	public void deleteFirst() {
		
		if (this.head == null) 
		{
			System.out.println("Nothing to delete. List is empty"); 
			return; 
		}
		else 
		{
			this.head = head.nextNode; 
		}
	}
	
	/**
	 *Method deleteLast - Deletes the last item of the LinkedList.
	 */
	public void deleteLast() 
	{
		if (this.head == null) 
		{
			System.out.println("Nothing to delete. List is empty"); 
			return; 
		}
		else if (this.head.nextNode == null)
		{
			this.head = null; 
		}
		else
		{
			int count = 0; 
			int listSize = 0; 
			Node traversal = this.head; 
			Node previous = this.head; 
			
			while(traversal != null) //determine length of LinkedList
			{
				traversal = traversal.nextNode; 
				listSize++; 
			}
			
			traversal = this.head; //reset traversal 
			while(traversal != null && count < listSize - 1) //traverse to the next to last node
			{
				previous = traversal; 
				traversal = traversal.nextNode; 
				count++; 
			}
			previous.nextNode = null; //next to last node points to a null value
		}
	}
	/**
	 * Method size - returns the size of the LinkedList
	 * @param - none
	 * @return - none
	 */
	public int size()
	{
		Node traversal = this.head; 
		int count = 0; 
		
		if (this.head != null)
		{
			while (traversal != null)
			{
				traversal = traversal.nextNode; 
				count++; 
			}
			return count; 
		}
		return 0; 
	}
	//SORT
	/**
	 * Method mergeSortByID - a recursive method which performs a mergeSort on the LinkedList by changing the next pointers
	 * @param head - (a Node object) the starting node, to be passed as the .head of the LinkedList
	 * @return - a sorted version of the LinkedList (ex. products.head = products.mergeSort(products.head) )
	 */
	public Node mergeSort(Node head)
	{
		this.head = head; 
		
		if (head == null || head.nextNode == null)
		{
			return head; 
		}
		
		Node middle = getMiddle(head); //get the middle node
		Node nextOfMiddle = middle.nextNode; 
		
		middle.nextNode = null; 
		
		Node left = mergeSort(head); 
		Node right = mergeSort(nextOfMiddle); 
		Node sortedList = sortedMerge(left, right);
		
		return sortedList; 
	}
	/**
	 * Method getMiddle - gets the middle of the LinkedList
	 * @param head - (a Node object)
	 * @return - the middle Node of the LinkedList
	 */
	public Node getMiddle(Node h1)
	{
		if (h1 == null)
		{
			return h1; 
		}
		
		Node slowTraversal = h1; 
		Node fastTraversal = h1; //traverses twice as fast 
		
		while (fastTraversal.nextNode != null && fastTraversal.nextNode.nextNode != null)
		{
			slowTraversal = slowTraversal.nextNode; 
			fastTraversal = fastTraversal.nextNode.nextNode; //traverses twice as fast 
		}
		return slowTraversal; 
	}
	/**
	 * Method sortedMerge - sorts Nodes by numerical value, NOTE: utilizes the ID of a Product object
	 * to perform the sort, not a generic method
	 * @param left - (a Node object)
	 * @param right - (a Node object)
	 */
	public Node sortedMerge(Node left, Node right)
	{
		Node result = null; 
		
		if (left == null)
		{
			return right; 
		}
		if (right == null)
		{
			return left; 
		}
		Product i = (Product) left.data; 
		Product j = (Product) right.data;
		int leftID = i.getProductID(); 
		int rightID = j.getProductID(); 
		
		if (leftID < rightID)
		{
			result = left; 
			result.nextNode = sortedMerge(left.nextNode, right); 
		}
		else 
		{
			result = right; 
			result.nextNode = sortedMerge(left, right.nextNode); 
		}
		return result; 
	}
	
	//PRINT METHODS
	/**
	 * Method print - prints all items in the Linked List
	 */
	public void print() 
	{
		if (this.head == null) //check to see if the list is empty
		{
			System.out.println("Nothing to print. List is empty"); 
		}
		else
		{
			System.out.println("-- LinkedList --------------------------------------------\n"); 
			
			Node traversal = this.head; 
			
			while(traversal != null) {
				
				System.out.print(traversal.data.toString()); //check the toString out
				System.out.println(); 
				traversal = traversal.nextNode; 
			}
			System.out.println("----------------------------------------- End of List ----"); 
		}
	}
	/**
	 * Method printGuitarProduct - custom print method specifically for GuitarProduct objects
	 */
	public void printGuitarProduct()
	{
		if(this.head == null)
		{
			System.out.println("LinkedList is Empty."); 
		}
		Node traversal = this.head; 
		
		while(traversal != null) {
			GuitarProduct i = (GuitarProduct) traversal.data; 
			i.menuPrint(); 
			System.out.println(); 
			traversal = traversal.nextNode; 
		}
	}
	/**
	 * Method printAuctionProduct - custom print method specifically for GuitarProduct objects
	 */
	public void printAuctionProduct()
	{
		Node traversal = this.head; 
		
		while(traversal != null) 
		{
			AuctionProduct i = (AuctionProduct) traversal.data; 
			i.print(); 
			System.out.println(); 
			traversal = traversal.nextNode; 
		}
		
	}
}//class LinkedList
