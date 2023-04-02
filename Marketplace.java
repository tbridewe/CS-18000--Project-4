import java.util.Scanner;
public class Marketplace {

    public static String WELCOME = "Welcome to the Marketplace!";
    public static String WELCOMEMENU = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit";
    public static String LINES = "-----------------";
    public static String USERNAME = "UserName:";
    public static String PASSWORD = "Password:";
    public static String INCORRECTCREDENTIALS = "Incorrect login credentials or account does not exist, please try again.";
    public static String LOGINSUCCESS = "Login Successful";
    public static String USEREXISTS = "Username is taken, please try again";
    public static String ACCOUNTCREATED = "Account successfully created!";
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
            while(loginBoolean) {
                System.out.println(LINES);
                System.out.println(USERNAME);
                String username = sc.nextLine();
                System.out.println(PASSWORD);
                String password = sc.nextLine();

                if (userExits(username) == false)
                {
                    System.out.println(INCORRECTCREDENTIALS);
                }
                else if (userExists(username)) {
                    if(isCorrectLogin(username, password)) {
                        System.out.println(LOGINSUCCESS);
                        loginBoolean = false;
                    } else {
                        System.out.println(INCORRECTCREDENTIALS);
                    }
                }
            }
        }

        else if (welcomeOption == 2) {
            System.out.println(LINES);
            System.out.println("Create Account");
            System.out.println(USERNAME);
            String username = sc.nextLine();
            System.out.println(PASSWORD);
            String password = sc.nextLine();

            if(userExists(username)) {
                System.out.println(USEREXISTS);
            } else {
                System.out.println(ACCOUNTCREATED);
            }
        }
    }
}
