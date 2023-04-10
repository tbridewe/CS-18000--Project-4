public class sellertest {
    public static void main(String[] args) {
        try {
            Seller me = new Seller("aaa@gmail.com", "abc123", 1);
            System.out.println("seller test");
            me.printListings();
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
