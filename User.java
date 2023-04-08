import java.io.*;
import java.util.ArrayList;

public class User {
    private String password; // password entered when a user creates their login information
    private String email; // email entered when a user creates their login information
    private boolean buyer;
    private boolean seller;
    private final static String FILENAME = "userData.txt"; // name of file where userData is stored

    public User(String email, String password, int userType) throws InvalidUserInput { // creates a new User object with email, password and userType; Buyer = 0, Seller = 1
        if (!isValidPassword(password) || !isValidEmail(email)) {
            throw new InvalidUserInput("Please enter the correct input types for email and password!");
        }

        this.buyer = (userType == 0);
        this.seller = !this.buyer;

        this.password = password;
        this.email = email;
    }

    public String getEmail() { // gets the current user email
        return email;
    }

    public String getPassword() { // gets the current user password
        return password;
    }

    public boolean isBuyer() { // is the user a buyer
        return this.buyer;
    }

    public boolean isSeller() { // is the user a seller
        return this.seller;
    }

    public void setUserType(int userType) { // sets whether the user is a buyer or seller; 0 = buyer, 1 = seller
        this.buyer = (userType == 0);
        this.seller = !this.buyer;
    }

    public void setEmail(String email) throws InvalidUserInput { // sets a new email for the user
        if (!isValidEmail(email)) {
            throw new InvalidUserInput("Please enter the correct input for email!");
        }

        this.email = email;
    }

    public void setPassword(String password) throws InvalidUserInput { // sets a new password for the user
        if (!isValidPassword(password)) {
            throw new InvalidUserInput("Please enter the correct input for password!");
        }

        this.password = password;
    }

    public boolean accountExists(String emailEntered, String passwordEntered) { // checks if a user's information is saved to userData.txt
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.indexOf(emailEntered) != -1 && line.indexOf(passwordEntered) != -1) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("The userData.txt file does not exist!");

            return false;
        } catch(IOException e) {
            System.out.println("An error has occurred!");

            return false;
        }

        return false;
    }

    public ArrayList<String> readUserData() { // reads the userData.txt file and returns an ArrayList of each line
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;

            ArrayList<String> list = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            return list;
        } catch (FileNotFoundException e) {
            System.out.println("The userData.txt file does not exist!");
        } catch (IOException e) {
            System.out.println("There was an error processing the file!");
        }

        return null;
    }

    public void saveNewUser(String email, String password, String userType) { // writes the new user information to userData.txt
        try {
            FileWriter fw = new FileWriter(FILENAME, true);

            fw.write(String.format("Email:%s, Password:%s, UserType:%s\n", email, password, userType));
            fw.close();

            // writes a new user to the userdata folder
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }
    }

    public boolean isCorrectLogin(String emailEntered, String passwordEntered) { // verifies if the login entered matches the current User object fields
        if (!(email.equals(emailEntered)) || (!(password.equals(passwordEntered)))) {
            return false;
        }

        return true;
    }

    public boolean isValidPassword(String password) { // verifies that the password exists and is greater than 0 characters
        if (password == null || password.length() == 0) {
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String email) { // verifies that the email exists and is greater than 0 characters
        if (email.length() == 0) {
            return false;
        } else {
            if (email.contains("@")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
