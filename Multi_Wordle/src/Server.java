import java.io.*;
import java.net.*;
import java.util.ArrayList;
//a
public class Server
{	
	private static ArrayList<Client> arrClients;
	private static int time;
	private static int guess;
	
	public static void main(String[] args) throws IOException
	{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(4444);
		arrClients = new ArrayList<Client>();
		time = 0;
		guess = 0;
		
		while (true)
		{
			Socket s = null;
			try
			{
				s = ss.accept();
			
				BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter write = new PrintWriter(s.getOutputStream(), true);
				
				System.out.println("[SERVER] " + "Assigning new thread for this client");

				Client client = new Client(s.getInetAddress().getHostAddress(), read, write);
				arrClients.add(client);
				
				//System.out.println("[SERVER] " + "A new client is connected : " + s);
				connected(client.getName());
				
				Server server = new Server();
				
				Thread t = server.new ClientHandler(s, read, write);

				t.start();
				
			}
			catch (Exception e){
				s.close();
				e.printStackTrace();
			}
		}
	}
	
	public static void connected(String string) {
		try {
			System.out.println("[ClientHandler] " + string + " has connected to the game.");
		} catch(Exception e) {
			System.out.println("No client has connected.");
		}
	}

	class ClientHandler extends Thread
	{
		final BufferedReader read;
		final PrintWriter write;
		final Socket s;
		private boolean Switch = true;
		 
	
		public ClientHandler(Socket s, BufferedReader read, PrintWriter write)
		{
			this.s = s;
			this.read = read;
			this.write = write;
		}
	
		@Override
		public void run()
		{
			String received = "";
			while (Switch)
			{
				try {
					received = read.readLine();

					String[] parts = received.split(" ");
					time = Integer.parseInt(parts[0]);
					guess = Integer.parseInt(parts[1]);
					
					System.out.println("[ClientHandler] " + "" + time + " " + guess);
					tellEveryone();
	
				} catch (IOException e) {
					System.out.println("[ClientHandler] Socket has left");
					Switch = false;
				}
			}
			
			try
			{
				this.read.close();
				this.write.close();
				
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
		
		public void tellEveryone() {
			try {
				for(Client c : arrClients) {
					c.getWriter().println(c.getName() + " finished in " + time + " in " + guess + "('s)");
				}
			} catch(Exception e) {
				
			}
		}
	}
}
	