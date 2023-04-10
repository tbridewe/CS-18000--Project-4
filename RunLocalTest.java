import org.junit.Test;
import org.junit.After;

import java.lang.reflect.Modifier;
import org.junit.Assert;
import org.junit.Before;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;

import static org.junit.Assert.*;

/**
 * RunLocalTest
 *
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Spring 2023</p>
 *
 * @author Sam Bodkin Purdue CS
 * @version April 07, 2023
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        // USER CLASS

        @Test(timeout = 1000)
        public void testEditUser() {
            String check = "testEditUser@email.com";
            String password = "abc123";
            String password2 = "abc12";
            String userType = "Buyer";

            boolean expected = true;
            boolean output = false;

            if (!User.accountExists(check)) {
                User.saveNewUser(check, password, userType);
            }

            try {
                User user = new User(check, password, userType);
                user.editUser(null, password2, false);

                if (User.isCorrectLogin(check, password2) > -1)
                    output = true;
            } catch (InvalidUserInput e) {

            }

            assertEquals("Make sure the editUser method in User properly edits the user!",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testSaveUser() {
            boolean expected = true;
            boolean output = false;

            String check = "email4@email.com";

            if (User.accountExists(check)) { // if the user exists, deletes it for the test method
                try {
                    User user = new User(check, "password", 1);

                    user.editUser(null, null, true);
                } catch(InvalidUserInput e) {

                }
            }

            User.saveNewUser(check, "password", "Seller");
            output = User.accountExists(check);

            assertEquals("Make sure the saveNewUser method in User properly saves a new user!",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testUserExists() {
            String check = "email3@email.com";

            boolean expected = true;
            boolean output = User.accountExists(check);

            if (!output) {
                User.saveNewUser(check, "password", "Buyer");
                output = true;
            }

            boolean expected2 = false;
            boolean output2 = User.accountExists(check + "mail");

            assertEquals("Make sure the accountExists method in User matches the expected value",
                    expected, output);

            assertEquals("Make sure the accountExists method in User matches the expected value",
                    expected2, output2);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput1() {
            boolean expected = false;
            boolean output = User.isValidEmail("Sambodkinlive");

            assertEquals("Make sure the isValidEmail method in User matches the expected value",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput2() {
            boolean expected = true;
            boolean output = User.isValidEmail("Sambodkin4@outlook.com");

            assertEquals("Make sure the isValidEmail method in User matches the expected value",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput3() {
            boolean expected = false;
            boolean output = User.isValidEmail("Sambodkin@live.co");

            assertEquals("Make sure the isValidEmail method in User matches the expected value",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput4() {
            boolean expected = false;
            boolean output = User.isValidEmail("sambo@.com");

            assertEquals("Make sure the isValidEmail method in User matches the expected value",
                    expected, output);
        }

        // MENU CLASS

        @Test(timeout = 1000)
        public void testExpectedOutput() throws InvalidUserInput {

            // Set the input
            String input = "3" + System.lineSeparator();

            // Pair the input with the expected result
            String expected =
                    "Welcome to the Marketplace!" +
                            System.lineSeparator() +
                            "Please select an option:" +
                            System.lineSeparator() +
                            "(1) Login" +
                            System.lineSeparator() +
                            "(2) Create an Account" +
                            System.lineSeparator() +
                            "(3) Quit" +
                            System.lineSeparator() +
                            "Bye!" +
                            System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            Menu.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            output = output.replace("\r\n", "\n");
            expected = expected.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected format",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedOutput2() throws InvalidUserInput {
            String expected =
                    "Welcome to the Marketplace!" +
                            System.lineSeparator() +
                            "Please select an option:" +
                            System.lineSeparator() +
                            "(1) Login" +
                            System.lineSeparator() +
                            "(2) Create an Account" +
                            System.lineSeparator() +
                            "(3) Quit" +
                            System.lineSeparator() +
                            "Create New Account" +
                            System.lineSeparator() +
                            "-----------------" +
                            System.lineSeparator() +
                            "Create Account" +
                            System.lineSeparator() +
                            "Are you a Buyer or a Seller" +
                            System.lineSeparator() +
                            "Email:" +
                            System.lineSeparator() +
                            "Password:" +
                            System.lineSeparator() +
                            "Account successfully created!" +
                            System.lineSeparator() +
                            "Please select an option:" +
                            System.lineSeparator() +
                            "(1) Choose Item" +
                            System.lineSeparator() +
                            "(2) Search" +
                            System.lineSeparator() +
                            "(3) Sort" +
                            System.lineSeparator() +
                            "(4) View Cart" +
                            System.lineSeparator() +
                            "(5) Edit Account" +
                            System.lineSeparator() +
                            "(6) Log Out" +
                            System.lineSeparator() +
                            "Goodbye!" +
                            System.lineSeparator() +
                            "Please select an option:" +
                            System.lineSeparator() +
                            "(1) Login" +
                            System.lineSeparator() +
                            "(2) Create an Account" +
                            System.lineSeparator() +
                            "(3) Quit" +
                            System.lineSeparator() +
                            "Bye!";

            String email = "sambodkin3@live.com";
            String password = "abc123";
            String userType = "Buyer";
            int userTypeInt = 0;

            String input = "2" + System.lineSeparator() +
                    userType + System.lineSeparator() +
                    email + System.lineSeparator() +
                    password + System.lineSeparator() +
                    "6" + System.lineSeparator() +
                    "3" + System.lineSeparator();

            if (User.accountExists(email)) {
                try {
                    User user = new User(email, password, userTypeInt);
                    user.editUser(null, null, true);
                } catch (InvalidUserInput e) {

                }
            }

            // Runs the program with the input values
            receiveInput(input);
            Menu.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            output = output.replace("\r\n", "\n");
            expected = expected.replace("\r\n", "\n");

            assertEquals("Make sure your output matches the expected format",
                    expected.trim(), output.trim());
        }
    }
}
