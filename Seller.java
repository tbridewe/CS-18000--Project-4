import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Seller extends User {
    private ArrayList<String> stores;

    public Seller (String email, String password, int userType) throws InvalidUserInput {
        super(email, password, userType);
        this.stores = new ArrayList<>();
    }

    public static String[] readFile(String filename) {
        String[] fileContents;
        ArrayList<String> contents = new ArrayList<>();

        File file = new File(filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        fileContents = contents.toArray(new String[0]);
        return fileContents;
    }
    
    public static writeFile(String filename) {
        
    }
    
    public static ArrayList<String> displayItems() {
        String[] contents = readFile("listings.txt");
        ArrayList<String> sellerItems = new ArrayList<>();

        for (int i = 0; i < contents.length; i++) {
            if (stores.contains(contents[i].split(",")[1])) {
                sellerItems.add(contents[i]);
                System.out.println(contents[i]);
            }
        }
        return sellerItems;
    }
    
    public void addFromCSV(String filename) {
        String[] csvData = readFile(filename);

        for (int i = 0; i < csvData.length; i++) {
            // write to itemListings.txt 
        }

    }

    public static void addNewItem(Item item) {
        ArrayList<String> sellerItems = displayItems();
        if (sellerItems.contains(String.valueOf(item))) { // if item already exists in item listings, just update quantity
            item.getQuantity()
        } else {
            sellerItems.set(sellerItems.size() + 1, String.valueOf(item)); // add new entry into sellerItems
        }
    }

    public static void removeItem(Item item) {
        ArrayList<String> sellerItems = displayItems();
        sellerItems.remove()
    }

    public void editItem() {
        displayItems();

    }
    public void viewSpecificStat() {

    }

    public static void viewAllStats() {
        String[] stats = readFile("cartHistory.txt");

        for (int i = 0; i < stats.length; i++) {

        }
    }

    public void sortStats(String sortType, String sortOrder) {
        if ()
    }
}
    /*
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

     */
