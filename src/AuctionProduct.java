
/*
 * Name: Christopher Grigsby 
 * Date: 3/22/21
 * Assignment: SemesterProjectPartB
 */

/**
 * Class AuctionProduct: Subclass of Product. This class requires an additional set of 
 * instance variables and unique behaviors to accommodate the requirements of an auction 
 * product. New instance variables relevant to the auction of a  product include the 
 * currentBid,the minimumBid allowed, the buyerID number, and a listing status. 
 * New behaviors include placeBid(): which accepts a bid on the item (if best offer), 
 * otherwise rejects the offer and acceptOffer(): which updates the listing status.
 * 
 * Note: To allow the AuctionProduct class to be as reusable as possible it provides a
 * constructor that will accept any object of the superclass type Product, which can then be 
 * auctioned. 
 */

import java.io.Serializable;

public class AuctionProduct extends Product implements Serializable
{ 	  
	//INSTANCE VARIABLES
	private Product obj;  
	private double currentBid;  
	private double minimumBid; 
	private int buyerID; 
	private boolean listingStatus; 

	//CONSTRUCTOR
	/**
	 * Default Constructor
	 */
	public AuctionProduct()
	{
		currentBid = 0; 
		minimumBid = 0; 
		buyerID = 0; 
		listingStatus = true; 
	}
	/**
	 * Constructor to utilize any object type Product (Product, OnlineProduct, GuitarProduct)
	 * @param obj - (type Product) an existing object to be auctioned
	 * @param currentBid - (a double) the highest current bid on the product
	 * @param minimumBid - (a double) the minimum acceptable bid on the product
	 * @param listingStatus - (a boolean) to determine if the listing is active
	 */
	public AuctionProduct(Product obj, double currentBid, double minimumBid, int buyerID, boolean listingStatus)
	{
		this.obj = obj; 
		super.setProductID(obj.getProductID()); 
		super.setDescription(obj.getDescription()); 
		this.currentBid = currentBid; 
		this.minimumBid = minimumBid; 
		this.buyerID = buyerID;  
		this.listingStatus = listingStatus; 
	}
	/**
	 * Constructor for all instance variables 
	 * @param ID - (an integer) the product ID number
	 * @param description - (a String) a description of the product
	 * @param currentBid - (a double) the highest current bid on the product
	 * @param minimumBid - (a double) the minimum acceptable bid on the product
	 * @param buyerID - (an integer) the buyer ID number
	 * @param listingStatus - (a boolean) to determine if the listing is active
	 */
	public AuctionProduct(int ID, String description, double price, double currentBid, double minimumBid, int buyerID, boolean listingStatus)
	{
		obj = null; 
		super.setProductID(ID);
		super.setDescription(description); 
		this.currentBid = currentBid; 
		this.minimumBid = minimumBid; 
		this.buyerID = buyerID;  
		this.listingStatus = listingStatus; 
	}
	
	//ACCESSORS
	/**
	 * Method getCurrentBid - return the current bid on the product 
	 * @return - (a double) the data of the instance variable currentBid
	 */
	public double getCurrentBid()
	{
		return currentBid; 
	}
	/**
	 * Method getMinimumBid - returns the minimum bid on the item 
	 * @return - (a double) - the data of the instance variable minimumBid
	 */
	public double getMinimumBid()
	{
		return minimumBid; 
	}
	/**
	 * Method getBuyerID - returns the buyer ID number
	 * @return - (an integer) the data in the instance variable buyerID
	 */
	public double getBuyerID()
	{
		return buyerID; 
	}
	/**
	 * Method getObject - returns the object of type Product passed to the instance variable obj, 
	 * otherwise returns a new Product object
	 * @return (a Product object) - the data in the instance variable obj
	 */
	public Product getObject()
	{
		if (obj != null)
		{
			return obj; 
		}
		else
		{
			return new Product(); 
		}
	}
	/**
	 * Method getListingStatus - returns the listing status of the object
	 * @return - (a boolean) the data of the instance variable listingStatus
	 */
	public boolean getListingStatus()
	{
		return listingStatus; 
	}
	/**
	 * 
	 */
	public int getProductID()
	{
		return super.getProductID(); 
	}
	
	//MUTATORS
	/**
	 * Method getCurrentBid - return the current bid on the product 
	 * @return - (a double) the data of the instance variable currentBid
	 */
	public void setCurrentBid(double currentBid)
	{
		this.currentBid = currentBid; 
	}
	/**
	 * Method getMinimumBid - returns the minimum bid on the item 
	 * @return - (a double) - the data of the instance variable minimumBid
	 */
	public void setMinimumBid(double min)
	{
		this.minimumBid = min; 
	}
	/**
	 * Method setBuyerID - modifies the buyer ID number
	 * @param - (an integer) sets the data in the instance variable buyerID
	 */
	public void setBuyerID(int buyerID)
	{
		this.buyerID = buyerID;  
	}
	/**
	 * Method setObject - modifies the object of type Product passed to the instance variable obj, 
	 * otherwise returns a new Product object
	 * @param (a Product object) - sets the data in the instance variable obj
	 */
	public void setObject(Product p)
	{
		this.obj = p; 
	}
	/**
	 * Method setListingStatus - modifies the listing status of the object
	 * @param - (a boolean) the data of the instance variable listingStatus
	 */
	public void setListingStatus(boolean listingStatus) 
	{
		System.out.println("...Changing the active listing status to: "+listingStatus); 
		this.listingStatus = listingStatus; 
	}
	
	//BEHAVIORS 
	/**
	 * Method placeBid - places a bid on the item, only accepts the highest offer
	 * @param buyerID - (an integer) the buyerID
	 * @param offer - (a double) the amount to be interrogated
	 * @return - (a boolean) of true if the offer was accepted
	 */
	public boolean placeBid(int buyerID, double offer)
	{
		if (this.currentBid < offer)
		{
			this.buyerID = buyerID; 
			this.currentBid = offer; 
			super.setPrice(currentBid); 
			System.out.println("...Your bid has been placed."); 
			return true; 
		}
		else
		{
			System.out.println("This offer was rejected.");  
			return false; 
		}
	} 
	/**
	 * Method acceptOffer - accepts the current offer for the product
	 */
	public void acceptOffer()
	{
		if (buyerID != 0)
		{
			this.listingStatus = false; 
			System.out.println("Item sold for $"+ currentBid +" | Buyer #"+ buyerID +" is being notified."); 
		}
		else
		{
			System.out.println("There is currently no buyer for the item."); 
		}
		
	}
	
	//PRINT METHODS
	/**
	 * Method customerPrint - Prints the instance variables
	 */
	public void print()
	{
		if (obj != null)
		{
			obj.print(); 
			System.out.printf("*Auction Status*[CurrentBid: $%.2f | MinimumBid: $%.2f | Active Listing: "+ listingStatus +"]%n", 
					  currentBid, minimumBid); 
		}
		else
		{
			System.out.printf("%32s%n", "PRODUCT# " + super.getProductID()); 
			System.out.printf("%-50s%s%n", "Description:", "Price:"); 
			System.out.printf("%-49s $%.2f%n", super.getDescription(), super.getPrice());
			System.out.printf("*Auction Status*[CurrentBid: $%.2f | MinimumBid: $%.2f | Active Listing: "+ getListingStatus() + "]%n%n", 
							  currentBid, minimumBid); 	
		}
	}
}//class