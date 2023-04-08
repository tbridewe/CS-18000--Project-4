import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Seller extends User {
    private ArrayList<String> stores;

    public Seller (String email, String password, int userType) throws InvalidUserInput {
        super(email, password, userType);
        this.stores = new ArrayList<>();
    }

    public ArrayList<String> getStores() {
        return stores;
    }

    public void setStores(ArrayList<String> stores) {
        this.stores = stores;
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

    // DONE
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

    // DONE
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

    public void viewSpecificStat(String statType) {
        switch (statType) {
            case "Price":
            case "Quantity":
        }
    }

    public static void viewAllStats() {
        String[] stats = readFile("cartHistory.txt");

        for (int i = 0; i < stats.length; i++) {

        }
    }

    public void sortStats(String sortType, String sortOrder) {
        String[] stats = readFile("CustomerLog.txt");
        ArrayList<String> statsList = new ArrayList<>();
        ArrayList<String> sortedStatsList = new ArrayList<>();
        
        Collections.addAll(statsList, stats);

        switch (sortType) {
            case "Price":
                switch (sortOrder) {
                    case "Ascending":
                        statsList.sort(new PriceComparatorAscending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                    case "Descending":
                        statsList.sort(new PriceComparatorDescending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                }
            case "Quantity":
                switch (sortOrder) {
                    case "Ascending":
                        statsList.sort(new QuantityComparatorAscending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                    case "Descending":
                        statsList.sort(new QuantityComparatorDescending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                }
        }
    }

    // Comparator for sorting by price in ascending order
    static class PriceComparatorAscending implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            double price1 = Double.parseDouble(o1.split(",")[4]);
            double price2 = Double.parseDouble(o2.split(",")[4]);
            return Double.compare(price1, price2);
        }
    }

    // Comparator for sorting by price in descending order
    static class PriceComparatorDescending implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            double price1 = Double.parseDouble(o1.split(",")[4]);
            double price2 = Double.parseDouble(o2.split(",")[4]);
            return Double.compare(price2, price1);
        }
    }

    // Comparator for sorting by price in ascending order
    static class QuantityComparatorAscending implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int quantity1 = Integer.parseInt(o1.split(",")[3]);
            int quantity2 = Integer.parseInt(o2.split(",")[3]);
            return Integer.compare(quantity1, quantity2);
        }
    }

    // Comparator for sorting by price in descending order
    static class QuantityComparatorDescending implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int quantity1 = Integer.parseInt(o1.split(",")[3]);
            int quantity2 = Integer.parseInt(o2.split(",")[3]);
            return Integer.compare(quantity2, quantity1);
        }
    }
}
