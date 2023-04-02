import java.io.*;

public class Customer extends User {

    public Customer(String username, String password, String email) {
        super(username, password, email);
    }
    
    public static void displayMarketplace() {
        File listings = new File("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(listings));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Listing is incorrectly formatted!");
        }
    }
    public static void sortItems(int sortType, int sortOrder) {
        File listings = new File("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");
        try {
            switch (sortType) {
                case 1: // price
                    switch (sortOrder) {
                        case 1: // low

                        case 2: // high
                    }
                case 2: // quantity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void chooseItem() {

    }

    public static void keywordSearch(String keyword) {

    }

    public static void sortMarketplace() {

    }

    public static void viewCart() {

    }

}
