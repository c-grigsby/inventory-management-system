/*
 * Name: Christopher Grigsby 
 * Date: 03/11/2021
 * Assignment: SemesterProjectPartB
 */

/**
 * class Register - simulates a cash register for the US dollar
 */

import java.util.ArrayList; 

public class CashRegister 
{
	//Instance Variables
	private ArrayList<Double> register; 
	private double salesTax; 
	/*
	 * default constructor
	 */
	public CashRegister() 
	{
		register = new ArrayList<Double>(); 
		salesTax = 0;  
	}//default constructor 
	
	/*
	 * default constructor
	 */
	public CashRegister(double salesTax) 
	{
		register = new ArrayList<Double>(); 
		this.salesTax = salesTax; 
	}//default constructor 
	
	//MUTATORS
	/**
	 * Method addItems - Adds an item to cash register
	 * @param price: the price of the item
	 * @return - void 
	 */
	public void addItem(double price) 
	{
		register.add(price); 
	}//addItem
	/**
	 * Method setTax - updates the sales tax
	 * @param salesTax - a double of the new tax
	 */
	public void setTax(double salesTax)
	{
		this.salesTax = salesTax; 
	}
	
	//ACCESSORS
	/**
	 * Method getTotal - returns a double of the total of items 
	 * @param - none
	 * @return - a double representing the total price of the items 
	 */
	public double getTotal()
	{
		double total = 0; 
		
		for (Double item : register)
		{
			total += item; 
		}
		return total; 
		
	}//getTotal
	/**
	 * Method getTotalAfterTax - returns the total after sales tax
	 * @return - a double of the total after tax
	 */
	public double getTotalAfterTax()
	{
		double total = 0;
		double tax = 0; 
		
		for (Double item : register)
		{
			total += item; 
		}
		
		tax = total*salesTax; 
		
		return total + tax; 
	}//getTotalAfterTax
	/**
	 * Method getTaxTotal - returns the sales tax of the current total	
	 * @return - the sales tax
	 */
	public double getSalesTax()
	{
		double total = 0;
		double tax = 0; 
		
		for (Double item : register)
		{
			total += item; 
		}
		
		tax = total*salesTax; 
		return tax; 
	}//getSalesTax
	/**
	 * Method getCount - returns a count of the number of items in the array 
	 * @param - none
	 * @return - an integer representing a total count of items in the array 
	 */
	public int getCount() 
	{
		int count = 0; 
		
		for (Double item : register)
		{
			count++; 
		}
		return count; 
	}//getCount
	
	/**
	 * Method getRegister - returns the register array 
	 * @return - the double array of entered items
	 */
	public ArrayList<Double> getRegister()
	{
		return register; 
	}
	/**
	 * Method getChange - returns a value of an amount minus the total
	 * @param amount - the remainder of amount minus total
	 */
	public double getChange(double amount)
	{
		return (amount - getTotalAfterTax()); 
	}
	
	/**
	 * Method clear - empties the contents of the register/array 
	 * @param - none 
	 * @return - none 
	 */
	public void clear()
	{
		register = new ArrayList<Double>(); 
	}//clear
	
	/**
	 * Method displayAll - displays the prices of all items in the current sale 
	 * @param - none 
	 * @return - none 
	 */
	public void displayAll()
	{
		System.out.println("\n----Displaying All Items----");
		for(int i = 0; i < register.size(); i++)
		{
			System.out.printf("Item %d   ", i+1); 
			System.out.printf("$%.2f%n", register.get(i));
		}
		System.out.println("----End of Display----\n"); 
	}
}//class
