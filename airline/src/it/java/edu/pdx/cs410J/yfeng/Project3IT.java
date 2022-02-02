package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * An integration test for the {@link Project3} main class.
 */
class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
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

  @Test
    void testCorrectCommandLieArguementwithprint() {
      String[] temp = new String[]{"-print", "-README", "word test", "1", "abq", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12", "am"};
      MainMethodResult result = invokeMain(temp);
      assertThat(result.getExitCode(), equalTo(0));
      assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 3 -- Yixuan Feng"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("ABQ"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("PDX"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
  }

  @Test
  void testCorrectCommandLieArguementwithreadme() {
    String[] temp = new String[]{"-README"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 3 -- Yixuan Feng"));
  }

  @Test
  void testCorrectCommandLieArguementwithprettysandardout() {
    String[] temp = new String[]{"-textFile", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline", "-print", "-README", "-pretty", "-", "Test", "1", "ama", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12","am"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 3 -- Yixuan Feng"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("AMA"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PDX"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight number: 1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Departure airport: ABQ"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Destination airport: PDX"));
  }

  @Test
  void testCorrectCommandLieArguementwithprettyfile() {
    String[] temp = new String[]{"-textFile", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline", "-print", "-README", "-pretty", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline1", "Test", "1", "abq", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12","am"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 3 -- Yixuan Feng"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("ABQ"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PDX"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
  }


}