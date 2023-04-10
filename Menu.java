import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Menu {
    public final static String WELCOME = "Welcome to the Marketplace!";
    public final static String WELCOME_MENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit";
    public final static String LINES = "-----------------";
    public final static String EMAIL = "Email:";
    public final static String PASSWORD = "Password:";
    public final static String INCORRECT_CREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public final static String LOGIN_SUCCESS = "Login Successful";
    public final static String USER_EXISTS = "Username is taken, please try again";
    public final static String ACCOUNT_CREATED = "Account successfully created!";
    public final static String BUYER_MENU = "Please select an option:\n(1) Choose Item\n(2) Search\n(3) Sort\n(4) View Cart\n(5) Edit Account\n(6) Log Out";
    public final static String SELLER_MENU = "(1) View Listings\n(2) View Statistics\n(3) Edit Account\n(4) Log out";
    public final static String LISTINGS = "(1) Add\n(2) Edit\n(3) Delete\n(4) Back";
    public final static String INVALID_OPTION = "Invalid Option!";
    public final static String EDIT_OPTIONS = "(1) Edit Account Email\n(2) Edit Account Password\n(3) Delete Account\n(4) Back";
    public final static String CONFIRM = "Are you sure you would like to change your %s?\n";
    public final static String CONFIRM_OPTIONS = "(1) Yes\n(2) No";
    public final static String NEW_EMAIL = "Please enter a new email address for your account";
    public final static String NEW_PASSWORD = "Please enter a new password for your account";
    public final static String CREATE_ACCOUNT = "No account found with that email! Would you like to make an account, or continue trying to log in?";
    public final static String CREATE_OPTIONS = "(1) Create New Account\n(2) Re-attempt Login\n(3) Back";

    private static int processInteger(Scanner sc) {
        int integer = -1;

        try {
            String line = sc.nextLine();
            integer = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            //System.out.println(OPTION_ERROR);
        }

        return integer;
    }

    public static void main(String[] args) throws InvalidUserInput {
        int welcomeOption;
        String email;
        String password;
        String userTypeStr = null;
        // Customer buyer = null;
        // Customer seller = null;

        Scanner sc = new Scanner(System.in);
        System.out.println(WELCOME);

        boolean creatingAccount = false;

        while (true) {
            //User user = null;
            Customer buyer = null;
            Seller seller = null;

            do {
                System.out.println(WELCOME_MENU);
                welcomeOption = processInteger(sc);

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
                        email = sc.nextLine();
                        System.out.println(PASSWORD);
                        password = sc.nextLine();
                        if (!User.isValidEmail(email)) {
                            System.out.println("Please enter a valid email address!");
                            continue;
                        }

                        if (User.isCorrectLogin(email, password) == -1) {
                            if (User.accountExists(email)) {
                                System.out.println(INCORRECT_CREDENTIALS);
                                continue;
                            } else {
                                boolean login2 = true;

                                while (login2) {
                                    System.out.println(CREATE_ACCOUNT);
                                    System.out.println(CREATE_OPTIONS);
                                    int loginSelection = processInteger(sc);

                                    if (loginSelection == 1) {
                                        creatingAccount = true;
                                        login2 = false;
                                        loginBoolean = false;
                                    } else if (loginSelection == 2) {
                                        login2 = false;
                                    } else if (loginSelection == 3) {
                                        login2 = false;
                                        loginBoolean = false;
                                    }
                                }
                            }
                        } else {
                            System.out.println(LOGIN_SUCCESS);
                            try {
                                BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
                                String line;
                                ArrayList<String> contents = new ArrayList<>();

                                while ((line = reader.readLine()) != null) {
                                    contents.add(line);
                                }

                                for (int i = 0; i < contents.size(); i++) {
                                    if (contents.get(i).contains(email) && contents.get(i).contains(password)) {
                                        String[] loginInfo = contents.get(i).split(",");
                                        userTypeStr = loginInfo[2].split(":")[1];
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (userTypeStr.equals("Buyer")) { //if the user is a buyer // BUYER HERE
                                //Marketplace market = new Marketplace(itemsFileName); // initialize marketplace object (OLD COMMENT - MAY NOT NEED
                                boolean booleanBuyer = true;

                                buyer = new Customer(email, password, 0); // initialize buyer

                                Item selectedItem = null;

                                do {
                                    System.out.println(BUYER_MENU);
                                    int buyerselection = processInteger(sc);

                                    if (buyerselection == 1) { //Choose Item
                                        // user selection
                                        buyer.unsortListings();
                                        tryAddItem(sc, buyer);
                                    } else if (buyerselection == 2) { //Serch by Keyword
                                        System.out.println("(1) Search by Keyword");
                                        System.out.println("(2) Back");
                                        int searchOption = Integer.parseInt(sc.nextLine());
                                        if(searchOption == 1) {
                                            System.out.println("Search:");
                                            String keyword = sc.nextLine();
                                            buyer.keywordSearch(keyword);

                                            // not using tryAddItem here becuase of -1 back
                                            buyer.printListings();
                                            System.out.println("Please select the item you wish to purchase:");
                                            int purchase = processInteger(sc);
                                            selectedItem = buyer.getDisplayedItem(purchase); // get the item
                                            System.out.println("Please enter how many you would like to buy:");
                                            System.out.println("Enter '-1' to cancel purchase");
                                            int quantity = processInteger(sc);
                                            if (purchase < 0) {
                                                System.out.println("Purchase Cancelled");
                                                continue;
                                            }
                                            try { // add item with qantity error checking
                                                buyer.addToCart(selectedItem, quantity);
                                                System.out.printf("Added to Cart: %dx %s!", quantity, selectedItem.getName());
                                            } catch (InvalidQuantityException e) {
                                                System.out.println(e.getMessage());
                                            } catch (IndexOutOfBoundsException e) {
                                                System.out.println("Invalid item number selected!");
                                            }
                                        } else if (searchOption == 2) {
                                            continue;
                                            //go back
                                        }
                                    } else if (buyerselection == 3) { //Sorting items
                                        int sortType = 0;
                                        int sortOrder = 0;
                                        do {
                                            System.out.println("How would you like to sort the items?");
                                            System.out.println("(1) Price");
                                            System.out.println("(2) Quantity");
                                            sortType = processInteger(sc);
                                            System.out.println("Sort by Ascending order or Descending order?");
                                            System.out.println("(1) Ascending");
                                            System.out.println("(2) Descending");
                                            sortOrder = processInteger(sc);

                                            if ((sortType < 1) || (sortType > 2) || (sortOrder < 1) || (sortOrder > 2)) {
                                                System.out.println(INVALID_OPTION);
                                            } else {
                                                buyer.sortMarketplace(sortType, sortOrder);
                                                tryAddItem(sc, buyer);
                                            }
                                        } while ((sortType < 1) || (sortType > 2) || (sortOrder < 1) || (sortOrder > 2));
                                        //sortItems(sortType, sortOrder);
                                    } else if (buyerselection == 4) { //View Cart
                                        buyer.printCart();
                                        System.out.println("(1) Checkout?");
                                        System.out.println("(2) View Purchase History");
                                        System.out.println("(3) Remove Item");
                                        System.out.println("(4) Back");
                                        int cartOperation = processInteger(sc);
                                        if (cartOperation == 1) { // checkout
                                            buyer.checkout();
                                            System.out.println("Checkout Complete!");
                                        } else if (cartOperation == 2) { // purchase history
                                            buyer.viewPurchases();
                                            System.out.println("Would you like to export purchase history?\n(1) Yes\n(2) No");
                                            int export = processInteger(sc);
                                            if(export == 1) {
                                                System.out.println("Enter the name of the file to save the purchase history to:");
                                                String fileName = sc.nextLine();
                                                buyer.exportPurchases(fileName);
                                                System.out.println("Purchase history successfully exported to file!"); //or use filename in place of "file"
                                            } else if (export == 2) {
                                            } else {
                                                System.out.println(INVALID_OPTION);
                                            }
                                        }
                                        else if (cartOperation == 3) { // remove
                                            buyer.printCart();
                                            System.out.println("Select an item to remove from cart");
                                            int remove = Integer.parseInt(sc.nextLine());
                                            System.out.println("Please enter how many you would like to remove:");
                                            int quantity = processInteger(sc);
                                            //buyer.removeFromCart(remove, quantity);

                                        } else if(cartOperation == 4) {

                                        } else {
                                            System.out.println(INVALID_OPTION);
                                        }
                                    } else if (buyerselection == 5) { // edit user information
                                        boolean editing = true;

                                        do {
                                            System.out.println(EDIT_OPTIONS);
                                            int editOption = processInteger(sc);

                                            if (editOption == 1) { // edit account email
                                                System.out.println(NEW_EMAIL);
                                                String newEmail = sc.nextLine();

                                                boolean confirmation = true;

                                                while (confirmation) {
                                                    System.out.printf(CONFIRM, "email");
                                                    System.out.println(CONFIRM_OPTIONS);

                                                    int confirmOption = processInteger(sc);

                                                    if (confirmOption == (1)) { // yes, change email/password
                                                        buyer.editUser(newEmail, null, false);

                                                        confirmation = false;
                                                    } else if (confirmOption == (2)) { // no, go back
                                                        confirmation = false;
                                                    }
                                                }
                                            } else if (editOption == 2) { // edit account password
                                                System.out.println(NEW_PASSWORD);
                                                String newPassword = sc.nextLine();

                                                boolean confirmation = true;

                                                while (confirmation) {
                                                    System.out.printf(CONFIRM, "password");
                                                    System.out.println(CONFIRM_OPTIONS);

                                                    int confirmOption = processInteger(sc);

                                                    if (confirmOption == (1)) { // yes, change email/password
                                                        buyer.editUser(null, newPassword, false);

                                                        confirmation = false;
                                                    } else if (confirmOption == (2)) { // no, go back
                                                        confirmation = false;
                                                    }
                                                }
                                            } else if (editOption == 3) { // delete account: MUST LOG OUT THE USER
                                                buyer.editUser(null, null, true);
                                                booleanBuyer = false;
                                                welcomeOption = 99;
                                                editing = false;
                                            } else if (editOption == 4) { // go back
                                                editing = false;
                                            }
                                        } while (editing);
                                    } else if (buyerselection == 6) {//Logout
                                        //logout function
                                        System.out.println("Goodbye!");
                                        booleanBuyer = false;
                                        //we can either return and end the program or send the user back to the login screen
                                    } else {
                                        System.out.println(INVALID_OPTION);
                                    }
                                } while (booleanBuyer == true);
                            }
                            if (userTypeStr.equals("Seller")) { //If the user is a seller // SELLER HERE
                                seller = new Seller(email, password, 1); // initialize seller

                                boolean booleanSeller = true;
                                do {
                                    System.out.println(SELLER_MENU);
                                    int sellerSelection = processInteger(sc);
                                    if (sellerSelection == 1) { //View Listings
                                        seller.printListings();
                                        if (seller.getStores() != null) {
                                            boolean booleanListings = true;
                                            do {
                                                //printListings();
                                                System.out.println(LISTINGS);
                                                int listingOption = processInteger(sc);
                                                if (listingOption == 1) {
                                                    System.out.println("(1) Add Item \n(2) Add from CSV");
                                                    int addChoice = sc.nextInt();
                                                    sc.nextLine();

                                                    if (addChoice == 1) {
                                                        System.out.println("Enter name:");
                                                        String name = sc.nextLine();
                                                        System.out.println("Enter store:");
                                                        String store = sc.nextLine();
                                                        System.out.println("Enter description:");
                                                        String description = sc.nextLine();
                                                        System.out.println("Enter quantity:");
                                                        int quantity = sc.nextInt();
                                                        sc.nextLine();
                                                        System.out.println("Enter price:");
                                                        double price = sc.nextDouble();
                                                        sc.nextLine();
                                                        Item item = new Item(name, store, description, quantity, price);
                                                        seller.addNewItem(item);
                                                    } else if (addChoice == 2) {
                                                        System.out.println("Enter filename:");
                                                        String filename = sc.nextLine();
                                                        seller.addFromCSV(filename);
                                                    } else {
                                                        System.out.println("Incorrect choice!");
                                                    }

                                                } else if (listingOption == 2) {
                                                    //seller.editItem();
                                                } else if (listingOption == 3) {
                                                    //seller.removeItem();
                                                } else if (listingOption == 4) {
                                                    booleanListings = false; //break the switch statement and return to the previous screen
                                                } else {
                                                    System.out.println(INVALID_OPTION);
                                                }
                                            } while (booleanListings);
                                        } else {
                                            System.out.println("You have no listings!");
                                        }
                                    } else if (sellerSelection == 2) {//View Statistics -- This may need to be reviesed later as Tristan works on Seller.java
                                        if (/*user.getStore != null*/true) { //May need revisions - Check may not be needed
                                            System.out.println("(1) All Stats\n(2) Specific Stats\n(3) Back");
                                            int statSelection = processInteger(sc);
                                            if (statSelection == 1) {
                                                seller.viewAllStats();
                                            } else if (statSelection == 2) {
                                                System.out.println("(1) Price \n(2) Quantity");
                                                int sortType = sc.nextInt();
                                                sc.nextLine();
                                                System.out.println("(1) Ascending \n(2) Descending");
                                                int sortOrder = sc.nextInt();
                                                sc.nextLine();
                                                seller.sortStats(sortType, sortOrder);
                                            } else if (statSelection == 3) {

                                            }
                                        } else {
                                            System.out.println("You have no Stores!");
                                        }
                                    } else if (sellerSelection == 3) { // edit user information
                                        boolean editing = true;

                                        do {
                                            System.out.println(EDIT_OPTIONS);
                                            int editOption = processInteger(sc);

                                            if (editOption == 1) { // edit account email
                                                System.out.println(NEW_EMAIL);
                                                String newEmail = sc.nextLine();

                                                boolean confirmation = true;

                                                while (confirmation) {
                                                    System.out.printf(CONFIRM, "email");
                                                    System.out.println(CONFIRM_OPTIONS);

                                                    int confirmOption = processInteger(sc);

                                                    if (confirmOption == (1)) { // yes, change email/password
                                                        seller.editUser(newEmail, null, false);

                                                        confirmation = false;
                                                    } else if (confirmOption == (2)) { // no, go back
                                                        confirmation = false;
                                                    }
                                                }
                                            } else if (editOption == 2) { // edit account password
                                                System.out.println(NEW_PASSWORD);
                                                String newPassword = sc.nextLine();

                                                boolean confirmation = true;

                                                while (confirmation) {
                                                    System.out.printf(CONFIRM, "password");
                                                    System.out.println(CONFIRM_OPTIONS);

                                                    int confirmOption = processInteger(sc);

                                                    if (confirmOption == (1)) { // yes, change email/password
                                                        seller.editUser(null, newPassword, false);

                                                        confirmation = false;
                                                    } else if (confirmOption == (2)) { // no, go back
                                                        confirmation = false;
                                                    }
                                                }
                                            } else if (editOption == 3) { // delete account: MUST LOG OUT THE USER
                                                seller.editUser(null, null, true);
                                                booleanSeller = false;
                                                welcomeOption = 99;
                                                editing = false;
                                            } else if (editOption == 4) { // go back
                                                editing = false;
                                            }
                                        } while (editing);
                                    }else if (sellerSelection == 4) {//Log out
                                        booleanSeller = false;
                                        System.out.println("Logout Successful!");
                                        System.out.println(LINES);
                                    } else {
                                        System.out.println(INVALID_OPTION);
                                    }
                                } while (booleanSeller == true);
                            }
                        }
                    }
                }

                if (welcomeOption == 2 || creatingAccount) {
                    boolean loginBoolean = true;
                    creatingAccount = false;

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
                        email = sc.nextLine();
                        System.out.println(PASSWORD);
                        password = sc.nextLine();

                        if (User.accountExists(email)) {
                            System.out.println(USER_EXISTS);
                        } else {
                            try {
                                if (userType == 0) {
                                    buyer = new Customer(email, password, userType);
                                } else if (userType == 1) {
                                    seller = new Seller(email, password, userType);
                                }

                                User.saveNewUser(email, password, role);

                                System.out.println(ACCOUNT_CREATED);

                                loginBoolean = false;
                            } catch (InvalidUserInput e) {
                                email = "";
                                password = "";
                                userType = -1;

                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            } while (welcomeOption > 3 || welcomeOption < 1);
        }
    }

    // METHODS!!! (buyer stuff to make it less messy)

    /* tryAddItem
     * Prompts for adding an item to cart with error checking
     */
    public static void tryAddItem(Scanner sc, Customer buyer) {
        buyer.printListings();
        System.out.println("Please select the item you wish to purchase:");
        int purchase = processInteger(sc);
        
        Item selectedItem = buyer.getDisplayedItem(purchase); // get the item

        System.out.println("Please enter how many you would like to buy:");
        int quantity = processInteger(sc);

        try { // add item with qantity error checking
            buyer.addToCart(selectedItem, quantity);
            System.out.printf("Added to Cart: %dx %s!\n", quantity, selectedItem.getName());
        } catch (InvalidQuantityException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid item number selected!");
        }
    }
}
