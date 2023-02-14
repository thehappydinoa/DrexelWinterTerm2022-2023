package filters;

import java.io.EOFException;
import java.util.LinkedList;

/**
 * The Pipe class
 */
public class Pipe {
	private final LinkedList<String> buffer;
	private boolean closed;

	/**
	 * Creates a new Pipe
	 */
	public Pipe() {
		buffer = new LinkedList<>();
		closed = false;
	}

	/**
	 * Writes to its pipe
	 *
	 * @param s The string to write
	 */
	public void write(String s) {
		if (closed)
			return;
		buffer.add(s);
	}

	/**
	 * Reads from its pipe
	 *
	 * @return The string read
	 * @throws EOFException If the pipe is closed
	 */
	public String read() throws EOFException {
		while (true) {
			if (buffer.isEmpty()) {
				if (closed)
					throw new EOFException();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					System.err.println("InterruptedException caught in Pipe");
				}
			} else {
				return buffer.pop();
			}
		}
	}

	/**
	 * Closes the pipe
	 */
	public void close() {
		closed = true;
	}

}
