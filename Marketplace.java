import java.util.Scanner;
public class Marketplace {

    public static String WELCOME = "Welcome to the Marketplace!";
    public static String WELCOMEMENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit\n";
    public static String LINES = "-----------------";
    public static String EMAIL = "Email:";
    public static String PASSWORD = "Password:";
    public static String INCORRECTCREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public static String LOGINSUCCESS = "Login Successful";
    public static String USEREXISTS = "Username is taken, please try again";
    public static String ACCOUNTCREATED = "Account successfully created!";
    public static String BUYERMENU = "Please select an option:\n(1) Choose Item\n(2) Search\n(3) Sort\n(4) View cart\n";

    public static void main(String[] args) {
        int welcomeOption;

        Scanner sc = new Scanner(System.in);
        System.out.println(WELCOME);

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
                System.out.println("Invalid Option!");
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

                if (emailExits(email) == false) {
                    System.out.println(INCORRECTCREDENTIALS);
                } else if (emailExists(email)) {
                    if (isCorrectLogin(email, password)) {
                        System.out.println(LOGINSUCCESS);
                        //Set the current user to the login credentials
                        User user = new User(email, password); //create a user with the login credentials
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

                if (emailExists(email)) {
                    System.out.println(USEREXISTS);
                } else {
                    System.out.println(ACCOUNTCREATED);
                    //Add the user to the list of accounts
                    User user = new User(email, password); //create a user object with the credentials
                }
            }
        }
        if(user.isBuyer) {
            displayMarketplace();//print the marketplace
            System.out.println(BUYERMENU);
            int buyerselection = Integer.parseInt(sc.nextLine());
            boolean booleanBuyer = true
            do {
                switch (buyerselection) {
                    case 1:
                        System.out.println("Please select the item you wish to purchase:");
                        int purchase = Integer.parseInt(sc.nextLine());
                        //Add to cart function
                        break;
                    case 2:
                        System.out.println("Search by Keyword: ");
                        String keyword = sc.nextLine();
                        //search function to be used
                    case 3:
                        int sortType = 0;
                        int sortOrder = 0;
                        do {
                            System.out.println("How would you like to sort the items?");
                            System.out.println("1. Price");
                            System.out.println("2. Quantity");
                            sortType = Integer.parseInt(sc.nextLine());
                            if ((sortType < 1) || (sortType > 2)) {
                                System.out.println("Invalid Option");
                            }
                        } while ((sortType < 1) || (sortType > 2));
                        do {
                            System.out.println("Sort by Ascending order or Descending order?");
                            System.out.println("1. Ascending");
                            System.out.println("2. Descending");
                            sortOrder = Integer.parseInt(sc.nextLine());
                            if ((sortOrder < 1) || (sortOrder > 2)) {
                                System.out.println("Invalid Option");
                            }
                        } while ((sortOrder < 1) || (sortOrder > 2));
                        sortItems(sortType, sortOrder);
                }
            } while(booleanBuyer);
        if(user.isSeller) {
            //seller options
        }
    }
}
