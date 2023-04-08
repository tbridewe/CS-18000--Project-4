import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Menu {

    public static String WELCOME = "Welcome to the Marketplace!";
    public static String WELCOMEMENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit";
    public static String LINES = "-----------------";
    public static String EMAIL = "Email:";
    public static String PASSWORD = "Password:";
    public static String INCORRECTCREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public static String LOGINSUCCESS = "Login Successful";
    public static String USEREXISTS = "Username is taken, please try again";
    public static String ACCOUNTCREATED = "Account successfully created!";
    public static String BUYERMENU = "Please select an option:\n(1) Choose Item\n(2) Search\n(3) Sort\n(4) View cart\n(5) Log out";
    public static String SELLERMENU = "(1) View Listings\n(2) View Statistics\n(3) Log out";
    public static String LISTINGS = "(1) Add\n(2) Edit\n(3) Delete\n(4) Back";
    public static String INVALIDOPTION = "Invalid Option!";
    private static String itemsFileName = "ItemInformation.txt";

    //public Menu(String email, String password, int userType) throws InvalidUserInput {
    //  super(email, password, userType);
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

                if (welcomeOption == 1) { //login option
                    boolean loginBoolean = true;
                    while (loginBoolean == true) {
                        System.out.println(LINES);
                        System.out.println(EMAIL);
                        String email = sc.nextLine();
                        System.out.println(PASSWORD);
                        String password = sc.nextLine();

                        if (isValidEmail(email) == false) { //IsValidEmail used to check if there is an email match
                            System.out.println(INCORRECTCREDENTIALS);
                            loginBoolean = false;
                        } else if (isValidEmail(email) == true) { //isValidEmail used to check if there is NOT an email match
                            if (isCorrectLogin(email, password)) { //isCorrectLogin function to check if the login is correct (May not need)
                                System.out.println(LOGINSUCCESS);
                                //Set the current user to the login credentials
                                user = new User(email, password, 1); //create a user with the login credentials
                                loginBoolean = false;
                            } else {
                                System.out.println(INCORRECTCREDENTIALS);
                            }
                        }
                    }
                } else if (welcomeOption == 2) { //Create an account option
                    boolean loginBoolean = true; //used to loop back if needed
                    while (loginBoolean) {
                        System.out.println(LINES);
                        System.out.println("Create Account");
                        System.out.println("Are you a Buyer or a Seller");
                        String role = sc.nextLine();
                        //Assign role to the new object if buyer or seller
                        System.out.println(EMAIL);
                        String email = sc.nextLine();
                        System.out.println(PASSWORD);
                        String password = sc.nextLine();
                        
                        if (isValidEmail(email) == true) { //If there is an email match
                            System.out.println(USEREXISTS);
                        } else if(isValidEmail(email) == false {//if there isn't an email match
                            System.out.println(ACCOUNTCREATED);
                            //Add the user to the list of accounts by writing to the file
                            user = new User(email, password, 2); //create a user object with the credentials
                            loginBoolean = false;
                        }
                    }
                }
                if (user.isBuyer()) { //if the user is a buyer
                    //Marketplace market = new Marketplace(itemsFileName); // initialize marketplace object (OLD COMMENT - MAY NOT NEED
                    boolean booleanBuyer = true;
                    do {
                        printListings();//print the marketplace
                        System.out.println(BUYERMENU);
                        int buyerselection = Integer.parseInt(sc.nextLine());
                        if (buyerselection == 1) { //Choose Item
                            System.out.println("Please select the item you wish to purchase:");
                            int purchase = Integer.parseInt(sc.nextLine());
                            //Add to cart function
                            System.out.println("Added to Cart!");
                        } else if (buyerselection == 2) { //Serch by Keyword
                            System.out.println("Search by Keyword: ");
                            String keyword = sc.nextLine();
                            //search function to be used
                            //Add to cart function
                        } else if (buyerselection == 3) { //Sorting items
                            int sortType = 0;
                            int sortOrder = 0;
                            do {
                                System.out.println("How would you like to sort the items?");
                                System.out.println("(1) Price");
                                System.out.println("(2) Quantity");
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
                                System.out.println("(1) Ascending");
                                System.out.println("(2) Descending");
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
                        } else if (buyerselection == 4) { //View Cart
                            //display cart
                            System.out.println("(1) Checkout?");
                            System.out.println("(2) Back");
                            int cartOpperaion = Integer.parseInt(sc.nextLine());
                            if (cartOpperaion == 1) {
                                //Checkout Operations
                                System.out.println("Checkout Complete!");
                            } else if (cartOpperaion == 2) {
                            } else {
                                System.out.println(INVALIDOPTION);
                            }
                        } else if (buyerselection == 5) {//Logout
                            //logout function
                            System.out.println("Goodbye!");
                            booleanBuyer = false;
                            //we can either return and end the program or send the user back to the login screen
                        } else {
                            System.out.println(INVALIDOPTION);
                        }
                    } while (booleanBuyer == true);
                }
                if (user.isSeller) { //If the user is a seller
                    boolean booleanSeller = true;
                    do {
                        System.out.println(SELLERMENU);
                        int sellerSelection = Integer.parseInt(sc.nextLine());
                        if (sellerSelection == 1) { //View Listings
                            if (userHasStore or user.getStore != null)) { //EDIT THIS LINE DEPENDING ON YOUR FUNCTION (MAY NOT NEED)
                                boolean booleanListings = true;
                                do {
                                    //printListings();
                                    System.out.println(LISTINGS);
                                    int listingOption = Integer.parseInt(sc.nextLine());
                                    if (listingOption == 1) {
                                        //addStore
                                    } else if (listingOption == 2) {
                                        //edit specific listing
                                    } else if (listingOption == 3) {
                                        //removeListing function
                                    } else if (listingOption == 4) {
                                        booleanListings = false; //break the switch statement and return to the previous screen
                                    } else {
                                        System.out.println(INVALIDOPTION);
                                    }
                                } while (booleanListings);
                            } else {
                                System.out.println("You have no listings!");
                                break;
                            }
                        } else if (sellerSelection == 2) {//View Statistics -- This may need to be reviesed later as Tristan works on Seller.java
                            if (user.getStore != null) { //May need revisions - Check may not be needed
                                System.out.println("(1) All Stats\n(2) Specific Stats\n(3) Back");
                                int statSelection = Integer.parseInt(sc.nextLine());
                                if (statSelection == 1) {
                                    //printAllStats();
                                } else if (statSelection == 2) {
                                    System.out.println();//Buyer organization;
                                    //specificStats();
                                } else if (statSelection == 3) {
                                }
                            } else {
                                System.out.println("You have no Stores!");
                                break;
                            }
                        } else if (sellerSelection == 3) {//Log out
                            booleanSeller = false;
                            System.out.println("Logout Successful!");
                            System.out.println(LINES);
                        } else {
                            System.out.println(INVALIDOPTION);
                        }
                    } while (booleanSeller == true);
                }
            } while (welcomeOption > 3 || welcomeOption < 1);
        }
    }
}
