/*
 * Name: Christopher Grigsby 
 * Date: 3/22/21
 * Assignment: SemesterProjectPartB
 * PsuedoCode:
 * 
 * PROGRAM GoGoGuitarRetailer:
 * 
 * Initialize all variables (products, auctionP, shoppingCart, done, choice)
 * 
 * Repeat while (not done)
 *     Display menu 1) Administrator 2) General User 3) Exit 
 *     Prompt user for choice 
 *     
 *     If choice equals one 
 *         Call method administrator
 *         
 *     Otherwise if choice equals two
 *         Call method generalUser
 *         
 *     Otherwise 
 *         End program
 * 
 * --------------------------------------------------------------------------------
 * Method administrator: 
 * 
 * Repeat while (not done)
 *     
 *     Display menu 1) to edit a product 2) to view products 3) return to main menu 
 *     Prompt user for choice 
 *     
 *     If choice is one 
 *     
 *         Display menu 1) for retail products 2) for auction products 
 *         Prompt user for choice 
 *         
 *         If choice is one 
 *             Call method viewRetailProduct 
 *             Call method editGuitarProduct
 *             
 *         Otherwise
 *             Call method viewAuctionProduct
 *             Call method editAuctionProduct
 *     
 *     Otherwise if choice is two 
 *     
 *         Display menu 1) for retail products 2) for auction products 
 *         Prompt user for choice
 *         
 *         If choice is one 
 *             Call method viewRetailProduct
 *         
 *         Otherwise 
 *             Call method viewAuctionProduct
 *     
 *     Otherwise 
 *     		return user to main menu 
 * 
 * -------------------------------------------------------------------------------
 * Method generalUser: 
 * 
 * Repeat while (not done)
 * 
 *     Display menu 1) shop products 2) checkout 3) view shopping cart 4) view active bids 5)exit 
 * 	   Prompt user for choice 
 * 		
 *     If choice is one 
 *         
 *         Display menu 1) for retail products 2) for auction products 
 *         Prompt user for choice 
 *         
 *         If choice is one 
 *             Call method viewRetailProducts
 *             Call method shopRetailProducts 
 *         
 *         Otherwise 
 *             Call method viewAuctionProducts
 *             Call method shopRetailProducts
 *             
 *     Otherwise if choice is two 
 *         Call method checkout
 *         
 *     Otherwise if choice is three
 *         Call method displayShoppingCart
 *    
 *     Otherwise if choice is four
 *         Call method displayBids
 *    
 *     Otherwise 
 *         Return to main menu 
 * 
 */

/**
 * Program Description: Simulates an inventory system of an online retailer. Provides a menu that allows the user to choose
 * being an administrator and a regular user. As an administrator, the program will allow the user to edit attributes of the item. 
 * of the item. The regular user is provided the option to shop the store, bid on auction items, and checkout.
 */

import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.NoSuchElementException; 

public class GogoGuitarRetailer 
{
	public static ArrayList<GuitarProduct> products = new ArrayList<>(); 
	public static ArrayList<AuctionProduct> auctionP = new ArrayList<>(); 
	public static ArrayList<GuitarProduct> shoppingCart = new ArrayList<>();
	public static int totalHardCases; 
	public static int totalSoftCases; 
	
	public static void main(String[] args)
	{
		//Initialize all variables
		Scanner in = new Scanner(System.in);
		inputData(); //initializes the database
		boolean done = false; 
		int choice = 0;  
		
		//Prompt for info
		while (!done)
		{
			try
			{
				System.out.println("\n----------------------Welcome to GoGo Electric Guitar!-----------------------\n"); 
				System.out.println("Enter 1) to enter as an ADMINISTRATOR");
				System.out.println("Enter 2) to enter a  GENERAL USER");
				System.out.println("Enter 3) to EXIT\n"); 
				System.out.println("-----------------------------------------------------------------------------\n"); 
				System.out.print("Please enter choice: ");
				choice = in.nextInt(); 
				
				switch(choice)
				{
					case 1:
						administratorMenu(); //call method for the administrator menu 
						break; 
						
					case 2:
						generalUserMenu(); //call method for the generalUser menu
						choice = 3;  
						break; 
						
					case 3:
						done = true; 
						System.out.println("\nThanks for visiting GoGo Guitars! Have a swell day."); 
						break; 
						
					default:
						System.out.println("Invalid entry. Please try again."); 
						break; 
				}
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Error. Please confirm input is valid, and try again.\n");
				in = new Scanner(System.in); 
			}
			catch(Exception error)
			{
				System.out.println("Unknown Error. Please try again.");
				in = new Scanner(System.in); 
			}
			
		}//menu loop		
	}//main
	
