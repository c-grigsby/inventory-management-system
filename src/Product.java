
/*
 * Name: Christopher Grigsby 
 * Date: 4/26/21
 * Assignment: SemesterProjectPartC
 */

/**
 * Class Product - provides a description of a product intended for sale. 
 */

import java.io.Serializable;

public class Product implements Serializable
{
	//INSTANCE VARIABLES 
	private int productID; 
	private String description;
	private double price;  	
	
	//CONSTRUCTORS
	/**
	 * Default Constructor 
	 */
	public Product()
	{   
		productID = 0; 
		description = " "; 
		price = 0; 
	}
	
	/**
	 * Constructor to set values to attributes 
	 * @param description - name/description of product 
	 * @param price - product price 
	 */
	public Product(int productID, String description, double price)
	{
		this.productID = productID; 
		this.description = description; 
		this.price = price; 
	}
	
	//MUTATORS 
	/**
	 * Method setProductID - modifies the products identification number
	 * @param - (an integer) of the data to be stored in the productID
	 */
	public void setProductID(int productID)
	{
		this.productID = productID; 
	}
	/**
	 * Method setDescription - Sets a String type value for the instance variable 'description'
	 * @param description - the data to be stored in the instance variable 'description' 
	 * @return - none 
	 */
	public void setDescription(String description)
	{
		this.description = description; 
	}
	/**
	 * Method setPrice - Sets a double type value for the instance variable 'price'
	 * @param price - the data to be stored in the instance variable 'price' 
	 * @return - none 
	 */
	public void setPrice(double price)
	{
		this.price = price; 
	}
	
	//ACCESSORS
	/**
	 * Method getProductID - returns the products identification number
	 * @return - (an integer) of the data stored in the instance variable productID
	 */
	public int getProductID()
	{
		return productID; 
	}
	/**
	 * Method getDescription - returns the data (type String) from instance variable 'description'
	 * @param - none
	 * @return - the data stored in the instance variable 'description'
	 */
	public String getDescription()
	{
		return description; 
	}
	/**
	 * Method getPrice - returns the data (type double)from the instance variable 'price'
	 * @param - none
	 * @return - the data stored in the instance variable 'price'
	 */
	public double getPrice()
	{
		return price; 
	}
	
	//PRINT METHOD
	/**
	 * Method toString - provides a toString method for all instance variables
	 */
	public String toString()
	{
		String output =  String.format("%32s%n", "PRODUCT# " + productID)
						+String.format("%-50s%s%n", "Description:", "Price:")
						+String.format("%-49s $%.2f%n", description, price); 
		return output;
	}
	/**
	 * Method print() - no return type print
	 */
	public void print()
	{
		System.out.printf("%32s%n", "PRODUCT# " + productID); 
		System.out.printf("%-50s%s%n", "Description:", "Price:"); 
		System.out.printf("%-49s $%.2f%n", description, price); 
	}
}//Product class
