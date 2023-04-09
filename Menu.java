import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Menu {

    public static String WELCOME = "Welcome to the Marketplace!";
    public static String WELCOME_MENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit";
    public static String LINES = "-----------------";
    public static String EMAIL = "Email:";
    public static String PASSWORD = "Password:";
    public static String INCORRECT_CREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public static String LOGIN_SUCCESS = "Login Successful";
    public static String USER_EXISTS = "Username is taken, please try again";
    public static String ACCOUNT_CREATED = "Account successfully created!";
    public static String BUYER_MENU = "Please select an option:\n(1) Choose Item\n(2) Search\n(3) Sort\n(4) View cart\n(5) Log out";
    public static String SELLER_MENU = "(1) View Listings\n(2) View Statistics\n(3) Log out";
    public static String LISTINGS = "(1) Add\n(2) Edit\n(3) Delete\n(4) Back";
    public static String INVALID_OPTION = "Invalid Option!";
    private static String itemsFileName = "ItemInformation.txt";

    //public Menu(String email, String password, int userType) throws InvalidUserInput {
    //  super(email, password, userType);
    //}//


    public static void main(String[] args) throws InvalidUserInput {
        int welcomeOption;

        // Customer buyer = null;
        // Customer seller = null;

        Scanner sc = new Scanner(System.in);
        System.out.println(WELCOME);

        while (true) {
            //User user = null;
            Customer buyer = null;
            Seller seller = null;

            do {
                System.out.println(WELCOME_MENU);
                welcomeOption = Integer.parseInt(sc.nextLine());

                if (welcomeOption == 1) {
                    System.out.println("Login Menu");
                } else if (welcomeOption == 2) {
                    System.out.println("Create New Account");
                } else if (welcomeOption == 3) {
                    System.out.println("Bye!");
                    return;
                } else {
                    System.out.println(INVALID_OPTION);
                }

                if (welcomeOption == 1) {
                    boolean loginBoolean = true;

                    while (loginBoolean) {
                        System.out.println(LINES);
                        System.out.println(EMAIL);
                        String email = sc.nextLine();
                        System.out.println(PASSWORD);
                        String password = sc.nextLine();

                        if (!User.isValidEmail(email)) {
                            System.out.println(INCORRECT_CREDENTIALS);
                        } else if (User.isValidEmail(email)) {
                            int userType = User.isCorrectLogin(email, password);

                            if (userType == -1) {
                                System.out.println(INCORRECT_CREDENTIALS);

                                loginBoolean = true;
                            } else if (userType == 0) {
                                buyer = new Customer(email, password, userType);

                                loginBoolean = false;
                            } else if (userType == 1) {
                                seller = new Seller(email, password, userType);

                                loginBoolean = false;
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

                        int userType;

                        if (role.equals("Buyer")) {
                            userType = 0;
                        } else if (role.equals("Seller")) {
                            userType = 1;
                        } else {
                            System.out.println(INVALID_OPTION);

                            continue;
                        }

                        System.out.println(EMAIL);
                        String email = sc.nextLine();
                        System.out.println(PASSWORD);
                        String password = sc.nextLine();

                        if (User.isValidEmail(email)) {
                            System.out.println(USER_EXISTS);
                        } else {
                            System.out.println(ACCOUNT_CREATED);

                            User.saveNewUser(email, password, role);

                            if (userType == 0) {
                                buyer = new Customer(email, password, userType);
                            } else if (userType == 1) {
                                seller = new Seller(email, password, userType);
                            }

                            loginBoolean = false;
                        }
                    }
                }
                if (buyer != null) { //if the user is a buyer
                    //Marketplace market = new Marketplace(itemsFileName); // initialize marketplace object (OLD COMMENT - MAY NOT NEED
                    boolean booleanBuyer = true;

                    //Customer buyer = new Customer(customer.getEmail(), customer.getPassword(), 0);

                    Item selectedItem = null;

                    do {
                        System.out.println(BUYER_MENU);
                        int buyerselection = Integer.parseInt(sc.nextLine());

                        if (buyerselection == 1) { //Choose Item
                            // user selection
                            buyer.printListings();;//print the marketplace
                            System.out.println("Please select the item you wish to purchase:");

                            int purchase = Integer.parseInt(sc.nextLine());
                            selectedItem = buyer.getDisplayedItem(purchase); // get the item

                            System.out.println("Please enter how many you would like to buy:");
                            int quantity = Integer.parseInt(sc.nextLine());

                            try { // add item with qantity error checking
                                buyer.addToCart(selectedItem, quantity);
                                System.out.printf("Added to Cart: %dx %s!", quantity, selectedItem.getName());
                            } catch (InvalidQuantityException e) {
                                System.out.println(e.getMessage());
                            }

                        } else if (buyerselection == 2) { //Serch by Keyword
                            // TODO
                            System.out.println("Search by Keyword: ");
                            String keyword = sc.nextLine();
                            buyer.keywordSearch(keyword);
                            buyer.printListings();

                            //Add to cart function
                            buyer.printListings();;//print the marketplace
                            System.out.println("Please select the item you wish to purchase:");
                            int purchase = Integer.parseInt(sc.nextLine());
                            selectedItem = buyer.getDisplayedItem(purchase); // get the item
                            System.out.println("Please enter how many you would like to buy:");
                            int quantity = Integer.parseInt(sc.nextLine());
                            try { // add item with qantity error checking
                                buyer.addToCart(selectedItem, quantity);
                                System.out.printf("Added to Cart: %dx %s!", quantity, selectedItem.getName());
                            } catch (InvalidQuantityException e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (buyerselection == 3) { //Sorting items
                            // TODO
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
                                    System.out.println(INVALID_OPTION);
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
                                    System.out.println(INVALID_OPTION);
                                }
                            } while ((sortOrder < 1) || (sortOrder > 2));
                            //sortItems(sortType, sortOrder);
                        } else if (buyerselection == 4) { //View Cart
                            buyer.printCart();
                            System.out.println("(1) Checkout?");
                            System.out.println("(2) Back");
                            int cartOpperaion = Integer.parseInt(sc.nextLine());
                            if (cartOpperaion == 1) {
                                buyer.checkout();
                                System.out.println("Checkout Complete!");
                            } else if (cartOpperaion == 2) {
                            } else {
                                System.out.println(INVALID_OPTION);
                            }
                        } else if (buyerselection == 5) {//Logout
                            //logout function
                            System.out.println("Goodbye!");
                            booleanBuyer = false;
                            //we can either return and end the program or send the user back to the login screen
                        } else {
                            System.out.println(INVALID_OPTION);
                        }
                    } while (booleanBuyer == true);
                }
                if (seller != null) { //If the user is a seller
                    boolean booleanSeller = true;
                    do {
                        System.out.println(SELLER_MENU);
                        int sellerSelection = Integer.parseInt(sc.nextLine());
                        if (sellerSelection == 1) { //View Listings
                            if (/*userHasStore || user.getStore != null*/true) { //EDIT THIS LINE DEPENDING ON YOUR FUNCTION (MAY NOT NEED)
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
                                        System.out.println(INVALID_OPTION);
                                    }
                                } while (booleanListings);
                            } else {
                                System.out.println("You have no listings!");
                                break;
                            }
                        } else if (sellerSelection == 2) {//View Statistics -- This may need to be reviesed later as Tristan works on Seller.java
                            if (/*user.getStore != null*/true) { //May need revisions - Check may not be needed
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
                            System.out.println(INVALID_OPTION);
                        }
                    } while (booleanSeller == true);
                }
            } while (welcomeOption > 3 || welcomeOption < 1);
        }
    }
}
