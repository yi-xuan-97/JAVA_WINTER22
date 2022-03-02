package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    /**
     * Test remove all
     * @throws IOException may throw ioexception
     */
    @Test
    void test0RemoveAllMappings() throws IOException {
        AirlineRestClient client = new AirlineRestClient(HOSTNAME, Integer.parseInt(PORT));
        client.removeAllDictionaryEntries();
    }

    /**
     * test with no commandline arguement
     */
    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project5.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    /**
     * Print readme
     */
    @Test
    void test2Readme() {
        MainMethodResult result = invokeMain( Project5.class, "-README" );
        assertThat(result.getTextWrittenToStandardOut(),containsString("usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>"));
    }

    /**
     * print airline information
     */
    @Test
    void test3Print() {
        MainMethodResult result = invokeMain(Project5.class, "-print","-host", "localhost", "-port", "8080",
                "Airline", "123", "PDX", "07/19/2022", "1:02", "pm", "ABE", "07/19/2022", "6:22", "pm");
        assertThat(result.getTextWrittenToStandardOut(),containsString("123"));
    }

    /**
     * seach airline information
     */
    @Test
    void test4Search() {
        invokeMain(Project5.class, "-print","-host", "localhost", "-port", "8080",
                "Airline", "123", "PDX", "07/19/2022", "1:02", "pm", "ABE", "07/19/2022", "6:22", "pm");
        MainMethodResult result = invokeMain(Project5.class,"-host", "localhost", "-port", "8080",
                "-search", "Airline", "PDX", "ABE");
        assertThat(result.getTextWrittenToStandardOut(),containsString("Airline"));
    }

    /**
     * Missing prot information
     */
    @Test
    void test5MissingPort() {
        MainMethodResult result1 = invokeMain(Project5.class, "-print","-host", "localhost");
        assertThat(result1.getTextWrittenToStandardError(),containsString("usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>"));
        MainMethodResult result2 = invokeMain(Project5.class, "-port", "8080");
        assertThat(result2.getTextWrittenToStandardError(),containsString("usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>"));
    }

}