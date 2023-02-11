
import filters.Input;
import filters.CircularShifter;
import filters.NoiseWordRemoval;
import filters.Alphabetizer;
import filters.Output;
import filters.Pipeline;

public class Controller {
  public static void main(String[] args) {

    // At least two filters are needed
    (new Pipeline(new Input(), new CircularShifter(), new NoiseWordRemoval(),
                  new Alphabetizer(), new Output()))
        .run();
  }
}
