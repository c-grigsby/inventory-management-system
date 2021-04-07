/*
 * Name: Christopher Grigsby 
 * Date: 3/12/21
 * Assignment: SemesterProjectPartB
 */

/**
 * Class GuitarProduct: Subclass of OnlineProduct, provides an additional set of instance variables and unique behaviors 
 * beneficial to the sale of a guitar. New instance variables relevant to the sale of a guitar include the 
 * guitar's condition,the number of strings on the instrument, and the type of wood used to construct the neck.
 * New behaviors include addHardCase(): which adds the cost of a hard case to the price of the instrument, and addSoftCase(): 
 * which adds the cost of a soft case to the price of the instrument. 
 */

public class GuitarProduct extends OnlineProduct
{
	//INSTANCE VARIABLES 
	private String condition;  
	private String neckWood; 
	private int strings; 
	private double hardCase; 
	private double softCase; 
	
	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public GuitarProduct()
	{
		condition = ""; 
		neckWood = ""; 
		hardCase = 120.00; 
		softCase = 60.00; 
	}
	
	/**
	 * Constructor for instance variables 
	 * @param productID
	 * @param description
	 * @param price
	 * @param manufacturer
	 * @param quantityAvailable
	 * @param category
	 */
	public GuitarProduct(int productID, String description, double price, String manufacturer,
			 int qtyAvailable, String condition, String neckWood, int strings)
	{
		super.setProductID(productID);
		super.setDescription(description);
		super.setPrice(price);
		super.setManufacturer(manufacturer); 
		super.setQtyAvailable(qtyAvailable); 
		this.condition = condition; 
		this.neckWood = neckWood;    
		this.strings = strings; 
		hardCase = 120.00; 
		softCase = 60.00; 
	}
	
	//ACCESSORS
	/**
	 * Method getCondition - returns the condition of the guitar
	 * @return - (a String) the data in the instance variable condition
	 */
	public String getCondition()
	{
		return condition; 
	}
	/**
	 * Method getNeckWood - returns the type of wood used to construct the neck of the instrument
	 * @return - (a String) the data in the instance variable neckWood
	 */
	public String getNeckWood()
	{
		return neckWood; 
	}
	/**
	 * Method getStrings - returns how many strings the instrument has
	 * @return - (an integer) the data in the instance variable condition
	 */
	public int getStrings()
	{
		return strings; 
	}
	
	/**
	 * Method getHardCase - returns the cost of the hard case
	 * @return - (a double) the data in the instance variable condition
	 */
	public double getHardCase()
	{
		return hardCase; 
	}
	/**
	 * Method getSoftCase - returns the cost of the hard case
	 * @return - (a double) the data in the instance variable condition
	 */
	public double getSoftCase()
	{
		return softCase; 
	}
	
	//MUTATORS
	/**
	 * Method setCondition - modifies the condition of the guitar
	 * @param - (a String) sets the data in the instance variable condition
	 */
	public void setCondition(String condition)
	{
		this.condition = condition; 
	}

	/**
	 * Method setNeckWood - modifies the type of wood used to construct the neck of the instrument
	 * @param - (a String) sets the data in the instance variable neckWood
	 */
	public void setNeckWood(String neckWood)
	{
		this.neckWood = neckWood; 
	}
	
	/**
	 * Method setStrings - modifies how many strings the instrument has
	 * @param - (an integer) sets the data in the instance variable condition
	 */
	public void setStrings(int strings)
	{
		this.strings = strings;  
	}
	
	/**
	 * Method setHardCase - modifies the cost of the hard case
	 * @param - (a double) sets the data in the instance variable condition
	 */
	public void setHardCase(double cost)
	{
		this.hardCase = hardCase; 
	}
	/**
	 * Method setSoftCase - modifies the cost of the hard case
	 * @param - (a double) sets the data in the instance variable condition
	 */
	public void setSoftCase(double cost)
	{
		this.softCase =  softCase; 
	}
	
	//BEHAVIORS
	/**
	 * Method addHardCase - allows the user to add the purchase of a hard case for the instrument
	 * @param num - (an integer) the number of cases to purchase
	 */
	public void addHardCase(int number)
	{
		int num = number; 
		super.setPrice(getPrice() + (hardCase*num)); 
	}
	
	/**
	 * Method addSoftCase - allows the user to add the purchase of a soft case for the instrument
	 * @param num - (an integer) the number of cases to purchase
	 */
	public void addSoftCase(int number)
	{
		int num = number; 
		super.setPrice(getPrice() + (softCase*num)); 
	}
	
	//PRINT METHODS
	/**
	 * Method print - displays the instance variables 
	 */
	public void print()
	{
		System.out.printf("Guitar Product[ID: %s | %s | Retail: $%.2f | ", getProductID(), getDescription(), getPrice()); 
		System.out.printf("Make: %s | Condition: %s | Neck: %s | ", getManufacturer(), condition, neckWood); 
		System.out.printf("Strings: %d | Stock: %d]%n", strings, getQtyAvailable()); 
	}
	/**
	 * Method menuPrint - simplifies the display
	 */
	public void menuPrint()
	{
		System.out.printf("[ID: %s | %s | Retail: $%.2f | ", getProductID(), getDescription(), getPrice()); 
		System.out.printf("Make: %s | Condition: %s | Neck: %s | ", getManufacturer(), condition, neckWood); 
		System.out.printf("Strings: %d | Stock: %d]%n", strings, getQtyAvailable()); 
	}
}//class
