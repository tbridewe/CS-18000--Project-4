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

        @Test(timeout = 1000)
        public void testValidEmailOutput1() {
            boolean expected = false;
            boolean output = User.isValidEmail("Sambodkinlive");

            assertEquals("Make sure the isValidEmail method in User matches the expected format",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput2() {
            boolean expected = true;
            boolean output = User.isValidEmail("Sambodkin@live.com");

            assertEquals("Make sure the isValidEmail method in User matches the expected format",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput3() {
            boolean expected = false;
            boolean output = User.isValidEmail("Sambodkin@live.co");

            assertEquals("Make sure the isValidEmail method in User matches the expected format",
                    expected, output);
        }

        @Test(timeout = 1000)
        public void testValidEmailOutput4() {
            boolean expected = false;
            boolean output = User.isValidEmail("sambo@.com");

            assertEquals("Make sure the isValidEmail method in User matches the expected format",
                    expected, output);
        }

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



    }

}