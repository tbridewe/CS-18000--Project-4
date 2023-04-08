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

        for (int i = 0; i < sellerItems.size(); i++) { // loop through all the seller items
            if (sellerItems.get(i).contains()))
        }
        if (sellerItems.contains(String.valueOf(item))) { // if item already exists in item listings, just update quantity
            item.setQuantity(item.getQuantity() + );
        } else {
            sellerItems.set(sellerItems.size() + 1, String.valueOf(item)); // add new entry into sellerItems
        }
    }

    public static void removeItem(Item item) {
        ArrayList<String> sellerItems = displayItems(); // seller items

        for (int i = 0; i < sellerItems.size(); i++) { // loop through seller items
            if (sellerItems.contains(String.valueOf(item))) { // if the item is in seller items, remove it
                sellerItems.remove(String.valueOf(item));
            } else {
                System.out.println("Item does not exist!"); // display error message if not in the seller items
            }
        }
    }

    public void editItem(Item item, String changeType, String changeValue ) {
        ArrayList<String> sellerItems = displayItems();
        
        if (sellerItems.contains(String.valueOf(item))) {
            for (int i = 0; i < sellerItems.size(); i++) { // loop through the seller items
                if (sellerItems.get(i).equals(String.valueOf(item))) { // find the item
                    switch (changeType) { // choose what changeType
                        case "Name":
                            item.setName(changeValue);
                        case "Store":
                            item.setStore(changeValue);
                        case "Description":
                            item.setDescription(changeValue);
                        case "Quantity":
                            item.setQuantity(Integer.parseInt(changeValue));
                        case "Price":
                            item.setPrice(Integer.parseInt(changeValue));
                    }
                }
            }
        } else {
            System.out.println("Item does not exist!");
        }
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
