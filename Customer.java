import java.io.*;
import java.util.*;

public class Customer extends User {

    public Customer(String username, String password, String email) {
        super(username, password, email);
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
    public static void displayMarketplace() {
        String[] contents = readFile("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");

        for (int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }
    }

    public static void main(String[] args) {
        displayMarketplace();
    }

    public static void chooseItem() {

    }

    public static void keywordSearch(String keyword) {

    }

    public static void sortMarketplace(int sortType, int sortOrder) {
        String[] contents = readFile("/Users/tristan.brideweser/Desktop/SP 2023/CS 18000/Projects/PJ04/PJ04/src/Listings.txt");
        ArrayList<Integer> pricesList = new ArrayList<>();
        ArrayList<Integer> quantitiesList = new ArrayList<>();

        int price;
        int quantity;

        for (int i = 0; i < contents.length; i++) {
            String[] line = contents[i].split(",");
            price = Integer.parseInt(line[4]);
            pricesList.add(price);
            quantity = Integer.parseInt(line[3]);
            quantitiesList.add(quantity);
        }
        int[] quantities = ;
        switch (sortType) {
            case 1: // price
                switch (sortOrder) {
                    case 1: // ascending
                        Collections.sort()
                    case 2: // descending

                }
            case 2: // quantity
                switch (sortOrder) {
                    case 1: // ascending

                    case 2: // descending
                }
        }
    }

    public static void viewCart() {

    }
}
