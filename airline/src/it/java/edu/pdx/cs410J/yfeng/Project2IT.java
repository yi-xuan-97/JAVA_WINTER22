package edu.pdx.cs410J.yfeng;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * An integration test for the {@link Project2} main class.
 */
class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
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
      String temp[] = temp = new String[]{"-print", "-README", "word test", "1", "yum", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12", "am"};
      MainMethodResult result = invokeMain(temp);
      assertThat(result.getExitCode(), equalTo(0));
      assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 2 -- Yixuan Feng"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("yum"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("pdx"));
      assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
  }

  @Test
  void testCorrectCommandLieArguementwithreadme() {
    String temp[] = temp = new String[]{"-README"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 2 -- Yixuan Feng"));
  }

  @Test
  void testCorrectCommandLieArguementwithprettysandardout() {
    String temp[] = temp = new String[]{"-textFile", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline", "-print", "-README", "-pretty", "-", "Test", "1", "yum", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12","am"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 2 -- Yixuan Feng"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("yum"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("pdx"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight number: 1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Departure airport: yum"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Destination airport: pdx"));
  }

  @Test
  void testCorrectCommandLieArguementwithprettyfile() {
    String temp[] = temp = new String[]{"-textFile", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline", "-print", "-README", "-pretty", "/Users/yixuanfeng/Documents/GitHub/JAVA_WINTER22/airline/src/test/resources/edu/pdx/cs410J/yfeng/valid-airline1", "Test", "1", "yum", "01/01/2011", "01:11","am", "pdx", "01/02/2011", "10:12","am"};
    MainMethodResult result = invokeMain(temp);
    assertThat(result.getExitCode(), equalTo(0));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Porject 2 -- Yixuan Feng"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("yum"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/1/11, 1:11 AM"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("pdx"));
    assertThat(result.getTextWrittenToStandardOut(), containsString("1/2/11, 10:12 AM"));
  }


}