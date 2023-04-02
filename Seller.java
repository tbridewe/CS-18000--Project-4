import java.util.NoSuchElementException;
import java.util.Scanner;
public class Seller extends User {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View store statistics\n2. View listings");
        int view = scanner.nextInt();
        scanner.nextLine();
        if (view == 1) {
            if (User.userExists && User.isCorrectLogin && User.hasStore) { //if seller exists and has a store
                System.out.println("1. View all statistics\n2. View specific statistics");
                int whichStatistics = scanner.nextInt();
                scanner.nextLine();
                if (whichStatistics == 1) {
                    System.out.println(this.store);  //view all of the store statistics
                } else if (whichStatistics == 2) {
                    System.out.println(); //which statistics do you want to view
                    //view the store statistics specified by the value
                } else {
                    throw new InvalidUserInput(e) {
                        e.printStackTrace;
                    }
                }
            } else {
                throw new IncorrectLoginInformation(e) {
                    e.printStackTrace;
                }
            }
        } else if (view == 2) {
            //view all store listings
            System.out.println("1. Add\n2. Edit\n 3. Delete");
            int storeListingFunctions = scanner.nextInt();
            scanner.nextLine();
            if (storeListingFunctions == 1) { //add
                //input csv values
            } else if (storeListingFunctions == 2) { //edit
                //input which listing
                //input which value
                //change value
            } else if (storeListingFunctions == 3) { //delete
                //remove listing/move other things up
            }else {
                throw new InvalidUserInput(e) {
                    e.printStackTrace;
                }
            }
        } else {
            throw new InvalidUserInput(e) {
                e.printStackTrace;
            }
        }
    }
}