	/**
	 * Method administratorMenu - provides a menu which allows the user to edit a product, utilizes helper methods
	 * viewRetailProducts(), viewAuctionProducts(), getGuitarProductID(), getAuctionGuitarID(), editGuitarProduct(),
	 * editAuctionProduct()
	 * @param - none
	 * @return - none 
	 */
	public static void administratorMenu()
	{
		boolean done = false; 
		Scanner in = new Scanner(System.in); 
		int choice = 0; 
		int ch1 = 0; 
		
		while(!done)
		{
			try
			{
				System.out.println("\n------------------------ADMINISTRATOR MAIN MENU-----------------------------\n"); 
				System.out.println("Enter 1) to EDIT a product");  
				System.out.println("Enter 2) to VIEW products");  
				System.out.println("Enter 3) to EXIT\n");
				System.out.println("----------------------------------------------------------------------------\n"); 
				System.out.print("Enter choice: "); 
				choice = in.nextInt(); 
				switch(choice)
				{
					case 1://EDIT PRODUCT
						System.out.println("\n-------------EDIT PRODUCT--------------\n"); 
						System.out.println("Enter 1) to edit a  RETAIL product");
						System.out.println("Enter 2) to edit an AUCTION listing");
						System.out.println("\n---------------------------------------\n"); 
						System.out.print("Enter choice: ");
						ch1=in.nextInt(); 
						switch (ch1)
						{
							case 1:
								viewRetailProducts(); 
								editGuitarProduct(); 
								break; 
								
							case 2:
								viewAuctionProducts(); 
								editAuctionProduct(); 
								break; 
								
							default:
								System.out.println("Invalid entry. Please try again."); 
								break; 
						}
						break; 
						
					case 2://VIEW MENU
						System.out.println("\n-------------VIEW PRODUCT--------------\n"); 
						System.out.println("Enter 1) to view a RETAIL product");
						System.out.println("Enter 2) to view an AUCTION listing");
						System.out.println("\n---------------------------------------\n"); 
						System.out.print("Enter choice: ");
						ch1=in.nextInt(); 
						switch (ch1)
						{
							case 1:
								viewRetailProducts(); 
								break; 
								
							case 2:
								viewAuctionProducts(); 
								break;
								
							default:
								System.out.println("Invalid entry. Please try again."); 
								break; 
						}
						break; 
						
					case 3:
						done = true; 
						break; 
						
					default:
						break; 
				}
				
			}
			catch (NoSuchElementException e)
			{
				System.out.println("Error. Please confirm input is valid, and try again.");
				in = new Scanner(System.in); 
			}
			catch(Exception error)
			{
				System.out.println("Unknown Error. Please try again.");
				in = new Scanner(System.in); 
			}
		}
	}//method 
	
