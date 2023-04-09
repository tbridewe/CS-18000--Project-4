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

    public String[] readFile(String filename) {
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

    public ArrayList<String> displayItems() {
        String[] itemsList = readFile("listings.txt");
        ArrayList<String> sellerItems = new ArrayList<>();

        for (int i = 0; i < itemsList.length; i++) {
            if (stores.contains(itemsList[i].split(",")[1])) {
                sellerItems.add(itemsList[i]);
                System.out.println(itemsList[i]);
            }
        }
        return sellerItems;
    }

    public void addFromCSV(String filename) {
        String[] csvData = readFile(filename);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("itemListings.txt", true));

            for (int i = 0; i < csvData.length; i++) {
                writer.println(csvData[i]);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace(); // implement different exception catch?
        }
    }

    public void addNewItem(Item item) {
        ArrayList<String> sellerItems = displayItems();
        sellerItems.add(String.valueOf(item));
    }

    public void removeItem(Item item) {
        ArrayList<String> sellerItems = displayItems(); // seller items

        for (int i = 0; i < sellerItems.size(); i++) { // loop through seller items
            if (sellerItems.contains(String.valueOf(item))) { // if the item is in seller items, remove it
                sellerItems.remove(String.valueOf(item));
            } else {
                System.out.println("Item does not exist!"); // display error message if not in the seller items
            }
        }
    }
    // TODO: the values for changeType and changeValues will be integers, not Strings
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

    // TODO: implement sort
    public void viewSpecificStat(int statType) {
        String[] stats = readFile("customerLog.txt");
        ArrayList<String> statsList = new ArrayList<>();
        ArrayList<String> customerData = new ArrayList<>();
        Collections.addAll(statsList, stats);

        // filters item listings to see if the listing is from one of the seller's stores
        for (int i = 0; i < statsList.size(); i++) {
            String[] customerHistory = statsList.get(i).split(";");
            ArrayList<String> sellerSales = new ArrayList<>();
            for (int j = 1; j < customerHistory.length; j++) {
                if (stores.contains(customerHistory[j])) {
                    sellerSales.add(customerHistory[j]);
                }
            }
            if (sellerSales.size() > 0) {
                String format = customerHistory[0] + sellerSales;
                customerData.add(format);
            }
        }

        switch (statType) {
            case 1: //price
                for (int i = 0; i < customerData.size(); i++) {
                    String[] individualData = customerData.get(i).split(",");
                }
            case 2: //quantity
                for (int i = 0; i < customerData.size(); i++) {

                }
        }
    }

    public void viewAllStats() {
        String[] stats = readFile("customerLog.txt");
        ArrayList<String> statsList = new ArrayList<>();
        ArrayList<String> customerData = new ArrayList<>();
        Collections.addAll(statsList, stats);

        // filters item listings to see if the listing is from one of the seller's stores
        for (int i = 0; i < statsList.size(); i++) {
            String[] customerHistory = statsList.get(i).split(";");
            ArrayList<String> sellerSales = new ArrayList<>();
            for (int j = 1; j < customerHistory.length; j++) {
                if (stores.contains(customerHistory[j])) {
                    sellerSales.add(customerHistory[j] + ";");
                }
            }
            if (sellerSales.size() > 0) {
                String format = customerHistory[0] + ";" + sellerSales;
                customerData.add(format);
            }
        }

        // displays all stats
        for (int i = 0; i < customerData.size(); i++) {
            System.out.println(customerData.get(i));
        }
    }

    public void sortStats(int sortType, int sortOrder) {
        String[] stats = readFile("customerLog.txt");
        ArrayList<String> statsList = new ArrayList<>();
        ArrayList<String> customerData = new ArrayList<>();
        Collections.addAll(statsList, stats);

        for (int i = 0; i < statsList.size(); i++) {
            String[] customerHistory = statsList.get(i).split(";");
            ArrayList<String> sellerSales = new ArrayList<>();
            for (int j = 1; j < customerHistory.length; j++) {
                if (stores.contains(customerHistory[j])) {
                    sellerSales.add(customerHistory[j] + ";");
                }
            }
            if (sellerSales.size() > 0) {
                String format = customerHistory[0] + ";" + sellerSales;
                customerData.add(format);
            }
        }

        switch (sortType) {
            case 1:
                switch (sortOrder) {
                    case 1:
                        customerData.sort(new PriceComparatorAscending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                    case 2:
                        customerData.sort(new PriceComparatorDescending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                }
            case 2:
                switch (sortOrder) {
                    case 1:
                        customerData.sort(new QuantityComparatorAscending());
                        for (int i = 0; i < statsList.size(); i++) {
                            System.out.println(statsList.get(i));
                        }
                    case 2:
                        customerData.sort(new QuantityComparatorDescending());
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
