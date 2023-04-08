import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Menu {

    public static String WELCOME = "Welcome to the Marketplace!";
    public static String WELCOMEMENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit\n";
    public static String LINES = "-----------------";
    public static String EMAIL = "Email:";
    public static String PASSWORD = "Password:";
    public static String INCORRECTCREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public static String LOGINSUCCESS = "Login Successful";
    public static String USEREXISTS = "Username is taken, please try again";
    public static String ACCOUNTCREATED = "Account successfully created!";
    public static String BUYERMENU = "Please select an option:\n(1) Choose Item\n(2) Search\n(3) Sort\n(4) View cart\n(5) Log out";
    public static String SELLERMENU = "(1) View Listings\n(2) View Store\n(3) Log out";
    public static String LISTINGS = "(1) Add\n(2) Edit\n(4) Delete";
    public static String INVALIDOPTION = "Invalid Option!";
    private static String itemsFileName = "ItemInformation.txt";

    
    //creating a user public Menu(String email, String password, int userType) throws InvalidUserInput {
        //super(email, password, userType);
    //}


    public static void main(String[] args) throws InvalidUserInput {
        int welcomeOption;

        Scanner sc = new Scanner(System.in);
        System.out.println(WELCOME);

        while (true) {
            User user = null;
            do {
                System.out.println(WELCOMEMENU);
                welcomeOption = Integer.parseInt(sc.nextLine());

                if (welcomeOption == 1) {
                    System.out.println("Login Menu");
                } else if (welcomeOption == 2) {
                    System.out.println("Create New Account");
                } else if (welcomeOption == 3) {
                    System.out.println("Bye!");
                    return;
                } else {
                    System.out.println(INVALIDOPTION);
                }

            } while (welcomeOption > 3 || welcomeOption < 1);

            if (welcomeOption == 1) {
                boolean loginBoolean = true;
                while (loginBoolean) {
                    System.out.println(LINES);
                    System.out.println(EMAIL);
                    String email = sc.nextLine();
                    System.out.println(PASSWORD);
                    String password = sc.nextLine();

                    if (User.isValidEmail(email) == false) {
                        System.out.println(INCORRECTCREDENTIALS);
                    } else if (User.isValidEmail(email)) {
                        if (isCorrectLogin(email, password)) {
                            System.out.println(LOGINSUCCESS);
                            //Set the current user to the login credentials
                            user = new User(email, password, 1); //create a user with the login credentials
                            loginBoolean = false;
                        } else {
                            System.out.println(INCORRECTCREDENTIALS);
                        }
                    }
                }
            } else if (welcomeOption == 2) {
                boolean loginBoolean = true;

                while (loginBoolean) {
                    System.out.println(LINES);
                    System.out.println("Create Account");
                    System.out.println("Are you a Buyer or a Seller");
                    String role = sc.nextLine();
                    System.out.println(EMAIL);
                    String email = sc.nextLine();
                    System.out.println(PASSWORD);
                    String password = sc.nextLine();

                    if (isValidEmail(email)) {
                        System.out.println(USEREXISTS);
                    } else {
                        System.out.println(ACCOUNTCREATED);
                        //Add the user to the list of accounts
                        user = new User(email, password, 2); //create a user object with the credentials
                    }
                }
            }
            if (user.isBuyer()) {
                //Marketplace market = new Marketplace(itemsFileName); // initialize marketplace object
                //market.printListings();//print the marketplace
                // CREATE A BUYER
                Customer buyer = new Customer("email@gmail.com", "1234", 1);
                // TODO: make an actual customer object 
                System.out.println(BUYERMENU);
                int buyerselection = Integer.parseInt(sc.nextLine());
                boolean booleanBuyer = true;
                do {
                    switch (buyerselection) {
                        case 1: //Choose Item  
                            buyer.printListings();                          
                            System.out.println("Please select the item you wish to purchase:");
                            int purchase = Integer.parseInt(sc.nextLine());
                            System.out.println("How many would you like to add to the cart?");
                            int quantity = Integer.parseInt(sc.nextLine());
                            Item item = buyer.getDisplayedItem(purchase); // get the item
                            buyer.addToCart(item, quantity);            // put the item in cart
                            //Add to cart function
                            break;
                        case 2: //Search
                            System.out.println("Search by Keyword: ");
                            String keyword = sc.nextLine();
                            //search function to be used
                            //Add to cart function
                        case 3: //View Sort
                            int sortType = 0;
                            int sortOrder = 0;
                            do {
                                System.out.println("How would you like to sort the items?");
                                System.out.println("1. Price");
                                System.out.println("2. Quantity");
                                sortType = Integer.parseInt(sc.nextLine());
                                if (sortType == 1) {
                                    //sortByPrice
                                } else if (sortType == 2) {
                                    //sortByQuantity
                                } else {
                                    System.out.println(INVALIDOPTION);
                                }

                            } while ((sortType < 1) || (sortType > 2));
                            do {
                                System.out.println("Sort by Ascending order or Descending order?");
                                System.out.println("1. Ascending");
                                System.out.println("2. Descending");
                                sortOrder = Integer.parseInt(sc.nextLine());
                                if (sortOrder == 1) {
                                    //sortAscending
                                } else if (sortOrder == 2) {
                                    //sortDecending
                                } else {
                                    System.out.println(INVALIDOPTION);
                                }
                            } while ((sortOrder < 1) || (sortOrder > 2));
                            //sortItems(sortType, sortOrder);
                            break;
                        case 4: //View Cart
                            //display cart
                            System.out.println("1. Checkout?");
                            System.out.println("2. Back");
                            int cartOpperaion = Integer.parseInt(sc.nextLine());
                            if (cartOpperaion == 1) {
                                //Checkout Operations
                                System.out.println("Checkout Complete!");
                                break;
                            } else if (cartOpperaion == 2) {
                                break;
                            } else {
                                System.out.println(INVALIDOPTION);
                            }
                        case 5: //Logout
                            //logout function
                            System.out.println("Goodbye!");
                            booleanBuyer = false;
                            break; //we can either return and end the program or send the user back to the login screen
                    }
                } while (booleanBuyer);
                if (user.isSeller) {
                    System.out.println(SELLERMENU);
                    int sellerSelection = Integer.parseInt(sc.nextLine());
                    boolean booleanSeller = true;
                    do {
                        switch(sellerSelection) {
                            case 1: //View Listings
                                if(userHasStore or user.getStore != null) {
                                    printListings();
                                    System.out.println(LISTINGS);
                                    int listingOption = Integer.parseInt(sc.nextLine());
                                    if(listingOption == 1) {
                                        //addStore
                                    } else if(listingOption == 2) {
                                        //edit specific listing
                                    } else if(listingOption == 3) {
                                        //removeListing function
                                    } else if(lisingOption == 4) {
                                        break; //break the switch statement and return to the previous screen
                                    } else {
                                        System.out.println(INVALIDOPTION);
                                    }
                                 } else {
                                System.out.println("You have no listings!");
                                break;
                            }
                            case 2: //View Store
                                if(user.getStore != null) {
                                    System.out.println("(1) All Stats\n(2) Specific Stats\n(3) Back");
                                    int statSelection = Integer.parseInt(sc.nextLine());
                                    if(statSelection == 1) {
                                        printAllStats();
                                    }
                                    else if(statSelection == 2) {
                                        specificStats();
                                    }
                                } else {
                                    System.out.println("You have no Stores!");
                                    break;
                                }
                        }
                    } while(booleanSeller == true);
                }
            }
        }
    }
}
