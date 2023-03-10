import alphabetizer.Alphabetizer;
import circularshifter.CircularShifter;
import filters.Pipeline;
import input.Input;
import noisewordremoval.NoiseWordRemoval;
import output.Output;

/**
 * The controller class
 */
public class Controller {

  /**
   * The main method
   *
   * @param args The command line arguments
   */
  public static void main(String[] args) {

    // At least two filters are needed
    (new Pipeline(
        new Input(),
        new CircularShifter(),
        new NoiseWordRemoval(),
        new Alphabetizer(),
        new Output()))
        .run();
  }
}
