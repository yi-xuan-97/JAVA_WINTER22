package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * An integration test for the {@link Converter} main class.
 */
class ConverterIT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Converter} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Converter.class, args);
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }


    /***
     * Test athat invoking the main method with correct arguments issues an error
     */
    @Test
    void testCorrectCommandLieArguementwithvalid() {
        String[] temp = new String[]{"src/test/resources/edu/pdx/cs410J/yfeng/valid-airline", "src/test/resources/edu/pdx/cs410J/yfeng/valid-airline2"};
        MainMethodResult result = invokeMain(temp);
        assertThat(result.getExitCode(), equalTo(0));

        InputStream resource = getClass().getResourceAsStream("valid-airline2.xml");
        assertThat(resource, notNullValue());
    }

    /***
     * Test athat invoking the main method with missing one arguments issues an error
     */
    @Test
    void testCorrectCommandLieArguementwithmissing() {
        String[] temp = new String[]{"src/test/resources/edu/pdx/cs410J/yfeng/valid-airline"};
        MainMethodResult result = invokeMain(temp);
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }
}


