import filters.*;


public class Controller {
	public static void main(String[] args) {
 
 // At least two filters are needed
    (new Pipeline(
			new Input(),
			new CircularShifter(),
			new NoiseWordRemoval(),
			new Alphabetizer(),
			new Output()
		)).run();
	}
}
