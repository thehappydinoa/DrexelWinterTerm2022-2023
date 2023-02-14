package filters;

import java.io.EOFException;

/**
 * The abstract class for all filters
 */
public abstract class Filter implements Runnable {

	protected Pipe in, out;

	/**
	 * Sets the input pipe
	 *
	 * @param p The pipe to set
	 */
	public void setIn(Pipe p) {
		in = p;
	}

	/**
	 * Sets the output pipe
	 *
	 * @param p The pipe to set
	 */
	public void setOut(Pipe p) {
		out = p;
	}

	/**
	 * Writes to its pipe
	 * 
	 * @param s The string to write
	 */
	public void write(String s) {
		if (s == null) {
			out.close();
			return;
		}
		out.write(s);
	}

	/**
	 * Reads from its pipe
	 * 
	 * @return The string read
	 * @throws EOFException If the pipe is closed
	 */
	public String read() throws EOFException {
		return in.read();
	}

}
