import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Customer extends User {
    private ArrayList<Item> cart = new ArrayList<>(); // stores the user's items. Everytime the cart is updtates the cart file will also be updated
    private ArrayList<Item> listings;
    private String cartFileName = "shoppingCarts.txt";

    public Customer(String email, String password, int userType) throws InvalidUserInput {
        super(email, password, userType);
        // TODO: why is usertype an input here? The type is known to be customer

        loadCart(this.cartFileName); // loads items from the cart file into the cart
    }

    /**
     * readFile(String filename)
     * @param filename: name of the file that needs to be read
     * @return String[] with all the lines of the file
     */
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
     * writeFile(String filename)
     * @param filename: name of the file that needs to be read
     * @param lines: array of lines to be written
     * @return String[] with all the lines of the file
     */
    public static void writeFile(String filename, String[] lines) {
        File file = new File(filename);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < lines.length; i++) {
                bw.write(lines[i] + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * viewCart()
     * user has the option to view their cart and is displayed with a menu
     */
    public void viewCart(String cartOption) {
        // print out all listings in the cart
        printCart();
        if (cartOption.equals("Add")) {
            addToCart();
        }
        removeFromCart();
    }

    /**
     * PrintCart()
     * just prints the items in the cart with nice formatting. 
     * TODO: we should standardize the print formatting at some point
     */
    public void printCart() {
        String itemFormat = "[%3d]: %-30s | %-4d | %-24s | $ %-6.2f\n";
        System.out.printf("[num]: %-30s | %-4s| %-24s | %-7s\n\n", "NAME", "QNTY", "STORE", "PRICE");
        for (int i = 0; i < cart.size(); i++) {
            Item item = cart.get(i);
            System.out.printf(itemFormat, i+1, item.getName(), item.getQuantity(), item.getStore(), item.getPrice());
        }
    }

    /**
     * addToCart()
     * @param item: the item to be added to the cart
     * @param quantity: the quanitity to be added
     * @exception InvalidQuantityException: if the quantity is more than is in stock or <= 0. The quanity in the item object should be the amount in stock
     * This adds the item to the cart and adjusts the items remaining in the store accordingly
     */
    public void addToCart(Item item, int quantity) throws InvalidQuantityException {
        // check quantity
        if (quantity > item.getQuantity()) {
            throw new InvalidQuantityException(String.format("Invalid Quantity: There are only %d of this item in stock", item.getQuantity()));
        } else if (quantity <= 0) {
            throw new InvalidQuantityException(String.format("Invalid Quantity: Must be >= 0"));
        }
        // check if item is already in cart
        if (this.cart != null && item.findItem(this.cart) > 0) {
            // add quantity to existing item in cart
            int i = item.findItem(this.cart);
            item.changeQuanityBy(this.cart.get(i).getQuantity()); // update quantity in item
            this.cart.set(i, item);  // put updated item back in cart
            // TODO: Finish debeugging this, it wasn't adjusting quantity properly (hannah)
            /** 
            * testing notes: some items add but some don't adding is also wrong. I'll fix it later
            */
        } else {
            item.setQuantity(quantity); // set quantity to quantity added
            this.cart.add(item);
        }

        // Write cart to file
        saveCart(this.cartFileName);
    }

    /**
     * saveCart()
     * writes all the items in this.cart to the cart file. Puts them on the line of the corresponding user. 
     * Potential problem: it is assumed that there is only 1 line per user in cart file.
     * @param fileName: name of the shopping cart file 
     */
    public void saveCart(String fileName) {
        String[] fileLines = readFile(fileName);
        for (int l = 0; l < fileLines.length; l++) { // find the correct user line
            String user = this.getEmail(); // TODO: Update email to username, but there is not getUsername method rn
            String line = fileLines[l];
            if (line.split(";")[0].equals(user)) { // found correct line
                line = user + ";";
                for (int i = 0; i < cart.size(); i++) {
                    line += cart.get(i).toLine() + ";";
                }
                fileLines[l] = line; // replace updated line
                break;
            }
            if (l == fileLines.length-1) { // user has no existing cart file
                ArrayList<String> newLines = new ArrayList<>(Arrays.asList(fileLines));
                line = user + ";";
                for (int i = 0; i < cart.size(); i++) {
                    line += cart.get(i).toLine() + ";";
                }
                newLines.add(line);
                fileLines = newLines.toArray(new String[0]);
            }
        }
        writeFile(fileName, fileLines);
    }

    /**
     * loadCart()
     * reads the cart file and puts the items for this user into this.cart
     * @param fileName: name of the shopping cart file 
     */
    public void loadCart(String fileName) {
        String[] fileLines = readFile(fileName);
        String user = this.getEmail(); // TODO: Update email to username, but there is not getUsername method rn
        for (int l = 0; l < fileLines.length; l++) {
            String line = fileLines[l];
            if (line.split(";")[0].equals(user)) { // load these items
                String[] itemStrings = line.split(";");
                for (int i = 1; i < itemStrings.length; i++) {
                    try{    // catch invalid lines
                        this.cart.add(new Item(itemStrings[i]));
                    } catch (InvalidLineException e) {
                        System.out.printf("Invalid item format line while reading %s. \nLine: %s\n", 
                            this.cartFileName, itemStrings[i]);
                    }
                }
                break;
            }
        }
    }
    // public static void addToCart() {
    //     displayMarketplace(); // display options for user to select
    //     // prompt user for listing
    //     // append the new listing to the shopping cart
    // }

    /**
     * removeFromCart(Item listing)
     * removes specified listing from the cart
     * @param listing: the specified listing that the user wants to remove from cart
     */
    public static void removeFromCart(Item listing) {
        // loop through cart indices 
        // find specified listing
        // remove from listings
        // move index up
        // TODO: this (hannah)
    }

    /**
     * keywordSearch(String keyword)
     * @param keyword: specified word that the user is looking for
     * @return String[] containing all lines containing the keyword 
     */
    public static String[] keywordSearch(String keyword) {
        // loop through listings
            // find listings that contain element
            // display elements
        return // String[]
    }

    /**
     * sortMarketplace(int sortType, int sortOrder)
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
}
