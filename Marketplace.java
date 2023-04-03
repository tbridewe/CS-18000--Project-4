import java.util.ArrayList;
import java.io.*;
public class Marketplace {
    // Marketplace properties
    // TODO: consider moving these to a seprate marketplace class and make this the Menu (main) class
    private ArrayList<Item> items = new ArrayList<>();
    // private String itemsFileName = "ItemInformation.txt";
 
    public Marketplace(String fileName) {
        // reads each line then sends it to the Item constructor to create a new item
        // sets the items of this class to the arraylist of the new items
        File file = new File(fileName);
        ArrayList<Item> items = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            do {
                try {  
                    items.add(new Item(line));
                    line = br.readLine();
                } catch (InvalidLineException e) { // invalid line format exception
                }
            } while (line != null);
            br.close();
            this.items = items;
        } catch (Exception e) { // File io exception
            e.printStackTrace();
        }
    }

    public void printListings() {
        // TODO: hannah
    }
}
