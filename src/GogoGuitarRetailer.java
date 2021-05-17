/*
 * Name: Christopher Grigsby 
 * Date: 04/19/21
 * Assignment: SemesterProjectPartC
 * PsuedoCode:
 * 
 * PROGRAM GoGoGuitarRetailer:
 * 
 * Initialize all variables (products, auctionP, shoppingCart, adminHistory, purchaseHistory, done, choice)
 * 
 * Call method retrieveAllData() to pull stored data from files
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
 *     Display menu 1) to edit a product 2) to view products 3) to view Administrator History Report 4) return to main menu 
 *     Prompt user for choice 
 *     
 *     If choice is one 
 *     
 *         Display menu 1) for retail products 2) for auction products 3) for add guitar product 4) for add auction product
 *         Prompt user for choice 
 *         
 *         If choice is one 
 *             Call method viewRetailProduct 
 *             Call method editGuitarProduct
 *          
 *         If choice is two 
 *         	   Call method addGuitarProduct
 *         
 *         If choice is three
 *         	   Call method addAuctionProduct 
 *         
 *         Otherwise
 *             Call method viewAuctionProduct
 *             Call method editAuctionProduct
 *     
 *      If choice is two 
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
 *     If choice is three
 *     
 *     		Display the data structure containing the adminHistory report
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
 */

