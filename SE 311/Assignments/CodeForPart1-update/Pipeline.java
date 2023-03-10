package filters;

public class Pipeline implements Runnable {
	
	private Filter[] filters;
	
	public Pipeline(Filter ... filters ) {
		this.filters = filters;
		
		if (filters.length > 1) {		
			for(int i = 0; i < filters.length - 1; i++) {
				Pipe p = new Pipe();
				filters[i].setOut(p);
				filters[i+1].setIn(p);
			}
		}
	}

	@Override
	public void run() {
		for(Filter f : filters) {
			(new Thread(f)).start();
		}
	}
}
