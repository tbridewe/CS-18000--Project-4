import java.io.*;

public class User {
    private String password;
    private String email;

    public User(String email, String password) throws InvalidUserInput {
        if (!isValidPassword(password) || !isValidEmail(email)) {
            throw new InvalidUserInput("Please enter the correct input types for email and password!");
        }

        this.password = password;
        this.email = email;
    }

    public boolean userExists(String emailEntered) {
        try (BufferedReader br = new BufferedReader(new FileReader("userData.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("Username:" + emailEntered)) {
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

    public boolean isCorrectLogin(String emailEntered, String passwordEntered) {
        if (!(email.equals(emailEntered)) || (!(password.equals(passwordEntered)))) {
            return false;
        }

        return true;
    }

    public boolean isValidPassword(String password) {
        if (password == null || password.length() == 0) {
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String email) {
        if (email.length() == 0) {
            return false;
        }

        return true;
    }
}