/**
 * Program Description: Simulates an inventory system of an online retailer. Provides a menu that allows the user to choose
 * being an administrator and a regular user. As an administrator, the program will allow the user to edit attributes of the item. 
 * of the item. The regular user is provided the option to shop the store, bid on auction items, and checkout.
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.NoSuchElementException; 
import java.util.NoSuchElementException; 
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 

public class GogoGuitarRetailer 
{
	public static LinkedList<GuitarProduct> products = new LinkedList<>(); 
	public static LinkedList<AuctionProduct> auctionP = new LinkedList<>(); 
	public static LinkedList<GuitarProduct> shoppingCart = new LinkedList<>();
	public static LinkedList<String> adminHistory = new LinkedList<>(); 
	public static LinkedList<String> purchaseHistory = new LinkedList<>(); 
	public static int totalHardCases; 
	public static int totalSoftCases; 
	
	public static void main(String[] args)throws IOException
	{
		//Initialize all variables
		Scanner in = new Scanner(System.in);
		//inputData(); //initializes the data from hardCode 
		boolean done = false; 
		int choice = 0;  
		String gPFile = "data/GuitarProductsData.txt";  //the folder/fileName for GuitarProduct data
		String aPFile = "data/AuctionProductsData.txt"; //the folder/fileName for AuctionProduct data 
		String aHFile = "data/AdminHistory.txt"; 		//the folder/fileName for adminHistory data 
		String pHFile = "data/PurchaseHistory.txt"; 	//the folder/fileName for purchaseHistory data 
		
		//Prompt for info
		while(!done)
		{
			try 
			{
				retrieveAllData(gPFile, aPFile, aHFile, pHFile); 	//get  the Object data
				products.head = products.mergeSort(products.head);  //sort the products inventory
				auctionP.head = auctionP.mergeSort(auctionP.head);  //sort the auction  inventory
				
				System.out.println("\n----------------------Welcome to GoGo Electric Guitar!-----------------------\n"); 
				System.out.println("Enter 1 to enter as an ADMINISTRATOR");
				System.out.println("Enter 2 to enter a  GENERAL USER");
				System.out.println("Enter 3 to EXIT\n"); 
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
				}//switch
			}//try
			catch(NoSuchElementException e)
			{
				System.out.println("\nError. Please confirm input is valid, and try again.\n");
				in = new Scanner(System.in); 
			}
			catch(Exception error)
			{
				System.out.println("\nUnknown Error. Please try again.\n");
				in = new Scanner(System.in); 
			}
		}//loop
				
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
				System.out.println("Enter 1 to EDIT/ADD product data");  
				System.out.println("Enter 2 to VIEW products"); 
				System.out.println("Enter 3 to VIEW Administrator HISTORY REPORT"); 
				System.out.println("Enter 4 to EXIT\n");
				System.out.println("----------------------------------------------------------------------------\n"); 
				System.out.print("Enter choice: "); 
				choice = in.nextInt(); 
				switch(choice)
				{
					case 1://EDIT PRODUCT
						System.out.println("\n-------------EDIT PRODUCT--------------\n"); 
						System.out.println("Enter 1 to edit a  RETAIL product");
						System.out.println("Enter 2 to edit an AUCTION listing");
						System.out.println("Enter 3 to ADD  a  RETAIL prodcut"); 
						System.out.println("Enter 4 to ADD  an Auction listing"); 
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
								
							case 3:
								addGuitarProduct(); 
								break; 
								
							case 4: 
								addAuctionProduct(); 
								break; 
							default:
								System.out.println("Invalid entry. Please try again."); 
								break; 
						}
						break; 
						
					case 2://VIEW MENU
						System.out.println("\n-------------VIEW PRODUCT--------------\n"); 
						System.out.println("Enter 1 to view a RETAIL product");
						System.out.println("Enter 2 to view an AUCTION listing");
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
						ListIterator iter = adminHistory.listIterator();
						System.out.println("\n--- ADMINISTRATOR HISTORY --------------------------------------------------\n");
						while (iter.hasNext())
						{
							System.out.println(iter.next()); 
						}
						System.out.println("\n------------------------------------------------------END OF HISTORY--------\n");
						break; 
						
					case 4:
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
	}//method 
	
	/**
	 * Method editGuitarProduct - allows the administrator the edit the GuitarProduct 
	 * @param - none
	 * @return - none
	 */
	public static void editGuitarProduct()
	{
		Scanner in = new Scanner(System.in); 
		LocalDateTime timeStamp = LocalDateTime.now();
		String aHFile = "data/AdminHistory.txt"; //file path for adminHistory data
		String gPFile = "data/GuitarProductsData.txt"; //file path for (Guitar)products data
		int num = 0;
		int stock = 0; 
		int newTotal = 0; 
		double newPrice = 0; 
		double previousPrice = 0; 
		String condition = ""; 
		String oldCondition = ""; 
		String output = ""; 
		boolean done = false; 
		ListIterator iter = products.listIterator(); 
		
		try
		{
			GuitarProduct gP = getGuitarProductID(); //get ID number
			if (gP.getProductID() == 0)//input validation 
			{
				viewRetailProducts(); 
				editGuitarProduct(); 
			}
			while(!done)
			{
				System.out.println();  
				System.out.println("Enter 1 to edit product RETAIL PRICE"); 
				System.out.println("Enter 2 to edit product CONDITION");
				System.out.println("Enter 3 to edit product QUANTITY");
				System.out.println("Enter 4 to REMOVE product"); 
				System.out.println("Enter 5 to view TOTAL SOLD"); 
				System.out.println("Enter 6 to EXIT");
				System.out.println("-------------------------------------------------------"); 
				System.out.print("Enter choice: "); 
				num = in.nextInt(); 
				System.out.println(); 
				switch(num)
				{
					case 1://Price 
						previousPrice = gP.getPrice(); 
						System.out.print("Enter the NEW PRICE of the item: $"); 
						newPrice = in.nextDouble(); 
						gP.setPrice(newPrice); 
						gP.menuPrint(); 
						//Document Event
						output = String.format("%s", timeStamp) //time&date
								 + String.format(" NEW PRICE for Guitar Product: Previous $%.2f | New $%.2f Product ID# %d %n", 
										  			previousPrice, newPrice, gP.getProductID()); 
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(gPFile, products); 		 //write to file, products
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						break; 
						
					case 2://Condition
						in = new Scanner(System.in); //fresh scanner for nextLine()
						oldCondition = gP.getCondition(); 
						System.out.print("Enter words for the CONDITION of the item (e.g. New, Used): "); 
						condition = in.nextLine(); 
						in = new Scanner(System.in); 
						gP.setCondition(condition); 
						gP.menuPrint(); 
						//Document Event
						output = String.format("%s", timeStamp) //time&date
								 + String.format(" CONDITION changed for Guitar Product: Previous %s | New %s Product ID# %d %n", 
										  			oldCondition, condition, gP.getProductID()); 
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(gPFile, products); 		 //write to file, products
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						break; 
						
					case 3://Stock
						System.out.print("Enter a number for the new QUANTITY of items in stock: "); 
						stock = in.nextInt(); 
						gP.setQtyAvailable(stock); 
						gP.menuPrint();
						//Document Event
						output = String.format("%s", timeStamp) //time&date
								 + String.format(" QUANTITY in-stock changed for Guitar Product: In-Stock %d | Product ID# %d %n", 
										  			stock, gP.getProductID()); 
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(gPFile, products); 		 //write to file, products
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						
						break;
						 
					case 4://Remove Product
						while (iter.hasNext())
						{
							GuitarProduct i = (GuitarProduct) iter.next(); 
							if (i.getProductID() == gP.getProductID())
							{
								products.deleteNode(gP); 
							}
						}
						System.out.println("...Product removed from active inventory.\n"); 
						System.out.println("...returning to Main Menu."); 
						output = String.format("%s", timeStamp) //time&date
								 + String.format(" Guitar Product DELETED | Product ID# %d %n", 
										  			gP.getProductID()); 
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(gPFile, products); 		 //write to file, products
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						break; 
						
					case 5://Total Sold
						System.out.println("Enter new Total Sold for the item: "+ gP.getTotalSold()); 
						newTotal = in.nextInt(); 
						gP.setTotalSold(newTotal);
						output = String.format("%s", timeStamp) //time&date
								 + String.format(" Guitar Product TOTAL SOLD updated: %d | Product ID# %d %n", 
										  			newTotal, gP.getProductID()); 
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(gPFile, products); 		 //write to file, products
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						System.out.println("...Total Sold for product is now: "+newTotal); 
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
	 * Method addGuitarProduct: allows the user to add a GuitarProduct to the inventory
	 * @param - none
	 * @return - none
	 */
	public static void addGuitarProduct()
	{
		Scanner in = new Scanner(System.in); 
		String gPFile = "data/GuitarProductsData.txt";  //the folder/fileName the GuitarProduct data is stored under
		String aHFile = "data/AdminHistory.txt"; 		//the folder/fileName the adminHistory data is stored under
		LocalDateTime timeStamp = LocalDateTime.now();
		ListIterator iter2 = products.listIterator(); 
		ListIterator iter3 = products.listIterator(); 
		int choice = 0; 
		boolean done = false; 
		boolean done2 = false; 
		boolean found = false; 
		int itemID = 0; 
		String description = ""; 
		double price = 0; 
		String manufacturer = ""; 
		String condition = ""; 
		String neck = ""; 
		int strings = 0; 
		int inStock = 0; 
				
		while (!done)
		{
			try 
			{
				System.out.println("\n---- ADD GUITAR PRODUCT MENU ----\n"); 
				System.out.println("Enter 1 to add a Fender Guitar"); 
				System.out.println("Enter 2 to add a Strandberg Guitar"); 
				System.out.println("Enter 3 to add a Gibson Guitar"); 
				System.out.println("Enter 4 to EXIT"); 
				System.out.println(); 
				System.out.print("Enter choice: ");
				choice = in.nextInt(); 
				System.out.println(); 
				switch (choice)
				{
					case 1: //create Fender guitar data
						while (!done2)
						{
							System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n");
							ListIterator iter = products.listIterator(); 
							while(iter.hasNext())
							{
								GuitarProduct i = (GuitarProduct) iter.next(); 
								int ID = i.getProductID(); 
								if (ID < 20000)
								{
									i.menuPrint(); 
									System.out.println(); 
								}
							}
							System.out.println("--------------------------------------------------------------------------------------------------"); 
							System.out.print("Enter a NEW product ID between 10000 and 19999, inclusive: ");//get new ID#
							itemID = in.nextInt(); 
							if (itemID < 10000 || itemID > 19999)
							{
								System.out.println("\nInvalid Entry. Please try again.\n"); 
							}
							else
							{
								while (iter2.hasNext())
								{
									GuitarProduct i = (GuitarProduct) iter2.next(); 
									int existingID = i.getProductID(); 
									
									if (existingID == itemID) //reject duplicate numbers
									{
										System.out.println("\nThis ID Number is currently being used. Please try again.\n"); 
										found = true;   
									}
								}
								if (!found)
								{
									done2 = true; 
								}
							}
						}
						in = new Scanner(System.in);
						System.out.print("Enter words for a short description of the guitar: "); 
						description = in.nextLine(); 
						in = new Scanner(System.in);
						System.out.print("Enter the retail price of the guitar: $"); 
						price = in.nextDouble();
						manufacturer = "Fender"; 						
						System.out.print("Enter if the condition of the guitar(New/Used): "); 
						condition = in.next(); 
						in = new Scanner(System.in); 
						System.out.print("Enter a word for the type of wood used to build the neck: "); 
						neck = in.next(); 
						System.out.print("Enter an integer for the number of strings on the guitar: "); 
						strings = in.nextInt(); 
						System.out.print("Enter an integer for the number In-Stock: "); 
						inStock = in.nextInt(); 
							
						GuitarProduct gP = new GuitarProduct(itemID, description, price, manufacturer,
															inStock, condition, neck, strings); 
							
						products.addNodeAsTail(gP);        					//add the item to inventory
						products.head = products.mergeSort(products.head);  //sort the inventory
						writeData(gPFile, products); 						//write products data to the file
						
						String output = String.format("%s", timeStamp)
									  + String.format(" Data for new guitar product created. Product ID# %d %n", itemID); 
						
						adminHistory.addNodeAsTail(output);  				//add the event to adminHistory
						writeData(aHFile, adminHistory); 					//write  adminHistpry to the file	
						System.out.println(); 
						System.out.println(timeStamp+" Data for new guitar product created. Product ID# "+ itemID);
								
						System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n");
						while(iter3.hasNext())
						{
							GuitarProduct i = (GuitarProduct) iter3.next(); 
							int ID = i.getProductID(); 
							if (ID < 20000)
							{
								i.menuPrint(); 
								System.out.println(); 
							}
						}
						System.out.println("--------------------------------------------------------------------------------------------------"); 
						done = true; 	
						break; 
						
					case 2: //create Strandberg guitar data
						while (!done2)
						{
							System.out.println("\n--------------------------------------STRANDBERG GUITARS-------------------------------------------\n");
							ListIterator iter = products.listIterator(); 
							while (iter.hasNext())
							{
								GuitarProduct i = (GuitarProduct) iter.next(); 
								int ID = i.getProductID(); 
								if (ID >= 20000 && ID < 30000)
								{
									i.menuPrint(); 
									System.out.println(); 
								}
							}
							System.out.println("--------------------------------------------------------------------------------------------------"); 
							System.out.print("Enter a NEW product ID between 20000 and 29999, inclusive: ");
							itemID = in.nextInt(); 
							if (itemID < 20000 || itemID > 29999) //reject
							{
								System.out.println("\nInvalid Entry. Please try again.\n"); 
							}
							else
							{
								while (iter2.hasNext())
								{
									GuitarProduct i = (GuitarProduct) iter2.next(); 
									int existingID = i.getProductID(); 
								
									if (existingID == itemID)  
									{
										System.out.println("\nThis ID Number is currently being used. Please try again.\n"); 
										found = true;   
									}
								}
								if (!found)
								{
									done2 = true; 
								}
							}
						}//while(!done2)
						
						in = new Scanner(System.in);
						System.out.print("Enter words for a short description of the guitar: "); 
						description = in.nextLine(); 
						in = new Scanner(System.in);
						System.out.print("Enter the retail price of the guitar: $"); 
						price = in.nextDouble(); 
						manufacturer = "Strandberg"; 							
						System.out.print("Enter if the condition of the guitar(New/Used): "); 
						condition = in.next(); 
						in = new Scanner(System.in); 
						System.out.print("Enter a word for the type of wood used to build the neck: "); 
						neck = in.next(); 
						System.out.print("Enter an integer for the number of strings on the guitar: "); 
						strings = in.nextInt(); 
						System.out.print("Enter an integer for the number In-Stock: "); 
						inStock = in.nextInt(); 
						
						GuitarProduct gP2 = new GuitarProduct(itemID, description, price, manufacturer,
														inStock, condition, neck, strings); 
						
						products.addNodeAsTail(gP2);        				//add the item to inventory
						products.head = products.mergeSort(products.head);  //sort the inventory						
						String output2 = String.format("%s", timeStamp)
								  + String.format(" Data for new guitar product created. Product ID# %d ", itemID); 
					
						adminHistory.addNodeAsTail(output2);  				//add the event to adminHistory
						
						System.out.println(); 
						System.out.println(timeStamp+" Data for new guitar product created. Product ID# "+ itemID);
						
						//writeData(gPFile, products); 						//write products data to the file
						//writeData(aHFile, adminHistory); 					//write  adminHistpry to the file 
						System.out.println("\n--------------------------------------STRANDBERG GUITARS-------------------------------------------\n"); 
						while (iter3.hasNext())
						{
							GuitarProduct i = (GuitarProduct) iter3.next(); 
							int ID = i.getProductID(); 
							if (ID >= 20000 && ID < 30000)
							{
								i.menuPrint(); 
								System.out.println(); 
							}
						}
						System.out.println("--------------------------------------------------------------------------------------------------"); 	
						done = true; 
						
						break; 
						
					case 3: //create Gibson guitar data
						while (!done2)
						{
							System.out.println("\n----------------------------------------GIBSON GUITARS---------------------------------------------\n"); 
							ListIterator iter = products.listIterator(); 
							while (iter.hasNext())
							{
								GuitarProduct i = (GuitarProduct) iter.next(); 
								int ID = i.getProductID(); 
								if (ID >= 30000 && ID < 40000)
								{
									i.menuPrint(); 
									System.out.println(); 
								}
							}
							System.out.println("--------------------------------------------------------------------------------------------------"); 
							System.out.print("Enter a NEW product ID between 30000 and 39999, inclusive: ");
							itemID = in.nextInt(); 
							if (itemID < 30000 || itemID >= 40000) //reject
							{
								System.out.println("\nInvalid Entry. Please try again.\n"); 
							}
							else
							{
								while (iter2.hasNext())
								{
									GuitarProduct i = (GuitarProduct) iter2.next(); 
									int existingID = i.getProductID(); 
								
									if (existingID == itemID)
									{
										System.out.println("\nThis ID Number is currently being used. Please try again.\n"); 
										found = true;
									}
								}
								if (!found)
								{
									done2 = true; 
								}
							}
						}//while(!done2)
						
						in = new Scanner(System.in);
						System.out.print("Enter words for a short description of the guitar: "); 
						description = in.nextLine(); 
						in = new Scanner(System.in);
						System.out.print("Enter the retail price of the guitar: $"); 
						price = in.nextDouble(); 
						manufacturer = "Gibson"; 							
						System.out.print("Enter if the condition of the guitar(New/Used): "); 
						condition = in.next(); 
						in = new Scanner(System.in); 
						System.out.print("Enter a word for the type of wood used to build the neck: "); 
						neck = in.next(); 
						System.out.print("Enter an integer for the number of strings on the guitar: "); 
						strings = in.nextInt(); 
						System.out.print("Enter an integer for the number In-Stock: "); 
						inStock = in.nextInt(); 
						
						GuitarProduct gP3 = new GuitarProduct(itemID, description, price, manufacturer,
														inStock, condition, neck, strings); 
						
						products.addNodeAsTail(gP3);        				//add the item to inventory
						products.head = products.mergeSort(products.head);  //sort the inventory						
						String output3 = String.format("%s", timeStamp)
								  + String.format(" Data for new guitar product created. Product ID# %d ", itemID); 
					
						adminHistory.addNodeAsTail(output3);  				//add the event to adminHistory
						
						System.out.println(); 
						System.out.println(timeStamp+" Data for new guitar product created. Product ID# "+ itemID);
						
						//writeData(gPFile, products); 						//write products data to the file
						//writeData(aHFile, adminHistory); 					//write  adminHistpry to the file 
						System.out.println("\n----------------------------------------GIBSON GUITARS---------------------------------------------\n"); 
						while (iter3.hasNext())
						{
							GuitarProduct i = (GuitarProduct) iter3.next(); 
							int ID = i.getProductID(); 
							if (ID >= 30000 && ID < 40000)
							{
								i.menuPrint(); 
								System.out.println(); 
							}
						}
						System.out.println("--------------------------------------------------------------------------------------------------"); 	
						done = true; 
						break;
						
					case 4://Exit
						done = true; 
						break; 
					default:
						System.out.println("Invalid Entry. Please try again."); 
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
	}
	/**
	 * Method addAuctionProduct - add a new auction product to the inventory
	 */
	public static void addAuctionProduct()
	{
		Scanner in = new Scanner(System.in); 
		String aPFile = "data/AuctionProductsData.txt"; //the folder/fileName for AuctionProduct data 
		String aHFile = "data/AdminHistory.txt"; 		//the folder/fileName the adminHistory data is stored under
		LocalDateTime timeStamp = LocalDateTime.now();
		ListIterator iter = auctionP.listIterator(); 
		int choice = 0; 
		boolean done = false; 
		boolean done2 = false; 
		int itemID = 0; 
		String description = ""; 
		double price = 0; 
		double minBid = 0; 
		String manufacturer = ""; 
		String condition = ""; 
		String neck = ""; 
		int strings = 0; 
		int inStock = 0; 
		
		while (!done)
		{
			try
			{
				while(!done2)//repeat until new ID is established
				{
					boolean found = false; 
					System.out.println("\n----- ADD AUCTION GUITAR PRODUCT -----\n");
					viewAuctionProducts(); //display existing auction products
					//Get Info
					System.out.print("Enter a NEW product ID between 50000 and 59999, inclusive: ");//get new ID#
					itemID = in.nextInt(); 
					if (itemID < 50000 || itemID > 59999)
					{
						System.out.println("\nInvalid Entry. Please try again.\n"); 
					}
					else
					{
						while (iter.hasNext())
						{
							AuctionProduct i = (AuctionProduct) iter.next(); 
							int existingID = i.getProductID(); 
						
							if (existingID == itemID) //reject duplicate numbers
							{
								System.out.println("\nThis ID Number is currently being used. Please try again.\n"); 
								found = true;   
							}
						}
						if (!found) //allow to continue 
						{
							done2 = true; 
						}
						iter = auctionP.listIterator(); 
					}
				}//while
				in = new Scanner(System.in);
				System.out.print("Enter words for a short description of the guitar: "); 
				description = in.nextLine(); 
				in = new Scanner(System.in);
				System.out.print("Enter the retail price of the guitar: $"); 
				price = in.nextDouble();
				System.out.print("Enter the name of the maufacturer: "); 
				manufacturer = in.next(); 						
				System.out.print("Enter if the condition of the guitar(New/Used): "); 
				condition = in.next(); 
				in = new Scanner(System.in); 
				System.out.print("Enter a word for the type of wood used to build the neck: "); 
				neck = in.nextLine(); 
				in = new Scanner(System.in); 
				System.out.print("Enter an integer for the number of strings on the guitar: "); 
				strings = in.nextInt(); 
				System.out.print("Enter an integer for the number In-Stock: "); 
				inStock = in.nextInt(); 
				System.out.print("Enter a number for the minimum bid of the guitar: $"); 
				minBid = in.nextDouble(); 
				
				GuitarProduct gP = new GuitarProduct(itemID, description, price, manufacturer,
						inStock, condition, neck, strings); 						//new Guitar Product 
				AuctionProduct aP = new AuctionProduct(gP, 0.00, minBid, 0, true); //added to a new Auction Product 
				
				auctionP.addNodeAsTail(aP);        					//add the item to inventory
				auctionP.head = auctionP.mergeSort(auctionP.head);  //sort the inventory
				writeData(aPFile, auctionP); 						//write auction data to the file
				
				String output = String.format("%s", timeStamp)
							  + String.format(" Data for new Auction Guitar product created. Product ID# %d %n", itemID); 
				
				adminHistory.addNodeAsTail(output);  				//add the event to adminHistory
				writeData(aHFile, adminHistory); 					//write adminHistory to the file	
				System.out.println(); 
				System.out.println(timeStamp+" Data for new Auction Guitar product created. Product ID# "+ itemID);
				System.out.println(); 
				viewAuctionProducts();  
				
			done = true; //end input validation loop
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
	}//addAuctionProduct
	/**
	 * Method editAuctionProduct - allows an administrator to edit an auction listing
	 * @param - none
	 * @return - none
	 */
	public static void editAuctionProduct()
	{
		Scanner in = new Scanner(System.in);
		LocalDateTime timeStamp = LocalDateTime.now();
		String aPFile = "data/AuctionProductsData.txt"; //file path to store the auctionP data
		String aHFile = "data/AdminHistory.txt"; 		//file path to store the adminHistory data
		int num = 0;
		int stock = 0; 
		double newBid = 0; 
		String s = ""; 
		boolean done = false; 
		String output; 
		
		try
		{
			AuctionProduct aP = getAuctionProductID(); 
			if (aP.getProductID() == 0)//input validation 
			{
				viewAuctionProducts(); 
				editAuctionProduct(); 
			}

			while(!done)
			{
				System.out.println();  
				System.out.println("Enter 1 to edit auction product MINIMUM BID"); 
				System.out.println("Enter 2 to edit auction product LISTING STATUS"); 
				System.out.println("Enter 3 to ACCEPT OFFER"); 
				System.out.println("Enter 4 to DELETE PRODUCT"); 
				System.out.println("Enter 5 to EXIT\n"); 
				System.out.print("Enter choice: ");
				num = in.nextInt(); 
				System.out.println(); 
				switch(num)
				{
					case 1://Price 
						double oldMinBid = aP.getMinimumBid(); 
						System.out.print("Enter the new MINIMUM BID of the item: $"); 
						newBid = in.nextDouble(); 
						aP.setMinimumBid(newBid); 
						System.out.println("New minimum bid is: $"+newBid); 
						aP.print(); 
						output = String.format("%s", timeStamp)
								  + String.format(" New MINIMUM BID for Auction Product created: Old Minimum Bid $%.2f | New Minimum Bid $%.2f Product ID# %d %n", oldMinBid, newBid, aP.getProductID()); 
					
						adminHistory.addNodeAsTail(output);  //add to adminHistory
						writeData(aPFile, auctionP); 		 //write to file, auctionP 
						writeData(aHFile, adminHistory);     //write to file, adminHistory
					
						
						break; 
						
					case 2://Condition
						System.out.print("Change the activity of LISTING STATUS? Y/N: "); 
						s = in.next(); 
						if (s.equals("Y") || s.equals("y"))
						{
							if (aP.getListingStatus())//is true
							{
								aP.setListingStatus(false); 
								
								output = String.format("%s", timeStamp)
										  + String.format(" New item LISTING STATUS for Auction Product: Previous Status 'Active' | New Status 'In-Active' Product ID# %d %n", aP.getProductID()); 
							
								adminHistory.addNodeAsTail(output);  //add activity to adminHistory
								writeData(aPFile, auctionP); 		 //write to file, auctionP
								writeData(aHFile, adminHistory);     //write to file, adminHistory
							}
							else
							{
								aP.setListingStatus(true);
								
								output = String.format("%s", timeStamp)
										  + String.format(" New item LISTING STATUS for Auction Product: Previous Status 'In-Active' | New Status 'Active' Product ID# %d %n", aP.getProductID()); 
							
								adminHistory.addNodeAsTail(output);  //add activity to adminHistory
								writeData(aPFile, auctionP); 		 //write to file, auctionP
								writeData(aHFile, adminHistory);     //write to file, adminHistory
							}
						}
						else if (s.equals("N") || s.equals("n"))
						{
							break; 
						}
						else
						{
							System.out.println("Invalid entry. Please try again."); 
							viewAuctionProducts(); 
							editAuctionProduct(); 
						}
						System.out.println(); 
						aP.print(); 
						break; 
						
					case 3://Accept Offer
						aP.acceptOffer(); 
						
						output = String.format("%s", timeStamp)
								  + String.format(" Offer Accepted for Auction Product: SalePrice $%.2f Product ID# %d %n", aP.getCurrentBid(),aP.getProductID()); 
					
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(aPFile, auctionP); 		 //write to file, auctionP
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						aP.print(); 
						break;
					
					case 4://delete auction item
						viewAuctionProducts(); 
						aP = getAuctionProductID(); 
						ListIterator iter = auctionP.listIterator(); 
						while (iter.hasNext())
						{
							AuctionProduct i = (AuctionProduct) iter.next(); 
							if (i.getProductID() == aP.getProductID())
							{
								auctionP.deleteNode(i);
							}
						}
						System.out.println(timeStamp+"Auction Product Deleted. ProductID#"+ aP.getProductID());
						output = String.format("%s", timeStamp)
								  + String.format(" Auction product deleted. ID# %d %n",aP.getProductID()); 
					
						adminHistory.addNodeAsTail(output);  //add activity to adminHistory
						writeData(aPFile, auctionP); 		 //write to file, auctionP
						writeData(aHFile, adminHistory);     //write to file, adminHistory
						viewAuctionProducts();  
						break; 
					case 5://Exit 
						done = true; 
						break; 
						
					default://Input Validation
						System.out.println("Invalid entry.");
						viewAuctionProducts(); 
						editAuctionProduct(); 
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
				System.out.println("Enter 1 to  SHOP products");  
				System.out.println("Enter 2 to  CHECKOUT"); 
				System.out.println("Enter 3 to  VIEW your SHOPPING CART");
				System.out.println("Enter 4 to  VIEW your ACTIVE BIDS"); 
				System.out.println("Enter 5 to  VIEW your PURCHASE HISTORY"); 
				System.out.println("Enter 6 to  EXIT"); 
				System.out.println("\n---------------------------------------------------------------------------\n");  
				System.out.print("Enter choice: ");  
				choice = in.nextInt(); 
				
				switch(choice)
				{
					case 1://SHOP
						
						System.out.println("\n-------------SHOP GUITARS--------------\n"); 
						System.out.println("Enter 1 to shop RETAIL  products");
						System.out.println("Enter 2 to shop AUCTION listings");
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
						displayPurchaseHistory(); 
						break; 
					case 6:
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
	 * Method displayPurchaseHistory - displays the contents to the purchaseHistory array
	 */
	public static void displayPurchaseHistory()
	{
		try 
		{
			if (purchaseHistory.size() == 0)
			{
				System.out.println("\nNo Previous Purchase History Recorded.\n"); 
			}
			else
			{
				ListIterator iter = purchaseHistory.listIterator();
				System.out.println("\n-- PREVIOUS PURCHASE HISTORY --------------------------------\n"); 
				while(iter.hasNext())
				{
					String i = (String) iter.next(); 
					System.out.println(i); 
				}
				System.out.println("\n--------------------------- END OF PURCHASE HISTORY-------\n"); 
			}
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again."); 
		}
	}
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
		boolean done2 = false; 
		String choice = ""; 
		ListIterator iter = shoppingCart.listIterator(); 
		GuitarProduct gP = new GuitarProduct(); 
		String gPFile = "data/GuitarProductsData.txt";  //the folder/fileName for GuitarProduct data
		
		try
		{
			while(!done)
			{
				System.out.println("\nEnter 1 to ADD a product to ShoppingCart"); 
				System.out.println("Enter 2 to REMOVE a product from ShoppingCart"); 
				System.out.println("Enter 3 to VIEW guitar selection"); 
				System.out.println("Enter 4 to CHECKOUT"); 
				System.out.println("Enter 5 to RETURN to Main Menu"); 
				System.out.println(); 
				System.out.print("Enter your choice: "); 
				num = in.nextInt(); 
				switch (num)
				{
					case 1: //add an item to shopping cart
						in = new Scanner(System.in); 
						gP = getGuitarProductID(); 
						if (gP.getProductID() == 0) 
						{
							shopGuitarProduct(); //return
						}
						
						if (gP.inStock(1))	//if in stock
						{
							System.out.println(); 
							try 
							{
								while(!done2)
								{
									System.out.print("Would you like to ADD A CASE? Enter Y or N: "); 
									choice = in.nextLine(); 
									in = new Scanner(System.in);
									if (choice.equals("Y") || choice.equals("y"))
									{
									
										System.out.println("Enter the number 1 for hard case | $120.00 each"); 
										System.out.println("Enter the number 2 for soft case | $60.00 each"); 
										System.out.print("Enter choice: "); 
										num2 = in.nextInt(); 
										System.out.println(); 
										if (num2 == 1)
										{
											//gP.addHardCase(1);
											totalHardCases++; 
											System.out.println("Hard case added!"); 
											done2 = true; 
									
										}
										else if (num2 == 2) 
										{
											//gP.addSoftCase(1);
											totalSoftCases++; 
											System.out.println("Soft case added!");
											done2 = true; 
										}
										else
										{
											System.out.println("Invalid entry. Please try again.\n"); 
										}
									}
									else if (choice.equals("N") || choice.equals("n"))
									{
										System.out.println("\nNo case was added."); 
										done2 = true; 
										in = new Scanner(System.in); 
									} 
									else
									{
										System.out.println("Invalid entry. Please try again.\n"); 
									}
								}//loop
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
							gP.sellItem(1); //sell Item
							shoppingCart.addNodeAsTail(gP);
							shoppingCart.mergeSort(shoppingCart.head); //sort the cart in numerical order
							writeData(gPFile, products); //update the Guitar Products file
							
							displayShoppingCart(); 	
						}//if InStock
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
							while (iter.hasNext())
							{
								GuitarProduct i = (GuitarProduct) iter.next(); 
								if (i.getProductID() == productID)
								{
									int num3 = i.getQtyAvailable();
									num3++; 
									i.setQtyAvailable(num3);    //restore quantity 
									i.returnItem(1);     		//decrements totalSold
									shoppingCart.deleteNode(i); //delete object 
									System.out.println("...Item removed.");
									break; 
								}	
							}
							in = new Scanner(System.in); 
							System.out.print("Do you need to remove a case as well? Enter Y or N: "); 
							choice = in.nextLine(); 
							in = new Scanner(System.in);
							if (choice.equals("Y") || choice.equals("y"))
							{
								System.out.println("Enter 1 to remove hard case"); 
								System.out.println("Enter 2 to remove soft case"); 
								System.out.print("Enter choice: "); 
								num2 = in.nextInt(); 
								if (num2 == 1 && totalHardCases - 1 >= 0)
								{
									totalHardCases--; 
									System.out.println("\nHard case removed.\n"); 
									
								}
								else if (num2 == 2 && totalSoftCases - 1 >= 0) 
								{
									totalSoftCases--; 
									System.out.println("\nSoft case removed.\n");
								}
							}
							else if (choice.equals("N") || choice.equals("n"))
							{
								in = new Scanner(System.in); 
								System.out.println("\nNo case was removed.\n"); 
							}
							else
							{
								System.out.println("Invalid entry. Please try again."); 
							}
							displayShoppingCart(); 
						}
						break; 
					case 3:
						viewRetailProducts(); 
						break; 
					case 4://checkout process 
						checkout(); 
						break; 
					case 5://returns to main menu
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
		String aPFile = "data/AuctionProductsData.txt"; //the folder/fileName for AuctionProduct data 
		int num3 = 0; 
		int buyerID = 0; 
		double bid = 0; 
		boolean done = false; 
		String choice = ""; 
		
		try
		{
			while(!done)
			{
				System.out.println("Enter 1 to PLACE BID on a product"); 
				System.out.println("Enter 2 to VIEW auction guitars"); 
				System.out.println("Enter 3 to REVIEW your ACTIVE BIDS"); 
				System.out.println("Enter 4 to RETURN to Main Menu"); 
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
						System.out.print("Enter your buyer ID number (e.g. 66432): "); 
						buyerID = in.nextInt(); 
						aP.placeBid(buyerID, bid); 
						System.out.println(); 
						aP.print(); 
						writeData(aPFile, auctionP); //write Object to file
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
		ListIterator iter = products.listIterator(); 
		boolean done = false; 
		try
		{
			while (!done) {
				
			System.out.println("\nEnter 1 to view FENDER guitars"); 
			System.out.println("\nEnter 2 to view STRANDBERG guitars"); 
			System.out.println("\nEnter 3 to view GIBSON guitars"); 
			System.out.println("\nEnter 4 to view ALL guitars\n"); 
			System.out.print("Enter choice: "); 
			choice = in.nextInt();  
			switch (choice)
			{
				case 1: //FENDER
					System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n"); 
					while (iter.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter.next(); 
						int ID = i.getProductID(); 
						if (ID < 20000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					done = true; 
					break; 
					
				case 2: //STRANDBERG
					System.out.println("\n--------------------------------------STRANDBERG GUITARS-------------------------------------------\n"); 
					while (iter.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter.next(); 
						int ID = i.getProductID(); 
						if (ID > 20000 && ID < 30000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					done = true; 
					break; 
					
				case 3: //GIBSON
					System.out.println("\n--------------------------------------GIBSON GUITARS-----------------------------------------------\n"); 
					while(iter.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter.next(); 
						int ID = i.getProductID(); 
						if (ID > 30000 && ID < 40000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					done = true; 
					break; 
					
				case 4://view all
					System.out.println("\n---------------------------------------FENDER GUITARS---------------------------------------------\n"); 
					while(iter.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter.next(); 
						int ID = i.getProductID(); 
						if (ID < 20000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					System.out.println("\n--------------------------------------STRANDBERG GUITARS-------------------------------------------\n");
					ListIterator iter2 = products.listIterator(); 
					while (iter2.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter2.next(); 
						int ID = i.getProductID(); 
						if (ID > 20000 && ID < 30000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					System.out.println("\n--------------------------------------GIBSON GUITARS----------------------------------------------\n"); 
					ListIterator iter3 = products.listIterator(); 
					while (iter3.hasNext())
					{
						GuitarProduct i = (GuitarProduct) iter3.next(); 
						int ID = i.getProductID(); 
						if (ID > 30000 && ID < 40000)
						{
							i.menuPrint(); 
							System.out.println(); 
						}
					}
					System.out.println("--------------------------------------------------------------------------------------------------"); 
					done = true; 
					break;
				default:
					System.out.println("\nInvalid entry. Please try again."); 
					break; 
			}//switch
			
			}//while(!done)
			
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
			auctionP.printAuctionProduct();
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
		ListIterator iter = products.listIterator(); 
		try
		{
			while(!done)
			{
				System.out.print("Enter the product's ID number: ");
				IDnum = in.nextInt();
			
				while (iter.hasNext())
				{
					gP = (GuitarProduct) iter.next(); 
					if (gP.getProductID() == IDnum)
					{
						System.out.println("\n...Product found.");
						gP.menuPrint(); 
						done = true; 
						return gP; 
					}
				}
				iter = products.listIterator(); //refresh iterator
				System.out.println("Item not found. Please try again.");
				in = new Scanner(System.in);
			} 
		}
		catch (NoSuchElementException e)
		{
			System.out.println("Error. Please enter a valid ID number, and try again."); 
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
		ListIterator iter = auctionP.listIterator(); 
		try
		{
			while(!done)
			{
				System.out.print("Enter the product's ID number: ");
				IDnum = in.nextInt();
			
				while (iter.hasNext())
				{
					aP = (AuctionProduct) iter.next();  
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
			System.out.println("Error. Please enter a valid ID number, and try again."); 
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
			System.out.println("\nShopping Cart is empty. Nothing to checkout."); 
			return; 
		}
		final double HARDCASE_PRICE = 120.00; 
		final double SOFTCASE_PRICE = 60.00; 
		final double SALES_TAX = .04; 
		LocalDateTime timeStamp = LocalDateTime.now();
		String pHFile = "data/PurchaseHistory.txt"; //the folder/fileName for purchaseHistory data 
		int max = 50000; 
		int min = 10010; 
		int salesID = (int) (Math.random()*(max-min+1) + min); //generates a random salesID
		Scanner in = new Scanner(System.in);
		CashRegister reg = new CashRegister(SALES_TAX); 
		String choice = ""; 
		int num = 0; 
		int num2 = 0; 
		int menu = 0; 
		int totalItems = 0;
		double price = 0; 
		double payment = 0;
		double total = 0; 
		double finalTotal = 0; //afterTax
		ListIterator iter  = shoppingCart.listIterator(); 
		ListIterator iter2 = shoppingCart.listIterator(); 
		ListIterator iter3 = shoppingCart.listIterator(); 
		ListIterator iter4 = shoppingCart.listIterator(); 
		
		totalItems = totalItems + shoppingCart.size() + totalHardCases + totalSoftCases; 
		try 
		{
			System.out.printf("%n-------------------------- FINAL SALE -----------------------------%n");
			System.out.println("Total Items: " + totalItems ); 
			while (iter.hasNext())
			{
				Product i = (Product) iter.next(); 
				System.out.println(i.toString()); 
			}
			System.out.println(); 
			System.out.printf("Hard Cases: %d @ $120.00 ea | Soft Cases: %d @ $60.00 ea", totalHardCases, totalSoftCases); 
			
			while(iter2.hasNext())//send prices to the cash register
			{
				Product i = (Product) iter2.next();
				price = i.getPrice(); 
				reg.addItem(price); 
			}
			reg.addItem(totalHardCases*HARDCASE_PRICE); //add any hardCases
			reg.addItem(totalSoftCases*SOFTCASE_PRICE); //add any softCases
			
			System.out.printf("%nTOTAL: %.2f%nSTATE TAX: %.2f%nSALES TAX: %.2f%nFINAL TOTAL: $%.2f%n",
								reg.getTotal(), SALES_TAX, reg.getSalesTax(), reg.getTotalAfterTax()); 
			System.out.printf("--------------------------------------------------------------------%n");
			finalTotal = reg.getTotalAfterTax();
			reg.clear(); //clear until final payment is received
			
			System.out.println("\nPLEASE ENTER YOUR PAYMENT METHOD"); 
			System.out.println("Enter 1 For Credit\nEnter 2 For Cash"); 
			System.out.println("Enter 3 To return to Menu"); 
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
				System.out.println(); 
				return;   
			}
			else
			{
				totalItems = 0; 
				System.out.print("Invalid Entry.");
				return; 
			}
			//CREATE RECEIPT
			while(iter3.hasNext())//send prices to the cash register
			{
				Product i = (Product) iter3.next();
				price = i.getPrice(); 
				reg.addItem(price); 
			}
			reg.addItem(totalHardCases*HARDCASE_PRICE); //add any hardCases
			reg.addItem(totalSoftCases*SOFTCASE_PRICE); //add any softCases
			
			System.out.printf("%n---------------------------- RECIEPT -------------------------------%n");
			System.out.println("SaleID# " +salesID); 
			System.out.println("Total Items: " + totalItems); 
			while (iter4.hasNext())
			{
				Product i = (Product) iter4.next(); 
				System.out.println(i.toString());  
			}
			System.out.printf("Hard Cases: %d @ $120.00 ea | Soft Cases: %d @ $60.00 ea", totalHardCases, totalSoftCases); 
			System.out.printf("%nTOTAL: %.2f%nSTATE TAX: %.2f%nSALES TAX: %.2f%nFINAL TOTAL: $%.2f%n",
								reg.getTotal(), SALES_TAX, reg.getSalesTax(), reg.getTotalAfterTax());
			
			if (payment > reg.getTotalAfterTax() )
			{
				System.out.printf("\nPAYMENT: $%.2f", payment); 
				System.out.printf("  CHANGE:$%.2f%n%n", reg.getChange(payment));
				System.out.println(timeStamp); 
				System.out.println("---------------------------------------------------------------------\n");
				System.out.println("Thank you for shopping at GoGo Guitar!\n");
			}
			else 
			{
				System.out.printf("\nPAYMENT: $%.2f%n%n", payment); 
				System.out.println(timeStamp); 
				System.out.println("---------------------------------------------------------------------\n");
				System.out.println("Thank you for shopping at GoGo Guitar!\n");
			}
			
			//CREATE A COPY OF RECEIPT TO SAVE
			String output = String.format("%n---------------------------- RECIEPT -------------------------------%n")
						  + String.format("SaleID# "+salesID+"\n")
						  + String.format("Total Items: " + totalItems + "\n");
			iter4 = shoppingCart.listIterator();
			while (iter4.hasNext())
			{
				Product i = (Product) iter4.next(); 
				output += String.format(i.toString()+"\n");  
			}	
			output += String.format("Hard Cases: %d @ $120.00 ea | Soft Cases: %d @ $60.00 ea", totalHardCases, totalSoftCases) 
				   + String.format("%nTOTAL: %.2f%nSTATE TAX: %.2f%nSALES TAX: %.2f%nFINAL TOTAL: $%.2f%n",
							reg.getTotal(), SALES_TAX, reg.getSalesTax(), reg.getTotalAfterTax());
			if (payment > reg.getTotalAfterTax() )
			{
				output += String.format("\nPAYMENT: $%.2f", payment)
					   +  String.format("  CHANGE:$%.2f%n%n", reg.getChange(payment))
					   +  String.format(timeStamp+"\n")
				       +  String.format("---------------------------------------------------------------------\n");
			}
			else 
			{
				output += String.format("\nPAYMENT: $%.2f%n%n", payment)
					   +  String.format(timeStamp+"\n")
					   +  String.format("---------------------------------------------------------------------\n");
			}
			
			purchaseHistory.addNodeAsTail(output);           //add the receipt the purchaseHistory LinkedList
			purchaseHistory.mergeSort(purchaseHistory.head); //sort the list
			writeData(pHFile, purchaseHistory);              //write the object to a purchaseHistory file
			
			reg.clear(); //clear all relevant items
			shoppingCart = new LinkedList<GuitarProduct>(); 
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
				shoppingCart.printGuitarProduct(); 
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
		ListIterator iter = auctionP.listIterator(); 
		
		try
		{
			System.out.print("Enter your buyerID (e.g 66432): ");
			buyerID = in.nextInt(); 
			System.out.println("\n------------------------Your Auction Bids----------------------------\n"); 
			while(iter.hasNext())//send prices to the cash register
			{
				AuctionProduct i = (AuctionProduct) iter.next();
				
				if (buyerID == i.getBuyerID())
				{
					i.print(); 
					cost = cost + i.getPrice();
					totalBids++; 
					System.out.println(); 
				}
			}
			System.out.println("\nTotal active bids: " +totalBids+ " | Offer Total: $" +cost); 
			System.out.println("---------------------------------------------------------------------\n"); 
		}
		catch(Exception error)
		{
			System.out.println("Unknown Error. Please try again.");  
		}
	}
	
	/**
	 * Method retrieveObjectData: Retrieves any stored data for the LinkedLists
	 * @param fileNameAndPath  -(a String) the file name/path of the GuitarProduct data
	 * @param fileNameAndPath2 -(a String) the file name/path of the AuctionProduct data
	 * @param fileNameAndPath3 -(a String) the file name/path of the adminHistory data
	 * @param fileNameAndPath4 -(a String) the file name/path of the purchaseHistory data
	 * @return - none
	 */
	public static void retrieveAllData(String fileNameAndPath, String fileNameAndPath2, 
										  String fileNameAndPath3, String fileNameAndPath4)  
	{
		try
		{
			FileServices fs = new FileServices(fileNameAndPath); //GuitarProduct data
			if (fs.fileExists())
			{
				products = (LinkedList<GuitarProduct>) fs.readObject(); 
			}
			
			FileServices fs2 = new FileServices(fileNameAndPath2); //AuctionProduct data
			if (fs2.fileExists())
			{
				auctionP = (LinkedList<AuctionProduct>) fs2.readObject(); 
			}
			
			FileServices fs3 = new FileServices(fileNameAndPath3); //adminHistory data
			if (fs3.fileExists())
			{
				adminHistory = (LinkedList<String>) fs3.readObject(); 
			}
			
			FileServices fs4 = new FileServices(fileNameAndPath4); //purchaseHistory data
			if (fs4.fileExists())
			{
				purchaseHistory = (LinkedList<String>) fs4.readObject(); 
			}
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.print("Error. File was not found.");
		}
		catch (IOException ioe)
		{
			System.out.print("Error. IOException has occured.");
		}
		catch (ClassNotFoundException ce)
		{
			System.out.print("Error. The class required for this object was not found.");
		}
		catch (Exception e)
		{
			System.out.print("Unknown Error. Please try again.");
		}
	}
	
	/**
	 * Method writeObjectData: writes the data from a LinkedList to a file
	 * @param fileNameAndPath - (a String) the fileName/path in which to store the data
	 * @param name - (a LinkedList) the name of the LinkedList containing the data to be stored
	 */
	public static void writeData(String fileNameAndPath, Object name) 
	{
		try
		{
			FileServices fs = new FileServices(fileNameAndPath);  
			fs.writeObject(name);
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.print("Error. File was not found.");
		}
		catch (IOException ioe)
		{
			System.out.print("Error. IOException has occured.");
		}
		catch (Exception e)
		{
			System.out.print("Unkown Error. Please try again.");
		}
	}
	
	/**
	 * Method inputData: Maintains a hard code of the data to be retrieved.
	 */
	public static void inputData()
	{
		GuitarProduct gP = new GuitarProduct(10001, "1958 Reissue Telecaster: Ash Blonde", 
				1400.00, "Fender", 1, "Used","Maple", 6); 
		products.addNodeAsTail(gP); 
		GuitarProduct gP1 = new GuitarProduct(10003, "Tash Sultana Stratocastor: Transparent Cherry", 
				1199.99, "Fender", 5, "New","Maple", 6); 
		products.addNodeAsTail(gP1); 
		GuitarProduct gP2 = new GuitarProduct(10006, "Vintera '60s Jaguar: Sonic Blue", 
				1229.99, "Fender", 8, "New","Rosewood", 6); 
		products.addNodeAsTail(gP2);
		GuitarProduct gP3 = new GuitarProduct(10011, "Johnny Marr Jaguar: Olympic White", 
				2099.99, "Fender", 7, "New","Rosewood", 6); 
		products.addNodeAsTail(gP3); 
		
		//STRANDBERG
		GuitarProduct gP5 = new GuitarProduct(20015, "Boden Fusion 6: Black", 
				2495.00, "Strandberg", 7, "New","Rosewood", 6); 
		products.addNodeAsTail(gP5); 
		GuitarProduct gP4 = new GuitarProduct(20021, "Boden Prog 7: Natural", 
				2395.00, "Strandberg", 8, "New","Maple", 7); 
		products.addNodeAsTail(gP4);
		GuitarProduct gP7 = new GuitarProduct(20025, "Boden Original 7: Black ", 
				2295.00, "Strandberg", 6, "New","Maple", 7); 
		products.addNodeAsTail(gP7);
		GuitarProduct gP6 = new GuitarProduct(20031, "Boden Sarah Longfield Edt. 8 ", 
				2495.00, "Strandberg", 7, "New","Maple", 8); 
		products.addNodeAsTail(gP6);
		
		//GIBSON
		GuitarProduct gP8 = new GuitarProduct(30003, "1954 Les Paul Reissue Heavy Aged: Goldtop", 
				6999.00, "Gibson", 5, "New","Rosewood", 6); 
		products.addNodeAsTail(gP8);
		GuitarProduct gP9 = new GuitarProduct(30007, "Les Paul Standard '50s: Goldtop", 
				2499.00, "Gibson", 6, "New","Rosewood", 6); 
		products.addNodeAsTail(gP9);
		GuitarProduct gP10 = new GuitarProduct(30009, "70s Flying V: Ash White", 
				1999.00, "Gibson", 6, "New","Rosewood", 6); 
		products.addNodeAsTail(gP10);
		GuitarProduct gP11 = new GuitarProduct(30011, "SG Standard '61: Red", 
				1799.00, "Gibson", 7, "New","Rosewood", 6); 
		products.addNodeAsTail(gP11);
		
		//AUCTION
		GuitarProduct gP12 = new GuitarProduct(50011, "Vintera '60s Jaguar: Sonic Blue", 
				1229.99, "Fender", 1, "Like New","Rosewood", 6); 
		AuctionProduct aP1 = new AuctionProduct(gP12, 0.00, 849.99, 0, true);
		auctionP.addNodeAsTail(aP1); 
		GuitarProduct gP13 = new GuitarProduct(50031, "Boden Sarah Longfield Edt. 8 ", 
				2495.00, "Strandberg", 1, "Used","Maple", 8);
		AuctionProduct aP2 = new AuctionProduct(gP13, 0.00, 1200.99, 0, true);
		auctionP.addNodeAsTail(aP2); 
		GuitarProduct gP14 = new GuitarProduct(50001, "1958 Reissue Telecaster: Ash Blonde", 
				1400.00, "Fender", 1, "Used","Maple", 6); 
		AuctionProduct aP3 = new AuctionProduct(gP14, 0.00, 800.00, 0, true);
		auctionP.addNodeAsTail(aP3); 
		GuitarProduct gP15 = new GuitarProduct(50009, "70s Flying V: Ash White", 
				1999.00, "Gibson", 1, "Used","Rosewood", 6); 
		AuctionProduct aP4 = new AuctionProduct(gP15, 955.00, 900.00, 1345, true);
		auctionP.addNodeAsTail(aP4); 
	}
}//class
