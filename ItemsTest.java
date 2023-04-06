public class ItemsTest {
    public static void main(String[] args) {
        System.out.println("items test");
        String filename = "ItemInformation.txt";
        Marketplace market = new Marketplace(filename);
        market.printListings();
        System.out.println("done");
        System.out.println(market.getItem(5).toLine());
    }
}