	/**
	 * Method editGuitarProduct - allows the administrator the edit the GuitarProduct 
	 * @param - none
	 * @return - none
	 */
	public static void editGuitarProduct()
	{
		Scanner in = new Scanner(System.in);  
		int num = 0;
		int stock = 0; 
		double dub = 0; 
		String s = ""; 
		boolean done = false; 
		
		try
		{
			GuitarProduct gP = getGuitarProductID(); 
			while(!done)
			{
				System.out.println();  
				System.out.println("Enter 1) to edit product RETAIL PRICE"); 
				System.out.println("Enter 2) to edit product CONDITION");
				System.out.println("Enter 3) to edit product QUANTITY");
				System.out.println("Enter 4) to REMOVE product from active inventory"); 
				System.out.println("Enter 5) to view TOTAL SOLD"); 
				System.out.println("Enter 6) to EXIT");
				System.out.println("-------------------------------------------------------"); 
				System.out.print("Enter choice: "); 
				num = in.nextInt(); 
				System.out.println(); 
				switch(num)
				{
					case 1://Price 
						System.out.print("Enter the new PRICE of the item: $"); 
						dub = in.nextDouble(); 
						gP.setPrice(dub); 
						gP.menuPrint(); 
						break; 
						
					case 2://Condition
						System.out.print("Enter the words of the CONDITION of the item: "); 
						s = in.next(); 
						gP.setCondition(s); 
						gP.menuPrint(); 
						break; 
						
					case 3://Stock
						System.out.print("Enter the number of the QUANTITY in stock: "); 
						stock = in.nextInt(); 
						gP.setQtyAvailable(stock); 
						gP.menuPrint();
						break;
						
					case 4://Remove 
						for (GuitarProduct i: products)
						{
							if (i.getProductID() == gP.getProductID())
							{
								products.remove(i); 
							}
						}
						System.out.println("...Product removed from active inventory.\n"); 
						System.out.println("...returning to Main Menu."); 
						num = 6; 
						break; 
						
					case 5://Total Sold
						System.out.println("Total Sold: "+ gP.getTotalSold()); 
						break; 
						
					case 6://Exit 
						done = true; 
						break; 
						
					default://Input Validation
						System.out.println("Invalid entry."); 
						break; 
				}
			}//switch
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
	}

	/**
	 * Method editAuctionProduct - allows an administrator to edit an auction listing
	 * @param - none
	 * @return - none
	 */
	public static void editAuctionProduct()
	{
		Scanner in = new Scanner(System.in);
		int num = 0;
		int stock = 0; 
		double dub = 0; 
		String s = ""; 
		boolean done = false; 
		
		try
		{
			AuctionProduct aP = getAuctionProductID(); 
			while(!done)
			{
				System.out.println();  
				System.out.println("Enter 1) to edit auction product MINIMUM BID"); 
				System.out.println("Enter 2) to edit auction product LISTING STATUS"); 
				System.out.println("Enter 3) to ACCEPT OFFER"); 
				System.out.println("Enter 4) to exit.\n"); 
				System.out.print("Enter choice: ");
				num = in.nextInt(); 
				System.out.println(); 
				switch(num)
				{
					case 1://Price 
						System.out.print("Enter the new MINIMUM BID of the item: $"); 
						dub = in.nextDouble(); 
						aP.setMinimumBid(dub); 
						System.out.println("New minimum bid is: $"+dub); 
						aP.print(); 
						break; 
						
					case 2://Condition
						System.out.print("Change the activity of LISTING STATUS? Y/N: "); 
						s = in.next(); 
						if (s.equals("Y") || s.equals("y"))
						{
							if (aP.getListingStatus())//is true
							{
								aP.setListingStatus(false); 
							}
							else
							{
								aP.setListingStatus(true);
							}
						}
						else if (s.equals("N") || s.equals("n"))
						{
							break; 
						}
						else
						{
							System.out.println("Invalid entry. Please try again."); 
						}
						System.out.println(); 
						aP.print(); 
						break; 
						
					case 3://Stock
						aP.acceptOffer(); 
						aP.print(); 
						break;
						
					case 4://Exit 
						done = true; 
						break; 
						
					default://Input Validation
						System.out.println("Invalid entry."); 
						break;
				}//switch
			}//while 		
		}//try
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
	}

	/**
	 * Method generalUser - provides a menu which allows the user to purchase products. Utilizes helper methods
	 * shopGuitarProduct(), shopAuctionProduct(), checkout(), getGuitarProductID(), and getAuctionProductID()
	 * @param - none
	 * @return - none
	 */
	public static void generalUserMenu()
	{
		boolean done = false; 
		boolean done2 = false; 
		Scanner in = new Scanner(System.in); 
		int choice = 0; 
		int ch1 = 0;
		int buyerID = 0; 
		int totalBids = 0; 
		
		while(!done)
		{
			try
			{
				System.out.println("\n--------------* Welcome to the GoGo Guitar Shopping Menu! *---------------\n");  
				System.out.println("Enter 1) to  SHOP products");  
				System.out.println("Enter 2) to  CHECKOUT"); 
				System.out.println("Enter 3) to  VIEW your SHOPPING CART");
				System.out.println("Enter 4) to  VIEW your ACTIVE BIDS"); 
				System.out.println("Enter 5) to  EXIT"); 
				System.out.println("\n---------------------------------------------------------------------------\n");  
				System.out.print("Enter choice: ");  
				choice = in.nextInt(); 
				
				switch(choice)
				{
					case 1://SHOP
						
						System.out.println("\n-------------SHOP GUITARS--------------\n"); 
						System.out.println("Enter 1) to shop RETAIL  products");
						System.out.println("Enter 2) to shop AUCTION listings");
						System.out.println(); 
						System.out.println("---------------------------------------\n"); 
						System.out.print("Enter choice: ");
						ch1=in.nextInt(); 
						switch (ch1)
						{
							case 1://SHOP
								viewRetailProducts(); 
								shopGuitarProduct(); 
								break; 
								
							case 2:
								viewAuctionProducts(); 
								shopAuctionProduct(); 
								break; 
								
							default:
								System.out.println("Invalid entry. Please try again."); 
								break; 
						}
						break; 
					case 2://CHECKOUT
						checkout(); 
						break; 
					case 3:
						displayShoppingCart(); 
						break; 
					case 4://ACTIVE BIDS
						displayBids(); 
						break;
					case 5:
						done = true; 
					default:
						break; 
				}
				
			}
			catch (NoSuchElementException e)
			{
				System.out.println("Error. Please confirm input is valid, and try again."); 
				in = new Scanner(System.in); 
			}
			catch(Exception error)
			{
				System.out.println("Unknown Error. Please try again."); 
				in = new Scanner(System.in); 
			}
		}
	}//method generalUser
	
	/**
	 * Method shopGuitarProduct - allows the user to add a product to the shopping cart, view more guitars
	 * or checkout. Utilizes helper methods getGuitarProductID(), getAuctionProductID()
	 * @param - none
	 * @return - none
	 */
	public static void shopGuitarProduct()
	{
		Scanner in = new Scanner(System.in); 
		int num = 0; 
		int num2 = 0; 
		int productID = 0; 
		boolean done = false; 
		String choice = ""; 
		
		try
		{
			while(!done)
			{
				System.out.println("Enter 1) to ADD a product to ShoppingCart"); 
				System.out.println("Enter 2) to REMOVE a product from ShoppingCart"); 
				System.out.println("Enter 3) to VIEW guitar selection"); 
				System.out.println("Enter 4) to CHECKOUT"); 
				System.out.println("Enter 5) to RETURN to Main Menu"); 
				System.out.println(); 
				System.out.print("Enter your choice: "); 
				num = in.nextInt(); 
				switch (num)
				{
					case 1: //add an item to shopping cart
						in = new Scanner(System.in); 
						GuitarProduct gP = getGuitarProductID(); 
						if (gP.inStock(1))	//if in stock
						{
							System.out.println(); 
							System.out.print("Would you like to ADD A CASE? Enter Y/N: "); 
							choice = in.nextLine(); 
							in = new Scanner(System.in);
							if (choice.contains("Y") || choice.contains("y"))
							{
								System.out.println("Enter 1) for hard case | $120.00 each"); 
								System.out.println("Enter 2) for soft case | $60.00 each"); 
								System.out.print("Enter choice: "); 
								num2 = in.nextInt(); 
								if (num2 == 1)
								{
									gP.addHardCase(1);
									totalHardCases++; 
									System.out.println("Hard case added."); 
									
								}
								else if (num2 == 2) 
								{
									gP.addSoftCase(1);
									totalSoftCases++; 
									System.out.println("Soft case added.");
								}
							}
							else
							{
								System.out.println("No case was added."); 
								in = new Scanner(System.in); 
							} 
							gP.sellItem(1); //sell Item
							shoppingCart.add(gP); 
							displayShoppingCart(); 	
						}
						break;
					case 2://delete an item from shopping cart
						if (shoppingCart.size() == 0)
						{
							
							System.out.println("Shopping cart is empty."); 
						}
						else
						{
							displayShoppingCart(); 
							System.out.print("Enter the Product ID of the item you wish to remove: ");
							productID = in.nextInt(); 
							for (GuitarProduct i: shoppingCart)
							{
								if (i.getProductID() == productID)
								{
									int num3 = i.getQtyAvailable();
									num3++; 
									i.setQtyAvailable(num3); //restore quantity 
									shoppingCart.remove(i); 
									System.out.println("...Item removed.");
									break; 
								}	
							}
							in = new Scanner(System.in); 
							System.out.print("Do you need to remove a case as well? Y/N"); 
							choice = in.nextLine(); 
							in = new Scanner(System.in);
							if (choice.contains("Y") || choice.contains("y"))
							{
								System.out.println("Enter 1) to remove hard case"); 
								System.out.println("Enter 2) to remove soft case"); 
								System.out.print("Enter choice: "); 
								num2 = in.nextInt(); 
								if (num2 == 1 && totalHardCases - 1 >= 0)
								{
									totalHardCases--; 
									System.out.println("Hard case removed."); 
									
								}
								else if (num2 == 2 && totalSoftCases - 1 >= 0) 
								{
									totalSoftCases--; 
									System.out.println("Soft case removed.");
								}
							}
							in = new Scanner(System.in); 
							displayShoppingCart(); 
						}
						break; 
					case 3:
						viewRetailProducts(); 
						break; 
					case 4://returns to main menu 
						checkout(); 
						break; 
					case 5:
						done = true; 
						break; 
					default:
						System.out.println("\nInvalid entry. Please try again.\n"); 
						break; 
				}
			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("\nError. Please confirm input is valid, and try again.\n"); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("\nUnknown Error. Please try again.\n"); 
			in = new Scanner(System.in); 
		}
	}
	
	/**
	 *Method shopAuctionProduct - Allows the user to bid on an auction item.
	 *@param - none 
	 *@return - none
	 */
	public static void shopAuctionProduct()
	{
		Scanner in = new Scanner(System.in); 
		int num3 = 0; 
		int buyerID = 0; 
		double bid = 0; 
		boolean done = false; 
		String choice = ""; 
		
		try
		{
			while(!done)
			{
				System.out.println("Enter 1) to PLACE BID on a product"); 
				System.out.println("Enter 2) to VIEW auction guitars"); 
				System.out.println("Enter 3) to REVIEW your ACTIVE BIDS"); 
				System.out.println("Enter 4) to RETURN to Main Menu"); 
				System.out.print("Enter your choice: "); 
				num3 = in.nextInt(); 
				switch (num3)
				{
					case 1: 
						AuctionProduct aP = getAuctionProductID();
						System.out.printf("%nCurrent bid on item: $%.2f | Minimum Bid: $%.2f%n", 
										aP.getCurrentBid(), aP.getMinimumBid()); 
						System.out.print("Enter your BID on the item: $ "); 
						bid = in.nextDouble(); 
						System.out.print("Enter your buyer ID number (e.g 66432): "); 
						buyerID = in.nextInt(); 
						aP.placeBid(buyerID, bid); 
						System.out.println(); 
						aP.print(); 
						System.out.println(); 
						break; 
					case 2:
						viewAuctionProducts(); 
						break;  
					case 3: 
						displayBids(); 
						break; 
					case 4:
						done = true; 
						break; 
					default:
						System.out.println("Invalid entry. Please try again."); 
						break; 
				}
			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
			
	}

	/**
	 * Method viewRetailProducts - Displays the GuitarProducts.
	 * @param - none
	 * @return - none
	 */
	public static void viewRetailProducts()
	{
		Scanner in = new Scanner(System.in); 
		int choice = 0; 
		
		try
		{
			System.out.println("\nEnter 1) to view FENDER guitars"); 
			System.out.println("\nEnter 2) to view STRANDBERG guitars"); 
			System.out.println("\nEnter 3) to view GIBSON guitars"); 
			System.out.println("\nEnter 4) to view ALL guitars\n"); 
			System.out.print("Enter choice: "); 
			choice = in.nextInt();  
			switch (choice)
			{
				case 1: //FENDER
					System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID < 20000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					break; 
					
				case 2: //STRANDBERG
					System.out.println("\n--------------------------------------STANDBERG GUITARS-------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID > 20000 && ID < 30000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					break; 
					
				case 3: //GIBSON
					System.out.println("\n--------------------------------------GIBSON GUITARS-----------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID > 30000 && ID < 40000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("---------------------------------------------------------------------------------------------------"); 
					break; 
					
				case 4:
					System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID < 20000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					System.out.println("\n--------------------------------------STANDBERG GUITARS-------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID > 20000 && ID < 30000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					System.out.println("\n--------------------------------------GIBSON GUITARS----------------------------------------------\n"); 
					for (GuitarProduct i: products)
					{
						int ID = i.getProductID(); 
						if (ID > 30000 && ID < 40000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("---------------------------------------------------------------------------------------------------"); 
					break;
				default:
					System.out.println("Invalid entry. Please try again."); 
					break; 
			}
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
	}//retailProductMenu
	
	/**
	 * Method viewAuctionProducts - Displays the AuctionProducts.
	 * @param - none
	 * @return - none
	 */
	public static void viewAuctionProducts()
	{
		Scanner in = new Scanner(System.in); 
		try
		{
			System.out.println("\n---------------------------------AUCTION PRODUCTS--------------------------------------\n"); 
			for (AuctionProduct i: auctionP)
			{
				i.print(); 
				System.out.println(); 
			}
			System.out.println("-----------------------------------------------------------------------------------------\n"); 
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
	}
	
	/**
	 * Method getGuitarProductID - Returns a reference to a GuitarProduct, looked up by the ID number. 
	 * @return - (type GuitarProduct) returns a reference to the object
	 */
	public static GuitarProduct getGuitarProductID()
	{
		Scanner in = new Scanner(System.in); 
		GuitarProduct gP = new GuitarProduct(); 
		boolean done = false; 
		int IDnum = 0; 
		try
		{
			while(!done)
			{
				System.out.print("Enter the product's ID number: ");
				IDnum = in.nextInt();
			
				for (int i = 0; i < products.size(); i++)
				{
					gP = products.get(i); 
					if (gP.getProductID() == IDnum)
					{
						System.out.println("\n...Product found.");
						gP.menuPrint(); 
						done = true; 
						return gP; 
					}
				}
				System.out.println("Item not found. Please try again.");
				in = new Scanner(System.in); 
			} 
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
		return gP; 
	}
	
	/**
	 * Method getAuctionProductID - Returns a reference to an AuctionProduct, looked up by the product ID. 
	 * @return - (type AuctionProduct) returns a reference to the object
	 */
	public static AuctionProduct getAuctionProductID()
	{
		Scanner in = new Scanner(System.in); 
		AuctionProduct aP = new AuctionProduct(); 
		boolean done = false; 
		int IDnum = 0; 
		try
		{
			while(!done)
			{
				System.out.print("Enter the product's ID number: ");
				IDnum = in.nextInt();
			
				for (int i = 0; i < auctionP.size(); i++)
				{
					aP = auctionP.get(i); 
					if (aP.getProductID() == IDnum)
					{
						System.out.println("\n...Product found.");
						aP.print();  
						done = true; 
						return aP; 
					}
				}
				System.out.println("Item not found. Please try again.");
				in = new Scanner(System.in); 
			} 
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
		return aP; 
	}
	
	/**
	 * Method checkout - Provides a bill of sale to the user.
	 * @param - none
	 * @return - none
	 */
	public static void checkout()
	{
		if (shoppingCart.size() == 0)
		{
			System.out.println("Shopping Cart is empty. Nothing to checkout."); 
			return; 
		}
		int max = 50000; 
		int min = 10010; 
		int salesID = (int) (Math.random()*(max-min+1) + min); 
		
		Scanner in = new Scanner(System.in);
		final double SALES_TAX = .04; 
		CashRegister reg = new CashRegister(SALES_TAX); 
		String choice = ""; 
		int num = 0; 
		int num2 = 0; 
		int menu = 0; 
		int totalItems = 0;
		double price = 0; 
		double payment = 0; 
		double finalTotal = 0; ; 
		
		totalItems = totalItems + shoppingCart.size() + totalHardCases + totalSoftCases; 
		try 
		{
			System.out.printf("%n---------------------------FINAL SALE------------------------------%n");
			System.out.println("Total Items: " + totalItems ); 
			for (int i= 0; i < shoppingCart.size(); i++)
			{
				System.out.println("ITEM#"+(i+1)+" "+shoppingCart.get(i)); 
			}
			System.out.println(); 
			System.out.printf("Hard Cases: %d @ $120.00 ea | Soft Cases: %d @ $60.00 ea", totalHardCases, totalSoftCases); 
			
			for (Product i: shoppingCart)//send prices to the cash register
			{
				price = i.getPrice(); 
				reg.addItem(price); 
			}
			
			System.out.printf("%nTOTAL: %.2f%nSTATE TAX: %.2f%nSALES TAX: %.2f%nFINAL TOTAL: $%.2f%n",
								reg.getTotal(), SALES_TAX, reg.getSalesTax(), reg.getTotalAfterTax());
			System.out.printf("--------------------------------------------------------------------%n");
			finalTotal = reg.getTotalAfterTax();
			reg.clear(); //clear until payment is received
			
			System.out.println("\nPLEASE ENTER YOUR PAYMENT METHOD"); 
			System.out.println("Enter 1) For Credit\nEnter 2) For Cash"); 
			System.out.println("Enter 3) To return to Menu"); 
			System.out.print("\nEnter choice: "); 
			menu = in.nextInt(); 
			if (menu == 1)
			{
				
				payment = finalTotal;   
			}
			else if (menu == 2)
			{
				System.out.print("Enter dollar amount: $ "); 
				payment = in.nextDouble(); 
				if (payment < finalTotal)
				{
					System.out.println("Insufficient Amount."); 
					System.out.println("Returning to Menu.\n"); 
					totalItems = 0; 
					return; 
				}	
			}
			else if (menu == 3)
			{
				totalItems = 0; 
				System.out.println();; 
				return;   
			}
			else
			{
				totalItems = 0; 
				System.out.print("Invalid Entry.");
				return; 
			}
			
			for (Product i: shoppingCart)//send prices to the cash register
			{
				price = i.getPrice(); 
				reg.addItem(price); 
			}
			
			System.out.printf("%n-----------------------------RECIEPT--------------------------------%n");
			System.out.println("SaleID# " +salesID); 
			System.out.println("Total Items: " + totalItems); 
			for (int i= 0; i < shoppingCart.size(); i++)
			{
				System.out.println("ITEM#"+(i+1)+" "+shoppingCart.get(i)); 
			}
			System.out.printf("Hard Cases: %d @ $120.00 ea | Soft Cases: %d @ $60.00 ea", totalHardCases, totalSoftCases); 
			System.out.printf("%nTOTAL: %.2f%nSTATE TAX: %.2f%nSALES TAX: %.2f%nFINAL TOTAL: $%.2f%n",
								reg.getTotal(), SALES_TAX, reg.getSalesTax(), reg.getTotalAfterTax());
			
			if (payment > reg.getTotalAfterTax() )
			{
				System.out.printf("\nPAYMENT: $%.2f", payment); 
				System.out.printf("  CHANGE:$%.2f%n",reg.getChange(payment));
				System.out.println("---------------------------------------------------------------------\n");
				System.out.println("Thank you for shopping at GoGo Guitar!\n");
			}
			else 
			{
				System.out.printf("\nPAYMENT: $%.2f%n", payment); 
				System.out.println("---------------------------------------------------------------------\n");
				System.out.println("Thank you for shopping at GoGo Guitar!\n");
			}
			reg.clear(); //clear all relevant items
			shoppingCart = new ArrayList<GuitarProduct>(); 
			totalItems = 0; 
			totalHardCases = 0; 
			totalSoftCases = 0; 
		}			
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please confirm input is valid, and try again."); 
			in = new Scanner(System.in); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
			in = new Scanner(System.in); 
		}
	}
	
	/**
	 * Method displayShoppingCart - Displays the customer's shopping cart.
	 * @param - none
	 * @return - none
	 */
	public static void displayShoppingCart()
	{
		try
		{
			if (shoppingCart.size() > 0)
			{
				System.out.println("\n-----------------------Your SHOPPING CART--------------------------------\n"); 
				for (GuitarProduct i: shoppingCart)
				{
					i.menuPrint(); 
				}
				System.out.println("\n--------------------------------------------------------------------------\n");
			}
			else
			{
				System.out.println("\nSHOPPING CART is currently empty.\n"); 
			}
		}
		catch(Exception error)
		{
			System.out.println("\n...Unknown Error. Please try again.\n");  
		}
		
	}
	
	/**
	 * Method displayBids - Displays the auction bids of the user.
	 * @param - none
	 * @return - none
	 */
	public static void displayBids()
	{
		Scanner in = new Scanner(System.in); 
		int buyerID = 0; 
		double cost = 0; 
		int totalBids = 0; 
		try
		{
			System.out.print("Enter your buyerID (e.g 66432): ");
			buyerID = in.nextInt(); 
			System.out.println("\n------------------------Your Auction Bids----------------------------\n"); 
			for (AuctionProduct i: auctionP)
			{
				if (i.getBuyerID() == buyerID)
				{
					i.print(); 
					cost = i.getPrice(); 
					totalBids++; 
					System.out.println(); 
				}
			}
			System.out.println("\nTotal active bids: " +totalBids+ " | Total Cost: $" +cost); 
			System.out.println("---------------------------------------------------------------------\n"); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again.");  
		}
		
	}
	public static void inputData()
	{
		//FENDER
		GuitarProduct gP = new GuitarProduct(10001, "1958 Reissue Telecaster: Ash Blonde", 
				1400.00, "Fender", 1, "Used","Maple", 6); 
		products.add(gP); 
		GuitarProduct gP1 = new GuitarProduct(10003, "Tash Sultana Stratocastor: Transparent Cherry", 
				1199.99, "Fender", 5, "New","Maple", 6); 
		products.add(gP1); 
		GuitarProduct gP2 = new GuitarProduct(10006, "Vintera '60s Jaguar: Sonic Blue", 
				1229.99, "Fender", 8, "New","Rosewood", 6); 
		products.add(gP2);
		GuitarProduct gP3 = new GuitarProduct(10011, "Johnny Marr Jaguar: Olympic White", 
				2099.99, "Fender", 7, "New","Rosewood", 6); 
		products.add(gP3); 
		
		//STRANDBERG
		GuitarProduct gP5 = new GuitarProduct(20015, "Boden Fusion 6: Black", 
				2495.00, "Strandberg", 7, "New","Rosewood", 6); 
		products.add(gP5); 
		GuitarProduct gP4 = new GuitarProduct(20021, "Boden Prog 7: Natural", 
				2395.00, "Strandberg", 8, "New","Maple", 7); 
		products.add(gP4);
		GuitarProduct gP7 = new GuitarProduct(20025, "Boden Original 7: Black ", 
				2295.00, "Strandberg", 6, "New","Maple", 7); 
		products.add(gP7);
		GuitarProduct gP6 = new GuitarProduct(20031, "Boden Sarah Longfield Edt. 8 ", 
				2495.00, "Strandberg", 7, "New","Maple", 8); 
		products.add(gP6);
		
		//GIBSON
		GuitarProduct gP8 = new GuitarProduct(30003, "1954 Les Paul Reissue Heavy Aged: Goldtop", 
				6999.00, "Gibson", 5, "New","Rosewood", 6); 
		products.add(gP8);
		GuitarProduct gP9 = new GuitarProduct(30007, "Les Paul Standard '50s: Goldtop", 
				2499.00, "Gibson", 6, "New","Rosewood", 6); 
		products.add(gP9);
		GuitarProduct gP10 = new GuitarProduct(30009, "70s Flying V: Ash White", 
				1999.00, "Gibson", 6, "New","Rosewood", 6); 
		products.add(gP10);
		GuitarProduct gP11 = new GuitarProduct(30011, "SG Standard '61: Red", 
				1799.00, "Gibson", 7, "New","Rosewood", 6); 
		products.add(gP11);
		
		//AUCTION
		GuitarProduct gP12 = new GuitarProduct(50011, "Vintera '60s Jaguar: Sonic Blue", 
				1229.99, "Fender", 1, "Like New","Rosewood", 6); 
		AuctionProduct aP1 = new AuctionProduct(gP12, 0.00, 849.99, 0, true);
		auctionP.add(aP1); 
		GuitarProduct gP13 = new GuitarProduct(50031, "Boden Sarah Longfield Edt. 8 ", 
				2495.00, "Strandberg", 1, "Used","Maple", 8);
		AuctionProduct aP2 = new AuctionProduct(gP13, 0.00, 1200.99, 0, true);
		auctionP.add(aP2); 
		GuitarProduct gP14 = new GuitarProduct(50001, "1958 Reissue Telecaster: Ash Blonde", 
				1400.00, "Fender", 1, "Used","Maple", 6); 
		AuctionProduct aP3 = new AuctionProduct(gP14, 0.00, 800.00, 0, true);
		auctionP.add(aP3); 
		GuitarProduct gP15 = new GuitarProduct(50009, "70s Flying V: Ash White", 
				1999.00, "Gibson", 1, "Used","Rosewood", 6); 
		AuctionProduct aP4 = new AuctionProduct(gP15, 955.00, 900.00, 1345, true);
		auctionP.add(aP4); 
	}//inputData
	
}//class
