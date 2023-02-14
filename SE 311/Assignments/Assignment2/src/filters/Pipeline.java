package filters;

/**
 * The pipeline class
 */
public class Pipeline implements Runnable {

	private final Filter[] filters;

	/**
	 * Creates a new pipeline
	 *
	 * @param filters The filters to add to the pipeline
	 */
	public Pipeline(Filter... filters) {
		this.filters = filters;

		if (filters.length > 1) {
			for (int i = 0; i < filters.length - 1; i++) {
				Pipe p = new Pipe();
				filters[i].setOut(p);
				filters[i + 1].setIn(p);
			}
		}
	}

	/**
	 * Runs the pipeline
	 */
	@Override
	public void run() {
		for (Filter f : filters) {
			(new Thread(f)).start();
		}
	}
}
