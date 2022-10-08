import java.io.*;

public class Client {

	private String name;
	private BufferedReader read;
	private PrintWriter write;
	
	public Client(String name, BufferedReader read, PrintWriter write) {
		this.name = name;
		this.read = read;
		this.write = write;
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedReader getReader() {
		return read;
	}
	
	public PrintWriter getWriter() {
		return write;
	}
}
