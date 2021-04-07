/*
 * Name: Christopher Grigsby 
 * Date: 3/22/21
 * Assignment: SemesterProjectPartB
 */

/**
 * Class OnlineProduct - A subclass of Product, requires a unique set of instance variables and behaviors. 
 * New instance variables are available to provide a numerical identifier, categorize a product, record the quantity of 
 * the product available, the manufacturer of the product, and records the total number of units sold. 
 * New behaviors include inStock(): which determines the availability of the product, sellProduct(): records a sale of a 
 * product (if inStock), and returnProduct(): records the return of a product. 
 */
public class OnlineProduct extends Product
{
	//INSTANCE VARIABLES
	private int productID;  
	private String manufacturer; 
	private int qtyAvailable;
	private String category;
	private int totalSold; 
	
	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public OnlineProduct()
	{
		productID = 0; 
		manufacturer = ""; 
		qtyAvailable = 0; 
		category = ""; 
		totalSold = 0; 
	}
	/**
	 * Constructor for all instance variables
	 * @param productID - an integer to identify the product 
	 * @param description - a String describing the product 
	 * @param price - a double representing the cost of the item
	 * @param quantityAvailable - an integer representing the number of units availability
	 * @param category - a String stating the category of the product (e.g. electronics)
	 */
	public OnlineProduct(int productID, String description, double price, String manufacturer,
						 int quantityAvailable, String category) 
	{
		super.setProductID(productID); 
		super.setDescription(description);  
		super.setPrice(price); 
		this.manufacturer = manufacturer; 
		this.qtyAvailable = quantityAvailable; 
		this.category = category; 
	}
	
	
	//ACCESSORS
	/**
	 * Method getManufacturer - returns the products manufacturer
	 * @return - (a String) of the data stored in the instance variable manufacturer
	 */
	public String getManufacturer()
	{
		return manufacturer; 
	}
	/**
	 * Method getQuantityAvailable - returns the number of units available
	 * @return - (an integer) of the data stored in the instance variable QuantityAvailable
	 */
	public int getQtyAvailable()
	{
		return qtyAvailable; 
	}
	/**
	 * Method getCategory - returns the products category
	 * @return - (a String) of the data stored in the instance variable manufacturer
	 */
	public String getCategory()
	{
		return category; 
	}
	/**
	 * Method getTotalSold - returns the number of units sold
	 * @return - (an integer) of the data stored in the instance variable totalSold
	 */
	public int getTotalSold()
	{
		return totalSold; 
	}
	
	//MUTATORS
	/**
	 * Method setManufacturer - modifies the products manufacturer
	 * @param - (a String) of the data to be stored in the instance variable manufacturer
	 */
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer; 
	}
	/**
	 * Method setQuantityAvailable - modifies the number of units available
	 * @param - (an integer) of the data to be stored in the instance variable QuantityAvailable
	 */
	public void setQtyAvailable(int quantity)
	{
		this.qtyAvailable = quantity; 
	}
	/**
	 * Method setCategory - modifies the products category
	 * @param - (a String) of the data to be stored in the instance variable category
	 */
	public void setCategory(String category)
	{
		this.category = category; 
	}
	/**
	 * Method setTotalSold - modifies the number of units sold
	 * @param - (an integer) to be stored in the instance variable totalSold
	 */
	public void setTotalSold(int totalSold)
	{
		 this.totalSold = totalSold; 
	}
	
	//BEHAVIORS
	/**
	 * Method inStock - determines if the requested quantity of the item is available
	 * @param - (an integer) the requested units of the product
	 * @return - a boolean true if requested units is available, otherwise false
	 */
	public boolean inStock(int request)
	{
		if ( (qtyAvailable - request) < 0)
		{
			return false; 
		}
		else
		{
			return true; 
		}	
	}
	/**
	 * Method sellItem - determines if the requested quantity of the item is available, 
	 * and if so increments the totalSold instance variable
	 * @param - (an integer) the requested number of units 
	 * @return - a boolean if the product is in stock
	 */
	public boolean sellItem(int requested)
	{
		if(inStock(requested))
		{
			totalSold += requested; 
			qtyAvailable--; 
			return true; 
		}
		else
		{
			System.out.println("...Quantity requested is not available."); 
			return false; 
		}
	}
	/**
	 * Method returnItem - decrement the totalSold instance variable by the number of units
	 * @param - (an integer) for the number of units to be returned
	 */
	public void returnItem(int returnUnits)
	{
		totalSold -= returnUnits; 
	}
	
	//PRINT METHOD
	/**
	 * Method print - displays all instance variables of the object
	 */
	public void print()
	{
		System.out.printf("Product[ID: %s | %s | Retail: $%.2f | ", productID, getDescription(), getPrice()); 
		System.out.printf("Manufacturer: %s | In Stock: %d | Category: %s | ", manufacturer, qtyAvailable, category); 
		System.out.printf("Units Sold: %d]%n", totalSold); 
	}
}//class OnlineProduct
