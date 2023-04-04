import java.io.*;
import java.util.*;

public class Customer extends User {

    public Customer(String email, String password, int userType) {
        super(email, password, userType);
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

    /**
     * displayMarketplace
     * reads file containing listings on the marketplace by newest to oldest,
     * prints view to the console
     * 
     */
    public static void displayMarketplace() {
        String[] contents = readFile("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");

        for (int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }
    }

    public static void chooseItem() {

    }

    public static void keywordSearch(String keyword) {

    }

    /**
     * sortMarketplace
     * sorts the marketplace listings based on user input
     * 
     * @param sortType: either sort by price or by quantity
     * @param sortOrder: either sort in ascending or descending order
     *
     */
    
    
    // TODO: how do we want to view this?
    public static void sortMarketplace(int sortType, int sortOrder) {
        String[] contents = readFile("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");
        ArrayList<Integer> pricesList = new ArrayList<>(); // add all prices to list
        ArrayList<Integer> quantitiesList = new ArrayList<>(); // add all quantities to list

        int price; // price of an item
        int quantity; // quantity of an item

        for (int i = 0; i < contents.length; i++) { // loop through the lines of the listings
            String[] line = contents[i].split(","); // split on ,
            price = Integer.parseInt(line[4]); // set price of item
            pricesList.add(price); // add price to the pricesList
            quantity = Integer.parseInt(line[3]); // set quantity of item
            quantitiesList.add(quantity); // add quantity to the quantitiesList
        }
        
        switch (sortType) { // either price or quantity
            case 1: // price
                switch (sortOrder) {
                    case 1: // ascending
                        pricesList.sort(Comparator.naturalOrder());
                    case 2: // descending
                        pricesList.sort(Comparator.reverseOrder());
                }
            case 2: // quantity
                switch (sortOrder) {
                    case 1: // ascending
                        quantitiesList.sort(Comparator.naturalOrder());
                    case 2: // descending
                        quantitiesList.sort(Comparator.reverseOrder());
                }
        }
    }

    public static void viewCart() {

    }
}
