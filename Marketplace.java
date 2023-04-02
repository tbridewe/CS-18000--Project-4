import java.util.Scanner;
public class Marketplace {

    public static String welcome = "Welcome to the Marketplace!";
    public static String welcomeMenu = "Please select an option:\n(1) Login\n(2) Create an Account\n(3) Quit";
    public static void main(String[] args) {
        int welcomeOption;

        Scanner sc = new Scanner(System.in);
        System.out.println(welcome);

        do {
            System.out.println(welcomeMenu);
            welcomeOption = sc.nextInt();

            if (welcomeOption == 1) {
                System.out.println("Login Menu");
            } else if (welcomeOption == 2) {
                System.out.println("Create New Account");
            } else if (welcomeOption == 3) {
                System.out.println("Bye!");
            } else {
                System.out.println("Invalid Option!");
            }

        } while (welcomeOption > 3 || welcomeOption < 1);

    }
}
